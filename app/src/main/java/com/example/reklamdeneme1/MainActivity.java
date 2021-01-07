package com.example.reklamdeneme1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    private FirebaseAuth mAuth;
    String email = "ozan@ozan.com";
    String password = "123456";
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    StorageReference ref;
private TextView mImageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//fullscreeen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//ekranı açık tutma


        mAuth = FirebaseAuth.getInstance();


        SingUp();
        Singİn();
        //yaz();
        oku();
        //oynat();


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }




    public void SingUp() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                            System.out.println("singup if");
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            System.out.println("singup else");
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void Singİn() {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println("singIn if");
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            System.out.println("singIn else");
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void yaz() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("videoSira");

        myRef.setValue(9);

    }

    public void oku() {


        DatabaseReference myRef = database.getReference("videoSira");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int value = dataSnapshot.getValue(int.class);
                System.out.println("gelen değer: " + value);
                if (value == 1) {
                    System.out.println("oynat1 çalıştı");
                    oynat1();
                    myRef.setValue(9);

                } else if (value == 2) {
                    System.out.println("oynat2 çalıştı");
                    yaz();
                    oynat2();
                    myRef.setValue(9);
                } else if (value == 3) {
                    System.out.println("oynat3 çalıştı");
                    yaz();
                    oynat3();
                    myRef.setValue(9);
                }

                // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void oynat1() {

        webView = findViewById(R.id.webtara);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://firebasestorage.googleapis.com/v0/b/fir-storegadeneme1.appspot.com/o/videos%2Fdonen2.mp4?alt=media&token=7b2dfce6-2d55-47c3-8a19-bbdeaa015188");

    }

    public void oynat2() {
        webView = findViewById(R.id.webtara);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://firebasestorage.googleapis.com/v0/b/fir-storegadeneme1.appspot.com/o/JBB%20-%20OUR%20DRIFT%20DAY.mp4?alt=media&token=85ed4881-4cf8-4d9c-9454-798923f31b34");

    }

    public void oynat3() {
        webView = findViewById(R.id.webtara);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://firebasestorage.googleapis.com/v0/b/fir-storegadeneme1.appspot.com/o/Little%20Mix%20-%20Break%20Up%20Song%20(Official%20Vertical%20Video).mp4?alt=media&token=1bb124d0-a6f6-4608-a33a-5632d3e63558");

    }


}