$(document).ready(function (e) {
    var counter = 0;
    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            window.history.pushState('forward', null, '#');
            window.history.forward(1);
            // alert("不可回退");  //如果需在弹框就有它
            // self.location="index.html"; //如查需要跳转页面就用它
        });
    }

    window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
    window.history.forward(1);
});