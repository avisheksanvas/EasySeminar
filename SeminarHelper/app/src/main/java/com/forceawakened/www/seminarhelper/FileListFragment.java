package com.forceawakened.www.seminarhelper;

import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileListFragment extends Fragment implements FileListAdapter.Callback{
    private FileListAdapter adapter;
    private ArrayList<String> arrayList;
    String filename="filelist.txt";

    public FileListFragment() {
        arrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FileListAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        (new LoadData()).execute();
        return view;
    }

    @Override
    public void call(Integer id) {
        String myFile = arrayList.get(id);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("googlechrome://navigate?url=" + myFile));
        startActivity(browserIntent);
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
                System.out.println("FileListFragment: file not found!");
                e.printStackTrace();
            } catch (IOException e) {
                result = false;
                System.out.println("FileListFragment: i/o error!");
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

