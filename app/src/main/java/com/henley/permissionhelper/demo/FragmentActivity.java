package com.henley.permissionhelper.demo;


import android.app.FragmentManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 在Fragment中请求权限
 *
 * @author Henley
 * @date 2017/7/28 15:54
 */
public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.framelayout, new PermissionFragment())
                .commitAllowingStateLoss();
    }
}
