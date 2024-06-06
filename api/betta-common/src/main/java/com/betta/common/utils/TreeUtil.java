package com.betta.common.utils;

import com.betta.common.core.domain.TreeSelect;

import java.util.*;

public class TreeUtil {

    /**
     * queryTreeList的子方法 ====1=====
     * 该方法设置TreeModel的children
     */
    public static List wrapTreeDataToTreeList(List<TreeSelect> list, Long parentId) {
        Map<Long, TreeSelect> allMap = new HashMap<>();
        List<TreeSelect> result = new ArrayList<>();
        for (TreeSelect model : list) {
            allMap.put(model.getId(), model);
            if (parentId.equals(model.getParentId())) {
                result.add(model);
            }
        }
        findChildren(list, allMap);
        return result;
    }

    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static void findChildren(List<? extends TreeSelect> recordList,
                                     Map<Long, TreeSelect> allMap) {

        for (int i = 0; i < recordList.size(); i++) {
            TreeSelect branch = recordList.get(i);
            if (branch.getParentId()!= null && allMap.containsKey(branch.getParentId())) {
                TreeSelect parent = allMap.get(branch.getParentId());
                if(Objects.isNull(parent.getChildren())){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(branch);
            }
        }
    }
}
