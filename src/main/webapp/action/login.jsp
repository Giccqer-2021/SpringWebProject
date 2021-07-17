<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>登录界面</title>
  <link rel="stylesheet" href="bootstrap.min.css">
  <script src="jquery-3.6.0.min.js"></script>
  <script src="bootstrap.min.js"></script>
  <script type="text/javascript">
    $(function(){
      $("#submitBtn").click(function(event){
        $.get("login.action",{user_name:$("#user_name").val(),user_password:$("#user_password").val()},function(data){
          if(data == "true"){
            window.location.href = "employee.html";
          }else{
            $("#tip").text("请输入正确的用户名和密码");
          }
        });
        return false;
      });
    })
  </script>
</head>
<body>
<form action="login.action" method="get" onsubmit="return false">
  <div class="form-group">
    <label for="email">用户名</label>
    <input id="user_name" type="text" class="form-control" name="user_name">
  </div>
  <div class="form-group">
    <label for="pwd">密码:</label>
    <input id="user_password" type="password" class="form-control" name="user_password">
  </div>
  <button id="submitBtn" type="submit" class="btn btn-primary">登录</button>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <button type="reset" class="btn btn-primary">重置</button>
</form>
<br>
<p id="tip" class="text-warning"></p>
</body>
</html>