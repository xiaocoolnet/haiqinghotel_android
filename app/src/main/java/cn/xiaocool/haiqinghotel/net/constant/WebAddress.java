package cn.xiaocool.haiqinghotel.net.constant;

/**
 * Created by wzh on 2016/5/7.
 */
public interface WebAddress extends NetBaseConstant {
    /**
     * 发送验证码后缀
     */
    String SEND_CODE = NET_BASE_PREFIX + "a=SendMobileCode";
    /**
     * 注册后缀
     */
    String REGISTER = NET_BASE_PREFIX + "a=AppRegister";
    /**
     * 修改密码
     */
    String AMEND_PASS = NET_BASE_PREFIX + "a=forgetpwd";
}
