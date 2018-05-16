package com.tims.core.image.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
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
        if(StringUtil.isNotEmpty(uploadQo.getBillType())){
            uploadQoTmp.setBillType(uploadQo.getBillType());
        }
        List<FileStore> fileStoreList=fileStoreRepository.queryFileStore(uploadQoTmp);
        List<File> list=new ArrayList<>();
        Map<String,String> treeIdMap=new HashMap<String,String>();
        File fileRoot = new File();
        fileRoot.setId("0");
        fileRoot.setParentId("-1");
        fileRoot.setName("文件目录");
        fileRoot.setUrl(null);
        list.add(fileRoot);
        for(FileStore fileStore:fileStoreList){
            String[] pathArry=fileStore.getFilePath().split("/");
            if(pathArry.length==3&&"n".equals(fileStore.getIsFolder())){
                File file = new File();
                file.setId(fileStore.getFilePath());
                file.setParentId("0");
                file.setName(fileStore.getImageName());
                file.setUrl(fileStore.getUrl());
                list.add(file);
                treeIdMap.put(file.getId(), file.getId());
            }
            if(pathArry.length==3&&"y".equals(fileStore.getIsFolder())){
                if (treeIdMap.get(pathArry[0] + pathArry[2]) == null) {
                    File file = new File();
                    file.setId(fileStore.getFilePath());
                    file.setParentId("0");
                    file.setName(pathArry[2]);
                    file.setUrl(null);
                    list.add(file);
                    treeIdMap.put(pathArry[0] + pathArry[2], file.getId());
                }
            }
            if(pathArry.length>=4){
                if("n".equals(fileStore.getIsFolder())){
                    if (treeIdMap.get(pathArry[0] + pathArry[2]) == null) {
                        File file = new File();
                        file.setId(fileStore.getFilePath());
                        file.setParentId("0");
                        file.setName(pathArry[2]);
                        file.setUrl(null);
                        list.add(file);
                        treeIdMap.put(pathArry[0] + pathArry[2], file.getId());
                    }
                    if( pathArry.length>4) {
                        for (int i = 3; i < pathArry.length-1; i++) {
                            if (treeIdMap.get(pathArry[0] + pathArry[i]) == null) {
                                File file = new File();
                                file.setId(fileStore.getFilePath());
                                file.setParentId(pathArry[0] + pathArry[i - 1]);
                                file.setName(pathArry[i]);
                                file.setUrl(null);
                                list.add(file);
                                treeIdMap.put(pathArry[0] + pathArry[i], file.getId());
                            }
                        }
                    }
                    if (fileStore.getImageName() != null && !fileStore.getImageName().isEmpty()) {
                        if (treeIdMap.get(pathArry[0] + pathArry[pathArry.length - 1] + fileStore.getId()) == null) {
                            File file = new File();
                            file.setId(fileStore.getFilePath());
                            file.setParentId(pathArry[0] + pathArry[pathArry.length - 2]);
                            file.setName(fileStore.getImageName());
                            file.setUrl(fileStore.getUrl());
                            list.add(file);
                            treeIdMap.put(pathArry[0] + pathArry[pathArry.length - 1] + fileStore.getId(), file.getId());
                        }
                    }
                }

                if("y".equals(fileStore.getIsFolder())){
                    if (treeIdMap.get(pathArry[0] + pathArry[2]) == null) {
                        File file = new File();
                        file.setId(fileStore.getFilePath());
                        file.setParentId("0");
                        file.setName(pathArry[2]);
                        file.setUrl(null);
                        list.add(file);
                        treeIdMap.put(pathArry[0] + pathArry[2], file.getId());
                    }
                    if( pathArry.length>4) {
                        for (int i = 3; i < pathArry.length; i++) {
                            if (treeIdMap.get(pathArry[0] + pathArry[i]) == null) {
                                File file = new File();
                                file.setId(fileStore.getFilePath());
                                file.setParentId(pathArry[0] + pathArry[i - 1]);
                                file.setName(pathArry[i]);
                                file.setUrl(null);
                                list.add(file);
                                treeIdMap.put(pathArry[0] + pathArry[i], file.getId());
                            }
                        }
                    }
                }

            }
        }
        List<File>  fileList=TreeParseUtil.getTreeList("-1",list);
        FileTree fileTree=new FileTree();
        fileTree.setFile(fileList);
        fileTree.setFileStoreList(fileStoreList);
      return fileTree;
    }

    /**
     * 根据单据信息查询文件目录列表
     * @param uploadQo
     * @return
     */
    public List<FileStore> queryFolderListByBillInfo(UploadQo uploadQo) throws Exception {
        //通过单据编号查询出对应的文件目录
        UploadQo uploadQoTmp=new UploadQo();
        uploadQoTmp.setBillId(uploadQo.getBillId());
        uploadQoTmp.setBillType(uploadQo.getBillType());
        List<FileStore> fileStoreTmpList=fileStoreRepository.queryFileStore(uploadQoTmp);
        List<FileStore> fileStoreList=new ArrayList<>();
        for(FileStore fileStore:fileStoreTmpList){
            String[] pathArry=fileStore.getFilePath().split("/");
            if(pathArry.length>2){
                fileStore.setFilePath(pathArry[3]);
                fileStoreList.add(fileStore);
            }
        }
        return fileStoreList;
    }

    /**
     * 根据id获取图片列表
     * @param imageClassifyId
     * @return
     */
    public List<ImageInfo> queryImageInfoByImageClassifyId(String imageClassifyId){
        return  imageInfoRepository.queryImageInfoByImageClassifyId(imageClassifyId);
    }

    public Page<FileStore> queryFileStoreList(FileStore fileStore) throws Exception {
        return fileStoreRepository.queryFileList(fileStore);
    }
}
