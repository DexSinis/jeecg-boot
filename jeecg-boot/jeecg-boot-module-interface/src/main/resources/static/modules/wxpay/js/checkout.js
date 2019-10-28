function jumpVuechat(type, id) {
    debugger;
    var fowordurl = $("#fowordurl").val();
    var customer_id = $("#customer_id").val()
    var backUrl = $("#backUrl").val() // 支付后回跳回来vue的地址
    var leftbackurl = $("#leftbackurl").val() //点击返回按钮调回去的地址
    var consulttype = $("#consulttype").val() //儿科妇科

    var status = $("#status").val() //是否跳转
    debugger;
    var jumpUrl = $("#jumpUrl").val() //跳转收集信息界面
    var renewal = $("#renewal").val() //原来工单ID

    if(status==3){
        var url = encodeURI(jumpUrl)+ "?" + type + "=" + id + "&consulttype=" + consulttype + "&backUrl=" + leftbackurl + "&customerId=" + $("#customer_id").val()
            + "&orderType=" + $("#orderType").val()+ "&renewal=" + renewal;
    }
    else if (renewal>0||backUrl == 'chat') {
        var url = encodeURI(fowordurl) + "?" + type + "=" + id + "&backUrl=" + leftbackurl + "&customerId=" + $("#customer_id").val()+ "&orderType=" + $("#orderType").val()+ "&renewal=" + renewal
    } else {
        window.history.go(-3)
        return false
    }
    window.location.href = url;
}

// 调用微信JS api 支付
var ispaying = false;
var indexk;

function freeorpayJsApiCall() {
    indexk = layer.open({type: 2});

    debugger;
    ispaying = true;
    $.ajax({
        url: "/vue/prePay",
        dataType: "json",
        data: {
            "paytemporderid": $("#paytemporderid").val(),
            "openid": $("#openid").val(),
            "consulterId": $("#consulterId").val(),
            "customer_id": $("#customer_id").val(),
            "label": $("#label").val(),
            "problem": $("#problem").val(),
            "consulte_type": $("#consulte_type").val(),
            "isdefault": $("#isdefault").val(),
            "labelId": $("#labelId").val(),
            "babyId": $("#babyId").val(),
            "flag": $("#flag").val(),
            "instid": $("#instid").val(),
            "bmark": $("#bmark").val(),
            "orderType": $("#orderType").val(),
            "renewal": $("#renewal").val(),
        },
        success: function (data) {
            layer.close(indexk);

            debugger;
            if (data && !data.code) {
                //todo 点击支付之后按钮置灰
                // console.log(data.)
                try{
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest',
                    data,
                    function (res) {
                        if (res.err_msg == "get_brand_wcpay_request:ok") {
                            //支付成功
                            isClick = false
                            var preOrderStr = data.package;
                            var preOrderArr = preOrderStr.split("=");
                            // freeorpayQestion(preOrderArr[1]);

                            // console.log(data.paytemporderid);
                            var paytemporderid = data.paytemporderid;
                            jumpVuechat("paytemporderid", paytemporderid);
                        } else {
                            isClick = false
                            // var paytemporderid = data.paytemporderid;
                            // console.log(paytemporderid);
                            // var preOrderId = data.package.split("=")[1];
                            // console.log(preOrderId);

                            // 关闭订单
                            if (data.package && data.package.indexOf("=") > 0) {
                                var preOrderId = data.package.split("=")[1];
                            }
                            $.ajax({
                                url: "/vue/closePayOrderRela",
                                dataType: "json",
                                data: {
                                    "preOrderStr": preOrderId,
                                    "consulterId": $("#consulterId").val()
                                },
                                success: function (data) {

                                }
                            });
                        }
                    }
                );
                }catch(e){
                    console.log(e)
                    isClick = false
                    // layer.open({
                    //     content: '页面没有初始化完成，请重试。',
                    //     // skin: 'msg',
                    //     time: 3
                    // });
                }
            } else if (data.code && data.code == -3) {
                //测试桩
                var preOrderStr = data.data.prePayId;
                freeorpayQestion(preOrderStr);

            } else if (data.code && data.code == -4) {  //使用了优惠券并且不用付费
                //已经付费但是没有提问的工单
                var paytemporderid = data.data.paytemporderid;
                jumpVuechat("paytemporderid", paytemporderid);

                // freeorpayQestion(preOrderStr);
            } else if (data.code && data.code == -2) {
                //已经付费但是没有提问的工单
                // freeorpayQestion(data.desc);
                var paytemporderid = data.desc;
                jumpVuechat("paytemporderid", paytemporderid);

            } else {
                if (!data.desc) {
                    var msg = "支付未成功";
                } else {
                    var msg = data.desc;
                }

                layer.open({
                    content: msg,
                    // skin: 'msg',
                    time: 2
                });
            }
            setTimeout(function () {
                ispaying = false;
            }, 2000);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (layIndex) {
                layer.close(layIndex);
                isClick = false
            }
            layer.open({
                content: '系统繁忙，请稍后再试',
                // skin: 'msg',
                time: 3
            });
            ispaying = false;
        }
    });


}

