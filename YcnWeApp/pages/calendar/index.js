Page({

  /**
   * 页面的初始数据
   */
  data: {
    user_id:"",
    project_id:"",
    selected: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   let that =this;
   that.data.project_id=parseInt(options.projectId);
   that.data.user_id=parseInt(options.userId);
   that.getCalendarDay()
  },

  getCalendarDay:function(){
    let that = this;
    wx.request({
        // url: app.globalData.urlRootPath +
        // "Admin/ProjectTypeLabelManage/getAllTypeLabel",
        url: "http://localhost:31005/ClockDiary/getCalendarDay",
        method: 'post',
        data: {
          userId: that.data.user_id,
          projectId: that.data.project_id
      },
        success: function (response) {
            console.log("getAllCourse 接口返回", response.data.data);
            let parentLabel = response.data.data;
            that.setData({
              selected: parentLabel,
            });
           // console.log("selected",that.data.selected)
        }
    });
  },
  /**
  * 日历是否被打开
  */
  bindselect(e) {
    console.log("dadddd" ,e.detail.ischeck)
  },
  /**
   * 获取选择日期
   */
  bindgetdate(e) {
    let time = e.detail;
    console.log("time",time)
    let obj =new Date();
    let nowyear=obj.getFullYear();
    let nowmonth=obj.getMonth()+1;
    let nowdate=obj.getDate();
   // console.log("year,mouth,date2",date2);
   // console.log("year,mouth,date2",nowdate);
    var flag="确定";
    // console.log("year<nowyear && month<nowmonth && date<nowday",year<nowyear && month<nowmonth && date<nowday?1:0)
   
    
  }
})