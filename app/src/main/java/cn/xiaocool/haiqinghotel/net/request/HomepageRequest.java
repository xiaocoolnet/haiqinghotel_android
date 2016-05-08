package cn.xiaocool.haiqinghotel.net.request;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.xiaocool.haiqinghotel.dao.CommunalInterfaces;
import cn.xiaocool.haiqinghotel.net.constant.WebAddress;

/**
 * Created by wzh on 2016/5/7.
 */
public class HomepageRequest  {
    private Context mContext;
    private Handler handler;

    public HomepageRequest(Context context, Handler handler) {
        super();
        this.mContext = context;
        this.handler = handler;
    }

    //获取首页促销列表
    public void onsaleList() {
        new Thread() {
            Message msg = Message.obtain();

            public void run() {
                String data = "";
                String result_data = NetUtil.getResponse(WebAddress.ONSALE_LIST, data);
                try {
                    JSONObject obj = new JSONObject(result_data);
                    msg.what = CommunalInterfaces.ONSALE_LIST;
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
