package com.feng.ycnweapp.controller;


import com.feng.api.yncweapp.IClock;
import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.request.ReCreateClockInfo;
import com.feng.framework.ycnweapp.response.ResGradeClbum;
import com.feng.ycnweapp.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ClockController
 * @Author 小风谷
 * @Date 2021/3/9 10:06
 * @Version 1.0
 * @Description 打卡接口
 */
@RestController
@RequestMapping("/Clock")
public class ClockController implements IClock {

    @Autowired
    ClockService clockService;



    @PostMapping("/createClock")
    @Override
    public AjaxResult createClock(ReCreateClockInfo reCreateClockInfo) {

        return clockService.createClock(reCreateClockInfo);
    }


    @GetMapping("/getAllCourse")
    @Override
    public AjaxResult getAllCourse() {
        return clockService.getAllCourse();
    }

    @GetMapping("/getAllGradeCourse")
    @Override
    public ResGradeClbum getAllGradeCourse() {
        return clockService.getAllGradeCourse();
    }


}
