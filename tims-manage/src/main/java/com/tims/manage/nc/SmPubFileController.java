package com.tims.manage.nc;

import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.nc.qo.UploadFileInfo;
import com.tims.facade.nc.vo.FileInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/11.
 */
@Api(value = "外部接口")
@RestController
@RequestMapping("/smfile")
public class SmPubFileController {


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


    @ApiOperation(value = "查询员工转正情况信息")
    @RequestMapping(value = {"preview"}, method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<String> queryEmployeeFormalByEmployeeId(@RequestParam(required = false) String billType,
        @RequestParam(required = false) String billName,
        @RequestParam(required = false) String billPk,
        @RequestParam(required = false) String billNo,
        @RequestParam(required = false) String path,
        @RequestParam(required = false) String creator,
        @RequestParam(required = false) String pkCorp,
        @RequestParam(required = false) String systemId
    ){
        String url="http://192.168.1.100:10800/group2/M00/00/00/wKgBG1q3xUeAcKvwACyfKC9S-ZY310.jpg";
        return ResultUtil.success("url");
    }

}
