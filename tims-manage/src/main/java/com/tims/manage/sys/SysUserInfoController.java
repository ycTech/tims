package com.tims.manage.sys;

import com.github.pagehelper.Page;
import com.tims.common.result.ResultVo;
import com.tims.common.util.ResultUtil;
import com.tims.facade.api.SysApiService;
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
 * @date 2018/4/1817:55
 */
@Api(value = "权限管理")
@RestController
@RequestMapping("/user")
public class SysUserInfoController {

    @Autowired
    private SysApiService sysApiService;

    @ApiOperation(value = "新增用户")
    @RequestMapping(method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Boolean> saveSysUserInfo(@RequestBody SysUserInfo sysUserInfo){
        return  ResultUtil.success(sysApiService.saveSysUserInfo(sysUserInfo));
    }

    @ApiOperation(value = "更新用户")
    @RequestMapping(method = RequestMethod.PUT, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Boolean> updateSysUserInfo(@RequestBody SysUserInfo sysUserInfo){
        return  ResultUtil.success(sysApiService.updateSysUserInfo(sysUserInfo));
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams(
        @ApiImplicitParam(value = "用户", name = "id", required = true, paramType = "path", dataType = "string")
    )
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE, headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<String> deleteSysUserInfo(@PathVariable String id){
        Assert.notNull(id,"用户id不能为空");
        Boolean result=sysApiService.deleteSysUserInfoById(id);
        return ResultUtil.success(result);
    }

    @ApiOperation(value = "用户分页")
    @PostMapping( value = {"/page"},headers = {"Accept=application/json"})
    @ResponseBody
    public ResultVo<Page<SysUserInfo>> querySysuserInfoPage(@RequestBody SysUserInfo sysUserInfo){
        Page<SysUserInfo>  sysUserInfoPage = sysApiService.querySysuserInfoPage(sysUserInfo);
        return ResultUtil.success(sysUserInfoPage);
    }
}
