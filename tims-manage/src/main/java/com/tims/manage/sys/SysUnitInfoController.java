package com.tims.manage.sys;

import com.github.pagehelper.Page;
import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.SysApiService;
import com.tims.facade.sys.SysUnitInfo;
import com.tims.facade.sys.SysUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuzm
 * @Description:
 * @date 2018/4/1817:56
 */
@Api(value = "权限管理")
@RestController
@RequestMapping("/unit")
public class SysUnitInfoController {
    @Autowired
    private SysApiService sysApiService;



    @ApiOperation(value = "新增单位")
    @RequestMapping(method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Boolean> saveSysUnitInfo(@RequestBody SysUnitInfo sysUnitInfo){
        return  ResultUtil.success(sysApiService.saveSysUnitInfo(sysUnitInfo));
    }

    @ApiOperation(value = "更新单位")
    @RequestMapping(method = RequestMethod.PUT, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Boolean> updateSysUnitInfo(@RequestBody SysUnitInfo sysUnitInfo){
        return  ResultUtil.success(sysApiService.updateSysUnitInfo(sysUnitInfo));
    }

    @ApiOperation(value = "删除单位")

    @ApiImplicitParams(
        @ApiImplicitParam(value = "用户", name = "id", required = true, paramType = "path", dataType = "string")
    )
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<String> deleteSysUnitInfo(@PathVariable String id){
        Assert.notNull(id,"用户id不能为空");
        Boolean result=sysApiService.deleteSysUnitInfo(id);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "单位分页")
    @PostMapping(value = {"/page"},headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Page<SysUnitInfo>> querySysUnitInfoPage(@RequestBody SysUnitInfo sysUnitInfo){
        Page<SysUnitInfo> sysUnitInfoPage = sysApiService.querySysUnitInfoPage(sysUnitInfo);
        return ResultUtil.success(sysUnitInfoPage);
    }
}
