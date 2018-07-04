function showtime() {
    //创建Date对象
    var today = new Date();

    //分别取出//分别取出年、月、日、时、分、秒
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();
    var hours = today.getHours();
    var minutes = today.getMinutes();
    var seconds = today.getSeconds();

    //如果是小于10，则前面补0
    month = month < 10 ? "0" + month : month;
    day = day < 10 ? "0" + day : day;
    hours = hours < 10 ? "0" + hours : hours;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    seconds = seconds < 10 ? "0" + seconds : seconds;

    //要输出的字符串
    var time = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    var clock = document.getElementById("clock");
    clock.innerText = time;
}


//年月选择控件
function BindSelect(selectObj, startValue, endValue, selectedValue) {
    var i = 0;
    for (var val = startValue; val <= endValue; val++) {
        selectObj.options.add(new Option(val, val));

        if (val == selectedValue) selectObj.options[i].selected = true;
        i++;
    }
}


function getattendance(year, month) {
    if (month < 10) month = '0' + month;
    $.getJSON("/getPersonalAttendace", {"year": year, "month": month}, function (result) {
        $("#user").text("");
        $("#user").append(result.username + "(" + result.id + ")");
        $("tbody").children().remove();

        let attendance = result.attendace;
        for (let i = 0; i < attendance.length; i++) {
            let day = attendance[i].day.slice(0, 10);
            let etime = attendance[i].etime.slice(10, 20);
            let ltime = attendance[i].ltime.slice(10, 20);
            let comment=attendance[i].comment;

            if (comment == "" || comment == null) {
                $("tbody").append("<tr><th scope='row'>" + day + "</th><td>" + etime + "</td><td>" + ltime + "</td><td>" + attendance[i].ttime + "</td><td><i class='iconfont icon-user'></i><a href='#' class='comment' >Comment here</a></td></tr>");
            }
            else {
                $("tbody").append("<tr><th scope='row'>" + day + "</th><td>" + etime + "</td><td>" + ltime + "</td><td>" + attendance[i].ttime + "</td><td> <i class='iconfont icon-user'></i>" + comment + "</td></tr>");
            }
        }
    })
}