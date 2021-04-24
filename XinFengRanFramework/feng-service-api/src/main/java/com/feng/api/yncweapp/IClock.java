package com.feng.api.yncweapp;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.ReCreateClockInfo;
import com.feng.framework.ycnweapp.response.ResGradeClbum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName IClock
 * @Author 小风谷
 * @Date 2021/3/8 21:22
 * @Version 1.0
 * @Description 打卡接口
 */
@Api(value = "打卡接口")
public interface IClock {

    @ApiOperation(value = "创建打卡接口")
    public AjaxResult createClock(@RequestBody ReCreateClockInfo reCreateClockInfo);

    @ApiOperation(value = "获取所有年级名称")
    public AjaxResult getAllCourse();

    @ApiOperation(value = "获取所有班级年级")
    public ResGradeClbum getAllGradeCourse();

}
