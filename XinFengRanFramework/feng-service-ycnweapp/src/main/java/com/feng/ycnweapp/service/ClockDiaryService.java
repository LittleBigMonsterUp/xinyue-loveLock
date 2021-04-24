package com.feng.ycnweapp.service;

import com.feng.framework.model.response.AjaxResult;
import com.feng.framework.ycnweapp.*;
import com.feng.framework.ycnweapp.request.*;
import com.feng.framework.ycnweapp.response.*;
import com.feng.ycnweapp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ClockDiaryService
 * @Author 小风谷
 * @Date 2021/3/23 22:05
 * @Version 1.0
 * @Description 获取打卡日记详情
 */
@Service
public class ClockDiaryService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClockDetailsInfoRepository clockDetailsInfoRepository;
    @Autowired
    ClockDetailsRepository clockDetailsRepository;

    @Autowired
    ClockDiaryRepository clockDiaryRepository;

    @Autowired
    AllCommentInfoRepository allCommentInfoRepository;

    @Autowired
    ReviewerInfoRepository reviewerInfoRepository;

    @Autowired
    PublisherInfoRepository publisherInfoRepository;

    @Autowired
    DiaryResourceRepository diaryResourceRepository;

    @Autowired
    TenLikeInfoRepository tenLikeInfoRepository;

    @Autowired
    AdmirerRepository admirerRepository;

    @Autowired
    RespondentInfoRepository respondentInfoRepository;


    //创建日记
    public AjaxResult clockCreate(ReCreateDiaryInfo reCreateDiaryInfo) {
        System.out.println("收到的创建打卡日记的数据是" + reCreateDiaryInfo);
        AjaxResult ajaxResult = new AjaxResult();
        Date datenow = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            datenow = df.parse(date);
        } catch (Exception e) {

        }

        ClockDetails clockDetails = new ClockDetails();

        ClockDetails clockDetailData = clockDetailsRepository.findByProjectIdUserId(reCreateDiaryInfo.getProject_id(), reCreateDiaryInfo.getUser_id());
        //打卡次数更新
        if (clockDetailData != null) {
            clockDetailsRepository.updateClockCount(clockDetailData.getId());
        }

        ClockDiary clockDiary = new ClockDiary();
        // AllCommentInfo allCommentInfo =new AllCommentInfo();
        // DiaryResource diaryResource =new DiaryResource();
        // PublisherInfo publisherInfo =new PublisherInfo();
        // TenLikeInfo tenLikeInfo =new TenLikeInfo();

        clockDiary.setUserId(reCreateDiaryInfo.getUser_id());
        clockDiary.setAddressLatitude(reCreateDiaryInfo.getAddress_latitude());
        clockDiary.setAddressLongitude(reCreateDiaryInfo.getAddress_longitude());
        clockDiary.setTextcontent(reCreateDiaryInfo.getText_content());
        clockDiary.setVisibleType(reCreateDiaryInfo.getVisible_type());
        clockDiary.setClockDetailsInfoId(reCreateDiaryInfo.getProject_id());
        clockDiary.setIsRepairDiary(reCreateDiaryInfo.getIs_repair_diary());
        clockDiary.setPunchCardAddress(reCreateDiaryInfo.getPunch_card_address());
        clockDiary.setIsRepairDiary(new Long(0));
        clockDiary.setPunchCardTime(datenow);


        //用作排行clockDetails

        clockDetails.setUserId(reCreateDiaryInfo.getUser_id());
        clockDetails.setClockDetailsInfoId(reCreateDiaryInfo.getProject_id());


        clockDetails.setClockCount(reCreateDiaryInfo.getProject_id());

        ClockDiary clockDiary1 = clockDiaryRepository.save(clockDiary);

        Long diaryId = clockDiary1.getId();
        //  allCommentInfo.setDiaryId(diaryId);
        //  diaryResource.setDiaryId(diaryId);
        //  publisherInfo.setDiaryId(diaryId);
        //  tenLikeInfo.setDiaryId(diaryId);
        //  AllCommentInfo allCommentInfo1= allCommentInfoRepository.save(allCommentInfo);
        //  DiaryResource diaryResource1= diaryResourceRepository.save(diaryResource);
        //  PublisherInfo publisherInfo1= publisherInfoRepository.save(publisherInfo);
        //  TenLikeInfo tenLikeInfo1= tenLikeInfoRepository.save(tenLikeInfo);
        //
        // ClockDiary clockDiary2 = clockDiaryRepository.findclockDiaryId(diaryId);
        // if(clockDiary2==null){
        //     ajaxResult.setCode(201);
        //     ajaxResult.setMsg("创建错误");
        //     ajaxResult.setSuccess(true);
        //     ajaxResult.setData(diaryId);
        //     return  ajaxResult;
        // }
        // clockDiary2.setAllCommentInfoId(allCommentInfo1.getId());
        // clockDiary2.setDiaryResourceId(diaryResource1.getId());
        // clockDiary2.setDiaryResourceId(publisherInfo1.getId());
        // clockDiary2.setTenLikeInfoId(tenLikeInfo1.getId());
        // clockDiaryRepository.save(clockDiary2);
        ajaxResult.setCode(200);
        ajaxResult.setMsg("创建打卡日记成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(diaryId);
        return ajaxResult;

    }


    /**
     * 获取日记列表
     */
    public AjaxResult getClockDiaryInfoList(ReClockDetailPage reClockDetailPage) {

        AjaxResult ajaxResult = new AjaxResult();

        System.out.println("获取打卡列表接收的数据" + reClockDetailPage);
        //分页参数
        Long page = reClockDetailPage.getPageNo();//从0开始
        //每页的数据条数
        Long size = reClockDetailPage.getDataNum();
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
        Page<ClockDiary> clockDiaryList = clockDiaryRepository.findProjectId(reClockDetailPage.getProjectId(), pageable);
        //Page<ClockDetailsInfo> alls = clockDetailsInfoRepository.findAll(pageable);
        if (clockDiaryList.getContent() == null) {
            count = Long.parseLong(String.valueOf(0));
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("获取打卡项列表失败");
            ajaxResult.setCode(201);
            ajaxResult.setData(null);
            return ajaxResult;
        }
        List<ResClockDiary> data = new ArrayList<>();
        for (ClockDiary a : clockDiaryList.getContent()) {
            //System.out.println(a.getNewsCategoryId().getId());
            ResClockDiary resClockDiary = new ResClockDiary();
            ResAllCommentInfo resAllCommentInfo = new ResAllCommentInfo();

            //时间格式化
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(a.getPunchCardTime());

            resClockDiary.setId(a.getId());
            resClockDiary.setPunchCardAddress(a.getPunchCardAddress());
            resClockDiary.setAddressLatitude(a.getAddressLatitude());
            resClockDiary.setAddressLongitude(a.getAddressLongitude());
            resClockDiary.setTextcontent(a.getTextcontent());
            resClockDiary.setPunchCardTime(date);
            resClockDiary.setCurrDiaryPunchCardDayNum(a.getCurrDiaryPunchCardDayNum());

            //评论者
            PublisherInfo publisherInfo = publisherInfoRepository.findpublisherInfoId(a.getPublisherID());
            ResPublisherInfo resPublisherInfo = new ResPublisherInfo();
            if (publisherInfo != null) {
                resPublisherInfo.setId(publisherInfo.getUseId());
                resPublisherInfo.setAvatartUrl(publisherInfo.getAvatartUrl());
                resPublisherInfo.setNickName(publisherInfo.getNickName());
                resPublisherInfo.setSex(publisherInfo.getSex());
            }
            resClockDiary.setPublisher(resPublisherInfo);


            //日记资源
            List<DiaryResource> diaryResourceList = diaryResourceRepository.findAll();
            System.out.println("日记资源" + diaryResourceList);
            List<ResDiaryResource> data2 = new ArrayList<>();
            for (DiaryResource d : diaryResourceList) {
                if(Objects.equals(a.getUserId(), d.getUserId())&&Objects.equals(a.getId(), d.getDiaryId())) {
                    ResDiaryResource resDiaryResource = new ResDiaryResource();
                    resDiaryResource.setId(d.getId());
                    resDiaryResource.setResourceUrl(d.getResourceUrl());
                    resDiaryResource.setType(d.getType());
                    data2.add(resDiaryResource);
                }
            }
            resClockDiary.setDiaryResource(data2);
            resClockDiary.setLikeUserNum(a.getLikeUserNum()); //点赞人数
            resClockDiary.setCommentNum(a.getCommentNum()); //评论人数
            resClockDiary.setHaveLike(a.getHaveLike()); // 当前小程序使用者对本条日记的点赞情况 true已点赞 false未点赞


            //点赞记录
            List<TenLikeInfo> tenLikeInfoList = tenLikeInfoRepository.findByTenLikeInfoId(a.getId());
            for (TenLikeInfo t : tenLikeInfoList) {
                System.out.println("tenLikeInfoList" + t);
            }

            List<ResTenLikeInfo> data3 = new ArrayList<>();
            for (TenLikeInfo t : tenLikeInfoList) {
                ResTenLikeInfo resTenLikeInfo = new ResTenLikeInfo();
                resTenLikeInfo.setId(t.getId());
                //点赞者集合
                Admirer admirer = admirerRepository.findadmirerId(t.getId());

                ResAdmirer resAdmirer = new ResAdmirer();
                resAdmirer.setId(admirer.getId());
                resAdmirer.setNickName(admirer.getNickName());
                resTenLikeInfo.setAdmirer(resAdmirer);
                data3.add(resTenLikeInfo);
            }
            resClockDiary.setTenLikeInfo(data3);

            //该日记的所有评论
            List<AllCommentInfo> allCommentInfoList = allCommentInfoRepository.findByallCommentId(a.getId());
            List<ResAllCommentInfo> data5 = new ArrayList<>();
            for (AllCommentInfo b : allCommentInfoList) {
                ResAllCommentInfo resAllCommentInfos = new ResAllCommentInfo();

                resAllCommentInfos.setId(b.getId());

                resAllCommentInfos.setPid(b.getPid());
                resAllCommentInfos.setCreateTime(b.getCreateTime());
                resAllCommentInfos.setDiaryId(b.getDiaryId());
                resAllCommentInfos.setSoundComment(b.getSoundComment());
                resAllCommentInfos.setTextComment(b.getTextComment());

                //评论者信息
                ReviewerInfo reviewerInfo = reviewerInfoRepository.findreviewerInfoId(b.getId());
                if (reviewerInfo != null) {
                    ResReviewerInfo resReviewerInfo = new ResReviewerInfo();
                    resReviewerInfo.setId(reviewerInfo.getId());
                    resReviewerInfo.setAvatartUrl(reviewerInfo.getNavatartUrl());
                    resReviewerInfo.setNickName(reviewerInfo.getNickName());
                    resReviewerInfo.setSex(reviewerInfo.getSex());
                    resAllCommentInfos.setReviewer(resReviewerInfo);
                }

                //评论回复信息
                RespondentInfo respondentInfo = respondentInfoRepository.findrespondentInfoId(b.getId());
                if (respondentInfo != null) {
                    ResRespondentInfo resRespondentInfo = new ResRespondentInfo();
                    resRespondentInfo.setId(respondentInfo.getId());
                    resRespondentInfo.setNavatartUrl(respondentInfo.getNavatartUrl());
                    resRespondentInfo.setNickName(respondentInfo.getNickName());
                    resRespondentInfo.setSex(respondentInfo.getSex());
                    resAllCommentInfos.setRespondent(resRespondentInfo);


                    data5.add(resAllCommentInfos);
                }
            }
            resClockDiary.setAllCommentInfo(data5);

            data.add(resClockDiary);
        }
        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("获取打卡项列表成功");
        ajaxResult.setCode(200);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 日记置顶
     */
    public AjaxResult clockDiaryInfoStick(Map<String, Object> map) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ClockDetailsInfo> clockDetailsInfoList = clockDetailsInfoRepository.findAll();

        ajaxResult.setCode(200);
        ajaxResult.setMsg("成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(clockDetailsInfoList);
        return ajaxResult;
    }


    /**
     * 日记点赞
     */
    public AjaxResult clockDiaryInfoLick(ReTenLikeInfo reTenLikeInfo) {
        System.out.println("收到的点赞数据:" + reTenLikeInfo);
        AjaxResult ajaxResult = new AjaxResult();
        Admirer admirer = new Admirer();
        ResTenLikeInfo resTenLikeInfo = new ResTenLikeInfo();
        TenLikeInfo tenLikeInfo = new TenLikeInfo();
        tenLikeInfo.setUseId(reTenLikeInfo.getLiked_user_id());
        tenLikeInfo.setAdmirerId(reTenLikeInfo.getAdmirer_id());
        tenLikeInfo.setDiaryId(reTenLikeInfo.getDiary_id());
        User user = userRepository.findByUserId(reTenLikeInfo.getLiked_user_id());
        if (user == null) {
            ajaxResult.setCode(201);
            ajaxResult.setMsg("点赞成功出错");
            ajaxResult.setSuccess(true);
            ajaxResult.setData(null);
            return ajaxResult;
        }
        // System.out.println("user的值"+user);
        admirer.setNickName(user.getUName());
        TenLikeInfo tenLikeInfo1 = tenLikeInfoRepository.save(tenLikeInfo);
        admirer.setTenLikeId(tenLikeInfo.getId());
        Admirer admirer2 = admirerRepository.save(admirer);
        System.out.println(admirer2);
        resTenLikeInfo.setId(tenLikeInfo1.getId());

        ajaxResult.setCode(200);
        ajaxResult.setMsg("点赞成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(resTenLikeInfo);
        return ajaxResult;
    }

    /**
     * 日记取消点赞
     */
    public AjaxResult clockDiaryInfoCancelLike(ReTenLikeInfo reTenLikeInfo) {
        AjaxResult ajaxResult = new AjaxResult();
        System.out.println("取消点赞收到的数据" + reTenLikeInfo);
        ClockDiary clockDiary = clockDiaryRepository.findclockDiaryId(reTenLikeInfo.getDiary_id());
        if (clockDiary != null) {
            tenLikeInfoRepository.deleteById(reTenLikeInfo.getLikeRecordId());
        }

        ajaxResult.setCode(200);
        ajaxResult.setMsg("取消点赞成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(null);
        return ajaxResult;
    }


    /**
     * 新增评论
     */
    public AjaxResult clockDiaryAddComment(ReClockDiaryComment reClockDiaryComment) {

        System.out.println("收到的评论数据:" + reClockDiaryComment);
        AjaxResult ajaxResult = new AjaxResult();

        AllCommentInfo allCommentInfo = new AllCommentInfo();

        Date datenow = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            datenow = df.parse(date);
        } catch (Exception e) {

        }
        User user = new User();
        User user2 = new User();
        //判断是否回复
        if (Objects.equals(reClockDiaryComment.getPid(), 0)) {

            user = userRepository.findByUserId(reClockDiaryComment.getReviewer_id());
            if (user == null) {
                ajaxResult.setCode(201);
                ajaxResult.setMsg("新增评论失败");
                ajaxResult.setSuccess(true);
                ajaxResult.setData(null);
                return ajaxResult;
            }
            user2 = userRepository.findByUserId(reClockDiaryComment.getReviewer_id());
            if (user2 == null) {
                ajaxResult.setCode(201);
                ajaxResult.setMsg("新增评论失败");
                ajaxResult.setSuccess(true);
                ajaxResult.setData(null);
                return ajaxResult;
            }

        } else {

            user = userRepository.findByUserId(reClockDiaryComment.getReviewer_id());
            if (user == null) {
                ajaxResult.setCode(201);
                ajaxResult.setMsg("新增评论失败");
                ajaxResult.setSuccess(true);
                ajaxResult.setData(null);
                return ajaxResult;
            }
            ClockDetails clockDetails = clockDetailsRepository.findByClockDetailsId(reClockDiaryComment.getDiary_id());

            user2 = userRepository.findByUserId(clockDetails.getUserId());
            System.out.println("user2"+user2);
            if (user2 == null) {
                ajaxResult.setCode(201);
                ajaxResult.setMsg("新增评论失败");
                ajaxResult.setSuccess(true);
                ajaxResult.setData(null);
                return ajaxResult;
            }
        }

        allCommentInfo.setDiaryId(reClockDiaryComment.getDiary_id());
        allCommentInfo.setTextComment(reClockDiaryComment.getText_comment());
        allCommentInfo.setPid(reClockDiaryComment.getPid());
        allCommentInfo.setCreateTime(datenow);
        AllCommentInfo allCommentInfo1 = allCommentInfoRepository.save(allCommentInfo);
        Long allCommentInfoId = allCommentInfo1.getId();
        ReviewerInfo reviewerInfo = new ReviewerInfo();

        if (reClockDiaryComment.getReviewer_id() == null && user == null) {
            ajaxResult.setCode(201);
            ajaxResult.setMsg("评论出错");
            ajaxResult.setSuccess(true);
            ajaxResult.setData(null);
            return ajaxResult;
        }

        if (reClockDiaryComment.getRespondent_id() == null && user == null) {
            ajaxResult.setCode(201);
            ajaxResult.setMsg("评论出错");
            ajaxResult.setSuccess(true);
            ajaxResult.setData(null);
            return ajaxResult;
        }
        reviewerInfo.setUserId(reClockDiaryComment.getReviewer_id());
        reviewerInfo.setNickName(user.getUName());
        reviewerInfo.setNavatartUrl(user.getAvatarUrl());
        reviewerInfo.setSex(user.getSex());
        reviewerInfo.setAllCommentId(allCommentInfoId);

        ReviewerInfo reviewerInfo1 = reviewerInfoRepository.save(reviewerInfo);

        RespondentInfo respondentInfo = new RespondentInfo();

        respondentInfo.setUserId(reClockDiaryComment.getReviewer_id());
        respondentInfo.setAllCommentId(allCommentInfoId);
        respondentInfo.setNickName(user2.getUName());
        respondentInfo.setNavatartUrl(user2.getAvatarUrl());
        respondentInfo.setSex(user2.getSex());

        RespondentInfo respondentInfo1 = respondentInfoRepository.save(respondentInfo);

        ResAllCommentInfo resAllCommentInfo = new ResAllCommentInfo();

        resAllCommentInfo.setId(allCommentInfo1.getId());
        resAllCommentInfo.setCreateTime(allCommentInfo1.getCreateTime());
        resAllCommentInfo.setDiaryId(allCommentInfo1.getDiaryId());
        resAllCommentInfo.setPid(allCommentInfo1.getPid());
        resAllCommentInfo.setTextComment(allCommentInfo1.getTextComment());


        ResReviewerInfo resReviewerInfo = new ResReviewerInfo();
        ResRespondentInfo resRespondentInfo = new ResRespondentInfo();
        resReviewerInfo.setId(reviewerInfo1.getId());
        resReviewerInfo.setSex(reviewerInfo1.getSex());
        resReviewerInfo.setNickName(reviewerInfo1.getNickName());
        resReviewerInfo.setAvatartUrl(reviewerInfo1.getNavatartUrl());

        resRespondentInfo.setId(respondentInfo1.getId());
        resRespondentInfo.setSex(respondentInfo1.getSex());
        resRespondentInfo.setNickName(respondentInfo1.getNickName());
        resRespondentInfo.setNavatartUrl(respondentInfo1.getNavatartUrl());


        resAllCommentInfo.setReviewer(resReviewerInfo);
        resAllCommentInfo.setRespondent(resRespondentInfo);


        ajaxResult.setCode(200);
        ajaxResult.setMsg("新增评论成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(resAllCommentInfo);
        return ajaxResult;
    }

    /**
     * 删除评论
     */
    public AjaxResult clockDiaryDeleteComment(ReClockDiaryComment reTenLikeInfo) {
        System.out.println("收到的点赞数据:" + reTenLikeInfo);
        AjaxResult ajaxResult = new AjaxResult();
        Admirer admirer = new Admirer();

        ajaxResult.setCode(200);
        ajaxResult.setMsg("点赞成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(null);
        return ajaxResult;
    }

    //获取用户打卡日历 日期
    public AjaxResult getCalendarDay(ReClockProject reClockProject) {
        System.out.println("收到的打卡日历数据:" + reClockProject);
        AjaxResult ajaxResult = new AjaxResult();

        List<ClockDiary> clockDiaryList = clockDiaryRepository.findAll();

        List<String> date = new ArrayList<>();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");//日期格式
        for (ClockDiary c : clockDiaryList) {
            if (c.getUserId().equals(reClockProject.getUserId()) && c.getClockDetailsInfoId().equals(reClockProject.getProjectId())) {

                String dateStr = new String();

                dateStr = sformat.format(c.getPunchCardTime());
                date.add(dateStr);
            }
        }
        List<String> date2 = new ArrayList<>();
        Iterator<String> iterator = date.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (!date2.contains(str)) {
                date2.add(str);
            }
        }
        ajaxResult.setCode(200);
        ajaxResult.setMsg("获取打开日历成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(date2);
        return ajaxResult;
    }

    //补卡
    public AjaxResult reissueClock(ReCreateDiaryInfo reCreateDiaryInfo) {
        AjaxResult ajaxResult = new AjaxResult();
        Date datenow = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            datenow = df.parse(date);
        } catch (Exception e) {

        }

        ClockDiary clockDiary = new ClockDiary();

        ClockDetails clockDetails = new ClockDetails();

        ClockDetails clockDetailData = clockDetailsRepository.findByProjectIdUserId(reCreateDiaryInfo.getProject_id(), reCreateDiaryInfo.getUser_id());
        //打卡次数更新
        if (clockDetailData != null) {
            clockDetailsRepository.updateClockCount(clockDetailData.getId());
        }

        clockDiary.setUserId(reCreateDiaryInfo.getUser_id());
        clockDiary.setAddressLatitude(reCreateDiaryInfo.getAddress_latitude());
        clockDiary.setAddressLongitude(reCreateDiaryInfo.getAddress_longitude());
        clockDiary.setTextcontent(reCreateDiaryInfo.getText_content());
        clockDiary.setVisibleType(reCreateDiaryInfo.getVisible_type());
        clockDiary.setClockDetailsInfoId(reCreateDiaryInfo.getProject_id());
        clockDiary.setIsRepairDiary(reCreateDiaryInfo.getIs_repair_diary());
        clockDiary.setPunchCardAddress(reCreateDiaryInfo.getPunch_card_address());
        clockDiary.setIsRepairDiary(new Long(1));
        clockDiary.setPunchCardTime(datenow);

        ClockDiary clockDiary1 = clockDiaryRepository.save(clockDiary);

        Long diaryId = clockDiary1.getId();

        ajaxResult.setCode(200);
        ajaxResult.setMsg("补卡成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(diaryId);
        return ajaxResult;
    }

    public AjaxResult getResponseList() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setMsg("补卡成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(null);
        return ajaxResult;
    }

    public AjaxResult getAdmirerList() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setMsg("补卡成功");
        ajaxResult.setSuccess(true);
        ajaxResult.setData(null);
        return ajaxResult;
    }

    /**获取打卡排行榜*/
    // public AjaxResult getRankingList(ReClockProject reClockProject){
    //     AjaxResult ajaxResult = new AjaxResult();
    //     List<ClockDiary> clockDiaryList = clockDiaryRepository.findAll();
    //
    //     ajaxResult.setCode(200);
    //     ajaxResult.setMsg("补卡成功");
    //     ajaxResult.setSuccess(true);
    //     ajaxResult.setData(diaryId);
    //     return ajaxResult;
    //
    // }
}
