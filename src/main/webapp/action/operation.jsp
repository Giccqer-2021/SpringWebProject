<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<!-- 该网页已不再使用 -->
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>操作</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <script src="jquery-3.6.0.min.js"></script>
    <script src="bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btn_submit").click(function(event) {
                if($("#name_space").val() == ""){
                    return false;
                }
                return true;
            });
        })
    </script>
    <%
        String operationID = (String)request.getAttribute("operationID");
        String operationMethod = (String)request.getAttribute("operationMethod");
    %>
</head>
<body>
<form class="form-horizontal" role="form" action="operationConfirm.action" method="post">
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  readonly="readonly" value="<%=operationID%>"name="id"> 
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
      <input type="text" class="form-control" name="age">
    </div>
  </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">性别</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="gender">
    </div>
  </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">邮箱</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="email">
    </div>
  </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">部门ID</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="departmentID">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-primary" id="btn_submit">确认提交</button>
    </div>
  </div>
  <input type="hidden" name="_method" value="<%=operationMethod%>">
</form>
</body>
</html>