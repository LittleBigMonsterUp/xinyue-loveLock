let app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        token: wx.getStorageSync("loginFlag"),
        userInfo:'',
        //判断小程序的API，回调，参数，组件等是否在当前版本可用。
        canIUse: wx.canIUse("button.open-type.getUserInfo"),
        // 是否登录，根据后台返回的token判断
        hasLogin: wx.getStorageSync("loginFlag") ? true : false,
        // 终端的屏幕宽度
        windowWidth: app.globalData.windowWidth,
        // 计算出日记2张图片以上时图片显示的长、宽度 (15为margin-left\right 5为图片与图片之间的间距)
        diaryImgWidth: Math.floor((app.globalData.windowWidth - (15 * 2 + 5 * 3)) / 3),

        recommendDiaryList: [
            // 属性值说明
            // {
            //     id: '打卡日记记录id',
            //     text_content: '打卡日记文本内容',
            //     punch_card_time: '打卡时间',
            //     punch_card_address: '打卡地理位置信息',
            //     address_longitude: '经度',
            //     address_latitude: '纬度',
            //     like_user_num: 点赞总人数
            //     comment_num: 评论总数
            //     haveLike: 当前小程序使用者对本条日记的点赞情况 true已点赞 false未点赞
            //     likeRecordId: 当前小程序用户对本条打卡日记点赞记录的ID号
            //     punchCardProject: {
            //         id: 0,// 日记所属的打卡圈子的圈子编号
            //         project_name:'圈子名称',
            //         cover_img_url: '圈子封面图片url'
            //     },
            //     publisher: {
            //         id: 0,// 日记发表者userId
            //         sex:'0--未知 1--男性 2--女性',
            //         nick_name:'',
            //         avatar_url: ''
            //     },
            //     diaryResource:{
            //         id: '打卡日记相关的资源文件记录id',
            //         resource_url: '资源文件路径信息',
            //         type: '1-图片 2-音频 3-视频'
            //     },
            //     recentThreeAttendUserList: {
            //         avatar_url: ''
            //     }
            // }
        ], // 推荐的打卡日记数据列表

    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function () {
        let that = this;
        // 关闭本页面右上角的转发按钮 想要转发只能通过button实现
        // wx.hideShareMenu();
        // wx.showLoading({
        //     title:'加载中...'
        // });
        // that.data.userInfo=wx.getStorageSync("userInfo");
         that.data.userInfo=app.globalData.userInfo
        console.log("onLoad that.data.userInfo11111",that.data.userInfo.nick_name)
       
        // if (that.data.userInfo.id) {
        //     console.log("that.data.userInfo.nick_name",that.data.userInfo.nick_name);
        //     console.log("that.data.userInfo.avatar_url",that.data.userInfo.avatar_url);
        //     this.setData({
        //         hasLogin: true,
        //     });

        // } else {
        //     this.setData({
        //         hasLogin: false,
        //     });
        //  }

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {
        // 获取动画实例 用于在切换导航栏功能的时候执行对应的动画
        this.animation = wx.createAnimation({
            duration: 400
        });
    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        let that = this;
        that.data.userInfo=app.globalData.userInfo
        console.log("onshow that.data.userInfo",that.data.userInfo.id)
        if (that.data.userInfo.id) {
            this.setData({
                userInfo:app.globalData.userInfo,
                hasLogin: true,
            });

        } else {
            this.setData({
                hasLogin: false,
            });
         }
    },
    gotoJoin:function(){
        wx.navigateTo({
            url: '../../pages/partDiaryDetail/index'
        })
    },
    //已创建的打卡
    gotoList:function(){
        wx.navigateTo({
            url: '../../pages/createdPunchCard/index'
        })
    },
    //创建新打卡
    gotoCreate:function(){
        wx.navigateTo({
          url: '../../pages/createPunchCardProject/stepOne/index',
        })
    },
    /**
     *用户授权登录
     */
    intoLoginAuth: function () {
        wx.navigateTo({
            url: '../../pages/loginAuth/index'
        })
    },

});