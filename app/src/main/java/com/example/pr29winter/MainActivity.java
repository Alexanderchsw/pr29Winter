package com.example.pr29winter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButton1, imageButton2, imageButton3, imageButton4, imageButton5;

    final int CAMERA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(this);

        imageButton2 = findViewById(R.id.imageButton2);
        imageButton1.setOnClickListener(this);

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(this);

        imageButton4 = findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(this);

        imageButton5 = findViewById(R.id.imageButton5);
        imageButton5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.imageButton1){
            proto();
        }else if (view.getId()==R.id.imageButton2){
            web();
        }else if (view.getId()==R.id.imageButton3){
            telephone();
        }else if (view.getId()==R.id.imageButton4){
            map();
        }else if (view.getId()== R.id.imageButton5){
            email();
        }
    }

    private void email(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:elozovetalyapynova05@gmail.com"));
        startActivity(intent);

    }
    private void map(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:55.044009,82.917108"));
        startActivity(intent);
    }
    private void telephone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:89231327040"));
        startActivity(intent);
    }

    private void web() {

        Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.google.com"));
        startActivity(intent);

    }

    private void proto() {
        try {
            // Намерение для запуска камеры
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(captureIntent, CAMERA_REQUEST);
        } catch (ActivityNotFoundException e) {
            // Выводим сообщение об ошибке
            String errorMessage = "Ваше устройство не поддерживает съемку";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // Вернулись от приложения Камера
            if (requestCode == CAMERA_REQUEST) {
                // Получим Uri снимка
                if (data != null && data.getExtras() != null) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");

                    // Примените полученное изображение к ImageView
                    ImageView imageView1 = findViewById(R.id.imageView);
                    imageView1.setImageBitmap(photo);

                }
            }
        }
    }



}