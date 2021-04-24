let app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        typeName: '',
        parentLabelName: '',
        isRecommend: false,

        punchCardProjectLists: [],
        pageNo: 1, // 打卡圈子列表数据页码
        pageSize: 8, // 打卡圈子列表数据页每一页条数
        showGetDataLoading: false, // 获取数据加载动画
        showMoreDataLoading: false, // 上拉加载更多打卡圈子数据的加载动画
        notMoreData: false, // 打卡圈子列表数据已全部加载标志

        childrenLabelList: [], // 用户所选打卡圈子父类型的所属子类型标签列表
    },


    onLoad: function (options) {
        let that = this;
        that.data.typeName = options.typeName;
        that.data.parentLabelName = options.typeName;

        // 开启获取打卡圈子列表的加载动画
        that.setData({
            showGetDataLoading: true
        });

        // 3.显示的用户所选择类型的打卡圈子列表(初次加载显示第一页数据)
        that.getProjectListByType(1, that.data.pageSize, function (res) {
            let respData = res.data;
            switch (res.statusCode) {
                case 200:
                    that.data.punchCardProjectLists = [];
                    
                    that.setData({
                        showGetDataLoading: false, // 关闭获取数据加载动画
                        punchCardProjectLists: respData.data,
                        pageNo: 1
                    });
                    console.log("成功拉",that.data.punchCardProjectLists)
                    break;

                    // 暂无该类型的打卡圈子数据
                case 400:
                    wx.showToast({
                        title: '暂无打卡圈子',
                        icon: 'none',
                        duration: 2000
                    });
                    that.setData({
                        showGetDataLoading: false, // 关闭获取数据加载动画
                    });
                    break;

                default:
                    wx.showToast({
                        title: respData.errMsg,
                        icon: 'none',
                        duration: 2000
                    });
                    break;
            }
        })
    },



    onReady: function () {
        let that = this;
        // wx.setNavigationBarTitle({
        //     title: that.data.parentLabelName
        // });
    },

    // 下拉刷新获取新的打卡圈子列表数据（同样是获取第一页数据）
    onPullDownRefresh: function () {
        let that = this;
        // 重新获取最新一页的指定类型的打卡圈子列表
        that.getProjectListByType(1, that.data.pageSize, function (res) {
            let respData = res.data;
            wx.stopPullDownRefresh();
            switch (res.statusCode) {
                case 200:
                    that.data.punchCardProjectLists = []; // 清空原先获取的数据列表
                    that.setData({
                        punchCardProjectLists: respData.data,
                        pageNo: 1,
                        notMoreData: false, // 重置打卡圈子列表数据未加载
                    });
                    wx.showToast({
                        title: '获取新数据成功',
                        icon: 'none',
                        duration: 2000
                    });
                    break;

                    // 暂无该类型的打卡圈子数据
                case 400:
                    wx.showToast({
                        title: '暂无该类型的打卡圈子',
                        icon: 'none',
                        duration: 2000
                    });
                    that.setData({
                        showGetDataLoading: false, // 关闭获取数据加载动画
                    });
                    break;

                default:
                    wx.showToast({
                        title: respData.errMsg,
                        icon: 'none',
                        duration: 2000
                    });
                    break;
            }
        });
    },

    // 下拉加载更多打卡圈子列表数据
    onReachBottom: function () {
        let that = this,
            nextPageNo = that.data.pageNo + 1;

        // 如果打卡圈子列表数据已加载完毕则不再进行请求
        if (that.data.notMoreData === true)
            return false;

        // 若第一页（onload的时候已获取）都没有数据则说明该类型暂无打卡圈子数据，不用进行请求获取数据
        if (that.data.pageNo === 1 && that.data.punchCardProjectLists.length <= 0)
            return false;

        // 显示加载下一页数据的加载动画
        that.setData({
            showMoreDataLoading: true
        });

        // 加载更多对应类型的打卡圈子列表数据
        that.getProjectListByType(nextPageNo, that.data.pageSize, function (res) {
            let respData = res.data;
            switch (res.statusCode) {
                case 200:
                    let length = respData.data.length;
                    // 当前请求页已经没有数据，说明上一页为最后一页数据
                    if (length <= 0) {
                        that.setData({
                            notMoreData: true, // 设置打卡圈子列表数据已经全部加载
                            showMoreDataLoading: false, // 关闭加载更多动画
                            pageNo: nextPageNo - 1, // 设置上一页的页号为尾页页号
                        });

                    } else {
                        // 将当前请求页的数据追加到前面已获取的数据列表中
                        for (let i = 0; i < length; i++) {
                            that.data.punchCardProjectLists[length + i] = respData.data[i];
                        }
                        that.setData({
                            notMoreData: false, // 下一页是否还存在数据未知
                            showMoreDataLoading: false,
                            pageNo: nextPageNo,
                            punchCardProjectLists: that.data.punchCardProjectLists
                        });
                    }
                    break;

                default:
                    wx.showToast({
                        title: respData.errMsg,
                        icon: 'none',
                        duration: 2000
                    });
                    break;
            }
        })
    },

    /**
     * 根据用户选择的打卡圈子类型，分页获取对应类型的打卡圈子列表
     * @param pageNo
     * @param pageSize
     * @param successCallback
     */
    getProjectListByType: function (pageNo, pageSize, successCallback) {
        let that = this;
        wx.request({
            url:'http://localhost:31005/ClockDetail/getAlreadyProjectInfoList',
            method: 'post',
            data: {
                nextPage: pageNo,
                dataNum: pageSize,
                userId:app.globalData.userInfo.id
            },
            success: function (res) {
                console.log("parentLabelName", res)
                // 请求成功后执行对应的函数进行后续处理
                successCallback && successCallback(res);
            },
            fail: function () {
                wx.showToast({
                    title: '网络异常!',
                    icon: 'none',
                    duration: 2000
                });
            }
        });
    },

    // 创建新的打卡圈子按钮点击事件
    createNewPunchCardProject: function () {
        wx.navigateTo({
            url: '/pages/createPunchCardProject/stepOne/index'
        });
    },

});