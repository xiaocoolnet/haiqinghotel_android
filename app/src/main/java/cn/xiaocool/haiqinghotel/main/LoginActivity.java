package cn.xiaocool.haiqinghotel.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.dao.CommunalInterfaces;
import cn.xiaocool.haiqinghotel.net.request.MainRequest;
import cn.xiaocool.haiqinghotel.net.request.NetUtil;
import cn.xiaocool.haiqinghotel.utils.IntentUtils;

/**
 * Created by wzh on 2016/5/13.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button btnLogin;
    private RelativeLayout rlRegister;
    private EditText etPhoNUm, etPassword;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CommunalInterfaces.LOGIN:
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    try {
                        String status = jsonObject.getString("status");
                        if (status.equals("success")){
                            Toast.makeText(LoginActivity.this,"登陆成功，正在跳转主界面！",Toast.LENGTH_SHORT).show();
                            IntentUtils.getIntent(LoginActivity.this,MainActivity.class);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"密码错误！",Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_login);
        initView();
    }

    private void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        etPhoNUm = (EditText) findViewById(R.id.et_phoNum);
        etPassword = (EditText) findViewById(R.id.et_password);
        rlRegister = (RelativeLayout) findViewById(R.id.rl_register);
        rlRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (NetUtil.isConnnected(this)) {
                    String phoNum = etPhoNUm.getText().toString();
                    String password = etPassword.getText().toString();
                    new MainRequest(this, handler).login(phoNum, password);
                }else{
                    Toast.makeText(this,"请检查网络！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_register:
                IntentUtils.getIntent(this,RegisterActivity.class);
                break;
        }
    }
}
