package cn.xiaocool.haiqinghotel.main.homepage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import cn.xiaocool.haiqinghotel.R;

/**
 * Created by wzh on 2016/5/8.
 */
public class RoomIntroActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_room_intro);
    }

    @Override
    public void onClick(View v) {

    }
}