package com.example.testapp1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.testapp1.R;

public class HomeFragment extends Fragment  implements View.OnClickListener,View.OnLongClickListener {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final Button button = (Button) root.findViewById(R.id.homebutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"click",Toast.LENGTH_LONG).show();
                homeViewModel.setText("abc");
                //homeViewModel.getText()
            }
        });
        button.setOnLongClickListener(this);
        final TextView tv_marquee = (TextView) root.findViewById(R.id.text_home);
        // 给tv_marquee设置点击监听器
        //tv_marquee.setOnClickListener(this);
        tv_marquee.requestFocus(); // 强制获得焦点，让跑马灯滚起来

        tv_marquee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_marquee.setFocusable(!tv_marquee.hasFocusable());
                if(tv_marquee.hasFocusable()){
                    tv_marquee.setFocusableInTouchMode(true); // 允许在触摸时获得焦点
                    tv_marquee.requestFocus();
                }
            }
        });
        tv_marquee.setOnLongClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),"onclick",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(getActivity(),"long click",Toast.LENGTH_SHORT).show();
        return false;
    }
}