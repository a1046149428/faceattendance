var jsCommonCp = 1;      //表示当前所在页
var jsCommonLs = 5;      //每页显示的数据个数
var jsCommonPageSize;    //总页数
function loadData() { //定义数据读取的操作函数
    $.post("xxx", {"cp": jsCommonCp, "ls": jsCommonLs}, function (obj) {
        createSplitBar(obj.allRecorders);
    }, "json");
}

loadData();

function createSplitBar(allRecorders) {  //创建分页
    clearBar();  //清空全部内容
    calcPageSize(allRecorders);    //计算总页数
    if (jsCommonPageSize > 1) {  //有很多页
        previousPage();
        addBar(1);
    }
    var seed=3; //分页种子数
    if (jsCommonCp>seed*2){   //    很多页
        addDetailPage();    //增加省略页
        var startPage = jsCommonCp-seed;
        for (var x=startPage;x<=jsCommonCp+seed;x++){
            if (x<jsCommonPageSize){
                addBar(x);
            }
        }
        if (jsCommonCp+seed*2<jsCommonPageSize){
            addDetailPage();
        }
    }else {
        for(var x=2;x<=jsCommonCp+seed;x++){
            if (x<jsCommonPageSize){
                addBar(x);
            }
        }
        if (jsCommonCp+seed<=jsCommonPageSize){
            addDetailPage();
        }
    }
    addBar(jsCommonPageSize);
    if (jsCommonPageSize > 1) {
        nextPage();
    }

}
function addDetailPage() {
    var liObj = $("<li class='page-item'><span>...</span></li>")
    $("#pageControl").append(liObj);
}
function previousPage() {
    var liObj = $("<li class='page-item'></li>"); //定义li元素
    var aObj = $("<a  class='page-link' style='cursor:pointer;'>Prev</a>");

    if (jsCommonCp == 1) {
        liObj.addClass("disabled");
    } else {
        aObj.on("click", function () {
            if (jsCommonCp > 1) {  //可以有上一页
                jsCommonCp--;
                loadData();
            }

        })
    }
    liObj.append(aObj);
    $("#pageControl").append(liObj);
}

function nextPage() {
    var liObj = $("<li class='page-item'></li>"); //定义li元素
    var aObj = $("<a  class='page-link' style='cursor:pointer;'>Next</a>");

    if (jsCommonCp == jsCommonPageSize) {  //已经是最后一页
        liObj.addClass("disabled");
    } else {
        aObj.on("click", function () {
            if (jsCommonCp < jsCommonPageSize) {  //可以有下一页
                jsCommonCp++;
                loadData();
            }

        })
    }
    liObj.append(aObj);
    $("#pageControl").append(liObj);
}

function addBar(index) {   //生成分页控制
    var liObj = $("<li class='page-item'></li>"); //定义li元素
    var aObj = $("<a  class='page-link' style='cursor:pointer;'>" + index + "</a>");
    if (jsCommonCp == index) {
        liObj.addClass("active");
    } else {
        aObj.on("click", function () {
            jsCommonCp = index;
            loadData();
        })
    }
    liObj.append(aObj);
    $("#pageControl").append(liObj);
}

function calcPageSize(allRecorders) { //计算总页数
    if (allRecorders == 0) {              //没有数据
        jsCommonPageSize = 1; //就在第一页显示
    } else {  //避免小数点问题
        jsCommonPageSize = parseInt((allRecorders + jsCommonLs - 1) / jsCommonLs);
    }
}

function clearBar() {  //清空已有内容

    $("#pageControl li").remove();
}