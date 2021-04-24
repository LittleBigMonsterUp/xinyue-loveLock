// pages/createPunchCardProject/stepOne/index.js

let app = getApp();
import {
    $wuxSelect
} from '../../../_wux/index'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        user_id: 0, // 用户id
        privacy_type: 0, // 打卡圈子隐私类型，默认为0--公开
        btn_disable: true, // 禁用下一步按钮直至数据验证通过
        parentLabel: '',
        options: [],
        value1: '',
        title1: ''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function () {
        let that = this;
        //   that.setData({
        //       user_id: app.globalData.userInfo.id
        //   });

        //加载标签
        this.getAllCourseLabel(function (parentLabel) {
            console.log("运行了回调函数")
            // 设置当前父级标签的子级标签全部隐藏 && 当前父级标签下被选中的子级标签字符串为空，即皆未被选中
            // for (let i = 0 ; i< parentLabel.length;i++) {
            //     parentLabel[i].show = true;
            //     parentLabel[i].chooseLabelNameStr = "";
            // }
            for (let i = 0; i < parentLabel.length; i++) {
               this.data.options[i].courseName= parentLabel.courseName;
            }
            that.setData({
                options: parentLabel,
                currId: -1
            });
        });


    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },
    // 动态获取课程的所有标签
    getAllCourseLabel: function () {
        let that = this;
        wx.request({
            // url: app.globalData.urlRootPath +
            // "Admin/ProjectTypeLabelManage/getAllTypeLabel",
            url: "http://localhost:31005/Clock/getAllCourse",
            method: 'GET',
            success: function (response) {
                console.log("getAllCourse 接口返回", response.data.data);
                let parentLabel = response.data.data;
                console.log("parentLabel 数据",parentLabel);
                let letoption=[];
                for (let i = 0; i < parentLabel.length; i++) {
                    letoption[i]=parentLabel[i].courseName;
                   //this.data.options[i]= parentLabel[i].courseName;
                    console.log(letoption[i]);
                }
                that.setData({
                options: letoption,
                currId: -1
            });
                // console.log("optio的值", options)
                //    let childLabel  = response.data.data.childLabel;
                // 成功后进行回调将获取的数据赋值到data当中
                return "function" === typeof callback &&
                    callback(parentLabel);
            }
        });
    },
    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    // 监听单选按钮
    radioChange: function (e) {
        let that = this;
        that.setData({
            privacy_type: parseInt(e.detail.value)
        });
        console.log(e.detail.value);
    },


    //  输入框失去焦点检测输入的数据正确性，并根据检测结果控制下一步按钮
    checkInput: function (e) {
        let that = this;
        // 检测是否为十五个汉字以内的字符
        let reg = /^[\u4E00-\u9FA5a-zA-Z0-9_]{1,20}$/;
        console.log("e.detail.value",e.detail.value)
        if(e.detail.value!=''){
        if (reg.test(e.detail.value)) {
            console.log("that.data.title1",that.data.value1)
           // if(that.data.value1 !=null && that.data.value1 !=''){

            that.setData({
                btn_disable: true,
                project_name: e.detail.value
            })
        //}

        } else {
            this.setData({
                btn_disable: false
            });
        
            let title = "圈子名称格式错误!";
            if (e.detail.value.length > 15)
                title = "圈子名称限制十五字符!";

            if (e.detail.value.length === 0)
                title = "请填写圈子名称";
            wx.showToast({
                title: title,
                icon: "none"
            })
        }
    }
    },

    // 下一步按钮点击事件
    intoSetTwo: function () {
        let project_name = this.data.project_name;
        let privacy_type = this.data.value1;
        wx.navigateTo({
            url: '../stepTwo/index' +
                '?project_name=' + project_name +
                '&privacy_type=' + privacy_type

        })
    },
    onClick1:function() {
        let that=this;
        console.log("options 数据",this.data.options)
        $wuxSelect('#wux-select1').open({
            value: this.data.value1,
            options: this.data.options,
            onConfirm: (value, index, options) => {
                console.log('onConfirm',  value )
                if (index !== -1) {
                    this.setData({
                        value1: value,
                        title1: options[index],
                        btn_disable: false,
                    })
                }
            },

        })
    }


});