package cn.xiaocool.haiqinghotel.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.adapter.MineRoomOrderAdapter;
import cn.xiaocool.haiqinghotel.dao.CommunalInterfaces;
import cn.xiaocool.haiqinghotel.net.request.MineRequest;
import cn.xiaocool.haiqinghotel.net.request.NetUtil;

/**
 * Created by wzh on 2016/5/21.
 */
public class MineOrderCateringFragment extends Fragment implements View.OnClickListener {
    private Context context;

    private ListView listView;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private String[] pic, name, count, price, time;
    private RelativeLayout btnback;
    private TextView tvTitle;
    private MineRoomOrderAdapter mineRoomOrderAdapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CommunalInterfaces.MINE_CATERING_ORDER:
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    try {
                        String status = jsonObject.getString("status");
                        if (status.equals("success")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            int length = jsonArray.length();
                            JSONObject dataObject;
                            pic = new String[length];
                            name = new String[length];
                            count = new String[length];
                            price = new String[length];
                            time = new String[length];
                            for (int i = 0; i < length; i++) {
                                dataObject = (JSONObject) jsonArray.get(i);
                                pic[i] = dataObject.getString("picture");
                                name[i] = dataObject.getString("name");
                                count[i] = dataObject.getString("number");
                                price[i] = dataObject.getString("price");
                                time[i] = dataObject.getString("time");
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("pic", pic[i]);
                                hashMap.put("name", name[i]);
                                hashMap.put("count", count[i]);
                                hashMap.put("price", price[i]);
                                hashMap.put("time", time[i]);
                                arrayList.add(hashMap);
                            }
                            mineRoomOrderAdapter = new MineRoomOrderAdapter(context, arrayList);
                            listView.setAdapter(mineRoomOrderAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_catering_order, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initView();
        if (NetUtil.isConnnected(context)) {
            Log.e("it is ok net","ok");
            new MineRequest(context, handler).myCateringOrder();
        }
    }

    private void initView() {
        listView = (ListView) getView().findViewById(R.id.lv_mine_catering_order);
    }

    @Override
    public void onClick(View v) {
        
    }
}
