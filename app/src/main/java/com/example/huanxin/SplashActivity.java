package com.example.huanxin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.huanxin.user.LoginFragment;
import com.example.huanxin.user.RegisterFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SplashActivity extends AppCompatActivity {
    private LoginFragment mLoginFragment;
    private RegisterFragment mRegisterFragment;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.button_login)
    public void showLoginDialog() {
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
        }
        mLoginFragment.show(getSupportFragmentManager(), null);
    }

    @OnClick(R.id.button_register)
    public void showRegisterDialog() {
        if (mRegisterFragment == null) {
            mRegisterFragment = new RegisterFragment();
        }
        mRegisterFragment.show(getSupportFragmentManager(), null);
    }













}
