$(document).ready(function () {
    // $("#edge-div *").attr("disabled", true);
    // $("#classification-div *").attr("disabled", true);
    // $("#edge-file").fileinput('disable');
    // $("#classification-file").fileinput('disable');
    let taskName;
    let algorithm;
    let boolean;
    // $("#edge-file").fileinput('enable');
    // $("#classification-file").fileinput('enable');
    var attriFileInput;
    var edgeFileInput;
});


function add_task() {
    taskName = $("#task-name").val();
    algorithm = $("#algorithm-type").val();
    boolean = document.getElementById("standardized").checked
    // $("#edge-file").fileinput('enable');
    // $("#classification-file").fileinput('enable');
    attriFileInput = $('#attri-file').get(0).files[0];
    edgeFileInput = $('#edge-file').get(0).files[0];
    if (taskName === "") {
        alert("Please enter a task name!");
    } else if (algorithm === "") {
        alert("Please select an algorithm!");
    } else if (attriFileInput == null) {
        alert("Please select a properties file!")
    } else if (!boolean) {
        alert("Please standardize the properties file!");
    } else if (edgeFileInput == null) {
        alert("Please select an edge file!");
    } else {
        var formdata = new FormData(document.getElementById("form"));
        console.log(formdata);
        $.ajax({
            type: "POST",
            url: "addTask",
            //dataType: "json",
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            data: formdata,
            success: function (res) {
                console.log(res.resultDesc);
                if (res.resultCode === "6016") {
                    console.log("success");
                    parent.window.location.reload();
                    // location.href = "index.html";
                }
            },
            error: function (res) {
                console.log(res.resultDesc);
            }
        });
    }
}