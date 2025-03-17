package com.betta.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betta.common.core.domain.entity.SysRole;
import com.betta.common.core.domain.entity.SysUser;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.system.mapper.SysDataMapper;
import com.betta.system.domain.SysData;
import com.betta.system.service.ISysDataService;

import static com.betta.common.constant.UserConstants.ROLE_HOME_DATA_TYPE;

/**
 * 系统数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
@Service
public class SysDataServiceImpl extends ServiceImpl<SysDataMapper,SysData> implements ISysDataService
{
    @Autowired
    private SysDataMapper sysDataMapper;

    /**
     * 查询系统数据
     * 
     * @param id 系统数据主键
     * @return 系统数据
     */
    @Override
    public SysData selectSysDataById(Long id)
    {
        return sysDataMapper.selectSysDataById(id);
    }

    /**
     * 查询系统数据列表
     * 
     * @param sysData 系统数据
     * @return 系统数据
     */
    @Override
    public List<SysData> selectSysDataList(SysData sysData)
    {
        return sysDataMapper.selectSysDataList(sysData);
    }

    /**
     * 新增系统数据
     * 
     * @param sysData 系统数据
     * @return 结果
     */
    @Override
    public int insertSysData(SysData sysData)
    {
        sysData.setCreateTime(DateUtils.getNowDate());
        return sysDataMapper.insertSysData(sysData);
    }

    /**
     * 修改系统数据
     * 
     * @param sysData 系统数据
     * @return 结果
     */
    @Override
    public int updateSysData(SysData sysData)
    {
        sysData.setUpdateTime(DateUtils.getNowDate());
        return sysDataMapper.updateSysData(sysData);
    }

    /**
     * 批量删除系统数据
     * 
     * @param ids 需要删除的系统数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDataByIds(Long[] ids)
    {
        return sysDataMapper.deleteSysDataByIds(ids);
    }

    /**
     * 删除系统数据信息
     * 
     * @param id 系统数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDataById(Long id)
    {
        return sysDataMapper.deleteSysDataById(id);
    }

    /**
     * 获取角色主页数据
     * @param user
     * @return
     */
    @Override
    public String getRoleHome(SysUser user) {
        List<String> roleIds = user.getRoles().stream().map(SysRole::getRoleId).map(String::valueOf).collect(Collectors.toList());
        if(roleIds.size()>0){
            LambdaQueryWrapper<SysData> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysData::getDataType, ROLE_HOME_DATA_TYPE);
            queryWrapper.in(SysData::getDataKey, roleIds);
            List<SysData> list = list(queryWrapper);
            if (list != null && !list.isEmpty()) {
                return list.get(0).getDataValue();
            }
        }
        return "";
    }
}
