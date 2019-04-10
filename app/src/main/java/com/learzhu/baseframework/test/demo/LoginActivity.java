package com.learzhu.baseframework.test.demo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.learzhu.baseframework.R;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

public class LoginActivity extends AppCompatActivity implements ICommonView {

    //    @BindView(R.id.login)
//    Button btn;
    @BindView(R.id.login)
    Button mLoginBtn;

    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DaggerCommonComponent.builder().commonModule(new CommonModule(this))
                .build().inject(this);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.login(new User());
            }
        });
    }

    @Override
    public Context getContext() {
        return null;
    }
}
