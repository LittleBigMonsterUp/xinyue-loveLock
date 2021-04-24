package com.feng.ycnweapp.controller;

import com.feng.api.yncweapp.IClockDetail;
import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.ReClockDiaryInfo;
import com.feng.framework.ycnweapp.request.ReClockPage;
import com.feng.framework.ycnweapp.request.ReClockProject;
import com.feng.ycnweapp.service.ClockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ClockInfoController
 * @Author 小风谷
 * @Date 2021/3/20 22:35
 * @Version 1.0
 * @Description  打卡圈子
 */
@RestController
@RequestMapping("/ClockDetail")
public class ClockDetailController implements IClockDetail {

    @Autowired
    ClockDetailService clockDetailService;

    @PostMapping("/checkUser")
    @Override
    public AjaxResult checkUser(ReClockProject reClockProject) {

        return clockDetailService.checkUser(reClockProject);
    }
    @PostMapping("/getProjectListByType")
    @Override
    public AjaxResult getProjectListByType(ReClockPage reClockPage) {
        return clockDetailService.getProjectListByType(reClockPage);
    }

    @PostMapping("/getChildrenLabel")
    @Override
    public AjaxResult getChildrenLabel(ReClockDiaryInfo reClockDiaryInfo) {
        return clockDetailService.getChildrenLabel(reClockDiaryInfo);
    }

    @PostMapping("/getProjectInfo")
    @Override
    public AjaxResult getProjectInfo(ReClockProject reClockProject)
    {
        return clockDetailService.getProjectInfo(reClockProject);
    }

    @PostMapping("/getProjectInfoList")
    @Override
    public AjaxResult getProjectInfoList(ReClockPage reClockPage) {
        return clockDetailService.getProjectInfoList(reClockPage);
    }

    @PostMapping("/getAlreadyProjectInfoList")
    @Override
    public AjaxResult getAlreadyProjectInfoList(ReClockPage reClockPage) {
        return clockDetailService.getAlreadyProjectInfoList(reClockPage);
    }

    @PostMapping("/getAlreadyJoinInProjectList")
    @Override
    public AjaxResult getAlreadyJoinInProjectList(ReClockPage reClockPage) {
        return clockDetailService.getAlreadyJoinInProjectList(reClockPage);
    }

    @PostMapping("/joinInProject")
    @Override
    public AjaxResult joinInProject(ReClockProject reClockProject) {
        return clockDetailService.joinInProject(reClockProject);
    }



    @PostMapping("/upProjectName")
    @Override
    public AjaxResult upProjectName(Map<String, Object> map) {

        return clockDetailService.upProjectName(map);
    }

}
