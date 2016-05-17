package cn.xiaocool.haiqinghotel.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.adapter.HomeOnsaleListAdapter;
import cn.xiaocool.haiqinghotel.main.homepage.CateringIntroActivity;
import cn.xiaocool.haiqinghotel.main.homepage.ContactUsActivity;
import cn.xiaocool.haiqinghotel.main.homepage.RoomIntroActivity;
import cn.xiaocool.haiqinghotel.net.request.HomepageRequest;
import cn.xiaocool.haiqinghotel.net.request.NetUtil;
import cn.xiaocool.haiqinghotel.utils.IntentUtils;
import cn.xiaocool.haiqinghotel.dao.CommunalInterfaces;

/**
 * Created by wzh on 2016/4/28.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout btnLocation, btnContact, btn_Details;
    private Context context;
    private ListView onsaleList;
    private HomeOnsaleListAdapter homeOnsaleListAdapter;
    private String[] picName, name, intro, price, id, type;
    private ArrayList<HashMap<String, Object>> arrayList;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CommunalInterfaces.ONSALE_LIST:
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    Log.e("jsonObject 获取成功", jsonObject.toString());
                    if (NetUtil.isConnnected(context)) {
                        try {
                            String status = jsonObject.getString("status");
                            if (status.equals("success")) {
                                JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                                JSONObject object;
                                int length = jsonArray.length();
                                picName = new String[length];
                                name = new String[length];
                                intro = new String[length];
                                price = new String[length];
                                id = new String[length];
                                type = new String[length];
                                for (int i = 0; i < length; i++) {
                                    object = (JSONObject) jsonArray.get(i);
                                    picName[i] = object.getString("picture");
                                    name[i] = object.getString("name");
                                    intro[i] = object.getString("type");
                                    price[i] = object.getString("price");
                                    id[i] = object.getString("id");
                                    type[i] = object.getString("type");
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    hashMap.put("picName", picName[i]);
                                    hashMap.put("name", name[i]);
                                    hashMap.put("intro", intro[i]);
                                    hashMap.put("price", price[i]);
                                    hashMap.put("id", id[i]);
                                    hashMap.put("type", type[i]);
                                    arrayList.add(hashMap);
                                }
                                homeOnsaleListAdapter = new HomeOnsaleListAdapter(context, arrayList);
                                onsaleList.setAdapter(homeOnsaleListAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(context, "网络连接有问题", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initView();
        new HomepageRequest(context, handler).onsaleList();
        setItemOnclick();
    }

    private void setItemOnclick() {
        onsaleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> intentMap = (HashMap<String, Object>) homeOnsaleListAdapter.getItem(position);
                String goodId = (String) intentMap.get("id");
                String goodName = (String) intentMap.get("name");
                String type = (String) intentMap.get("type");
                if (type.equals("1")) {
                    Intent intent = new Intent();
                    intent.setClass(context, RoomIntroActivity.class);
                    intent.putExtra("roomId", goodId);
                    intent.putExtra("roomName", goodName);
                    startActivity(intent);
                } else {
//                    Toast.makeText(context,"跳转餐饮",Toast.LENGTH_SHORT).show();//此处须跳转餐饮activity
                    if (type.equals("2")) {
                        Intent intent = new Intent();
                        intent.setClass(context, CateringIntroActivity.class);
                        intent.putExtra("cateringId",goodId);
                        intent.putExtra("cateringName",goodName);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void initView() {
        btnLocation = (RelativeLayout) getView().findViewById(R.id.home_btn_location);
        btnLocation.setOnClickListener(this);
        btnContact = (RelativeLayout) getView().findViewById(R.id.home_btn_contact_us);
        btnContact.setOnClickListener(this);
        btn_Details = (RelativeLayout) getView().findViewById(R.id.home_btn_details);
        btn_Details.setOnClickListener(this);
        onsaleList = (ListView) getView().findViewById(R.id.home_onsale_list);
        arrayList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btn_contact_us:
                IntentUtils.getIntent((Activity) context, ContactUsActivity.class);
                break;
        }
    }
}
