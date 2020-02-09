$(document).ready(function () {
    // let table = $('#dataTables-example').DataTable({
    //     searching : false,
    //     bLengthChange : false
    // });// 加载table插件
    // table.fnDestroy();

    $.ajax({
        type: "POST",
        url: "getTaskList",
        data: {
            UserId: $.session.get('UserId')
        },
        success: function (res) {
            if (res.resultCode === "2006") {// 查找任务列表成功
                console.log(res);

                let table = "";
                // let tableLength = res.data.length;
                $.each(res.data, function (i, value) {

                    let status;

                    if (value.EndTime === "") {
                        status = "<td>" + "Trading" + "</td>" +
                            "<td>" + "</td>"
                    } else {
                        status = "<td>" + "Finish" + "</td>" +
                            "<td>" +
                            '<button class="btn btn-xs btn-default" onclick="viewResult(' + value.id + ')">View</button>' +
                            '<button class="btn btn-xs btn-default" onclick="deleteTask(' + value.id + ')">Delete</button>' +
                            "</td>"
                    }
                    table += "<tr>" +
                        "<td>" + (i + 1) + "</td>" +
                        "<td>" + value.TaskName + "</td>" +
                        "<td>" + value.AlgorithmType + "</td>" +
                        "<td>" + value.StartTime + "</td>" +
                        "<td>" + value.EndTime + "</td>" +
                        status +
                        "</tr>"
                });
                $(".table tbody").append(table)
            } else if (res.resultCode === "1006") {
                console.log("数据库中没有记录")
            }
            console.log("open dataTable");
            // $("#dataTables-example").dataTable();
            $('#dataTables-example').DataTable({
                searching: false,
                bLengthChange: false
            });// 加载table插件
        },
        error: function (res) {
            console.log(res)
        }
    });

});


function deleteTask(taskId) {
    $.ajax({
        type: "POST",
        url: "/task/deleteTask",
        data: {
            t_id: taskId
        },
        success: function (res) {
            if (res.resultCode === "6004") {
                console.log("任务删除成功");
                location.reload()
            } else {
                console.log("任务删除失败")
            }
        },
        error: function (res) {

        }
    });
}

function viewResult(taskId) {
    $.ajax({
        type: "POST",
        url: "/task/viewResult",
        data: {
            g_id: taskId
        },
        success: function (res) {
            if (res.resultCode === "6005") {
                console.log("任务结果查看成功");
                location.reload()
            } else {
                console.log("任务结果查看失败")
            }
        },
        error: function (res) {

        }
    });
}


function newTask() {
    layer.open({
        type: 2,
        title: "New Task",
        content: "new_task.html",
        area: ['500px', '500px'],
        end: function () {
            parent.window.location.reload();
        }
    })
}