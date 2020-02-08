page("task");
function page(type) {
    $.ajax({
        url: type + ".html",
        type: "get",
        success: function (data) {
            // load the content area
            $("#contain-holder").html($(data).filter("#contain-loader").html());
            // load the js area
            $("#js-holder").html($(data).filter("#js-loader").html());
        },
        error: function () {
            layer.msg("Please refresh to try again.")
        }
    });
}

function logout() {
    layer.confirm("Confirm to logout?", {
            btn: ['confirm', 'cancel'],
            title: "Tip"
        },
        function () {
            console.log("confirm");
            $.cookie("username", '', {expires: -1});// delete the cookie
            $.ajax({
                url: "logout"
            })
        },
        function () {
            console.log("cancel")
        });
}