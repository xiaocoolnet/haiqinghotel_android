package cn.xiaocool.haiqinghotel.net.request;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.xiaocool.haiqinghotel.dao.CommunalInterfaces;
import cn.xiaocool.haiqinghotel.net.constant.WebAddress;
import view.HQApplacation;

/**
 * Created by wzh on 2016/5/7.
 */
public class MineRequest {
    private Context mContext;
    private Handler handler;

    public MineRequest(Context context, Handler handler) {
        super();
        this.mContext = context;
        this.handler = handler;
    }

    //获取我的房间订单
    public void myRoomOrder() {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + HQApplacation.UID;
                String result_data = NetUtil.getResponse(WebAddress.MINE_ROOM_ORDER, data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = CommunalInterfaces.MINE_ROOM_ORDER;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }
    //获取我的餐饮订单
    public void myCateringOrder() {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + HQApplacation.UID;
                String result_data = NetUtil.getResponse(WebAddress.MINE_CATERING_ORDER, data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = CommunalInterfaces.MINE_CATERING_ORDER;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }
    //获取我的餐饮订单
    public void myShopOrder() {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "&userid=" + 578;
                String result_data = NetUtil.getResponse(WebAddress.MINE_SHOP_ORDER, data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = CommunalInterfaces.MINE_SHOP_ORDER;
                    msg.obj = obj;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }
}
