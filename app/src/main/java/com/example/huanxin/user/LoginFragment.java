package com.example.huanxin.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.huanxin.HomeActivity;
import com.example.huanxin.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 登陆页面
 * Created by 江鱼跃 on 2016/10/12.
 */

public class LoginFragment extends DialogFragment {

    private Unbinder mUnbinder;

    @BindView(R.id.edit_username)EditText etUserName;
    @BindView(R.id.edit_password) EditText etPassword;
    @BindView(R.id.button_confirm) Button btnConfirm;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置一个对话框是无标题模式
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_login, container, false);
        
        
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
    
    @OnClick(R.id.button_confirm)
    public void  login(){
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        handleLogin(username,password);
    }

    private void handleLogin(final String username,final  String password) {
        startLoading();
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            String info = "用户名或密码不能为空";
            stopLoading();
            showLoginFail(info);
            return;
        }
        EMClient.getInstance().login(username, password, new EMCallBack() {
            //登陆成功
            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                        navigateToHome();
                    }
                });
            }

            //登录失败
            @Override
            public void onError(int i, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopLoading();
                        showLoginFail(s);
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });


    }
    // 视图实现 start ----------------------------------------------
    public void startLoading() {
        btnConfirm.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        setCancelable(false);
    }

    public void stopLoading() {
        btnConfirm.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        setCancelable(true);
    }

    public void showLoginFail(String msg) {
        String info = getString(R.string.user_error_login_fail, msg);
        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
    }

    public void navigateToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    // 视图实现 end ----------------------------------------------

}
