function checkStandard(element) {
    var content = $("#signin-" + element).val();
    switch (element) {
        case "no": {
            if (content == null || content == "") {
                //alert(content);
                layer.msg("请输入工号");
                return false;
            } else {
                return true;
            }
            break;
        }
        case "pwd": {
            if (content == null || content == "") {
                layer.msg("请输入密码");
                return false;
            } else {
                return true;
            }
            break;
        }
        default: {
        }
    }
}

//回车键登录
document.addEventListener("keypress", function (event) {
    if (event.keyCode == 13) signin()
});

function signin() {
    var sign_no = $("#signin-no").val();
    var sign_pwd = $("#signin-pwd").val();

    if (checkStandard('no') && checkStandard('pwd')) {
        msgInfo.innerHTML = "";
        //alert(sign_no+","+sign_pwd);
        $.ajax({
            type: "POST",
            url: "/profession/user/login",
            datatype: 'json',
            data: {
                "ur_account": sign_no,
                "ur_pass": sign_pwd,
            }, // 发送数据
            error: function (msg) {
                layer.msg('请求失败！', {
                    time: 1000
                });
            },
            success: function (jsonobj) {
                if (jsonobj.resultCode == "50000") {//登录成功
                    /*layer.msg(jsonobj.resultDesc+"！", {
                           time: 1000
                     });*/
                    //alert(JSON.stringify(jsonobj));
                    //(jsonobj.data.columns.ur_role);
                    switch (jsonobj.data.columns.ur_role) {
                        case 0:
                            $.cookie("utype", "leader");
                            break;
                        case 1:
                            $.cookie("utype", "leader_project");
                            break;
                        case 2:
                            $.cookie("utype", "contact");
                            break;
                        case 4:
                            $.cookie("utype", "receiver");
                            break;
                        case 3:
                            $.cookie("utype", "principle");
                            break;
                        case 5:
                            $.cookie("utype", "administrator");
                            break;
                    }
                    $.cookie("ZUEL_uid", sign_no);
                    $.cookie("ZUEL_uname", jsonobj.data.columns.ur_name);
                    $.cookie('ctype', 'user_center');

                    //$.cookie("ZUEL_uname",jsonobj.data.columns.ur_name);
                    window.location.href = "/profession/views/pages/index.html";
                } else {
                    layer.msg(jsonobj.resultDesc + "！", {
                        time: 1000
                    });
                }
            },
        });

    }

}
