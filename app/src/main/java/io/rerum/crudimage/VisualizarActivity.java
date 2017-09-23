package io.rerum.crudimage;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static android.R.attr.data;
import static android.R.attr.nestedScrollingEnabled;

/**
 * Created by osman on 23/09/2017.
 */

public class VisualizarActivity extends AppCompatActivity {

    private ImageView image1;
    private VideoView video1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizarfotos);

        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference mStorage = storage.getReference();

        StorageReference storageReference = mStorage.child("Fotos/34371");
        image1 = (ImageView) findViewById(R.id.image1);
        video1 = (VideoView) findViewById(R.id.videoView);
//        StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://crud-97ac4.appspot.com/Fotos/34371");

        StorageReference httpsReference = FirebaseStorage.getInstance().getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/crud-97ac4.appspot.com/o/Fotos%2F34462?alt=media&token=d8788d01-0a3b-4acd-b58e-c625e9199bf9");

        httpsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @Override
            public void onSuccess(Uri uri) {
                Log.e("Osman ver aqui = ", String.valueOf(image1));
                if (image1 != null){
//                    Picasso.with(VisualizarActivity.this).load(uri).fit().centerCrop().into(image1);
                    video1.setVideoURI(uri);
                    video1.start();
                }
                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }
}
