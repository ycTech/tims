package com.tims.facade.tree;

import java.util.List;

public interface TreeEntity<E> {
    public String getId();
    public String getParentId();
    public void setChildList(List<E> childList);
}
