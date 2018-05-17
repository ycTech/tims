package com.tims.core.api;

import com.tims.common.util.HttpClientUtil;
import com.tims.core.image.service.FileStoreService;
import com.tims.facade.api.FileStoreApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.domain.FileStore;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileStoreApiServiceImpl implements FileStoreApiService {

    private Logger log= LoggerFactory.getLogger(FileStoreApiServiceImpl.class);

    @Autowired
    private FileStoreService fileStoreService;

    @Value("${nc.url}")
    private String url;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveFileStore(UploadQo uploadQos) throws Exception {
        FileStore fileStore=new FileStore();
        fileStore.setBillId(uploadQos.getBillId());
        fileStore.setFilePath(URLDecoder.decode(uploadQos.getPath(),"UTF-8"));
        fileStore.setBillNo(uploadQos.getBillNo());
        fileStore.setBillType(uploadQos.getBillType());
        fileStore.setUrl(uploadQos.getImageUrl());
        fileStore.setUserCode(uploadQos.getUserCode());
        fileStore.setImageName(uploadQos.getImageName());
        fileStore.setIsFolder(uploadQos.getIsFolder());
        fileStore.setFileSize(uploadQos.getFileSize());
        fileStore.setIsDelete("0");
        fileStoreService.saveImageInfo(fileStore);

        Map<String, Object> params02 = new HashMap<>();
        params02.put("isFloder", uploadQos.getIsFolder());
        params02.put("path", uploadQos.getPath());
        params02.put("m_isDirty", false);
        params02.put("creator", uploadQos.getUserCode());
        List<Map> maplist=new ArrayList<>();
        maplist.add(params02);
        Map<String, List<Map>> params03 = new HashMap<>();
        params03.put("param",maplist);
        String paramTmp= JSONObject.valueToString(params03);
        String param=URLEncoder.encode(paramTmp, "UTF-8");
        if(url==null){
            url="http://10.188.183.85/YCRestfulService/rest/webnc2/file/dopost/";
        }
        log.info("请求的参数:"+url);
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost(url+""+"?param="+param);
        log.info("请求的结果reponse content:" + responseContent);
    }

    @Override
    public List<FileStore> queryUrlByPath(UploadQo uploadQo) throws Exception {
        return fileStoreService.queryUrlByPath(uploadQo);
    }

    @Override
    public Boolean deleteFileInfoByPath(String filePath) {
        return fileStoreService.deleteFileInfoByPath(filePath);
    }

    @Override
    public List<FileStore> queryFileStoreByPath(String path) {
        return fileStoreService.queryFileStoreByPath(path);
    }


}
