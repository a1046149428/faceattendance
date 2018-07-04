var today = new Date();
var year = today.getFullYear();
var month = today.getMonth() + 1;
setInterval("showtime()", 1000);
var tabYear = $("#year");
var tabMonth = $("#month");

var refresh = $("#refresh");
//设置选择器
BindSelect(document.getElementById("year"), year - 3, year + 3, year);
BindSelect(document.getElementById("month"), 1, 12, month);


tabMonth.change(function () {
    getattendance(tabYear.val(), tabMonth.val());
});


refresh.click(function () {
    getattendance(tabYear.val(), tabMonth.val());
});
var x = 1;

$("#logout").click(function () {
    window.location.href = "/logout";
});
getattendance(tabYear.val(), tabMonth.val());


$("#tbody").on('click','a',function () {
    if (x == 1) {
        var temp = $(this).parent();
        temp.empty();
        var day = temp.parent().find("th").text();
        console.log(day);
        temp.append("<form action='/setComment' method='post'><label >Add Self Comment:</label><input type='text' name='comment'><br><input type='hidden' value=" + day + " name='day'><input type='Submit'value='submit' class='btn btn-sm btn-info'>&nbsp;&nbsp;<a class='btn btn-sm btn-danger' href='/myAttendance' >Cancle</a></form>");
    }
    x = 0;
});
