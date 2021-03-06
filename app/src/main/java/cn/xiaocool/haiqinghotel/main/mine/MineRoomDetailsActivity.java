package cn.xiaocool.haiqinghotel.main.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.net.request.MineRequest;
import cn.xiaocool.haiqinghotel.net.request.NetUtil;

/**
 * Created by wzh on 2016/5/21.
 */
public class MineRoomDetailsActivity extends Activity implements View.OnClickListener {
    private TextView tvOrderNum, tvRoomType, tvName, tvCount, tvPhoNum, tvRemark;
    private TextView tvTitle;
    private RelativeLayout btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mine_room_details);
        initView();
        Intent intent = getIntent();
        String orderNum = intent.getStringExtra("orderNum");
        String name = intent.getStringExtra("name");
        String peoName = intent.getStringExtra("peoName");
        String count = intent.getStringExtra("count");
        String remark = intent.getStringExtra("remark");
        String phoNum = intent.getStringExtra("phoNum");
        tvOrderNum.setText(orderNum);
        tvRoomType.setText(name);
        tvName.setText(peoName);
        tvCount.setText(count);
        tvRemark.setText(remark);
        tvPhoNum.setText(phoNum);
    }

    private void initView() {
        tvOrderNum = (TextView) findViewById(R.id.mine_tv_order_num);
        tvRoomType = (TextView) findViewById(R.id.mine_tv_order_type);
        tvName = (TextView) findViewById(R.id.mine_tv_name);
        tvCount = (TextView) findViewById(R.id.mine_tv_count);
        tvPhoNum = (TextView) findViewById(R.id.mine_tv_phoNum);
        tvRemark = (TextView) findViewById(R.id.mine_tv_remark);
        tvTitle = (TextView) findViewById(R.id.top_title);
        tvTitle.setText(this.getString(R.string.mine_title_roomDetails));
        btnExit = (RelativeLayout) findViewById(R.id.btn_back);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
