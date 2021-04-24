package com.feng.api.yncweapp;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.ReClockDiaryInfo;
import com.feng.framework.ycnweapp.request.ReClockPage;
import com.feng.framework.ycnweapp.request.ReClockProject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @ClassName IClockInfo
 * @Author 小风谷
 * @Date 2021/3/20 21:42
 * @Version 1.0
 * @Description 打卡的一些信息
 */
@Api(value = "打卡信息接口")
public interface IClockDetail {

    @ApiOperation(value = "获取当前用户是否已经加入该项打卡")
    public AjaxResult checkUser(@RequestBody ReClockProject reClockProject);

    @ApiOperation(value = "根据用户选择的打卡圈子类型，分页获取对应类型的打卡圈子列表")
    public AjaxResult getProjectListByType(@RequestBody ReClockPage reClockPage);

    @ApiOperation(value = "获取打卡项目的所有子标签")
    public AjaxResult getChildrenLabel(@RequestBody ReClockDiaryInfo reClockDiaryInfo);

    @ApiOperation(value = "获取该打卡的相关信息")
    public AjaxResult getProjectInfo(@RequestBody ReClockProject reClockProject);

    @ApiOperation(value = "获取打卡列表")
    public AjaxResult getProjectInfoList(@RequestBody ReClockPage reClockPage);

    @ApiOperation(value = "获取当前用户已创建的打卡列表")
    public AjaxResult getAlreadyProjectInfoList(@RequestBody ReClockPage reClockPage);

    @ApiOperation(value = "获取当前用户已创建的打卡列表")
    public AjaxResult getAlreadyJoinInProjectList(@RequestBody ReClockPage reClockPage);

    @ApiOperation(value = "加入打卡项")
    public AjaxResult joinInProject(@RequestBody ReClockProject reClockProject);



    @ApiOperation(value = "更新打卡名字")
    public AjaxResult upProjectName(Map<String,Object> map);


}
