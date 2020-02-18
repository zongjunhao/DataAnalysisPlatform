// $(document).ready(function () {
//     $.ajax({
//         type: "POST",
//         url: "judgeLogin",
//         datatype: 'json',
//         data: {

//         }, // 发送数据
//         error: function () {
//             layer.msg('request failed', {
//                 time: 1000
//             });
//         },
//         success: function (jsonobj) {
//             if (jsonobj.resultCode === "1008") {/* not login */
//                 layer.msg(jsonobj.resultDesc, {
//                     time: 1000
//                 });

//                 window.location.href = "login.html";
//             }
//         },
//     });
// });