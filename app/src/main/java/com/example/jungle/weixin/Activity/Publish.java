package com.example.jungle.weixin.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import me.nereo.multi_image_selector.MultiImageSelector;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.jungle.weixin.Adapter.ExpressionAdapter;
import com.example.jungle.weixin.LBSApplication.LocationApplication;
import com.example.jungle.weixin.R;

import java.util.ArrayList;


public class Publish extends AppCompatActivity {

    private ImageButton back;
    private EditText content;
    private ImageButton chooseImage;
    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private GridView expGridView;
    private ExpressionAdapter expressionAdapter;

    private LocationClient mLocationClient;
    private Button location;
    private ArrayList<String> mSelectPath;
    private static ArrayList<ImageView> imageViewArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        back = (ImageButton)findViewById(R.id.back);
        content = (EditText)findViewById(R.id.content);
        chooseImage = (ImageButton)findViewById(R.id.chooseImage);
        location = (Button)findViewById(R.id.location);
        expGridView = (GridView)findViewById(R.id.expGridView);
        initImageViewList();

        //expressionFromAdapter
        expressionAdapter = new ExpressionAdapter(this);
        expGridView.setAdapter(expressionAdapter);
        expGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpannableString spannableString = new SpannableString(view.getTag().toString());
                Drawable drawable = ContextCompat.getDrawable(Publish.this,(int) expressionAdapter.getItemId(position));
                drawable.setBounds(0, 0, 70, 70);
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
                spannableString.setSpan(imageSpan, 0, view.getTag().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                content.getText().insert(content.getSelectionStart(), spannableString);
            }
        });

        mLocationClient = ((LocationApplication)getApplication()).mLocationClient;
        ((LocationApplication)getApplication()).mLocationResult = location;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
        });
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pickImage();
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitLocation();
                mLocationClient.start();
            }
        });
    }
    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else {
            MultiImageSelector selector = MultiImageSelector.create(Publish.this);
            selector.count(9);
            selector.multi();
            selector.origin(mSelectPath);//显示上次已选图片
            selector.start(Publish.this, REQUEST_IMAGE);
        }
    }
    private void requestPermission(final String permission, String rationale, final int requestCode){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Publish.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    private void display(ArrayList<String> mSelectPath) {
        for(int i= 0 ;i<imageViewArrayList.size();i++){
            imageViewArrayList.get(i).setVisibility(View.GONE);
        }
        for(int i = 0;i<mSelectPath.size();i++){
            imageViewArrayList.get(i).setVisibility(View.VISIBLE);
            imageViewArrayList.get(i).setImageBitmap(BitmapFactory.decodeFile(mSelectPath.get(i)));
        }
    }
    private void initImageViewList(){
        imageViewArrayList = new ArrayList<>();
        imageViewArrayList.add((ImageView)findViewById(R.id.first));
        imageViewArrayList.add((ImageView)findViewById(R.id.second));
        imageViewArrayList.add((ImageView)findViewById(R.id.third));
        imageViewArrayList.add((ImageView)findViewById(R.id.fourth));
        imageViewArrayList.add((ImageView)findViewById(R.id.fifth));
        imageViewArrayList.add((ImageView)findViewById(R.id.sixth));
        imageViewArrayList.add((ImageView)findViewById(R.id.seventh));
        imageViewArrayList.add((ImageView)findViewById(R.id.eighth));
        imageViewArrayList.add((ImageView)findViewById(R.id.ninth));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                //显示图片逻辑
                display(mSelectPath);
            }
        }
    }
    private void InitLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setCoorType("bd09ll");//设置百度经纬度坐标系格式
        option.setScanSpan(1000);//设置发起定位请求的间隔时间为1000ms
        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
        mLocationClient.setLocOption(option);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }
}
