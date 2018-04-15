package com.tims.facade.tree;

import com.tims.facade.domain.FileStore;

import java.io.Serializable;
import java.util.List;


public class FileTree implements Serializable {

    public List<File> file;
    public List<FileStore> fileStoreList;

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<FileStore> getFileStoreList() {
        return fileStoreList;
    }

    public void setFileStoreList(List<FileStore> fileStoreList) {
        this.fileStoreList = fileStoreList;
    }
}
