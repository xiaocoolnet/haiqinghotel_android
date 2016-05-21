package cn.xiaocool.haiqinghotel.main.mine;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;

import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.net.request.MineRequest;
import cn.xiaocool.haiqinghotel.net.request.NetUtil;

/**
 * Created by wzh on 2016/5/21.
 */
public class MyCateringOrderActivity extends Activity implements View.OnClickListener {

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mine_room_order);
        initView();
        if (NetUtil.isConnnected(this)) {
            new MineRequest(this, handler).myCateringOrder();
        }
    }

    private void initView() {
    }

    @Override
    public void onClick(View v) {

    }
}
