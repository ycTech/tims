package com.tims.manage.nc;

import com.tims.common.result.ResultVo;
import com.tims.common.util.FileUploadUtil;
import com.tims.common.util.HttpClientUtil;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.FileStoreApiService;
import com.tims.facade.api.SysApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.nc.vo.FileInfoVo;
import com.tims.facade.sys.SysUnitInfo;
import com.tims.facade.sys.SysUserInfo;
import com.tims.manage.controller.BaseController;
import com.tims.manage.fast.FastDFSClientWrapper;
import org.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/11.
 */
@Api(value = "外部接口")
@RestController
@RequestMapping("/smfile")
public class SmPubFileController extends BaseController {

    private Logger log= LoggerFactory.getLogger(SmPubFileController.class);


    @Value("${file.store.path}")
    String fileStorePath;
    @Value("${nc.url}")
    private String url;
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
                           @RequestParam(value = "path",required=true) String path
                           ) throws Exception {
        String pathTemp = fileStorePath;
        String billType=FileUploadUtil.getBillTypeByPath(path);
        // 根据PATH生成的随机目录
        String randomDirectory = FileUploadUtil.getRandomDirectory(path)+"/"+billType;
        // 注意:随机目录可能不存在，需要创建.
        File rd = new File(pathTemp, randomDirectory);
        if (!rd.exists()) {
            rd.mkdirs();
        }
        String originalFileName=FileUploadUtil.getFileNameByPath(path);
        String encryFileName=FileUploadUtil.encodeFileName(originalFileName);
        //先把传过来的文件放在临时文件夹下，然后从文件夹中取出
        InputStream inputStream = file.getInputStream();
        IOUtils.copy(inputStream, new FileOutputStream(new File(rd, encryFileName)));
        String filePath=fileStorePath+ randomDirectory + "/" + encryFileName;
        return ResultUtil.success(filePath);
    }

    @ApiOperation(value = "上传文件到NC")
    @RequestMapping(value = "/upload/nc", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo uploadToNc(@RequestParam(value="file",required=true)MultipartFile file,
                               @RequestParam(value = "userCode",required=false) String userCode,
                               @RequestParam(value = "path",required=true) String path
    ) throws Exception {
        String pathTemp = fileStorePath;
        String billType=FileUploadUtil.getBillTypeByPath(path);
        // 根据PATH生成的随机目录
        String randomDirectory = FileUploadUtil.getRandomDirectory(path)+"/"+billType;
        // 注意:随机目录可能不存在，需要创建.
        File rd = new File(pathTemp, randomDirectory);
        if (!rd.exists()) {
            rd.mkdirs();
        }
        String originalFileName=FileUploadUtil.getFileNameByPath(path);
        String encryFileName=FileUploadUtil.encodeFileName(originalFileName);
        //先把传过来的文件放在临时文件夹下，然后从文件夹中取出
        InputStream inputStream = file.getInputStream();
        IOUtils.copy(inputStream, new FileOutputStream(new File(rd, encryFileName)));
        String filePath=fileStorePath+ randomDirectory + "/" + encryFileName;
        sendDataToNc(path,"0");
        return ResultUtil.success(filePath);
    }

    private void sendDataToNc(String path,String userCode) throws Exception {
        Map<String, Object> params02 = new HashMap<>();
        params02.put("isFloder", "n");
        params02.put("path", path);
        params02.put("m_isDirty", false);
        params02.put("creator", userCode);
        List<Map> maplist=new ArrayList<>();
        maplist.add(params02);
        Map<String, List<Map>> params03 = new HashMap<>();
        params03.put("param",maplist);
        String paramTmp= JSONObject.valueToString(params03);
        String param=URLEncoder.encode(paramTmp, "UTF-8");
        if(url==null){
            url="http://10.188.183.85/YCRestfulService/rest/webnc2/file/dopost/";
        }
        log.info("请求的参数:"+url+"~"+param);
        String responseContent = HttpClientUtil.getInstance().sendHttpPost(url+""+"?param="+param);
        log.info("请求的结果reponse content:" + responseContent);
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
            String originalFilename =FileUploadUtil.getFileName(path);//获得上传文件名.后缀
            String originalFile=FileUploadUtil.getFileNameByPath(path);
            String encryFileName=FileUploadUtil.encodeFileName(originalFile);

            String billType=FileUploadUtil.getBillTypeByPath(path);
            String pathTemp = fileStorePath+FileUploadUtil.getRandomDirectory(path)+"/"+billType+"/"+encryFileName;

            File file = new File(pathTemp);
            if (!file.exists()) {
                throw new Exception("文件不存在!");
            }
            inputStream = new FileInputStream(file);
            resp.setContentType("application/octet-stream");
//            resp.setHeader("Content-Disposition", "attachment; filename="
//                    + URLEncoder.encode(fileStore1.getImageName(), "UTF-8"));
//            resp.setHeader("Content-Disposition", "attachment;fileName="+ fileStore1.getImageName());
            resp.setHeader("Content-Disposition", "attachment; fileName="+ originalFilename +";filename*=utf-8''"+URLEncoder.encode(originalFilename,"UTF-8"));
            resp.setHeader("Content-Length", String.valueOf(file.length()));
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
    public ResultVo insertOrg(@RequestParam(value = "corpId",required=true) String corpId ,
                              @RequestParam(value = "unitCode",required=true) String unitCode,
                              @RequestParam(value = "unitName",required=true) String unitName,
                              @RequestParam(value = "unitShortName",required=true) String unitShortName,
                              @RequestParam(value = "vSystem",required=true) String vSystem,
                              @RequestParam(value = "operate",required=true) String operate,
                              @RequestParam(value = "dr",required=true) int dr){
        if("add".equals(operate)){
            SysUnitInfo sysUnitInfo=new SysUnitInfo();
            sysUnitInfo.setId(corpId);
            sysUnitInfo.setUnitCode(unitCode);
            sysUnitInfo.setDr(dr);
            sysUnitInfo.setUnitName(unitName);
            sysUnitInfo.setUnitShortName(unitShortName);
            Boolean result=  sysApiService.saveSysUnitInfo(sysUnitInfo);
        }
        if("update".equals(operate)){
            SysUnitInfo sysUnitInfo=new SysUnitInfo();
            sysUnitInfo.setId(corpId);
            sysUnitInfo.setUnitCode(unitCode);
            sysUnitInfo.setDr(dr);
            sysUnitInfo.setUnitName(unitName);
            sysUnitInfo.setUnitShortName(unitShortName);
            Boolean result=  sysApiService.updateSysUnitInfo(sysUnitInfo);
        }
        return success;
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/save/user",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<FileInfoVo> insertUser(  @RequestParam(value = "userId",required=true) String userId,
                                             @RequestParam(value = "userCode",required=true) String userCode,
                                             @RequestParam(value = "userName",required=true) String userName,
                                             @RequestParam(value = "password",required=false) String password,
                                             @RequestParam(value = "dr",required=true) int dr,
                                             @RequestParam(value = "vSystem",required=true) String vSystem,
                                             @RequestParam(value = "operate",required=true) String operate,
                                             @RequestParam(value = "corpId",required=true) String corpId){
        if("add".equals(operate)){
            SysUserInfo  userInfo=new SysUserInfo();
            userInfo.setId(userId);
            userInfo.setPkCorp(corpId);
            userInfo.setUserName(userName);
            userInfo.setUserCode(userCode);
            userInfo.setPassword(password);
            userInfo.setDr(dr);
            Boolean result=  sysApiService.saveSysUserInfo(userInfo);
        }
        if("update".equals(operate)){
            SysUserInfo  userInfo=new SysUserInfo();
            userInfo.setId(userId);
            userInfo.setPkCorp(corpId);
            userInfo.setUserName(userName);
            userInfo.setUserCode(userCode);
            userInfo.setPassword(password);
            userInfo.setDr(dr);
            Boolean result=  sysApiService.updateSysUserInfo(userInfo);
        }
        return success;
    }

    @ApiOperation(value = "查询附件的URL")
    @RequestMapping(value = "/path/list",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo queryUrlByPath(@RequestBody UploadQo uploadQo) throws Exception {
        return ResultUtil.success(fileStoreApiService.queryUrlByPath(uploadQo));
    }

}
