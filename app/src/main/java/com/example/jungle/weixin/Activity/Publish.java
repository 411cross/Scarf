package com.example.jungle.weixin.Activity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
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


public class Publish extends AppCompatActivity {

    private ImageButton back;
    private EditText content;
    private ImageButton chooseImage;
    private int imageNumber = 0;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private GridView expGridView;
    private ExpressionAdapter expressionAdapter;

    private LocationClient mLocationClient;
    private Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        back = (ImageButton)findViewById(R.id.back);
        content = (EditText)findViewById(R.id.content);
        chooseImage = (ImageButton)findViewById(R.id.chooseImage);
        location = (Button)findViewById(R.id.location);
        expGridView = (GridView)findViewById(R.id.expGridView);

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
            }
        });
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageNumber < 9){
                    openAlbum();
                }
                else {
                    Toast.makeText(Publish.this, "图片数量不能超过9张", Toast.LENGTH_SHORT).show();
                }
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
    private void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PHOTO_REQUEST_GALLERY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        switch (requestCode){
            case PHOTO_REQUEST_GALLERY :
                if(resultCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT  >= 19){
                        //安卓4.4及以上
                        hadleImageOnKiKat(data);
                    }
                    else {
                        //其他
                        handleImageBeforeKiKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private void hadleImageOnKiKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId =DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        display(imagePath);
    }
    private void handleImageBeforeKiKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        display(imagePath);
    }

    private void display(String imagePath) {
        ImageView temp;
        if(imagePath != null){
            switch (imageNumber){
                case 0:
                    temp = (ImageView)findViewById(R.id.first);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 1:
                    temp = (ImageView)findViewById(R.id.second);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 2:
                    temp = (ImageView)findViewById(R.id.third);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 3:
                    temp = (ImageView)findViewById(R.id.fourth);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 4:
                    temp = (ImageView)findViewById(R.id.fifth);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 5:
                    temp = (ImageView)findViewById(R.id.sixth);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 6:
                    temp = (ImageView)findViewById(R.id.seventh);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 7:
                    temp = (ImageView)findViewById(R.id.eighth);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                case 8:
                    temp = (ImageView)findViewById(R.id.ninth);
                    temp.setVisibility(View.VISIBLE);
                    temp.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                    imageNumber ++;
                    break;
                default:
                    break;
            }
        }else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void InitLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setCoorType("bd09ll");//设置百度经纬度坐标系格式
        option.setScanSpan(1000);//设置发起定位请求的间隔时间为1000ms
        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
        mLocationClient.setLocOption(option);
    }
}
