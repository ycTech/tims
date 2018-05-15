package com.tims.manage.nc;

import com.tims.common.exception.BusinessException;
import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.FileStoreApiService;
import com.tims.facade.api.SysApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.domain.FileStore;
import com.tims.facade.nc.vo.FileInfoVo;
import com.tims.facade.sys.SysUnitInfo;
import com.tims.facade.sys.SysUserInfo;
import com.tims.manage.controller.BaseController;
import com.tims.manage.fast.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Api(value = "外部接口")
@RestController
@RequestMapping("/smfile")
public class SmPubFileController extends BaseController {

    @Autowired
    private FastDFSClientWrapper dfsClient;
    @Autowired
    private FileStoreApiService fileStoreApiService;
    @Autowired
    private SysApiService sysApiService;

    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo upload(@RequestParam(value="file",required=false)MultipartFile file,
                           @RequestParam(value = "userCode",required=false) String userCode,
                           @RequestParam(value = "billType",required=false) String billType,
                           @RequestParam(value = "billId",required=false) String billId,
                           @RequestParam(value = "billNo",required=false) String billNo,
                           @RequestParam(value = "path",required=true) String path,
                           @RequestParam(value = "isFolder",required=false) String isFolder
                           ) throws Exception {
        String fileUrl=null;
        if(file!=null) {
            fileUrl = dfsClient.uploadFile(file);
        }
        UploadQo uploadQos=new UploadQo();
        uploadQos.setBillNo(billNo);
        uploadQos.setBillType(billType);
        uploadQos.setBillId(billId);
        uploadQos.setUserCode(userCode);
        uploadQos.setPath(URLDecoder.decode(path,"UTF-8"));
        uploadQos.setIsFolder(isFolder);
        uploadQos.setImageUrl(fileUrl);
        if(file!=null){
            uploadQos.setFileSize(String.valueOf(file.getSize()));
            String fileName=URLDecoder.decode(file.getOriginalFilename(),"UTF-8");
            uploadQos.setImageName(fileName);
        }
        fileStoreApiService.saveFileStore(uploadQos);
        return ResultUtil.success(fileUrl);
    }


    @ApiOperation(value = "删除文件")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo  deleteFileByPath (@RequestParam(value = "path",required=true) String path) throws Exception{
        String filePath=URLDecoder.decode(path,"UTF-8");
        Boolean result=  fileStoreApiService.deleteFileInfoByPath(filePath);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "下载文件")
    @RequestMapping(value = "/downFile", method = RequestMethod.GET)
    public void downFile(@RequestParam(value = "path",required=true) String path,
                         @RequestParam(value = "creator",required=false) String creator,
                         HttpServletResponse resp) throws Exception{
        InputStream inputStream = null;
        try {
            Assert.isNull(path,"path参数不能为空");
            List<FileStore> fileStore=fileStoreApiService.queryFileStoreByPath(path);
            if(!CollectionUtils.isEmpty(fileStore) && fileStore.size()>1){
                throw  new BusinessException("传入的参数path不是唯一的！");
            }
            FileStore fileStore1=fileStore.get(0);
            String strUrl=null;
            if(null!=fileStore1.getUrl()){
                 strUrl =fileStore1.getUrl();
            }
            URL url = new URL(strUrl);
            //打开请求连接
            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            inputStream = httpURLConnection.getInputStream();
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Length", String.valueOf(url.openConnection().getContentLength()));

            byte[] bs = new byte[1024];
            int len;
            while (-1 != (len = inputStream.read(bs))) {
                resp.getOutputStream().write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    @ApiOperation(value = "新增机构")
    @RequestMapping(value = "/save/org",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo insertOrg(@RequestParam(value = "unitCode",required=true) String unitCode,
                              @RequestParam(value = "unitName",required=true) String unitName,
                              @RequestParam(value = "unitShortName",required=true) String unitShortName,
                              @RequestParam(value = "dr",required=true) int dr){
        SysUnitInfo sysUnitInfo=new SysUnitInfo();
        sysUnitInfo.setUnitCode(unitCode);
        sysUnitInfo.setDr(dr);
        sysUnitInfo.setUnitName(unitName);
        sysUnitInfo.setUnitShortName(unitShortName);
        Boolean result=  sysApiService.saveSysUnitInfo(sysUnitInfo);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/save/user",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<FileInfoVo> insertUser(  @RequestParam(value = "userCode",required=true) String userCode,
                                             @RequestParam(value = "userName",required=true) String userName,
                                             @RequestParam(value = "password",required=false) String password,
                                             @RequestParam(value = "dr",required=true) int dr,
                                             @RequestParam(value = "pkCorp",required=true) String pkCorp){
        SysUserInfo  userInfo=new SysUserInfo();
        userInfo.setPkCorp(pkCorp);
        userInfo.setUserName(userName);
        userInfo.setUserCode(userCode);
        userInfo.setPassword(password);
        userInfo.setDr(dr);
      Boolean result=  sysApiService.saveSysUserInfo(userInfo);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "查询附件的URL")
    @RequestMapping(value = "/path/list",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryUrlByPath(@RequestBody UploadQo uploadQo) throws Exception {
        return ResultUtil.success(fileStoreApiService.queryUrlByPath(uploadQo));
    }

}
