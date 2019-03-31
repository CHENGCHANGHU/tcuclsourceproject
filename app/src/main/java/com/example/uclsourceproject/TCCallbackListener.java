package com.example.uclsourceproject;

import android.net.Uri;

import java.io.File;

public interface TCCallbackListener {

    void jump(Uri uri, File file, int requestCode);
}
