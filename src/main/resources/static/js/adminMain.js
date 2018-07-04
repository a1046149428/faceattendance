var today = new Date();
var year = today.getFullYear();
var month = today.getMonth() + 1;
var day = today.getDate();
setInterval("showtime()", 1000);
var mDay = new Date(year, month, 0);
let year1 = document.getElementById("year");
let month1 = document.getElementById("month");
let day1 = document.getElementById("day");

//设置选择器
BindSelect(year1, year - 3, year + 3, year);
BindSelect(month1, 1, 12, month);
BindSelect(day1, 1, mDay.getDate(), day);

$("#logout").click(function () {
    window.location.href = "/logout";
});

$("#day").change(function () {
    var month =$("#month").val();
    var day=$("#day").val();
    if (month< 10) {
         month = '0' + month;
    }
    if (day<10) {
        day= '0' + $("#day").val()
    }
    let date = $("#year").val() + "-" + month + "-" + day;
    normalFreshByDate(date);
})
//-----------------------------------------------------分割线--------------------------------------

function getAttendance() {
    var month=$("#month").val()
    if ($("#month").val() <10) {
       month = '0' + $("#month").val();
    }
    var day =  $("#day").val();
    if ($("#day").val()<10) {
         day = '0' + $("#day").val();
    }
    let date = $("#year").val() + "-" + month + "-" + day;
    normalFreshByDate(date);
}

getAttendance();
