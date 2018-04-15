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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        for(FileStore fileStore:fileStoreList){
            String[] pathArry=fileStore.getFilePath().split("/");
            if(pathArry.length>0){
                File file=new File();
                file.setId(pathArry[1]);
                file.setParentId("0");
                file.setName(pathArry[1]);
                file.setUrl("");
                list.add(file);
            }
            if(pathArry.length>2){
                File file=new File();
                file.setId(pathArry[3]);
                file.setParentId(pathArry[1]);
                file.setName(pathArry[3]);
                file.setUrl("");
                list.add(file);
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
