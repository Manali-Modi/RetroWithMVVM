package com.example.retrowithmvvm.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrowithmvvm.R;
import com.example.retrowithmvvm.databinding.ActivityMainBinding;
import com.example.retrowithmvvm.interfaces.UserLoginResponseInterface;
import com.example.retrowithmvvm.viewmodel.UserLoginViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements UserLoginResponseInterface {

    ActivityMainBinding mainBinding;
    UserLoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        loginViewModel = ViewModelProviders.of(this).get(UserLoginViewModel.class);

        initListeners();
    }

    private void initListeners() {
        setTextChangeListener(mainBinding.etUsername);
        setTextChangeListener(mainBinding.etPwd);

        mainBinding.btnLogin.setOnClickListener(view -> {
            setOrRemoveEditTextError(mainBinding.etUsername);
            setOrRemoveEditTextError(mainBinding.etPwd);
            if(mainBinding.etUsername.getText().toString()!= null && mainBinding.etPwd.getText().toString()!=null){
                loginViewModel.getUserLoginStatus(this,mainBinding.etUsername.getText().toString(), mainBinding.etPwd.getText().toString());
            }
        });
    }

    void setTextChangeListener(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setOrRemoveEditTextError(editText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setOrRemoveEditTextError(EditText editText) {
        if (editText.getId() == R.id.et_username) {
            if (mainBinding.etUsername.getText().length() == 0) {
                mainBinding.txtUserError.setText(R.string.userError);
                mainBinding.etUsername.setBackground(getDrawable(R.drawable.error_bg));
            } else {
                mainBinding.txtUserError.setText("");
                mainBinding.etUsername.setBackground(getDrawable(R.drawable.edit_text_bg));
            }
        }

        if (editText.getId() == R.id.et_pwd) {
            if (mainBinding.etPwd.getText().length() < 6) {
                mainBinding.txtPwdError.setText(R.string.pwdError);
                mainBinding.etPwd.setBackground(getDrawable(R.drawable.error_bg));
            } else {
                mainBinding.txtPwdError.setText("");
                mainBinding.etPwd.setBackground(getDrawable(R.drawable.edit_text_bg));
            }
        }
    }

    @Override
    public void onApiResponse(String responseMessage) {
        Toast.makeText(MainActivity.this, responseMessage, Toast.LENGTH_LONG).show();
    }
}