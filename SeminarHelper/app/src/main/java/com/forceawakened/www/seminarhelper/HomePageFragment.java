package com.forceawakened.www.seminarhelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class HomePageFragment extends Fragment{
    private ArrayList<String> arrayList;
    private HomePageAdapter adapter;
    private String filename = "home.txt";
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean to_translate ;
    public HomePageFragment() {
        arrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        to_translate = false ;
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomePageAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                (new LoadData()).execute();
            }
        });
        (new LoadData()).execute();
        setHasOptionsMenu(true);

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
                        if( to_translate ) {
                            line = translate(line);
                        }
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
            if(swipeRefreshLayout.isRefreshing()){
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }
    public String translate(String text) {
        String ans = null;
        try {
            String query = URLEncoder.encode(text, "UTF-8");
            String langpair = URLEncoder.encode("en|hi", "UTF-8");
            String url = "http://mymemory.translated.net/api/get?q="+query+"&langpair="+langpair;
            HttpClient hc = new DefaultHttpClient();
            HttpGet hg = new HttpGet(url);
            HttpResponse hr = hc.execute(hg);
            if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                JSONObject response = new JSONObject(EntityUtils.toString(hr.getEntity()));
                ans = response.getJSONObject("responseData").getString("translatedText");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans ;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater )
    {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.language, menu );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId() ;
        if( id == R.id.lang_hindi)
        {
            to_translate = true ;
            return true ;
        }
        else if( id == R.id.lang_english )
        {
            to_translate = false ;
            return true ;
        }
        return super.onOptionsItemSelected(item) ;
    }
}
