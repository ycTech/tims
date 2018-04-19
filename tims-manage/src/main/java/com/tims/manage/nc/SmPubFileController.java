package com.tims.manage.nc;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.FileStoreApiService;
import com.tims.facade.api.SysApiService;
import com.tims.facade.dfs.qo.UploadQo;
import com.tims.facade.nc.qo.UploadFileInfo;
import com.tims.facade.nc.vo.FileInfoVo;
import com.tims.facade.sys.SysUnitInfo;
import com.tims.facade.sys.SysUserInfo;
import com.tims.manage.controller.BaseController;
import com.tims.manage.fast.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
                           @RequestParam(value = "userCode",required=true) String userCode,
                           @RequestParam(value = "billType",required=true) String billType,
                           @RequestParam(value = "billId",required=true) String billId,
                           @RequestParam(value = "billNo",required=true) String billNo,
                           @RequestParam(value = "path",required=true) String path,
                           @RequestParam(value = "isFolder",required=true) String isFolder
                           ) throws Exception {
        String fileUrl= dfsClient.uploadFile(file);
        UploadQo uploadQos=new UploadQo();
        uploadQos.setBillNo(billNo);
        uploadQos.setBillType(billType);
        uploadQos.setBillId(billId);
        uploadQos.setUserCode(userCode);
        uploadQos.setPath(path);
        uploadQos.setIsFolder(isFolder);
        uploadQos.setImageUrl(fileUrl);
        uploadQos.setFileSize(String.valueOf(file.getSize()));
        uploadQos.setImageName(file.getOriginalFilename());
        fileStoreApiService.saveFileStore(uploadQos);
        return ResultUtil.success(fileUrl);
    }


    @ApiOperation(value = "新增文件信息")
    @RequestMapping(value = "/saveFileSystem",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<FileInfoVo> insertSmPubFileSystem(@RequestBody List<UploadFileInfo> params){
        FileInfoVo fileInfoVo=new FileInfoVo();
        fileInfoVo.setCreator("101101");
        fileInfoVo.setIsFloder("Y");
        fileInfoVo.setPath("BTMS/I32310/文件目录/a.jpg");
        fileInfoVo.setTime(423423);
        fileInfoVo.setMd5("4323sfsfasfs23423423423");
        fileInfoVo.setPkCorp("4231");
        fileInfoVo.setFileLength(242323);
        return ResultUtil.success(fileInfoVo);
    }

    @ApiOperation(value = "新增机构")
    @RequestMapping(value = "/save/org",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo insertOrg(@RequestBody SysUnitInfo sysUnitInfo){
        Boolean result=  sysApiService.saveSysUnitInfo(sysUnitInfo);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/save/user",method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<FileInfoVo> insertUser(@RequestBody SysUserInfo userInfo){
      Boolean result=  sysApiService.saveSysUserInfo(userInfo);
        return ResultUtil.success(result);
    }

}
