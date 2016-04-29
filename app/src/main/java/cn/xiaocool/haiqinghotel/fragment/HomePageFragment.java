package cn.xiaocool.haiqinghotel.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.main.homepage.ContactUsActivity;
import cn.xiaocool.haiqinghotel.utils.IntentUtils;

/**
 * Created by wzh on 2016/4/28.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout btnLocation,btnContact,btn_Details;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        context = getActivity();
    }

    private void initView() {
        btnLocation = (RelativeLayout) getView().findViewById(R.id.home_btn_location);
        btnLocation.setOnClickListener(this);
        btnContact = (RelativeLayout) getView().findViewById(R.id.home_btn_contact_us);
        btnContact.setOnClickListener(this);
        btn_Details = (RelativeLayout) getView().findViewById(R.id.home_btn_details);
        btn_Details.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_btn_contact_us:
                IntentUtils.getIntent((Activity) context, ContactUsActivity.class);
                break;
        }
    }
}
