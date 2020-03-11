package com.example.testapp1.ui.dashboard;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.testapp1.R;
import com.example.testapp1.util.PermissionUtil;
import com.example.testapp1.widget.AudioPlayer;
import com.example.testapp1.widget.AudioRecorder;

public class DashboardFragment extends Fragment implements AudioRecorder.OnRecordFinishListener {

    private DashboardViewModel dashboardViewModel;


    private static final String TAG = "AudioActivity";
    private AudioRecorder ar_music; // 声明一个音频录制器对象
    private AudioPlayer ap_music; // 声明一个音频播放器对象


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1){

        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ar_music = root.findViewById(R.id.ar_music);
        // 给音频录制器设置录制完成监听器
        ar_music.setOnRecordFinishListener(this);
        // 从布局文件中获取名叫ap_music的音频播放器
        ap_music = root.findViewById(R.id.ap_music);

        PermissionUtil.checkMultiPermission(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        return root;
    }

    @Override
    public void onRecordFinish() {
        // 延迟1秒后启动准备播放任务，好让系统有时间生成音频文件
        mHandler.postDelayed(mPreplay, 1000);
    }

    private Handler mHandler = new Handler();
    // 定义一个准备播放任务
    private Runnable mPreplay = new Runnable() {
        @Override
        public void run() {
            // 为音频播放器初始化待播放的音频文件
            ap_music.init(ar_music.getRecordFilePath());
        }
    };
}