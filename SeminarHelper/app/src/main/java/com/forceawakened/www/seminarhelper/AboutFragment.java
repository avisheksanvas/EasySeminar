package com.forceawakened.www.seminarhelper;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements View.OnClickListener {

    TextView abhey, aditya, avishek, prabhat;

    public AboutFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        abhey = (TextView) v.findViewById(R.id.abhey);
        aditya = (TextView) v.findViewById(R.id.aditya);
        avishek = (TextView) v.findViewById(R.id.avishek);
        prabhat = (TextView) v.findViewById(R.id.prabhat);
        abhey.setOnClickListener(this);
        aditya.setOnClickListener(this);
        avishek.setOnClickListener(this);
        prabhat.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        String url = null;
        switch (v.getId()){
            case R.id.abhey:
                url = getResources().getString(R.string.abhey_github);
                break;
            case R.id.aditya:
                url = getResources().getString(R.string.aditya_github);
                break;
            case R.id.avishek:
                url = getResources().getString(R.string.avishek_github);
                break;
            case R.id.prabhat:
                url = getResources().getString(R.string.prabhat_github);
                break;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.setPackage("com.android.chrome");
        startActivity(intent);
    }
}
