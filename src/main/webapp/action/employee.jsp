<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>员工信息表</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <script src="jquery-3.6.0.min.js"></script>
    <script src="bootstrap.min.js"></script>
    <script type="text/javascript">
        var presentPage = 0;
        var countInPage = 5;
        var queryText = "";
        var totalPage = 0;
        function generatePageBtn(){
            $("#pages").empty();
            for(var i=1;i<=totalPage;i++){
                var $Page = "<button class='btn btn-default' id='page"+ i +"'>"+i+"</button>";
                $("#pages").append($Page);
                $("#page" + i).click(function(event) {
                    presentPage = parseInt($(this).attr("id").substring(4));
                    $("#resultBody").empty();
                    var presentMessage = (presentPage - 1)*countInPage;
                    $.getJSON("getEmployeeData.action",{floor: presentMessage,quantity:countInPage,query:queryText},callBack);
                });
            }
            $("#page" + presentPage).addClass('btn btn-success');
        }

        function analysisData(index,info){
            if(index == 0){
                var totalMessage = info["total"];
                // var totalPage =  Math.floor(totalMessage / countInPage) + 1;
                if(totalMessage == 0){
                    totalPage = 1;
                }else{
                    totalPage =  Math.ceil(totalMessage / countInPage);
                }
                generatePageBtn(totalPage);
                return;
            }
            var $tr=document.createElement("tr");
            $($tr).attr("id","tr"+info["id"]);
            var $td1="<td>"+info["id"]+"</td>";
            var $td2="<td>"+info["name"]+"</td>";
            var $td3="<td>"+info["age"]+"</td>";
            var $td4="<td>"+info["gender"]+"</td>";
            var $td5="<td>"+info["email"]+"</td>";
            var $td6="<td>"+info["department"]["departmentID"]+"</td>";
            var $td7="<td>"+info["department"]["departmentName"]+"</td>";

            var $setBtn="<button data-toggle='modal' data-target='#operationDialog' class='btn btn-primary' id='setBtn"+info["id"]+"'>修改</button>";
            var $delBtn="<button class='btn btn-primary' id='delBtn"+info["id"]+"'>删除</button>";
            var $td8="<td>"+$setBtn+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+$delBtn+"</td>";
            $($tr).append($td1).append($td2).append($td3).append($td4).append($td5).append($td6).append($td7).append($td8);
            $("#resultBody").append($tr);
            $("#setBtn" + info["id"]).click(function(event){
                // window.location.href = "operation.html?id=" + info["id"] + "&method=PUT";
                $("#id_space").val(info["id"]);
            });
            $("#delBtn" + info["id"]).click(function(event){
                if(confirm("确认删除?")){
                    // window.location.href = "deleteConfirm.action?id=" + info["id"];
                    $.post("operationConfirm.action",{_method:"delete",id:info["id"]},function(){
                        $("#tr"+info["id"]).remove();
                    });
                }
            });
        }

        var callBack = function(data){
            $.each(data,analysisData);
        }

        $(function(){
            presentPage = 1;
            $.getJSON("getEmployeeData.action",{floor: 0,quantity:countInPage,query:""},callBack);
            $("#insertButton").click(function(event){
                // window.location.href = "operation.html?id=Auto&method=POST";
                $("#id_space").val("AUTO");
            });

            $("#submitConfirm").click(function(event) {
                if($("#name_space").val() == ""){
                    $("#name_space").focus();
                    return;
                }
                var e_id = $("#id_space").val();
                var e_name = $("#name_space").val();
                var e_age = $("#age_space").val();
                var e_gender = $("#gender_space").val();
                var e_email = $("#email_space").val();
                var e_departmentID = $("#departmentID_space").val();
                if(e_id=="AUTO"){
                    $.post('operationConfirm.action', {_method:'post',name:e_name,age:e_age,gender:e_gender,email:e_email,departmentID:e_departmentID}, 
                        function(data) {
                            // console.log(data);
                            analysisData(-1,$.parseJSON(data));
                            // JSON.stringify(obj)
                            // JSON.parse(string)
                            $("#id_space").val("");
                            $("#name_space").val("");
                            $("#age_space").val("");
                            $("#gender_space").val("");
                            $("#email_space").val("");
                            $("#departmentID_space").val("");
                            $("#operationDialog").modal("hide");
                        });
                }else if($.isNumeric(e_id)){
                    $.post('operationConfirm.action', {_method:'put',id:e_id,name:e_name,age:e_age,gender:e_gender,email:e_email,departmentID:e_departmentID}, 
                        function(data) {
                            // alert(data);
                            var tds= $("#tr"+e_id+">td");
                            // alert($(tds[0]).text());
                            $(tds[1]).text(e_name);
                            $(tds[2]).text(e_age);
                            $(tds[3]).text(e_gender);
                            $(tds[4]).text(e_email);
                            $(tds[5]).text(e_departmentID);
                            $(tds[6]).text(data);
                            $("#id_space").val("");
                            $("#name_space").val("");
                            $("#age_space").val("");
                            $("#gender_space").val("");
                            $("#email_space").val("");
                            $("#departmentID_space").val("");
                            $("#operationDialog").modal("hide");
                        });
                }
            });

            $("#pageStart,#pageEnd").click(function(event) {
                //很奇怪的现象,start需要减少而end需要增加
                if($(this).attr("id")=="pageStart"){
                    if(presentPage <=1){
                        return;
                    }
                    presentPage--;
                }else{
                    if(presentPage >= totalPage){
                        return;
                    }
                    presentPage++;
                }
                var presentMessage = (presentPage - 1)*countInPage;
                $("#resultBody").empty();
                $.getJSON("getEmployeeData.action",{floor: presentMessage,quantity:countInPage,query:queryText},callBack);
            });

            $("#filtrationBtn,#filtrationReset").click(function(event) {
                if($(this).attr("id")=="filtrationBtn"){
                    queryText = $("#filtrationText").val();
                    if(queryText == ""){
                        $("#filtrationText").focus();
                        return;
                    }
                }else{
                    $("#filtrationText").val("");
                    queryText = "";
                }
                $.getJSON('getEmployeeData.action', {floor: 0,quantity:countInPage,query:queryText}, function(json, textStatus) {
                    presentPage = 1;
                    $("#resultBody").empty();
                    callBack(json);
                });
            });
        })
    </script>
