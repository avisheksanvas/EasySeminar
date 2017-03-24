package com.forceawakened.www.seminarhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by forceawakened on 24/3/17.
 */

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {
    Callback callback;
    ArrayList<String> arrayList;

    public FileListAdapter(Callback callback, ArrayList<String> list) {
        this.callback = callback;
        arrayList = list;
    }

    @Override
    public FileListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item, parent, false);
        return new FileListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileListAdapter.ViewHolder holder, int position) {
        String text = arrayList.get(position);
        int idx = text.lastIndexOf("/") + 1;
        System.out.println("last index of = " + idx);
        holder.textView.setText(text.substring(idx));
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
            textView.setTextSize(20);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.call(getAdapterPosition());
                }
            });
        }
    }

    public interface Callback{
        void call(Integer id);
    }
}
