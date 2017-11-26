package com.example.jungle.weixin.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;

import me.nereo.multi_image_selector.MultiImageSelector;
import retrofit2.Response;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.jungle.weixin.Adapter.ExpressionAdapter;
import com.example.jungle.weixin.Bean.XHRBase.ErrorData;
import com.example.jungle.weixin.Bean.XHRBase.XHRBaseBean;
import com.example.jungle.weixin.LBSApplication.LocationApplication;
import com.example.jungle.weixin.R;
import com.example.jungle.weixin.RetrofitUtil.H5Service;
import com.example.jungle.weixin.RetrofitUtil.HttpResultSubscriber;
import com.example.jungle.weixin.RetrofitUtil.NetRequestFactory;
import com.example.jungle.weixin.RetrofitUtil.Transform;

import java.util.ArrayList;

import static com.example.jungle.weixin.PublicUtils.StringUtils.transformPublish;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getCurrent;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getSp;


public class Publish extends BaseActivity implements View.OnClickListener {

    private ImageButton back;
    private EditText content;
    private ImageButton chooseImage;
    private ImageButton hot;
    private ImageButton send;
    private static final int REQUEST_IMAGE = 2;
    private static final int REQUEST_HOTTOPIC = 1;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private GridView expGridView;
    private ExpressionAdapter expressionAdapter;

    private LocationClient mLocationClient;
    private Button location;
    private ArrayList<String> mSelectPath;
    private static ArrayList<ImageView> imageViewArrayList;
    private static ArrayList<RelativeLayout> imageRelativeList;
    private static ArrayList<ImageButton> imageButtonArrayList;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        sp = getSp(this);
        back = (ImageButton) findViewById(R.id.back);
        content = (EditText) findViewById(R.id.content);
        chooseImage = (ImageButton) findViewById(R.id.chooseImage);
        location = (Button) findViewById(R.id.location);
        hot = (ImageButton) findViewById(R.id.hot);
        send = (ImageButton) findViewById(R.id.send);
        expGridView = (GridView) findViewById(R.id.expGridView);
        initImageViewList();
        Intent intent = getIntent();
        if (intent.getIntExtra("previousActivity", -1) == 1) {
            //判断来源是否为微博detail的分享到微博按钮
            content.setText(intent.getStringExtra("Body"));
        }

