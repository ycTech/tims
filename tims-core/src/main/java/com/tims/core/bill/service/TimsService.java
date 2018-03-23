package com.tims.core.bill.service;

import com.tims.core.image.service.ImageClassifyRelService;
import com.tims.core.image.service.ImageClassifyService;
import com.tims.core.image.service.ImageInfoService;
import com.tims.facade.domain.*;
import com.tims.facade.domain.vo.BillInfoVo;
import com.tims.facade.domain.vo.BillTypeVo;
import com.tims.facade.domain.vo.ImageClassifyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimsService {
    @Autowired
    private BillInfoService billInfoService;
    @Autowired
    private BillTypeService billTypeService;
    @Autowired
    private ImageClassifyRelService imageClassifyRelService;
    @Autowired
    private ImageClassifyService imageClassifyService;
    @Autowired
    private ImageInfoService imageInfoService;
    /**
     *根据id获取对象vo
     * @param id
     * @return
     */
    public BillInfoVo getBillInfoById(String id){
        BillInfoVo billInfoVo =new BillInfoVo();
        BillInfo billInfo=billInfoService.queryBillInfoById(id);
        ImageInfo imageInfo=new ImageInfo();
        List<ImageInfo> imageInfoList=new ArrayList<>();
        if(null!=billInfo){
            BeanUtils.copyProperties(billInfo,billInfoVo);
            if(null!=billInfo.getBillTypeId()){
                BillType billType=billTypeService.queryBillTypeById(id);
                if(null!=billType){
                    BillTypeVo billTypeVo=new BillTypeVo();
                    BeanUtils.copyProperties(billType,billTypeVo);
                    List<ImageClassifyRel> list=imageClassifyRelService.queryImageClassifyRelByBillTypeId(billType.getId());
                    if(null!=list &&  list.size()>0){
                        List<ImageClassifyVo> imageClassifyVos=new ArrayList<>();
                        for(ImageClassifyRel imageClassifyRel:list){
                            ImageClassify imageClassify=imageClassifyService.queryImageClassifyById(imageClassifyRel.getClassifyId());
                            if(null!=imageClassify){
                                ImageClassifyVo imageClassifyVo=new ImageClassifyVo();
                                BeanUtils.copyProperties(imageClassify,imageClassifyVo);
                                List<ImageInfo> imageInfos=imageInfoService.queryImageInfoByImageClassifyId(imageClassify.getId());
                                if(null!=imageInfos && imageInfos.size()>0){
                                    imageClassifyVo.setImageInfos(imageInfos);
                                }
                                imageClassifyVos.add(imageClassifyVo);
                            }
                        }
                        billTypeVo.setImageClassifyVos(imageClassifyVos);
                    }
                    billInfoVo.setBillTypeVo(billTypeVo);
                }
            }
        }
        return  billInfoVo;
    }
}
