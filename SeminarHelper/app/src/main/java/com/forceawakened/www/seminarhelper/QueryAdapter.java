package com.forceawakened.www.seminarhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = arrayList.get(position);
        holder.textView.setText(text.substring(1));
        if(text.charAt(0) == '1'){
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        else{
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.cyan));
        }
        holder.textView.setPadding(8, 8, 8, 8);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }
    }
}