        //expressionFromAdapter
        expressionAdapter = new ExpressionAdapter(this);
        expGridView.setAdapter(expressionAdapter);
        expGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpannableString spannableString = new SpannableString(view.getTag().toString());
                Drawable drawable = ContextCompat.getDrawable(Publish.this, (int) expressionAdapter.getItemId(position));
                drawable.setBounds(0, 0, 70, 70);
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
                spannableString.setSpan(imageSpan, 0, view.getTag().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                content.getText().insert(content.getSelectionStart(), spannableString);
            }
        });

        mLocationClient = ((LocationApplication) getApplication()).mLocationClient;
        ((LocationApplication) getApplication()).mLocationResult = location;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
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
        content.addTextChangedListener(new TextWatcher() {
            String original;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                original = content.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                content.append(transformPublish(Publish.this, content, original));
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!content.getText().equals(null)){
                    NetRequestFactory.getInstance().createService(H5Service.class).send("test", content.getText().toString())
                            .compose(Transform.<Response<XHRBaseBean<String>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<XHRBaseBean<String>>>() {
                        @Override
                        public void onSuccess(Response<XHRBaseBean<String>> xhrBaseBeanResponse) {
                            if (xhrBaseBeanResponse.body().getStatus() == 1) {
                                Toast.makeText(Publish.this, "发表成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                String errorCode = xhrBaseBeanResponse.body().getException().getError_code();
                                if (errorCode.equals("403")) {
                                    //根据token获取不了正确的cookie需要重新登录
                                    LayoutInflater layoutInflater = LayoutInflater.from(Publish.this); // 创建视图容器并设置上下文
                                    final View view = layoutInflater.inflate(R.layout.loginalterdialog, null); // 获取布局文件的视图
                                    final AlertDialog.Builder temp = new AlertDialog.Builder(Publish.this);
                                    final AlertDialog a = temp.setTitle("登录授权").setView(view).show();
                                    Button ensure = (Button) view.findViewById(R.id.ensure);
                                    Button cancel = (Button) view.findViewById(R.id.cancel);
                                    final EditText userName = (EditText) view.findViewById(R.id.userName);
                                    final EditText password = (EditText) view.findViewById(R.id.password);
                                    ensure.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            NetRequestFactory.getInstance().createService(H5Service.class).login(userName.getText().toString(), password.getText().toString())
                                                    .compose(Transform.<Response<XHRBaseBean<String>>>defaultSchedulers()).subscribe(new HttpResultSubscriber<Response<XHRBaseBean<String>>>() {
                                                @Override
                                                public void onSuccess(Response<XHRBaseBean<String>> xhrBaseBeanResponse) {
                                                    if (xhrBaseBeanResponse.body().getStatus() == 1) {
                                                        Toast.makeText(Publish.this, "登录成功，请重新提交", Toast.LENGTH_SHORT).show();
                                                        a.dismiss();
                                                    } else {
                                                        Toast.makeText(Publish.this, "登录失败", Toast.LENGTH_SHORT).show();
                                                        a.dismiss();
                                                    }
                                                }

                                                @Override
                                                public void _onError(Response<XHRBaseBean<String>> xhrBaseBeanResponse) {

                                                }

                                            });
                                        }
                                    });
                                    cancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            a.dismiss();
                                        }
                                    });
                                }
                            }

                        }
                    });
                }else {
                    Toast.makeText(Publish.this, "请输入微博内容", Toast.LENGTH_SHORT).show();
                }

            }
        });
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Publish.this, HotTopicActivity.class);
                startActivityForResult(intent, REQUEST_HOTTOPIC);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
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
        } else {
            MultiImageSelector selector = MultiImageSelector.create(Publish.this);
            selector.count(9);
            selector.multi();
            selector.origin(mSelectPath);//显示上次已选图片
            selector.start(Publish.this, REQUEST_IMAGE);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
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
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    private void display(ArrayList<String> mSelectPath) {
        for (int i = 0; i < imageRelativeList.size(); i++) {
            imageRelativeList.get(i).setVisibility(View.GONE);
        }
        for (int i = 0; i < mSelectPath.size(); i++) {
            imageRelativeList.get(i).setVisibility(View.VISIBLE);
            imageViewArrayList.get(i).setImageBitmap(BitmapFactory.decodeFile(mSelectPath.get(i)));
        }
    }

    private void initImageViewList() {
        imageViewArrayList = new ArrayList<>();
        imageRelativeList = new ArrayList<>();
        imageButtonArrayList = new ArrayList<>();
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.firstItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(0).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(0).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            imageButtonArrayList.get(0).setTag(0);
            imageButtonArrayList.get(0).setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.secondItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(1).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(1).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(1);
            temp.setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.thirdItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(2).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(2).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(2);
            temp.setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.fourthItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(3).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(3).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(3);
            temp.setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.fifthItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(4).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(4).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(4);
            temp.setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.sixthItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(5).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(5).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(5);
            temp.setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.seventhItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(6).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(6).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(6);
            temp.setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.eighthItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(7).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(7).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(7);
            temp.setOnClickListener(this);
        }
        {
            imageRelativeList.add((RelativeLayout) findViewById(R.id.ninthItem));
            ImageButton temp = (ImageButton) imageRelativeList.get(8).findViewById(R.id.imageItemButton);
            imageViewArrayList.add((ImageView) imageRelativeList.get(8).findViewById(R.id.imageItem));
            imageButtonArrayList.add(temp);
            temp.setTag(8);
            temp.setOnClickListener(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                //显示图片逻辑
                display(mSelectPath);
            }
        } else if (requestCode == REQUEST_HOTTOPIC) {
            if (resultCode == RESULT_OK)
                content.getText().insert(content.getSelectionStart(), data.getStringExtra("title"));
        }
    }

    private void InitLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setCoorType("bd09ll");//设置百度经纬度坐标系格式
        option.setScanSpan(1000);//设置发起定位请求的间隔时间为1000ms
        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onClick(View v) {
        int id = (int) v.getTag();
        switch (id) {
            case 0:
                mSelectPath.remove(0);
                display(mSelectPath);
                break;
            case 1:
                mSelectPath.remove(1);
                display(mSelectPath);
                break;
            case 2:
                mSelectPath.remove(2);
                display(mSelectPath);
                break;
            case 3:
                mSelectPath.remove(3);
                display(mSelectPath);
                break;
            case 4:
                mSelectPath.remove(4);
                display(mSelectPath);
                break;
            case 5:
                mSelectPath.remove(5);
                display(mSelectPath);
                break;
            case 6:
                mSelectPath.remove(6);
                display(mSelectPath);
                break;
            case 7:
                mSelectPath.remove(7);
                display(mSelectPath);
                break;
            case 8:
                mSelectPath.remove(8);
                display(mSelectPath);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

}
