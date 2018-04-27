package com.example.vitu.projetotese.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.vitu.projetotese.R;

/**
 * Created by vitu on 11/04/2018.
 */

public class Permissoes {

    public static final int CAMERA_PERMISSION = 0;
    public static final int GALLERY_PERMISSION = 1;

    private static boolean precisaPermissao(){
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    //CAMERA
    public static boolean temPermissaoCamera(Context context){
        if(precisaPermissao()){
            return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public static void pedirPermissaoCamera(final Activity activity){
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
            new AlertDialog.Builder(activity)
                    .setMessage(activity.getString(R.string.MsgpermissaoCamera))
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, new String[]
                                    {Manifest.permission.CAMERA}, CAMERA_PERMISSION
                            );
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        }
    }

    //GALERIA
    public static boolean temPermissaoGaleria(Context context){
        if(precisaPermissao()){
            return ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public static void pedirPermissaoGaleria(final Activity activity){
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(activity)
                    .setMessage(activity.getString(R.string.MsgpermissaoGaleria))
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, new String[]
                                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                                    , GALLERY_PERMISSION
                            );
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                    , GALLERY_PERMISSION);
        }
    }





}
