package id.kido1611.clickboot.data;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.kido1611.clickboot.R;

/**
 * Created by Kido1611 on 05-May-16.
 */
public class ClickAdapter extends RecyclerView.Adapter<ClickAdapter.MyViewHolder>  {

    private Context mContext;
    private LayoutInflater inflater;

    private ArrayList<ClickItem> mItems = new ArrayList<ClickItem>();

    public ClickAdapter(Context context,ArrayList<ClickItem> items ){
        mContext = context;
        inflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mIcon.setImageDrawable(mItems.get(position).getIcon(mContext));
        holder.mTitle.setText(mItems.get(position).getTitleRes());
        holder.mTitle.setTextColor(Color.GRAY);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView mIcon;
        TextView mTitle;

        public MyViewHolder(View itemView) {
            super(itemView);

            mIcon = (ImageView) itemView.findViewById(R.id.image);
            mTitle = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
