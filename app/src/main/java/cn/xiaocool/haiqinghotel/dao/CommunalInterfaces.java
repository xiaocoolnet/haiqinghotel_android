package cn.xiaocool.haiqinghotel.dao;

/**
 * Created by wzh on 2016/5/7.
 */
public interface CommunalInterfaces {

    //main number 0x0000开始
    int SEND_CODE = 0x0000;//发送验证码
    int BTN_UNTOUCH = 0x0001;//发送验证码按钮不可点击
    int BTN_TOUCH = 0x0002;//发送验证码按钮可点击
    int REGISTER = 0x0003;//注册
    int LOGIN = 0x0004;//登录






    //homepage number 0x0100开始
    int ONSALE_LIST = 0x0100;
    int ROOM_DETAILS = 0x0101;
    int RESERVE_ROOM_NOW = 0x0102;




    //ecshop number 0x0200开始





    //facility number 0x0300开始




    //mine number  0x0400开始





    //others  0x0500开始




}
