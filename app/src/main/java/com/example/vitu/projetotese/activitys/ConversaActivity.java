package com.example.vitu.projetotese.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.adapters.ChatAtualAdapter;
import com.example.vitu.projetotese.model.ItemChat;
import com.example.vitu.projetotese.utils.Permissoes;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

public class ConversaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textoMensagem;
    private FloatingActionButton fab, fabAnexo, fabFoto, fabGaleria, fabEnviar;
    private Boolean fabIsOpen = false;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_GALLEY_CAPTURE = 2;
    private String idUser, emailUser, idChat;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;
    private RecyclerView recyclerView;
    private ChatAtualAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            idUser = extra.getString("idUser");
            idChat = extra.getString("idChat");
            emailUser = extra.getString("emailUser");
        }

        textoMensagem = (EditText) findViewById(R.id.edit_text_mensagem);
        fab = (FloatingActionButton) findViewById(R.id.fabOpen);
        fabAnexo = (FloatingActionButton) findViewById(R.id.fabAnexo);
        fabFoto = (FloatingActionButton) findViewById(R.id.fabFoto);
        fabGaleria = (FloatingActionButton) findViewById(R.id.fabGaleria);
        fabEnviar = (FloatingActionButton) findViewById(R.id.fabEnviar);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        fab.setOnClickListener(this);
        fabAnexo.setOnClickListener(this);
        fabFoto.setOnClickListener(this);
        fabGaleria.setOnClickListener(this);
        fabEnviar.setOnClickListener(this);

        textoMensagem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(textoMensagem.getText().toString().isEmpty()){
                    fabEnviar.setClickable(false);
                    fabEnviar.setVisibility(View.INVISIBLE);
                    fab.setVisibility(View.VISIBLE);
                    fab.setClickable(true);
                }else {
                    fab.setClickable(false);
                    fab.setVisibility(View.INVISIBLE);
                    fabEnviar.setClickable(true);
                    fabEnviar.setVisibility(View.VISIBLE);
                    if(fabIsOpen)
                        animationFab();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        recyclerView = findViewById(R.id.recyclerView_conversa);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        firebaseFirestore.collection("chats")
                .document(idChat)
                .collection("mensagens")
                .orderBy("data")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e != null){
                            Log.i("Fire", "erro: ", e);
                            return;
                        }
                        List<ItemChat> itens = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots) {
                            ItemChat itemChat = doc.toObject(ItemChat.class);
                            itemChat.setMensagem(doc.getString("mensagem"));
                            itemChat.setIdUserSerder(doc.getString("email"));
                            itemChat.setIdUserSerder(doc.getString("idUserSerder"));
                            itemChat.setTipo(doc.getString("tipo"));
                            itens.add(itemChat);
                        }

                        mAdapter = new ChatAtualAdapter(ConversaActivity.this, itens);
                        recyclerView.setAdapter(mAdapter);
                    }
                });

    }

    private void animationFab(){
        if(fabIsOpen){
            fab.startAnimation(rotate_backward);
            fabAnexo.startAnimation(fab_close);
            fabFoto.startAnimation(fab_close);
            fabGaleria.startAnimation(fab_close);
            fabAnexo.setClickable(false);
            fabFoto.setClickable(false);
            fabGaleria.setClickable(false);
            fabIsOpen = false;
        }else{
            fab.startAnimation(rotate_forward);
            fabAnexo.startAnimation(fab_open);
            fabFoto.startAnimation(fab_open);
            fabGaleria.startAnimation(fab_open);
            fabAnexo.setClickable(true);
            fabFoto.setClickable(true);
            fabGaleria.setClickable(true);
            fabIsOpen = true;
        }

    }

    private void abrirCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fabOpen:

                animationFab();
                break;

            case R.id.fabAnexo:
                Toast.makeText(this, "Galeria Documentos", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fabFoto:
                if(Permissoes.temPermissaoCamera(this)){
                    abrirCamera();
                }else{
                    Permissoes.pedirPermissaoCamera(this);
                }
                break;

            case R.id.fabGaleria:
                if(Permissoes.temPermissaoGaleria(this)){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_GALLEY_CAPTURE);
                }else {
                    Permissoes.pedirPermissaoGaleria(this);
                }
                break;

            case R.id.fabEnviar:
                enviarMensagem();
        }
    }

    private void enviarMensagem(){
        if(!textoMensagem.getText().toString().isEmpty()){
            Calendar dataAtual = Calendar.getInstance();
            //SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            ItemChat itemChat = new ItemChat(textoMensagem.getText().toString(), dataAtual.getTime().toString(), idUser, emailUser, "mensagem");
            firebaseFirestore.collection("chats")
                    .document(idChat)
                    .collection("mensagens")
                    .document()
                    .set(itemChat);
            textoMensagem.setText("");
        }else {
            Toast.makeText(this, "Digite algo", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Permissoes.CAMERA_PERMISSION){
            if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                abrirCamera();
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("Não tem permissao para acessar a camara")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        } else if (requestCode == Permissoes.GALLERY_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Abriu Galeria ", Toast.LENGTH_SHORT).show();
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("Não tem permissao para acessar a galeria")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Uri filepath  = data.getData();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), imageBitmap, "Title", null);
            Uri filepath = Uri.parse(path);
            uploadImagem(filepath);

        } else if (requestCode == REQUEST_GALLEY_CAPTURE && resultCode == RESULT_OK){
            Uri filepath  = data.getData();
            uploadImagem(filepath);
        }
    }

    private void uploadImagem(Uri filepath){
        if(filepath != null){

            final StorageReference storageReference = firebaseStorage.getReference()
                    .child(idChat)
                    .child("imagens/"+ UUID.randomUUID().toString());
            UploadTask uploadTask = storageReference.putFile(filepath);

            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();

                        Calendar dataAtual = Calendar.getInstance();
                        ItemChat itemChat = new ItemChat(downloadUri.toString(),
                                dataAtual.getTime().toString(), idUser, emailUser, "imagem");

                        firebaseFirestore.collection("chats")
                                .document(idChat)
                                .collection("mensagens")
                                .document()
                                .set(itemChat);
                    }
                }
            });

        }



    }
}
