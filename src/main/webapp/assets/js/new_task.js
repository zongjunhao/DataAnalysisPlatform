$(document).ready(function(){
    // $("#edge-div *").attr("disabled", true);
    // $("#classification-div *").attr("disabled", true);
    $("#edge-file").fileinput('disable');
    $("#classification-file").fileinput('disable');
});


function add_task() {
    let algorithm = $("#algorithm-type").val();
    let boolean = document.getElementById("standardized").checked
    $("#edge-file").fileinput('enable');
    $("#classification-file").fileinput('enable');
}