//调用H5 支付
function freeorpayJsApiCallH5Web() {
    console.log($("#customer_id").val());
    debugger;
    ispaying = true;
    var instid = $("#instid").val()
    var url = "/vue/prePayH5Web?openid="
        + $("#openid").val()
        + "&consulterId=" + $("#consulterId").val()
        + "&customer_id=" + $("#customer_id").val()
        + "&label=" + $("#label").val()
        + "&problem=" + $("#problem").val()
        + "&consulte_type=" + $("#consulte_type").val()
        + "&isdefault=" + $("#isdefault").val()
        + "&labelId=" + $("#labelId").val()
        + "&babyId=" + $("#babyId").val()
        + "&flag=" + $("#flag").val()
        + "&paytemporderid=" + $("#paytemporderid").val()
        + "&bmark=" + $("#bmark").val()
        + "&appid=" + $("#appid").val()
        + "&orderType=" + $("#orderType").val();

    if (instid != null && instid != undefined) {
        url = url + "&instid=" + $("#instid").val();
    }
    console.log("url======"+url);
    // console.log(url);
    window.location.href = url
}


//送心意支付调用（）
function tipsJsApiCall(goodtype) {
    // console.log(goodtype);
    var cxc = $("#cxc").val();
    // console.log(cxc);
    var orderid = $("#orderid").val();
    // console.log(orderid);
    var content = $("#content").val();
    // console.log(content);
    var fee = 2;
    var feeType = goodtype
    tipscallpay($("#customer_id").val(), feeType, fee, orderid, content);
}

function tipscallpay(customerId, feeType, fee, orderid, content) {


    var customerRoleName = $("#customerRoleName").val();
    var alertMsg = "支付成功后，我们会把心意传达给"+customerRoleName+"哦";
    //color: #999;border: 0.005rem;border-bottom-style: solid;height: 1.4rem;padding-top: 0.05rem;
    //line-height:0.08rem;padding: 0rem 0.2rem 0.4rem 0.2rem;
    isClick = false
    layer.open({
        title: [
            '温馨提示',
            ''
        ]
        , anim: 'up'
        , content: alertMsg
        , style: ''
        , btn: ['支付', '取消']
        , yes: function (index) {
            if(isClick){
                return
            }
            isClick = true
            if (ispaying) {
                return;
            }
            if (typeof WeixinJSBridge == "undefined") {
                if (document.addEventListener) {
                    document.addEventListener('WeixinJSBridgeReady', function () {
                        tipsjsApiCall(customerId, index, feeType, fee, orderid, content);
                    }, false);
                } else if (document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', function () {
                        tipsjsApiCall(customerId, index, feeType, fee, orderid, content);
                    });
                    document.attachEvent('onWeixinJSBridgeReady', function () {
                        tipsjsApiCall(customerId, index, feeType, fee, orderid, content);
                    });
                }
            } else {
                tipsjsApiCall(customerId, index, feeType, fee, orderid, content);
            }

        },
        end: function (index) {
            //按钮【按钮二】的回调
            console.log("按钮【按钮二】的回调")
            isClick = false
        }
    });

}

function tipscallpayH5Web(goodtype) {
    // var alertMsg = "支付成功后，我们会把心意传达给医生哦";
    // //color: #999;border: 0.005rem;border-bottom-style: solid;height: 1.4rem;padding-top: 0.05rem;
    // //line-height:0.08rem;padding: 0rem 0.2rem 0.4rem 0.2rem;
    //
    // layer.open({
    //     title: [
    //         '温馨提示',
    //         ''
    //     ]
    //     , anim: 'up'
    //     , content: alertMsg
    //     , style: ''
    //     , btn: ['支付', '取消']
    //     , yes: function (index) {
    //         if (ispaying) {
    //             return;
    //         }
    //
    //         tipsjsApiCallH5Web(customerId, index, feeType, fee, orderid, content);
    //     }
    //
    // });

    // console.log(goodtype);
    var cxc = $("#cxc").val();
    // console.log(cxc);
    var orderid = $("#orderid").val();
    // console.log(orderid);
    var content = $("#content").val();
    // console.log(content);
    var fee = 2;
    var feeType = goodtype
    tipsjsApiCallH5Web($("#customer_id").val(), feeType, fee, orderid, content);

}

