package com.betta.system.domain;

import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * 系统数据对象 sys_data
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
@Data
public class SysData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 数据名称 */
    @Excel(name = "数据名称")
    private String dataName;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String dataType;

    /** 数据键名 */
    @Excel(name = "数据键名")
    private String dataKey;

    /** 数据值 */
    @Excel(name = "数据值")
    private String dataValue;

    private String remark;

}
