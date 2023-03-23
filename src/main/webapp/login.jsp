<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/layui/css/layui.css" />
    <script src="${pageContext.request.contextPath }/static/layui/layui.js"></script>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市订单管理系统</h1>
        </header>
        <section class="loginCont">
	        <form class="loginForm layui-form" action="${pageContext.request.contextPath }/login.do"  name="actionForm" id="actionForm"  method="post" >
                <%--<div class="layui-form-item">--%>
                    <%--<label class="layui-form-label">输入框</label>--%>
                    <%--<div class="layui-input-block">--%>
                        <%--<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">--%>
                    <%--</div>--%>
                </div>
				<div class="info">${error }</div>
				<div class="inputbox">
                    <label>用户名：</label>
					<input type="text" class="input-text" id="userCode" name="userCode" placeholder="请输入用户名" required lay-verify="required"/>
				</div>
				<div class="inputbox">
                    <label>密码：</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required lay-verify="required"/>
                </div>	
				<div class="subBtn">

                    <input type="submit" lay-submit lay-filter="login" value="登录"/>
                    <input type="reset" value="重置"/>
                </div>
			</form>
        </section>
    </section>
</body>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(login)', function(data){
            // layer.msg(JSON.stringify(data.field));
            form.submit();
        });
    });
</script>
</html>
