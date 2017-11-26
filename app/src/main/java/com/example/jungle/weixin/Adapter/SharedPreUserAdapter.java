package com.example.jungle.weixin.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jungle.weixin.Bean.BaseBean.SharedPreUser;
import com.example.jungle.weixin.R;
import java.util.List;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.deleteUser;
import static com.example.jungle.weixin.PublicUtils.sharedPreUtils.getAllUser;


/**
 * Created by chf on 2017/11/25.
 */

public class SharedPreUserAdapter extends ArrayAdapter<SharedPreUser>{
    private int itemId;
    private SharedPreferences sp;
    private List<SharedPreUser> list;
    private Context mContext;
    public SharedPreUserAdapter(Context context, int id, List<SharedPreUser> list, SharedPreferences sp) {
        super(context,id,list);
        this.mContext = context;
        this.itemId = id;
        this.sp = sp;
        this.list = list;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final SharedPreUser user = getItem(position);
        View view;
        ViewHolder holder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(itemId,parent,false);
            holder = new ViewHolder();
            holder.head = (ImageView) view.findViewById(R.id.head);
            holder.user_name = (TextView) view.findViewById(R.id.user_name);
            holder.delete = (ImageButton) view.findViewById(R.id.delete);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.user_name.setText(user.getUserName());
        Glide.with(mContext).load(user.getHead_url()).into(holder.head);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //因为list指向usermanager中的用户列表因此list内容发生变化，即可notifydata。。。
                list.remove(deleteUser(sp,user.getUid()));
                notifyDataSetChanged();
            }
        });
        return view;
    }
    class ViewHolder{
        ImageView head;
        TextView user_name;
        ImageButton delete;
    }
}
