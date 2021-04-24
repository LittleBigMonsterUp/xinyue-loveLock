package com.feng.api.yncweapp;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName IClockDiary
 * @Author 小风谷
 * @Date 2021/3/23 19:38
 * @Version 1.0
 * @Description
 */
@Api(value = "打卡日记接口")
public interface IClockDiary {


    @ApiOperation(value = "创建打卡日记")
    public AjaxResult clockCreate(@RequestBody ReCreateDiaryInfo reCreateDiaryInfo);

    @ApiOperation(value = "获取打卡日记列表")
    public AjaxResult getClockDiaryInfoList(@RequestBody ReClockDetailPage reClockDetailPage);

    @ApiOperation(value = "日记置顶")
    public AjaxResult clockDiaryInfoStick(@RequestBody ReClockDiaryInfoList reClockDiaryInfoList);

    @ApiOperation(value = "日记点赞")
    public AjaxResult clockDiaryInfoLick(@RequestBody ReTenLikeInfo reTenLikeInfo);

    @ApiOperation(value = "日记取消点赞")
    public AjaxResult clockDiaryInfoCancelLike(@RequestBody ReTenLikeInfo reTenLikeInfo);

    @ApiOperation(value = "日记添加评论")
    public AjaxResult clockDiaryAddComment(@RequestBody ReClockDiaryComment reClockDiaryComment);

    @ApiOperation(value = "日记删除评论")
    public AjaxResult clockDiaryDeleteComment(@RequestBody ReClockDiaryComment reClockDiaryComment);


    @ApiOperation(value = "获取打卡日历时间")
    public AjaxResult getCalendarDay(@RequestBody ReClockProject reClockProject);

    @ApiOperation(value = "获取打卡日历时间")
    public AjaxResult reissueClock (@RequestBody ReCreateDiaryInfo reCreateDiaryInfo);

    @ApiOperation(value = "获取评论消息")
    public AjaxResult getResponseList (@RequestBody ReClockProject reClockProject);

    @ApiOperation(value = "获取点赞消息")
    public AjaxResult getAdmirerList (@RequestBody ReClockProject reClockProject);
}