//调用微信JS api 支付
function tipsjsApiCall(customerId, layIndex, feeType, fee, orderid, content) {
    ispaying = true;
    debugger;
    $.ajax({
        url: "/vue/prePayReward",
        dataType: "json",
        data: {
            "openid": $("#openid").val(),
            "consulterId": $("#consulterId").val(),
            "customer_id": customerId,
            "feeType": feeType,
            "fee": fee,
            "orderid": orderid,
            "content": content
        },
        success: function (data) {
//                    console.log(JSON.stringify(data));
            layer.close(layIndex);

            if (data && !data.code) {
                try {
                    WeixinJSBridge.invoke(
                        'getBrandWCPayRequest',
                        data,
                        function (res) {
                            if (res.err_msg == "get_brand_wcpay_request:ok") {
                                //支付成功
                                isClick = false
                                var preOrderStr = data.package;
                                var preOrderArr = preOrderStr.split("=");
                                layer.open({
                                    content: '感谢您的支持！',
                                    // skin: 'msg',
                                    time: 2,
                                    success: function () {
                                        // window.history.back();
                                        // window.location.href = returnPage();;
                                        var orderid = $("#orderid").val()
                                        jumpVuechat("orderid", orderid);


                                    }
                                });
                            } else {
                                isClick = false
                            }

                        }
                    );
                }catch(e){
                    console.log(e)
                    isClick = false
                    // layer.open({
                    //     content: '页面没有初始化完成，请重试。',
                    //     // skin: 'msg',
                    //     time: 3
                    // });
                }
            } else if (data.code && data.code == -3) {
                //测试桩
                var preOrderStr = data.data.prePayId;
                layer.open({
                    content: '感谢您的支持！',
                    // skin: 'msg',
                    time: 2,
                    success: function () {
                        window.history.back();
                    }
                });

            } else if (data.code && data.code == -2) {
                //已经付费但是没有提问的工单
                layer.open({
                    content: '感谢您的支持！',
                    // skin: 'msg',
                    time: 2,
                    success: function () {
                        window.history.back();
                    }
                });
            } else {
                isClick = false
                if (!data.desc) {
                    var msg = "支付未成功";
                } else {
                    var msg = data.desc;
                }

                layer.open({
                    content: msg,
                    // skin: 'msg',
                    time: 2
                });
            }
            setTimeout(function () {
                ispaying = false;
            }, 2000);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            layer.close(layIndex);
            isClick = false
            layer.open({
                content: '系统繁忙，请稍后再试',
                // skin: 'msg',
                time: 3
            });
            ispaying = false;
        }
    });


}

//调用微信JS api 支付
function tipsjsApiCallH5Web(customerId, feeType, fee, orderid, content) {
    ispaying = true;
    window.location.href = "/vue/prePayRewardH5Web?openid="
        + $("#openid").val()
        + "&consulterId=" + $("#consulterId").val()
        + "&customer_id=" + $("#customer_id").val()
        + "&feeType=" + feeType
        + "&fee=" + fee
        + "&orderid=" + orderid
        + "&content=" + content
        + "&flag=" + $("#flag").val()
        + "&bmark=" + $("#bmark").val()
        + "&appid=" + $("#appid").val();
}


