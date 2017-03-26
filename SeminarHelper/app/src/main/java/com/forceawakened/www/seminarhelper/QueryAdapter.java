package com.forceawakened.www.seminarhelper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by forceawakened on 24/3/17.
 */
public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {
    Context context;
    ArrayList<String> arrayList;

    public QueryAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        arrayList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = arrayList.get(position).substring(1);
        Character type = arrayList.get(position).charAt(0);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        Resources res = context.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, res.getDisplayMetrics());
        if(type == '1'){
            params.setMarginStart(px);
            holder.t1.setVisibility(View.GONE);
            holder.t2.setText(text);
            holder.t2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.t2.setBackgroundColor(context.getResources().getColor(R.color.lightgreen));
        }
        else if (type == '2'){
            params.setMarginEnd(px);
            String text1, text2;
            int idx = text.lastIndexOf("&^%");
            text1 = text.substring(0, idx);
            text2 = text.substring(idx+3, text.length());
            holder.t1.setText(text1);
            holder.t2.setText(text2);
        }
        holder.layout.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1, t2;
        FrameLayout layout;
        public ViewHolder(View view) {
            super(view);
            layout = (FrameLayout) view.findViewById(R.id.frame_layout);
            t1 = (TextView) view.findViewById(R.id.textview1);
            t2 = (TextView) view.findViewById(R.id.textview2);
        }
    }
}
