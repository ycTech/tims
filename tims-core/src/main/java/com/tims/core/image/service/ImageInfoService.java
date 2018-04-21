package com.tims.core.image.service;

import com.tims.common.util.PkUtil;
import com.tims.core.image.repository.FileStoreRepository;
import com.tims.core.image.repository.ImageInfoRepository;
import com.tims.core.util.TreeParseUtil;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.dfs.vo.BillImageVo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.domain.ImageInfo;
import com.tims.facade.tree.File;
import com.tims.facade.tree.FileTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImageInfoService {
    @Autowired
    private ImageInfoRepository imageInfoRepository;
    @Autowired
    private FileStoreRepository fileStoreRepository;
    /**
     * 新增图片
     * @param imageInfo
     * @return
     */
    public void saveImageInfo(ImageInfo imageInfo){
        imageInfo.setId(PkUtil.getUUID());
        imageInfo.setCreateTime(new Date());
        imageInfoRepository.saveImageInfo(imageInfo);
    }

    /**
     * 更新图片
     * @param imageInfo
     * @return
     */
    public int updateImageInfo(ImageInfo imageInfo){
        return  imageInfoRepository.updateImageInfo(imageInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int  deleteImageInfo(String id){
        return  imageInfoRepository.deleteImageInfo(id);
    }
    /**
     * 根据单据编码查询图片列表
     * @param billNo
     * @return
     */
    public BillImageVo queryImagesByBillNo(String billNo) {
        return imageInfoRepository.queryImagesByBillNo(billNo);
    }

    /**
     * 根据单据信息查询文件列表
     * @param uploadQo
     * @return
     */
    public FileTree queryFileListByBillInfo(UploadQo uploadQo) throws Exception {
        //通过单据编号查询出对应的文件目录
        UploadQo uploadQoTmp=new UploadQo();
        uploadQoTmp.setBillId(uploadQo.getBillId());
        List<FileStore> fileStoreList=fileStoreRepository.queryFileStore(uploadQoTmp);
        List<File> list=new ArrayList<>();
        Map<String,String> treeIdMap=new HashMap<String,String>();
        for(FileStore fileStore:fileStoreList){
            String[] pathArry=fileStore.getFilePath().split("/");
            if(pathArry.length>0){
                if(treeIdMap.get(pathArry[1])==null) {
                    File file = new File();
                    file.setId(pathArry[1]);
                    file.setParentId("0");
                    file.setName(pathArry[1]);
                    file.setUrl(fileStore.getUrl());
                    list.add(file);
                    treeIdMap.put(file.getId(),file.getId());
                }
            }
            if(pathArry.length>2){
                if(treeIdMap.get(pathArry[3])==null) {
                    File file = new File();
                    file.setId(pathArry[3]);
                    file.setParentId(pathArry[1]);
                    file.setName(pathArry[3]);
                    file.setUrl(fileStore.getUrl());
                    list.add(file);
                    treeIdMap.put(file.getId(),file.getId());
                }
                if (fileStore.getImageName() != null && !fileStore.getImageName().isEmpty()) {
                    if(treeIdMap.get(fileStore.getImageName())==null) {
                        File file = new File();
                        file.setId(fileStore.getImageName());
                        file.setParentId(pathArry[3]);
                        file.setName(fileStore.getImageName());
                        file.setUrl(fileStore.getUrl());
                        list.add(file);
                        treeIdMap.put(file.getId(), file.getId());
                    }
                }
            }
        }
        List<File>  fileList=TreeParseUtil.getTreeList("0",list);
        FileTree fileTree=new FileTree();
        fileTree.setFile(fileList);
        fileTree.setFileStoreList(fileStoreList);
      return fileTree;
    }

    /**
     * 根据id获取图片列表
     * @param imageClassifyId
     * @return
     */
    public List<ImageInfo> queryImageInfoByImageClassifyId(String imageClassifyId){
        return  imageInfoRepository.queryImageInfoByImageClassifyId(imageClassifyId);
    }
}
