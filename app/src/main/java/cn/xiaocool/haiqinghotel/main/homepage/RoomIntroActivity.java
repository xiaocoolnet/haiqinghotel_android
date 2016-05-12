package cn.xiaocool.haiqinghotel.main.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cn.xiaocool.haiqinghotel.dao.CommunalInterfaces;
import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.net.request.HomepageRequest;
import cn.xiaocool.haiqinghotel.net.request.NetUtil;
import cn.xiaocool.haiqinghotel.utils.IntentUtils;

/**
 * Created by wzh on 2016/5/8.
 */
public class RoomIntroActivity extends Activity implements View.OnClickListener {
    private TextView tvTitle;
    private RelativeLayout btnExit;
    private LinearLayout llDateChoose;
    private String roomId,roomName;
    private Context context;
    private final int CODE = 1;
    private TextView tvOPrice, tvPrice, tvNetwork, tvWindow, tvPeopleNum, tvBathroom,
            tvRoomFloor, tvRoomArea, tvBedSize,tvTotalPrice,tvInday,tvOutday,tvDayCount;
    private Button btnReserve;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CommunalInterfaces.ROOM_DETAILS:
                    if (NetUtil.isConnnected(context)) {
                        JSONObject jsonObject = (JSONObject) msg.obj;
                        try {
                            String status = jsonObject.getString("status");
                            Log.e("room details status is", status);
                            if (status.equals("success")) {
                                JSONObject dataObject = (JSONObject) jsonObject.get("data");
                                String oPrice = dataObject.getString("oprice");
                                String price = dataObject.getString("price");
                                String network = dataObject.getString("network");
                                String window = dataObject.getString("window");
                                String peopleNum = dataObject.getString("peoplenumber");
                                String bathRoom = dataObject.getString("bathroom");
                                String roomFloor = dataObject.getString("floor");
                                String roomArea = dataObject.getString("acreage");
                                String bedSize = dataObject.getString("bedsize");
                                Log.e("", "oprice " + oPrice + "price" + price + "network" + network + "window" + window
                                        + "peopleNum" + peopleNum + "bathroom" + bathRoom + "floor" + roomFloor + "roomarea"
                                        + roomArea + "bedsize" + bedSize);
                                tvOPrice.setText(oPrice);
                                tvPrice.setText(price);
                                tvNetwork.setText(network);
                                tvWindow.setText(window);
                                tvPeopleNum.setText(peopleNum);
                                tvBathroom.setText(bathRoom);
                                tvRoomFloor.setText(roomFloor);
                                tvRoomArea.setText(roomArea);
                                tvBedSize.setText(bedSize);
                                tvTotalPrice.setText(price);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_room_intro);
        context = this;
        Intent intent = getIntent();
        roomId = intent.getStringExtra("roomId");
        roomName = intent.getStringExtra("roomName");
        Log.e("roomId/Name is ", roomId + roomName);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE){
            String indayMonNum = data.getStringExtra("inDayMonth1");
            String indayDayNum = data.getStringExtra("inDayNum0");
            String outdayMonNum = data.getStringExtra("outMonth1");
            String outdayDayNum = data.getStringExtra("outDayNum0");
            String dayCount = data.getStringExtra("dayCount");
            Log.e("五个数：",indayMonNum + indayDayNum + outdayMonNum + outdayDayNum + dayCount);
            tvInday.setText("入住：" + indayMonNum + "月" + indayDayNum);
            tvOutday.setText("离店：" + outdayMonNum + "月" + outdayDayNum);
            tvDayCount.setText("共" + dayCount + "晚");
        }
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.top_title);
        tvTitle.setText(roomName);
        btnExit = (RelativeLayout) findViewById(R.id.btn_back);
        btnExit.setOnClickListener(this);
        tvOPrice = (TextView) findViewById(R.id.tv_oprice);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvNetwork = (TextView) findViewById(R.id.tv_network);
        tvWindow = (TextView) findViewById(R.id.tv_window);
        tvPeopleNum = (TextView) findViewById(R.id.tv_prople_num);
        tvBathroom = (TextView) findViewById(R.id.tv_bathroom);
        tvRoomFloor = (TextView) findViewById(R.id.tv_floor);
        tvRoomArea = (TextView) findViewById(R.id.tv_area);
        tvBedSize = (TextView) findViewById(R.id.tv_bedsize);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        btnReserve = (Button) findViewById(R.id.btn_reserve_now);
        btnReserve.setOnClickListener(this);
        llDateChoose = (LinearLayout) findViewById(R.id.reserve_choose_date);
        llDateChoose.setOnClickListener(this);
        //日期
        tvInday = (TextView) findViewById(R.id.tv_inday);
        tvOutday = (TextView) findViewById(R.id.tv_outday);
        tvDayCount = (TextView) findViewById(R.id.tv_day_count);
        new HomepageRequest(this, handler).roomDetails(roomId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_reserve_now:
                IntentUtils.getIntent(this,BookingNowActivity.class);
                break;
            case R.id.reserve_choose_date:
                Intent intent = new Intent(this,CalenderMainActivity.class);
                startActivityForResult(intent, CODE);
                break;
        }
    }
}