//调用小程序支付费用
function freeorpayJsApiCallMini() {

    $.ajax({
        url: "/vue/prePayMini",
        dataType: "json",
        data: {
            "paytemporderid": $("#paytemporderid").val(),
            "openid": $("#openid").val(),
            "consulterId": $("#consulterId").val(),
            "customer_id": $("#customer_id").val(),
            "label": $("#label").val(),
            "problem": $("#problem").val(),
            "consulte_type": $("#consulte_type").val(),
            "isdefault": $("#isdefault").val(),
            "labelId": $("#labelId").val(),
            "babyId": $("#babyId").val(),
            "flag": $("#flag").val(),
            "instid": $("#instid").val(),
            "bmark": $("#bmark").val(),
            "orderType": $("#orderType").val(),
        },
        success: function (data) {


            console.log(JSON.stringify(data.data));
            var planRepayTotal = data.data.fee;
            if(planRepayTotal>0){
                var wxMpPrepayIdMiniResult = data.data.wxMpPrepayIdMiniResult;
                var backurl =  "ChatData"; //小程序成功跳转地址
                var failurl = "MessageList"
                var  payParam = {
                    appId: wxMpPrepayIdMiniResult.appid,
                    nonceStr: wxMpPrepayIdMiniResult.nonce_str,
                    package: "prepay_id="+wxMpPrepayIdMiniResult.prepay_id,
                    signType: "MD5",
                    timeStamp: wxMpPrepayIdMiniResult.timeStamp,
                    paySign:wxMpPrepayIdMiniResult.paySign,
                    backurl:backurl,
                    failurl:failurl,
                    consulttype:$("#consulte_type").val(),
                    paytemporderid:$("#paytemporderid").val(),
                    goodid:$("#goodid").val(),
                    payfee:data.data.fee,
                    nickName:$("#nickName").val(),
                    orderType:$("#orderType").val()
                };


                // {"out_trade_no":"BTN1565576194088","prePayId":"PI1565576194088","paytemporderid":10405}

                // url: '../../pages/webview/webview?cxcbackurl=' + this.data.payParam.failurl + "&orderid=" + this.data.payParam.orderid

                console.log(JSON.stringify(payParam));
                var url =
                    "/pages/wxPay/wxPay?payParam=" + encodeURIComponent(JSON.stringify(payParam));
                // alert('url:' + url);
                wx.miniProgram.navigateTo({
                    url: url
                });
            }else{
                var paytemporderid = data.data.paytemporderid;
                var consulttype = data.data.consulttype;
                var orderType = $("#orderType").val();
                console.log("--------------------");
                console.log(paytemporderid);
                console.log(consulttype);
                var backurl =  "ChatData"; //小程序成功跳转地址
                var url = "/pages/webview/webview?cxcbackurl=" + backurl+ "&paytemporderid=" + paytemporderid+"&consulttype="+consulttype+"&orderType="+orderType
                wx.miniProgram.navigateTo({
                    url: url
                });
            }



        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (layIndex) {
                layer.close(layIndex);
                isClick = false
            }
            layer.open({
                content: '系统繁忙，请稍后再试',
                // skin: 'msg',
                time: 3
            });
            ispaying = false;
        }
    });

    //请求预支付下单，并且返回到小程序支付
}


//调用小程序支付送心意
function freeorpayJsApiCallMiniReward(goodtype) {


    $.ajax({
        url: "/vue/prePayMiniReward",
        dataType: "json",
        data: {
                "openid": $("#openid").val(),
                "consulterId": $("#consulterId").val(),
                "customer_id": $("#customer_id").val(),
                "feeType": goodtype,
                "fee": 2,
                "orderid": $("#orderid").val(),
                "content": $("#content").val(),
                "nickName":$("#nickName").val(),
        },
        success: function (data) {

            var backurl =  "Chat";
            var failurl = "Chat"

            var wxMpPrepayIdMiniResult = data.data.wxMpPrepayIdMiniResult;

            var  payParam = {
                appId: wxMpPrepayIdMiniResult.appid,
                nonceStr: wxMpPrepayIdMiniResult.nonce_str,
                package: "prepay_id="+wxMpPrepayIdMiniResult.prepay_id,
                signType: "MD5",
                timeStamp: wxMpPrepayIdMiniResult.timeStamp,
                paySign:wxMpPrepayIdMiniResult.paySign,
                backurl:backurl,
                failurl:failurl,
                orderid:$("#orderid").val(),
                payfee:data.data.fee,
                goodid:$("#goodid").val(),
                nickName:$("#nickName").val(),
            };

            console.log(JSON.stringify(payParam));
            var url =
                "/pages/wxPay/wxPay?payParam=" + encodeURIComponent(JSON.stringify(payParam));
            wx.miniProgram.navigateTo({
                url: url
            });


        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (layIndex) {
                layer.close(layIndex);
                isClick = false
            }
            layer.open({
                content: '系统繁忙，请稍后再试',
                // skin: 'msg',
                time: 3
            });
            ispaying = false;
        }
    });

    //请求预支付下单，并且返回到小程序支付
}
