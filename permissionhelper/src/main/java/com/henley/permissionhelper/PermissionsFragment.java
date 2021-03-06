package com.henley.permissionhelper;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 用于权限请求的{@link Fragment}
 *
 * @author Henley
 * @date 2017/7/27 19:07
 */
public final class PermissionsFragment extends Fragment {

    private int requestCode;
    private OnRequestResultListener resultListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // 在配置变化的时候将这个fragment保存下来
    }

    public void setResultListener(OnRequestResultListener listener) {
        this.resultListener = listener;
    }

    @TargetApi(Build.VERSION_CODES.M)
    void requestPermissions(int requestCode, @NonNull String[] permissions) {
        this.requestCode = requestCode;
        requestPermissions(permissions, requestCode);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != this.requestCode) {
            return;
        }
        Collection<String> grantedPermissions = new LinkedList<>();
        Collection<String> deniedPermissions = new LinkedList<>();
        for (String permission : permissions) {
            if (PermissionHelper.hasPermission(getContext(), permission)) {
                grantedPermissions.add(permission);
            } else {
                deniedPermissions.add(permission);
            }
        }
        if (resultListener != null) {
            resultListener.onRequestResultListener(grantedPermissions, deniedPermissions);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        resultListener = null;
    }

}
