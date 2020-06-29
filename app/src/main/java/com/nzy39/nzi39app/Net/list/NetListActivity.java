package com.nzy39.nzi39app.Net.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nzy39.nzi39app.Net.HttpProxy;
import com.nzy39.nzi39app.Net.NetInputUtils;
import com.nzy39.nzi39app.Net.SignActivity;
import com.nzy39.nzi39app.Net.WebActivity;
import com.nzy39.nzi39app.Net.bean.VideoInfo;
import com.nzy39.nzi39app.Net.bean.VideoListResponse;
import com.nzy39.nzi39app.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NetListActivity extends AppCompatActivity {
    private static final String TAG = "NetListActivity";
    private VideoAdapter mAdapter;
    private Handler mHandler = new Handler();
    private List<VideoInfo> mDataList;


    private TextView mTextName;
    private TextView mSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_list);

        ListView mListView = findViewById(R.id.lv);
        View headLayout = buildListHeader();

        mTextName = headLayout.findViewById(R.id.txt_name);
        mSign = headLayout.findViewById(R.id.txt_sign);

        mListView.addHeaderView(headLayout);
        mDataList = new ArrayList<>();
        mAdapter = new VideoAdapter(mDataList, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                VideoInfo videoInfo = mDataList.get(position-1);
                Intent intent = new Intent(NetListActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.WEB_URL, videoInfo.getFilePath());
                startActivity(intent);

            }
        });


        initData();

    }

    private View buildListHeader() {
        View headLayout= LayoutInflater.from(this).inflate(R.layout.layout_header,null);
        TextView signTV=headLayout.findViewById(R.id.txt_sign);
        signTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NetListActivity.this, SignActivity.class);
                startActivityForResult(intent, 11);
            }
        });
        return headLayout;
    }
    private void initData() {
        /*String raUrl = "http://ramedia.sinaapp.com/videolist.json";*/
        String movieUrl = "http://ramedia.sinaapp.com/res/DouBanMovie2.json";
        HttpProxy.getInstance().load(movieUrl, new HttpProxy.NetInputCallback() {
            @Override
            public void onSuccess(InputStream inputStream) {
                String respJson = null;
                try {
                    respJson = NetInputUtils.readString(inputStream);
                    Log.i(TAG, "----response json:\n" + respJson);
                    VideoListResponse videoListResponse = convertJsonToBean(respJson);
                    final List<VideoInfo> list = videoListResponse.getList();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setData(list);

                            mAdapter.notifyDataSetChanged();

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
     /* Intent intent = getIntent();
        mTextName.setText(intent.getStringExtra("loginName"));
*/
    }

    private VideoListResponse convertJsonToBean(String json) {
        Gson gson = new Gson();
        VideoListResponse response = gson.fromJson(json, VideoListResponse.class);
       return response;
    }
  protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 11:{
                if (RESULT_OK == resultCode){
                    String sign = data.getStringExtra("mySign");
                    mSign.setText(sign);
                }else {
                    Toast.makeText(this,"取消设置", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

}
