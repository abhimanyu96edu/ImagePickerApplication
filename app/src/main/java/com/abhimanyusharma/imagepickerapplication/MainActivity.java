package com.abhimanyusharma.imagepickerapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button ap_select;
    ImageView profileImage;

    private static final int SELECT_SINGLE_PICTURE = 101;

    public static final String IMAGE_TYPE = "image/*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImage = (ImageView) findViewById(R.id.profileImage);
        ap_select = (Button) findViewById(R.id.ap_select);

        ap_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"), SELECT_SINGLE_PICTURE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTURE) {

                Uri selectedImageUri = data.getData();
                try {
                    profileImage.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                } catch (IOException e) {
                }
            }
        } else {
            // report failure
            Toast.makeText(getApplicationContext(), "Error ! Please Retry", Toast.LENGTH_LONG).show();
        }
    }

}