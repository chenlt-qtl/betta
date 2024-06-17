package com.betta.es.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.betta.common.annotation.Excel;
import com.betta.common.core.domain.BaseEntity;

/**
 * ES历史对象 es_history
 * 
 * @author chenlt
 * @date 2024-06-17
 */
@Data
public class EsHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 手工注释 */
    @Excel(name = "手工注释")
    private String comment;

    /** doc更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "doc更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDocTime;

}
