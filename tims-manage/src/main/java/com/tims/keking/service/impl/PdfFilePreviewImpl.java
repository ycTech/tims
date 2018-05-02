package com.tims.keking.service.impl;

import com.tims.keking.service.FilePreview;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * Created by kl on 2018/1/17.
 * Content :处理pdf文件
 */
@Service
public class PdfFilePreviewImpl implements FilePreview{

    @Override
    public String filePreviewHandle(String url, Model model) {
        model.addAttribute("pdfUrl", url);
        return "pdf";
    }
}
