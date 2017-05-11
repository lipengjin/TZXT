<!-- BEGIN LOGO -->
<div class="logo">
    <a href="javascript:void(0);">
        <h2 class="font-grey-gallery">台账管理系统</h2>
    <#--<img src="${request.contextPath}/static/images/logo-big.png" alt=""/>-->
    </a>
</div>
<!-- END LOGO -->
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="${request.contextPath}/validate" method="post">
        <h3 class="form-title">管理员账号登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span>
			请输入用户名和密码. </span>
        </div>
        <div class="form-group">
            <input class="hidden" value="1" name="accountType"/>
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Username</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username"
                       name="userName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Password</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Password"
                       name="password"/>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> 记住我 </label>
            <button class="btn blue pull-right" type="submit">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
        <div class="form-actions">
            <a class="btn blue pull-right" type="button" href="${request.contextPath}/ordinaryLogin">
                切换到普通账号登录 <i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
        <div class="forget-password">
            <h4>忘记密码了 ?</h4>
            <p>
                不用担心, 点击 <a href="javascript:;" id="forget-password">
                这里 </a>
                重置密码.
            </p>
        </div>
        <div class="create-account">
            <p>
                还没有账号 ?&nbsp; <a href="javascript:;" id="register-btn">
                点击创建一个吧 </a>
            </p>
        </div>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" action="" method="post">
        <h3>Forget Password ?</h3>
        <p>
            Enter your e-mail address below to reset your password.
        </p>
        <div class="form-group">
            <div class="input-icon">
                <i class="fa fa-envelope"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email"
                       name="email"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn">
                <i class="m-icon-swapleft"></i> Back
            </button>
            <button type="submit" class="btn blue pull-right">
                Submit <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
    <form class="register-form" action="${request.contextPath}/users/register" method="post">
        <h3>注 册</h3>
        <p>
            输入您的个人信息：
        </p>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">真实名称</label>
            <div class="input-icon">
                <i class="fa fa-font"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="Real Name" name="realName"/>
            </div>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Email</label>
            <div class="input-icon">
                <i class="fa fa-envelope"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="Email" name="email"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">手机号</label>
            <div class="input-icon">
                <i class="fa fa-phone"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="phone" name="phone"/>
            </div>
        </div>
        <p>
            Enter your account details below:
        </p>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username"
                       name="userName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password"
                       placeholder="Password" name="password"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">重新输入密码</label>
            <div class="controls">
                <div class="input-icon">
                    <i class="fa fa-check"></i>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
                           placeholder="Re-type Your Password" name="r_password"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>
                <input type="checkbox" name="tnc"/> I agree to the <a href="javascript:;">
                Terms of Service </a>
                and <a href="javascript:;">
                Privacy Policy </a>
            </label>
            <div id="register_tnc_error">
            </div>
        </div>
        <div class="form-actions">
            <button id="register-back-btn" type="button" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" id="register-submit-btn" class="btn blue pull-right">
                注册 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END REGISTRATION FORM -->
</div>
<!-- END LOGIN -->