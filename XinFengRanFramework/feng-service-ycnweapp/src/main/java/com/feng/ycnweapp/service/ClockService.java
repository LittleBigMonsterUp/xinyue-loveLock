package com.feng.ycnweapp.service;
import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.*;
import com.feng.framework.ycnweapp.request.ReCreateClockInfo;
import com.feng.framework.ycnweapp.response.ResClbum;
import com.feng.framework.ycnweapp.response.ResGrade;
import com.feng.framework.ycnweapp.response.ResGradeClbum;
import com.feng.ycnweapp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClockService
 * @Author 小风谷
 * @Date 2021/3/16 9:18
 * @Version 1.0
 * @Description
 */
@Service
public class ClockService  {



    @Autowired
    public CourseRepository courseRepository;
    @Autowired
    public GradeRepository gradeRepository;

    @Autowired
    public  ClockDetailsRepository clockDetailsRepository;

    @Autowired
    public ClockDetailsInfoRepository clockDetailsInfoRepository;

    @Autowired
    GradeClbumRepository gradeClbumRepository;



    //创建打卡
    public AjaxResult createClock(ReCreateClockInfo reCreateClockInfo){
        System.out.println("服务器收到的数据:"+reCreateClockInfo);
        AjaxResult ajaxResult = new AjaxResult();
        ClockDetails clockDetails =new ClockDetails();
        ClockDetailsInfo clockDetailsInfo = new ClockDetailsInfo(); //打卡详情
        clockDetailsInfo.setClassName(reCreateClockInfo.getProject_name());
        String TypeLabel=reCreateClockInfo.getClock_course_name().concat("-").concat(reCreateClockInfo.getType_label());

        clockDetailsInfo.setClockType(TypeLabel);
        clockDetailsInfo.setCreatorId(reCreateClockInfo.getCreator_id());
        clockDetailsInfo.setImgUrl("default_cover_img");
        ClockDetailsInfo clockDetailsInfoData=clockDetailsInfoRepository.save(clockDetailsInfo);

        clockDetails.setUserId(clockDetailsInfoData.getCreatorId());
        clockDetails.setClockDetailsInfoId(clockDetailsInfoData.getId());
        clockDetails.setClassName(clockDetailsInfoData.getClassName());
        clockDetailsRepository.save(clockDetails);

        ajaxResult.setCode(200);
        ajaxResult.setMsg("成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(clockDetailsInfoData.getId());

        return  ajaxResult;

    }



    //获取所有课程
    public AjaxResult getAllCourse(){
        AjaxResult ajaxResult = new AjaxResult();

        List<Course> allCourse=courseRepository.findAll();

        ajaxResult.setCode(200);
        ajaxResult.setMsg("成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(allCourse);

        return  ajaxResult;
    }

    //获取所有班级和年级
    public ResGradeClbum getAllGradeCourse(){
        ResGradeClbum resGradeClbum = new ResGradeClbum();

        List<ResGrade> resGradeList = new ArrayList<>();
        List<ResClbum> resClbumLsit = new ArrayList<>();
      //  List<GradeClbum> allClbum=gradeClbumRepository.findClbumISAll();

        List<Grade> allGrades =gradeRepository.findAll();

        List<GradeClbum> allGrade=gradeClbumRepository.findGradeISAll();

        //System.out.println("allClbum 打印....1"+allClbums );
        //System.out.println("allGrade 打印,,,,1"+allGrade );

        for(Grade grade :allGrades){

            ResGrade resGrade =new ResGrade();
            resGrade.setId(grade.getId());
            resGrade.setGradeName(grade.getGradeName());
            resGrade.setShow(false);
            resGradeList.add(resGrade);

        }

        for(GradeClbum gradeClbum2 :allGrade){
            ResClbum reGrade=new ResClbum();
            reGrade.setId(gradeClbum2.getId());
            reGrade.setClbumName(gradeClbum2.getGradeName());
            reGrade.setChoose(true);
            reGrade.setPareadId(gradeClbum2.getPareadId());
            resClbumLsit.add(reGrade);
            //System.out.println("allGrade 打印2"+ resGradeList);
        }

        resGradeClbum.setListClbum(resClbumLsit);
        resGradeClbum.setLsitGrade(resGradeList);
       // System.out.println("resGradeClbum 打印3"+ resGradeList);
        //System.out.println("allGrade 打印2"+reGradeList );
        resGradeClbum.setSuccess(true);
        resGradeClbum.setCode(200);
        resGradeClbum.setMsg("获取所有班级数据成功");

        return resGradeClbum ;
    }



}
