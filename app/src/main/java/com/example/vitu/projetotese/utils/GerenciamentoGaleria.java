package com.example.vitu.projetotese.utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by vitu on 11/04/2018.
 */

public class GerenciamentoGaleria {


    public static void criarPastasParaSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File folder = new File(Environment.getExternalStorageDirectory() + "/AppTese/" + "/Fotos");
            File folder1 = new File(Environment.getExternalStorageDirectory() + "/AppTese/" + "/Galeria");
            File folder2 = new File(Environment.getExternalStorageDirectory() + "/AppTese/" + "/Anexos");

            if (!folder.exists() && !folder1.exists() && !folder2.exists())
                folder.mkdirs();
                folder1.mkdir();
                folder2.mkdir();
        }
    }


}
