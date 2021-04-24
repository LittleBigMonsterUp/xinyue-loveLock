App({

  /**
   * 当小程序初始化完成时，会触发 onLaunch（全局只触发一次）
   */
  onLaunch: function () {

    let that = this;
    wx.getStorage({
        key: 'userInfo',
        success(res) {
          console.log('获取缓存用户为👇')
          console.log(res.data)
          that.globalData.userInfo = res.data
        },
        fail(res) {
          console.log('未缓存用户')
        }
      }),
      // 获取设备的相关信息
      wx.getSystemInfo({
        success: function (res) {
          that.globalData.windowWidth = res.windowWidth;
          that.globalData.windowHeight = res.windowHeight;
          that.globalData.pixelRatio = res.pixelRatio;
        },
        fail: function (res) {
          console.log(res);
        }
      })


  },

  /**
   * 当小程序启动，或从后台进入前台显示，会触发 onShow
   */
  onShow: function (options) {},

  /**
   * 当小程序从前台进入后台，会触发 onHide
   */
  onHide: function () {

  },

  /**
   * 当小程序发生脚本错误，或者 api 调用失败时，会触发 onError 并带上错误信息
   */
  onError: function (msg) {

  },

  // 检查本地 storage 中是否有登录态标识
  checkLoginStatus: function () {
    let that = this;
    let loginFlag = wx.getStorageSync("loginFlag");
    if (loginFlag) {
      // 检查 session_key 是否过期
      wx.checkSession({
        // session_key 有效(为过期)
        success: function () {
          // 直接从Storage中获取用户信息
          let userStorageInfo = wx.getStorageSync("userInfo");

          if (userStorageInfo) {
            that.globalData.userInfo = userStorageInfo;
          } else {
            that.showInfo("缓存信息缺失");
            console.error(
              "登录成功后将用户信息存在Storage的userStorageInfo字段中，该字段丢失"
            );
          }
        },
        // session_key 过期
        fail: function () {
          // session_key过期
          that.doLogin();
        },
      });
    } else {
      // 无登录态
      that.doLogin();
    }
  },

  // 公共登录动作
  doLogin: function (callback) {
    let that = this;
    console.log("app.js globalData", that.globalData.userInfo);
    wx.login({
      success: function (loginRes) {
        console.log(loginRes, "loginRes");

        if (loginRes.code) {
          /*
           * @desc: 获取用户信息 期望数据如下
           *
           * @param: userInfo       [Object]
           * @param: rawData        [String]
           * @param: signature      [String]
           * @param: encryptedData  [String]
           * @param: iv             [String]
           **/
          wx.getUserInfo({
            //withCredentials: true, // 非必填, 默认为true
            success: function (infoRes) {
              console.log("infoRes:", infoRes);
              console.log("that.globalData.userInfo1111:", that.globalData.userInfo);
              // 请求服务端的登录接口
              wx.request({
                url: "http://localhost:31005/ClockUser/WechatLogin",
                method: "POST",
                data: {
                  authType: 1, //1代表微信端登录
                  nickName: that.globalData.userInfo.nick_name,
                  gender: that.globalData.userInfo.gender,
                  avatarUrl: that.globalData.userInfo.avatar_url,
                  password: "",
                  code: loginRes.code, // 临时登录凭证
                  rawData: that.rawData, // 用户非敏感信息
                  signature: infoRes.signature, // 签名
                  encryptedData: infoRes.encryptedData, // 用户敏感信息
                  iv: infoRes.iv, // 解密算法的向量
                  token: wx.getStorageSync("loginFlag"),
                },

                success: function (res) {
                  console.log("login success:", res);
                  res = res.data;
                  if (res.success) {
                    that.globalData.userInfo.id = res.data.userInfo.id;
                    that.globalData.userInfo.nick_name = res.data.userInfo.uname;
                    that.globalData.userInfo.gender = res.data.userInfo.sex;
                    that.globalData.userInfo.sex = res.data.userInfo.sex;
                    that.globalData.userInfo.avatar_url = res.data.userInfo.avatarUrl;
                    console.log(
                      "globalData.userInfo2222",
                      that.globalData.userInfo
                    );
                    wx.setStorageSync("userInfo", that.globalData.userInfo);
                    wx.setStorageSync("loginFlag", res.data.token); //存储token
                    if (callback) {
                      callback();
                    }
                  } else {
                    that.showInfo(res.message);
                  }
                },
                fail: function (error) {
                  // 调用服务端登录接口失败
                  that.showInfo("调用接口失败");
                  console.log(error);
                },
              });
            },

            fail: function (error) {
              console.log(error);
              // 获取 userInfo 失败，去检查是否未开启权限
              wx.hideLoading();
              that.showInfo("调用request接口失败");
              console.log(error);
              wwx.navigateTo({
                url: "/pages/index/index",
              });
            },
          });
        } else {
          // 获取 code 失败
          that.showInfo("登录失败");
          console.log("调用wx.login获取code失败");
        }
      },

      fail: function (error) {
        // 调用 wx.login 接口失败
        that.showInfo("接口调用失败");
        console.log(error);
      },
    });
  },

  // 检查用户信息授权设置
  checkUserInfoPermission: function (callback = () => {}) {
    wx.getSetting({
      success: function (res) {
        console.log(res);
        if (!res.authSetting["scope.userInfo"]) {
          wx.openSetting({
            success: function (authSetting) {
              console.log("success:authSetting：");
              console.log(authSetting);
              callback();
            },
            fail: function (error) {
              console.log("fail:authSetting：");
              console.log(error);
            },
          });
        }
      },
      fail: function (error) {
        console.log(用户信息授权设置);
        console.log(error);
      },
    });
  },

  //切换到tab首页
  switchTheTab: function () {
    console.log("回调函数：callback swithcTheTab");
    wx.switchTab({
      url: "/pages/index/index",
      success: (result) => {
          let page = getCurrentPages().pop()
          if (page == undefined || page == null) {
            return
          }
          page.onLoad()
       // console.log(wx.getStorageSync("userInfo"));
      },
      fail: () => {},
      complete: () => {},
    });
  },

  // 获取用户登录标示 供全局调用
  getLoginFlag: function () {
    return wx.getStorageSync("loginFlag");
  },

  // 封装 wx.showToast 方法
  showInfo: function (info = "error", icon = "none") {
    wx.showToast({
      title: info,
      icon: icon,
      duration: 2000,
      mask: false,
    });
  },


  //全局数据
  globalData: {
    openid: '',
    rawData: [],
    userInfo: {
      id: '', //用户id
      nick_name: '',
      gender: '',
      city: '',
      avatar_url: '',
      sex: 0 // 性别 0-未知，1-男性，2-女性
    },
    urlRootPath: "https://armin-xu.cn/SmallPunchMiniProgramAfterEnd/public/",
    // urlRootPath_local:
    //     "http://myxu.com/EndProject/SmallPunchMiniProgramAfterEnd/public/",
    // urlRootPath: "http://myxu.com/EndProject/SmallPunchMiniProgramAfterEnd/public/",
    // 服务器图片访问BaseURl
    imgBaseSeverUrl: "https://armin-xu.cn/SmallPunchMiniProgramAfterEnd/"
    // imgBaseSeverUrl: "http://myxu.com/EndProject/SmallPunchMiniProgramAfterEnd/"

  }
});