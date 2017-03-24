package com.forceawakened.www.seminarhelper;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HomePageFragment extends Fragment implements MainActivity.Callback {
    private ArrayList<String> arrayList;
    private HomePageAdapter adapter;
    String filename = "home.txt";

    public HomePageFragment() {
        arrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomePageAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        (new LoadData()).execute();
        return view;
    }


    @Override
    public void sendMessage(String s) {
        arrayList.add(s);
        adapter.notifyItemInserted(arrayList.size()-1);
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
                    while ((line = br.readLine()) != null) {
                        arrayList.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                result = false;
                System.out.println("HomePageFragment: file not found!");
                e.printStackTrace();
            } catch (IOException e) {
                result = false;
                System.out.println("HomePageFragment: i/o error!");
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
        }
    }
}