</head>
<body>
    <h3 class="text-center">员工信息表</h3>
    
    <div class="btn-group btn-group-sm">
    <button class="btn btn-default" id="pageStart">上一页</button>
    <div class="btn-group btn-group-sm" id="pages">

    </div>
    <button class="btn btn-default" id="pageEnd">下一页</button>
    </div>

    <form class="form-inline" role="form" style="float:right" onsubmit="return false">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="请输入查询条件" id="filtrationText">
      </div>
      <button class="btn btn-info" id="filtrationBtn">提交</button>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <button class="btn btn-info" id="filtrationReset">重置后刷新</button>
    </form>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>邮箱</th>
        <th>部门ID</th>
        <th>部门名</th>
        <th>操作
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button id="insertButton" class="btn btn-success" data-toggle="modal" data-target="#operationDialog">
            新增员工</button>
        </th>
    </tr>
    </thead>
    <tbody id="resultBody">

    </tbody>
</table>

<div class="modal fade" id="operationDialog" tabindex="-1" role="dialog" aria-labelledby="dialogTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="dialogTitle">新增员工</h4>
            </div>
            <div class="modal-body">
                <!-- form start -->
                <form class="form-horizontal" role="form" action="operationConfirm.action" method="post" onsubmit="return false">
                  <div class="form-group">
                    <label for="firstname" class="col-sm-2 control-label">ID</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control"  readonly="readonly" name="id" id="id_space"> 
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control"  name="name" placeholder="姓名不可以为空"  id="name_space">
                    </div>
                  </div>
                    <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">年龄</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="age" id="age_space">
                    </div>
                  </div>
                    <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="gender" id="gender_space">
                    </div>
                  </div>
                    <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="email" id="email_space">
                    </div>
                  </div>
                    <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">部门ID</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control" name="departmentID" id="departmentID_space">
                    </div>
                  </div>
                </form>
                <!-- form end -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="submitConfirm" type="button" class="btn btn-primary">提交更改</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>