package com.betta.system.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 系统历史对象 sys_history
 * 
 * @author chenlt
 * @date 2024-07-13
 */
@Data
public class SysHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 内容 */
    @Excel(name = "内容")
    private String value;

}
