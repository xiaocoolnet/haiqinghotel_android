package cn.xiaocool.haiqinghotel.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.HashMap;

import cn.xiaocool.haiqinghotel.R;
import cn.xiaocool.haiqinghotel.net.constant.NetBaseConstant;

/**
 * Created by wzh on 2016/5/18.
 */
public class HomePageReserveAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<HashMap<String, String>> arrayList;
    private DisplayImageOptions displayImageOptions;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    public HomePageReserveAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
        displayImageOptions = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .showImageOnLoading(R.mipmap.default_loading).showImageOnFail(R.mipmap.default_loading)
                .cacheInMemory(true).cacheOnDisc(true).build();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.homepage_reserve_item, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_home_reserve);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_home_roomName);
        TextView tvRepast = (TextView) convertView.findViewById(R.id.tv_home_repast);
        TextView tvInfor = (TextView) convertView.findViewById(R.id._tv_home_reserve_infor);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_home_reserve_price);
        TextView tvOprice = (TextView) convertView.findViewById(R.id.tv_home_reserve_oprice);
        String pic = arrayList.get(position).get("pic").toString();
        String name = arrayList.get(position).get("name");
        String price = arrayList.get(position).get("price");
        String oprice = arrayList.get(position).get("oprice");
        String acreage = arrayList.get(position).get("acreage");
        String repast = arrayList.get(position).get("repast");
        String bedsize = arrayList.get(position).get("bedsize");
        String adtitle = arrayList.get(position).get("adtitle");
        String floor = arrayList.get(position).get("floor");
        tvName.setText(name);
        tvPrice.setText(price);
        tvOprice.setText(oprice);
        tvInfor.setText(acreage + bedsize + adtitle + floor);
        if (repast.equals("1")) {
            tvRepast.setText("有早餐");
        }else if (repast.equals("2")){
            tvRepast.setText("无早餐");
        }
        imageLoader.displayImage(NetBaseConstant.NET_PIC_PREFIX + pic, imageView, displayImageOptions);
        return convertView;
    }
}
