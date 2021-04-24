package com.feng.ycnweapp.controller;

import com.feng.api.yncweapp.IClockDiary;
import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.*;
import com.feng.ycnweapp.service.ClockDiaryService;
import com.feng.ycnweapp.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ClockDiaryController
 * @Author 小风谷
 * @Date 2021/3/23 19:39
 * @Version 1.0
 * @Description 打卡日记接口
 */
@RestController
@RequestMapping("/ClockDiary")
public class ClockDiaryController  implements IClockDiary {

    @Autowired
    ClockDiaryService clockDiaryService;


    @PostMapping("/ClockCreate")
    @Override
    public AjaxResult clockCreate(ReCreateDiaryInfo reCreateDiaryInfo){
        return clockDiaryService.clockCreate(reCreateDiaryInfo);
    }

    @PostMapping("/getClockDiaryInfoList")
    @Override
    public AjaxResult getClockDiaryInfoList(ReClockDetailPage reClockDetailPage) {
        return clockDiaryService.getClockDiaryInfoList(reClockDetailPage);
    }

    @PostMapping("/clockDiaryInfoStick")
    @Override
    public AjaxResult clockDiaryInfoStick(ReClockDiaryInfoList reClockDiaryInfoList) {
        return null;
    }

    @PostMapping("/clockDiaryInfoLick")
    @Override
    public AjaxResult clockDiaryInfoLick(ReTenLikeInfo reTenLikeInfo) {
        return clockDiaryService.clockDiaryInfoLick(reTenLikeInfo);
    }

    @PostMapping("/clockDiaryInfoCancelLike")
    @Override
    public AjaxResult clockDiaryInfoCancelLike(ReTenLikeInfo reTenLikeInfo) {
        return clockDiaryService.clockDiaryInfoCancelLike(reTenLikeInfo);
    }

    @PostMapping("/clockDiaryAddComment")
    @Override
    public AjaxResult clockDiaryAddComment(ReClockDiaryComment reClockDiaryComment) {
        return clockDiaryService.clockDiaryAddComment(reClockDiaryComment);
    }

    @PostMapping("/clockDiaryDeleteComment")
    @Override
    public AjaxResult clockDiaryDeleteComment(ReClockDiaryComment reClockDiaryComment) {
        return clockDiaryService.clockDiaryDeleteComment(reClockDiaryComment);
    }

    @PostMapping("/getCalendarDay")
    @Override
    public AjaxResult getCalendarDay(ReClockProject reClockProject) {
        return clockDiaryService.getCalendarDay(reClockProject);
    }


    @PostMapping("/reissueClock")
    @Override
    public AjaxResult reissueClock(ReCreateDiaryInfo reCreateDiaryInfo) {
        return clockDiaryService.reissueClock(reCreateDiaryInfo);
    }

    @PostMapping("/getResponseList")
    @Override
    public AjaxResult getResponseList(ReClockProject reClockProject) {
        return clockDiaryService.getResponseList();
    }

    @PostMapping("/getAdmirerList")
    @Override
    public AjaxResult getAdmirerList(ReClockProject reClockProject) {
        return clockDiaryService.getAdmirerList();
    }

    // /**获取打卡排行榜*/
    // @PostMapping("/getRankingList")
    // @Override
    // public AjaxResult getRankingList(ReClockProject reClockProject) {
    //     return clockDiaryService.getRankingList(reClockProject);
    // }


}
