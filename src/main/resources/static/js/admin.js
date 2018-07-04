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


var dds = 1;
$("#tbody").on('click', 'span', function () {
    if (dds == 1) {
        let temp = $(this).parent().parent().children().eq(5);
        temp.empty();
        var id = temp.parent().children().eq(1).text();
        console.log(id);
        var year = $("#year").val();
        var month = $("#month").val();
        var day = $("#day").val();
        if (day < 10) day = "0" + day;
        if (month < 10) month = "0" + month;
        var time = year + '-' + month + '-' + day;
        temp.append("<form action='/setTtime' method='post'><label >Set Ttime:</label><input type='text' name='ttime'><br><input type='hidden' value=" + id + " name='id'><input name ='time' type='hidden' value=" + time + "><input type='Submit'value='submit' class='btn btn-sm btn-info'>&nbsp;&nbsp;<a class='btn btn-sm btn-danger' href='/admin' >Cancle</a></form>");
    }
    dds = 0;
});
$("#etbody").on('click', '.delete', function () {
    if (confirm("are you sure delete the user?")) {
        var id = $(this).parent().parent().children().eq(1).text();
        console.log(id);
        $.ajax({
            type: 'POST',
            url: "/deleteUser",
            data: {"id": id},
            success: function (e) {
                if (e == 1) {
                    alert('Congratulations, deleted successfully!');
                    fff();
                } else {
                    alert('failed to delete the user!');
                }
            }
        });
    }
})
$("#etbody").on('click', '.edit', function () {


    var id = $(this).parent().parent().children().eq(1).text();
    var name = $(this).parent().parent().children().eq(2).text();
    var sex = $(this).parent().parent().children().eq(3).text();
  
        $("#id1").val(id);
        $("#sex1").val(sex);
        $("#name1").val(name);


})
$("#submit").click(function () {

    $.ajax({
        type: 'POST',
        url: "/updateUser",
        data: {"id": $("#id1").val(),"name":$("#name1").val()},
        success: function (e) {

            if (e == 1) {
                alert('Congratulations, update successfully!');
                fff();
                $('#exampleModalCenter').modal('hide');
            } else {
                alert('failed to update the user!');
            }
        }
    });

})
$("#v-pills-profile-tab").click(function () {
    fff();
})

function fff() {
    $.getJSON("/getAllUsers", function (result) {
        $("#etbody").empty();
        result = result.userlist;
        for (let i = 0; i < result.length; i++) {
            let id = result[i].id;
            let name = result[i].name;
            let sex = result[i].sex;
            let power = result[i].power;
            let pic = result[i].pic;
            if (power == 0) {
                $("#etbody").append("<tr>\n" +
                    "                                            <td><input type='checkbox' value=" + id + "></td>\n" +
                    "                                            <td>" + id + "</td>\n" +
                    "                                            <td>" + name + "</td>\n" +
                    "                                            <td>" + sex + "</td>\n" +
                    "                                            <td>HR</td>\n" +
                    "                                            <td>Admin</td>\n" +
                    "                                            <td>" + pic + "</td>\n" +
                    "                                            <td><span class='btn btn-warning btn-sm' data-toggle='modal' data-target='#exampleModalCenter'>Edit </span><span class='btn btn-danger btn-sm delete' >Delete </span></td>\n" +
                    "                                        </tr>")
            } else {
                $("#etbody").append("<tr>\n" +
                    "                                            <td><input type='checkbox' value=" + id + "></td>\n" +
                    "                                            <td>" + id + "</td>\n" +
                    "                                            <td>" + name + "</td>\n" +
                    "                                            <td>" + sex + "</td>\n" +
                    "                                            <td>HR</td>\n" +
                    "                                            <td>Normal</td>\n" +
                    "                                            <td>" + pic + "</td>\n" +
                    "                                            <td><span class='btn btn-warning btn-sm edit' data-toggle='modal' data-target='#exampleModalCenter'>Edit </span><span class='btn btn-danger btn-sm delete' >Delete </span></td>\n" +
                    "                                        </tr>")
            }
        }
    })
}

function normalFreshByDate(date) {
    $.getJSON("/getAllAttendance", {'date': date}, function (result) {
        $("#user").text("");
        $("#user").append(result.name + "(" + result.id + ")");
        all = result.AllAtt;
        $("#tbody").empty();
        for (let i = 0; i < all.length; i++) {
            let id = all[i].user.id;
            let name = all[i].user.name;
            let etime = all[i].etime.slice(10, 20);
            let ltime = all[i].ltime.slice(10, 20);
            let ttime = all[i].ttime;
            let comment = all[i].comment;
            $("#tbody").append(
                " <tr>" +
                "<th scope=\"row\"><input type=\"checkbox\" value=" + id + "></th>" +
                "<td>" + id + "</td>" +
                "<td>" + name + "</td>" +
                "<td>" + etime + "</td>" +
                "<td>" + ltime + "</td>" +
                "<td>" + ttime + "</td>" +
                "<td>" + comment + "</td>" +
                "<td><span class=\"btn btn-danger btn-sm\" >Edit</span></td>" +
                "</tr>"
            );
        }
    })

}
