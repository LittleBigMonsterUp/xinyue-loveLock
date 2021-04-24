package com.feng.ycnweapp.service;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.*;
import com.feng.framework.ycnweapp.request.ReClockDiaryInfo;
import com.feng.framework.ycnweapp.request.ReClockPage;
import com.feng.framework.ycnweapp.request.ReClockProject;
import com.feng.framework.ycnweapp.request.ReCreateDiaryInfo;
import com.feng.framework.ycnweapp.response.*;
import com.feng.ycnweapp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ClockInfoService
 * @Author 小风谷
 * @Date 2021/3/20 22:46
 * @Version 1.0
 * @Description
 */
@Service
public class ClockDetailService {


    @Autowired
    public ClockDetailsInfoRepository clockDetailsInfoRepository;

    @Autowired
    public ClockDetailsRepository clockDetailsRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ClockDiaryRepository clockDiaryRepository;

    @Autowired
    public GradeRepository gradeRepository;

    @Autowired
    public ClockDiarysRepository  clockDiarysRepository;



    //检查用户
    public AjaxResult checkUser(ReClockProject reClockProject) {

        System.out.println("收到的" + reClockProject);
        AjaxResult ajaxResult = new AjaxResult();
        // Long userId= clockDetailsInfoRepository.findByCreatorId(ReClockProject.getUserId());
        ClockDetails clockDetails = clockDetailsRepository.findByProjectIdUserId(reClockProject.getProjectId(), reClockProject.getUserId());
        if (clockDetails == null) {
            ResCheck check = new ResCheck();
            check.setCheckUserIsAttendRes("flase");
            ajaxResult.setCode(201);
            ajaxResult.setMsg("此用户未参与该项打卡");
            ajaxResult.setSuccess(false);
            ajaxResult.setData(check);
            return ajaxResult;
        }

        ResCheck check = new ResCheck();
        check.setCheckUserIsAttendRes("true");
        ajaxResult.setCode(200);
        ajaxResult.setMsg("成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(check);
        return ajaxResult;

    }


    //获取打卡的基本信息
    public AjaxResult getProjectInfo(ReClockProject reClockProject) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ResClockInfo> resClockInfoList = new ArrayList<>();
        ResClockInfo resClockInfo = new ResClockInfo();
        ResClockDetailsInfo resClockDetailsInfo = new ResClockDetailsInfo();
        List<ResAttendUserInfo> resAttendUserInfo = new ArrayList<ResAttendUserInfo>();

        Long userId = clockDetailsInfoRepository.findByCreatorId(reClockProject.getUserId());
        ClockDetailsInfo clockDiarys = clockDetailsInfoRepository.findByProjectId(reClockProject.getProjectId());
        if (userId == null && clockDiarys == null) {
            ResCheck check = new ResCheck();
            check.setCheckUserIsAttendRes("flase");
            ajaxResult.setCode(201);
            ajaxResult.setMsg("获取打卡的基本信息失败");
            ajaxResult.setSuccess(false);
            ajaxResult.setData(check);
            return ajaxResult;
        }
        resClockInfo.setType("1");
        resClockInfo.setContent("你好我是李换英");
        resClockInfo.setId("1");
        resClockInfoList.add(resClockInfo);

        resClockDetailsInfo.setClassName(clockDiarys.getClassName());
        resClockDetailsInfo.setImgUrl("default_cover_img");
        resClockDetailsInfo.setIntrInfoList(resClockInfoList);
        resClockDetailsInfo.setAllPunchCardNum(clockDiarys.getAllPunchCardNum());
        resClockDetailsInfo.setAttendUserNum(clockDiarys.getAttendUserNum());
        resClockDetailsInfo.setCreatorId(clockDiarys.getCreatorId());
        resClockDetailsInfo.setNickName(clockDiarys.getNickName());
        resClockDetailsInfo.setSex(clockDiarys.getSex());
        resClockDetailsInfo.setIntroduce(clockDiarys.getIntroduce());
        resClockDetailsInfo.setWeChatNum(clockDiarys.getWeChatNum());
        resClockDetailsInfo.setAvatarUrl("default_avatar");

        List<ClockDiarys> clockDetails = clockDiarysRepository.findAll();
        for (ClockDiarys c : clockDetails) {
            if(c.getClockDetailsInfoId().equals(reClockProject.getProjectId())) {
                ResAttendUserInfo resAttendUserInfo1 = new ResAttendUserInfo();
                User userinfo = userRepository.findByUserId(c.getUserId());
                System.out.println(userinfo);
                resAttendUserInfo1.setId(c.getUserId());
                resAttendUserInfo1.setNick_name(userinfo.getUName());
                resAttendUserInfo1.setAvatar_url(userinfo.getAvatarUrl());
                resAttendUserInfo1.setCount(c.getClockCount());
                resAttendUserInfo.add(resAttendUserInfo1);
            }
        }
        Collections.sort(resAttendUserInfo,new Comparator<ResAttendUserInfo>(){
            @Override
            public int compare(ResAttendUserInfo o1, ResAttendUserInfo o2) {
                int i =o2.getCount().intValue()-o1.getCount().intValue();
                if(i==0){
                   return o2.getId().intValue()-o1.getId().intValue();
                }
                return i;
            }
        });
        resClockDetailsInfo.setAttendUserInfo(resAttendUserInfo);

        ajaxResult.setCode(200);
        ajaxResult.setMsg("成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(resClockDetailsInfo);
        return ajaxResult;
    }

    /**
     * 加入打卡圈子
     */
    public AjaxResult joinInProject(ReClockProject reClockProject) {
        System.out.println("收到的" + reClockProject);
        AjaxResult ajaxResult = new AjaxResult();

        ClockDetails clockDetails = new ClockDetails();
        clockDetails.setUserId(reClockProject.getUserId());
        clockDetails.setClockDetailsInfoId(reClockProject.getProjectId());
        User user = userRepository.findByUserId(reClockProject.getUserId());
        if (clockDetails == null) {
            ajaxResult.setCode(201);
            ajaxResult.setMsg("没有此用户");
            ajaxResult.setSuccess(true);
            ajaxResult.setData(null);
        }
        clockDetails.setNickName(user.getUName());
        clockDetails.setAvatarUrl(user.getAvatarUrl());
        clockDetails.setSex(Long.parseLong(user.getSex()));

        clockDetailsRepository.save(clockDetails);
        ajaxResult.setCode(200);
        ajaxResult.setMsg("成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(null);
        return ajaxResult;

    }


    //更新表的名字
    public AjaxResult upProjectName(Map<String, Object> map) {

        System.out.println(map);
        AjaxResult ajaxResult = new AjaxResult();
        ResCheck check = new ResCheck();
        check.setCheckUserIsAttendRes("true");
        ajaxResult.setCode(200);
        ajaxResult.setMsg("成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(check);
        return ajaxResult;

    }


    /**
     * 获取项目列表  分页查询
     *
     * @param
     * @return
     */
    public AjaxResult getProjectInfoList(ReClockPage reClockPage) {
        AjaxResult ajaxResult = new AjaxResult();


        System.out.println("服务器: reClockPage 收到的数据" + reClockPage);

        //分页参数
        Long page = reClockPage.getNextPage();//从0开始
        //每页的数据条数
        Long size = reClockPage.getDataNum();
        if (page > 0) {
            page = page - 1;
        }
        if (page < 0) {
            page = Long.parseLong(String.valueOf(1));
        }
        if (size <= 0) {
            size = Long.parseLong(String.valueOf(8));
        }
        Long count = null;//页面中条数
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue());
        //List<ResNewsInfo> data = new ArrayList<ResNewsInfo>();
        Page<ClockDetailsInfo> alls = clockDetailsInfoRepository.findAll(pageable);
        //List<NewsInfo> all = cmsNewsInfoRepository.findAll();
        //queryResult.setData(alls.getContent());//数据列表
        //queryResult.setTotal(alls.getTotalElements());//数据总记录数
        if (alls.getContent() == null) {
            count = Long.parseLong(String.valueOf(0));
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("获取打卡项列表失败");
            ajaxResult.setCode(201);
            ajaxResult.setData(null);
            return ajaxResult;
        }
        List<ResClockList> data = new ArrayList<ResClockList>();
        for (ClockDetailsInfo a : alls.getContent()) {
            System.out.println("数据库 ClockDetailsInfo 的值" + a);
            ResClockList resClockList = new ResClockList();
            resClockList.id = a.getId();
            resClockList.project_name = a.getClassName();
            resClockList.cover_img_url = a.getImgUrl();
            resClockList.all_punch_card_num = a.getAllPunchCardNum();
            resClockList.attend_user_num = a.getAttendUserNum();
            data.add(resClockList);
            System.out.println(" data 集合 的值" + data);
        }
        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("获取打卡项列表成功");
        ajaxResult.setCode(200);
        ajaxResult.setData(data);
        return ajaxResult;
    }


    //获取已经创建的项目列表
    public AjaxResult getAlreadyProjectInfoList(ReClockPage reClockPage) {
        AjaxResult ajaxResult = new AjaxResult();


        System.out.println("服务器: reClockPage 收到的数据" + reClockPage);

        //分页参数
        Long page = reClockPage.getNextPage();//从0开始
        //每页的数据条数
        Long size = new Long(100);
        if (page > 0) {
            page = page - 1;
        }
        if (page < 0) {
            page = Long.parseLong(String.valueOf(1));
        }
        if (size <= 0) {
            size = Long.parseLong(String.valueOf(8));
        }
        Long count = null;//页面中条数
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue());
        //List<ResNewsInfo> data = new ArrayList<ResNewsInfo>();
        Page<ClockDetailsInfo> alls = clockDetailsInfoRepository.findAll(pageable);
        //List<NewsInfo> all = cmsNewsInfoRepository.findAll();
        //queryResult.setData(alls.getContent());//数据列表
        //queryResult.setTotal(alls.getTotalElements());//数据总记录数
        if (alls.getContent() == null) {
            count = Long.parseLong(String.valueOf(0));
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("获取打卡项列表失败");
            ajaxResult.setCode(201);
            ajaxResult.setData(null);
            return ajaxResult;
        }
        List<ResClockList> data = new ArrayList<>();
        for (ClockDetailsInfo a : alls.getContent()) {
            System.out.println("数据库 ClockDetailsInfo 的值" + a);
            if (a.getCreatorId().equals(reClockPage.getUserId())) {
                ResClockList resClockList = new ResClockList();
                resClockList.id = a.getId();
                resClockList.project_name = a.getClassName();
                resClockList.cover_img_url = a.getImgUrl();
                resClockList.all_punch_card_num = a.getAllPunchCardNum();
                resClockList.attend_user_num = a.getAttendUserNum();
                data.add(resClockList);
                System.out.println(" data 集合 的值" + data);
            }
        }

        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("获取打卡项列表成功");
        ajaxResult.setCode(200);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 获取用户参加的打卡列表
     */
    public AjaxResult getAlreadyJoinInProjectList(ReClockPage reClockPage) {
        AjaxResult ajaxResult = new AjaxResult();


        System.out.println("服务器: reClockPage 收到的数据" + reClockPage);

        //分页参数
        Long page = reClockPage.getNextPage();//从0开始
        //每页的数据条数
        Long size = new Long(100);
        if (page > 0) {
            page = page - 1;
        }
        if (page < 0) {
            page = Long.parseLong(String.valueOf(1));
        }
        if (size <= 0) {
            size = Long.parseLong(String.valueOf(8));
        }
        Long count = null;//页面中条数
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue());
        //List<ResNewsInfo> data = new ArrayList<ResNewsInfo>();
        Page<ClockDetailsInfo> alls = clockDetailsInfoRepository.findAll(pageable);
        //List<NewsInfo> all = cmsNewsInfoRepository.findAll();
        //queryResult.setData(alls.getContent());//数据列表
        //queryResult.setTotal(alls.getTotalElements());//数据总记录数
        if (alls.getContent() == null) {
            count = Long.parseLong(String.valueOf(0));
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("获取打卡项列表失败");
            ajaxResult.setCode(201);
            ajaxResult.setData(null);
            return ajaxResult;
        }
        List<ResClockList> data = new ArrayList<>();

        List<ClockDetails> clockDiaryList = clockDetailsRepository.findAll();

        for (ClockDetailsInfo a : alls.getContent()) {
            //System.out.println("数据库 ClockDetailsInfo 的值"+a);
            for (ClockDetails c : clockDiaryList) {
                if (c.getUserId().equals(reClockPage.getUserId()) && a.getId().equals(c.getClockDetailsInfoId())) {
                    ResClockList resClockList = new ResClockList();
                    resClockList.id = a.getCreatorId();
                    resClockList.project_name = a.getClassName();
                    resClockList.cover_img_url = a.getImgUrl();
                    resClockList.all_punch_card_num = a.getAllPunchCardNum();
                    resClockList.attend_user_num = a.getAttendUserNum();
                    data.add(resClockList);
                    System.out.println(" data 集合 的值" + data);
                }
            }
        }

        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("获取打卡项列表成功");
        ajaxResult.setCode(200);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 获取打卡 所有子标签
     */
    public AjaxResult getChildrenLabel(ReClockDiaryInfo reClockDiaryInfo) {
        System.out.println("收到的reClockDiaryInfo" + reClockDiaryInfo);
        AjaxResult ajaxResult = new AjaxResult();
        List<ClockDetailsInfo> clockDetailsInfoList = clockDetailsInfoRepository.findAll();
        List<ResClockDetails> resClockDetailsList = new ArrayList<>();

        List<Grade> gradeList = gradeRepository.findAll();
        // for(ClockDetailsInfo c:clockDetailsInfoList){
        //     String typeName=c.getClockType();
        //     StringTokenizer st =new StringTokenizer(typeName,"-");
        //     String[] split= typeName.split("-");
        //     String typeNames=split[1];
        //
        //     // if(split !=null && split.length !=0){
        //     //     for (int i=0; i<2;i++){
        //     //         typeNames=split[1];
        //     //     }
        //     // }
        //
        //    //System.out.println("typeNames"+typeNames);
        //      if(reClockDiaryInfo.getParentLabelName().equals(split[0])) {
        //
        //      }
        for (Grade g : gradeList) {
            ResClockDetails resClockDetails = new ResClockDetails();
            resClockDetails.setLabel_name(g.getGradeName());

            resClockDetailsList.add(resClockDetails);
        }

        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("所有子标签表成功");
        ajaxResult.setCode(200);
        ajaxResult.setData(resClockDetailsList);
        return ajaxResult;

    }


    /**
     * 根据用户选择的打卡圈子类型，分页获取对应类型的打卡圈子列表
     */
    public AjaxResult getProjectListByType(ReClockPage reClockPage) {
        AjaxResult ajaxResult = new AjaxResult();


        System.out.println("服务器: reClockPage 收到的数据" + reClockPage);

        //分页参数
        Long page = reClockPage.getPageNo();//从0开始
        //每页的数据条数
        Long size = reClockPage.getPageSize();
        if (page > 0) {
            page = page - 1;
        }
        if (page < 0) {
            page = Long.parseLong(String.valueOf(1));
        }
        if (size <= 0) {
            size = Long.parseLong(String.valueOf(8));
        }
        Long count = null;//页面中条数
        Pageable pageable = PageRequest.of(page.intValue(), size.intValue());
        //List<ResNewsInfo> data = new ArrayList<ResNewsInfo>();
        Page<ClockDetailsInfo> alls = clockDetailsInfoRepository.findAll(pageable);
        //List<NewsInfo> all = cmsNewsInfoRepository.findAll();
        //queryResult.setData(alls.getContent());//数据列表
        //queryResult.setTotal(alls.getTotalElements());//数据总记录数
        if (alls.getContent() == null) {
            count = Long.parseLong(String.valueOf(0));
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("获取打卡项列表失败");
            ajaxResult.setCode(201);
            ajaxResult.setData(null);
            return ajaxResult;
        }
        List<ResClockList> data = new ArrayList<>();

        List<Grade> gradeList = gradeRepository.findAll();

        for (ClockDetailsInfo a : alls.getContent()) {

            String typeName = a.getClockType();
            String[] split = typeName.split("-");
            String labelName = split[0];
            String typeNames = split[1];
            if (labelName.equals(reClockPage.getLabelName()) && labelName.equals(reClockPage.getTypeName())) {
                ResClockList resClockList = new ResClockList();
                resClockList.id = a.getId();
                resClockList.project_name = a.getClassName();
                resClockList.cover_img_url = a.getImgUrl();
                resClockList.all_punch_card_num = a.getAllPunchCardNum();
                resClockList.attend_user_num = a.getAttendUserNum();
                data.add(resClockList);
                System.out.println(" data 集合 的值" + data);
            } else if (labelName.equals(reClockPage.getLabelName()) && typeNames.equals(reClockPage.getTypeName())) {
                ResClockList resClockList = new ResClockList();
                resClockList.id = a.getId();
                resClockList.project_name = a.getClassName();
                resClockList.cover_img_url = a.getImgUrl();
                resClockList.all_punch_card_num = a.getAllPunchCardNum();
                resClockList.attend_user_num = a.getAttendUserNum();
                data.add(resClockList);
                System.out.println(" data 集合 的值" + data);
            }
        }


        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("获取打卡项列表成功");
        ajaxResult.setCode(200);
        ajaxResult.setData(data);
        return ajaxResult;

    }


}
