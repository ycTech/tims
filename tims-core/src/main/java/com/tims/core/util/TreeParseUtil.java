package com.tims.core.util;

import com.tims.facade.tree.TreeEntity;

import java.util.ArrayList;
import java.util.List;

public class TreeParseUtil {
    public static <E extends TreeEntity<E>> List<E> getTreeList(String topId, List<E> entityList){
        List<E> resultList=new ArrayList<>();
        String parentId;
        for(E entity:entityList){
            parentId=entity.getParentId();
            if(parentId==null||topId.equals(parentId)){
                resultList.add(entity);
            }
        }
        for(E entity:resultList){
            entity.setChildList(getSubList(entity.getId(),entityList));
        }
        return resultList;
    }
    private static  <E extends TreeEntity<E>> List<E> getSubList(String id,List<E> entityList){
        List<E> childList=new ArrayList<>();
        String parentId;
        for(E entity:entityList){
            parentId=entity.getParentId();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }
        for(E entity:childList){
            entity.setChildList(getSubList(entity.getId(),entityList));
        }
        if(childList.size()==0){
            return null;
        }
        return childList;
    }

}
