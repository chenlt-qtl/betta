package com.betta.message.service.impl;

import com.betta.common.utils.StringUtils;
import com.betta.message.dto.ActionResult;
import com.betta.message.dto.CommandDTO;
import com.betta.message.service.ICommandDispatchService;
import com.betta.other.domain.CardItem;
import com.betta.other.service.ICardItemService;
import com.betta.quartz.domain.SysJob;
import com.betta.quartz.service.ISysJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.quartz.SchedulerException;
import java.util.List;

/**
 * 命令分发实现：add_card 调加卡接口，start_task 立即执行定时任务
 *
 * @author betta
 */
@Slf4j
@Service
public class CommandDispatchServiceImpl implements ICommandDispatchService {

    /** 加卡时默认 type */
    private static final long DEFAULT_CARD_TYPE = 0L;

    @Autowired
    private ICardItemService cardItemService;

    @Autowired
    private ISysJobService jobService;

    @Override
    public ActionResult dispatch(CommandDTO command) {
        if (command == null || StringUtils.isEmpty(command.getIntent())) {
            return ActionResult.fail("无效命令");
        }
        switch (command.getIntent()) {
            case "add_card":
                return doAddCard(command);
            case "start_task":
                return doStartTask(command);
            default:
                return ActionResult.fail("暂不支持该指令，请换个说法");
        }
    }

    /**
     * 加卡：按名称和数量批量插入 card_item
     *
     * @param command 命令（target=名称, quantity=数量）
     * @return 执行结果
     */
    private ActionResult doAddCard(CommandDTO command) {
        String target = command.getTarget();
        Integer quantity = command.getQuantity();
        if (StringUtils.isEmpty(target)) {
            return ActionResult.fail("缺少加卡对象");
        }
        if (quantity == null || quantity <= 0) {
            return ActionResult.fail("请指定有效数量");
        }
        try {
            for (int i = 0; i < quantity; i++) {
                CardItem item = new CardItem();
                item.setName(target);
                item.setType(DEFAULT_CARD_TYPE);
                item.setValue(1L);
                item.setCreateBy("message-bot");
                cardItemService.insertCardItem(item);
            }
            return ActionResult.ok("已为【" + target + "】加卡 " + quantity + " 张");
        } catch (Exception e) {
            log.error("加卡失败: target={}, quantity={}", target, quantity, e);
            return ActionResult.fail("加卡失败：" + e.getMessage());
        }
    }

    /**
     * 启动定时任务：按任务名称查找后立即执行一次
     *
     * @param command 命令（taskName=任务名称）
     * @return 执行结果
     */
    private ActionResult doStartTask(CommandDTO command) {
        String taskName = StringUtils.isNotBlank(command.getTaskName()) ? command.getTaskName() : command.getTarget();
        if (StringUtils.isEmpty(taskName)) {
            return ActionResult.fail("请说明要启动的任务名称");
        }
        try {
            SysJob query = new SysJob();
            query.setJobName(taskName);
            List<SysJob> list = jobService.selectJobList(query);
            if (list == null || list.isEmpty()) {
                return ActionResult.fail("未找到任务：" + taskName);
            }
            SysJob job = list.get(0);
            boolean run = jobService.run(job);
            if (run) {
                return ActionResult.ok("任务【" + taskName + "】已触发执行");
            } else {
                return ActionResult.fail("任务触发失败，请检查任务状态");
            }
        } catch (SchedulerException e) {
            log.error("启动任务失败: taskName={}", taskName, e);
            return ActionResult.fail("启动任务失败：" + e.getMessage());
        }
    }
}
