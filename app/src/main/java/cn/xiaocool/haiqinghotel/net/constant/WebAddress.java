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
     * 发送验证码后缀
     */
    String LOGIN = NET_BASE_PREFIX + "a=applogin";
    /**
     * 注册后缀
     */
    String REGISTER = NET_BASE_PREFIX + "a=AppRegister";
    /**
     * 修改密码
     */
    String AMEND_PASS = NET_BASE_PREFIX + "a=forgetpwd";
    /**
     * 首页促销列表
     */
    String ONSALE_LIST = NET_BASE_PREFIX + "a=getpromotionlist";
    /**
     * 房间详情
     */
    String ROOM_DETAILS = NET_BASE_PREFIX + "a=showbedroominfo";
    /**
     * 立即预定房间
     */
    String RESERVE_ROOM_NOW = NET_BASE_PREFIX + "a=bookingroom";
}
