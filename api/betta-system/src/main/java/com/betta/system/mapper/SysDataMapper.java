package com.betta.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betta.system.domain.SysData;

/**
 * 系统数据Mapper接口
 * 
 * @author ruoyi
 * @date 2025-03-15
 */
public interface SysDataMapper extends BaseMapper<SysData>
{
    /**
     * 查询系统数据
     * 
     * @param id 系统数据主键
     * @return 系统数据
     */
    public SysData selectSysDataById(Long id);

    /**
     * 查询系统数据列表
     * 
     * @param sysData 系统数据
     * @return 系统数据集合
     */
    public List<SysData> selectSysDataList(SysData sysData);

    /**
     * 新增系统数据
     * 
     * @param sysData 系统数据
     * @return 结果
     */
    public int insertSysData(SysData sysData);

    /**
     * 修改系统数据
     * 
     * @param sysData 系统数据
     * @return 结果
     */
    public int updateSysData(SysData sysData);

    /**
     * 删除系统数据
     * 
     * @param id 系统数据主键
     * @return 结果
     */
    public int deleteSysDataById(Long id);

    /**
     * 批量删除系统数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDataByIds(Long[] ids);
}
