package com.tims.keking.service.impl;

import com.tims.keking.model.FileAttribute;
import com.tims.keking.model.ReturnResponse;
import com.tims.keking.service.FilePreview;
import com.tims.keking.utils.FileUtils;
import com.tims.keking.utils.SimTextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by kl on 2018/1/17.
 * Content :处理文本文件
 */
@Service
public class SimTextFilePreviewImpl implements FilePreview{

    @Autowired
    SimTextUtil simTextUtil;

    @Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model){
        FileAttribute fileAttribute=fileUtils.getFileAttribute(url);
        String decodedUrl=fileAttribute.getDecodedUrl();
        String fileName=fileAttribute.getName();
        ReturnResponse<String> response = simTextUtil.readSimText(decodedUrl, fileName);
        if (0 != response.getCode()) {
            model.addAttribute("msg", response.getMsg());
            return "fileNotSupported";
        }
        model.addAttribute("ordinaryUrl", response.getMsg());
        return "txt";
    }

}
