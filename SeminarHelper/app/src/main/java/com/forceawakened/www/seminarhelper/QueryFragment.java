package com.forceawakened.www.seminarhelper;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class QueryFragment extends Fragment {
    private ArrayList<String> arrayList;
    private QueryAdapter adapter;
    private EditText msgText;
    private Button sendBtn;
    private String email;
    private String filename = "query.txt";
    private SwipeRefreshLayout swipeRefreshLayout;

    public QueryFragment() {
        arrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        email = getArguments().getString("EMAIL");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        msgText = (EditText) view.findViewById(R.id.edit_text);
        sendBtn = (Button) view.findViewById(R.id.send);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = String.valueOf(msgText.getText());
                if(msg == null || "".equals(msg) || "\n".equals(msg)){
                    return;
                }
                msg = msg.replace('\n', ' ');
                SocketHandler.send("SEND:" + email + "::#" + msg);
                msgText.setText("");
                Toast.makeText(sendBtn.getContext(), "Message Sent.", Toast.LENGTH_SHORT).show();
                try {
                    OutputStream out = getActivity().openFileOutput(filename, Context.MODE_APPEND);
                    out.write(('1' + msg + '\n').getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QueryAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                (new LoadData()).execute();
            }
        });
        (new LoadData()).execute();
        return view;
    }

    public class LoadData extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            try {
                File file = getContext().getFileStreamPath(filename);
                if(file.exists()) {
                    FileInputStream fis = getActivity().openFileInput(filename);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    arrayList.clear();
                    while ((line = br.readLine()) != null) {
                        arrayList.add(line);
                        System.out.print(line);
                    }
                }
            } catch (FileNotFoundException e) {
                result = false;
                System.out.println("QueryFragment: file not found!");
                e.printStackTrace();
            } catch (IOException e) {
                result = false;
                System.out.println("QueryFragment: i/o error!");
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                adapter.notifyDataSetChanged();
            }
            else{
                //do stuff
            }
            if(swipeRefreshLayout.isRefreshing()){
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }
}
