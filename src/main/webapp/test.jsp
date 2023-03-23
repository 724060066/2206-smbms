<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>ajax</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.8.3.min.js"></script>
    <script>
        $(document).ready(function () {
            $("input[name=userCode]").val();
        });
    </script>
</head>
<body>
<div>
    <div>
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/jsp/test.do">
            <div>
                <label for="userCode">用户编码：</label>
                <input type="text" name="userCode" id="userCode" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div class="providerAddBtn">
                <input type="button" name="add" id="add" value="保存" >
                <input type="button" id="back" name="back" value="返回" >
            </div>
        </form>
    </div>
</div>
<script>
    $("#add").click(function () {
        $.ajax({
            type:"post",//请求类型
            url:"${pageContext.request.contextPath }/jsp/test.do",//请求的url
            data:{userCode:$("#userCode").val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                alert(data.message);// 服务器返回的json格式数据，也就是HashMap里key=message的对应的值
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                // validateTip(userCode.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
            }
        });
    });
</script>
</body>

</html>

