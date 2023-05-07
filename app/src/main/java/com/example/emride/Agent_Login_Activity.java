package com.example.emride;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Agent_Login_Activity extends AppCompatActivity {

    private TextView tv_forgetpassword;
    private EditText edt_email,edt_password;
    private Button btn_agent_login;
//    private LoginButton login_button;
//    private CallbackManager callbackManager;

//    private GoogleSignInClient mGoogleSignInClient;
//    private static int RC_SIGN_IN=100;

    private static final String EMAIL = "email";

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_login);
        edt_email=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);
        btn_agent_login=findViewById(R.id.btn_agent_login);
        tv_forgetpassword=findViewById(R.id.tv_forgetpassword);


        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Logging in....");
        progressDialog.setCanceledOnTouchOutside(false);

        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String ue=preferences.getString("userEmail","");
        String up=preferences.getString("userPassword","");
        tv_forgetpassword.setPaintFlags(tv_forgetpassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        if(!TextUtils.isEmpty(ue) && !TextUtils.isEmpty(up)){
            edt_email.setText(ue);
            edt_password.setText(up);
        }

//        String name=givename();




        tv_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Agent_Login_Activity.this,ForgetPasswordActivity.class));
            }
        });

        btn_agent_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String e=edt_email.getText().toString().trim();
                String p=edt_password.getText().toString().trim();

                SharedPreferences preferences1=getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences1.edit();
                editor.putString("userEmail",e);
                editor.putString("userPassword",p);
                editor.apply();

                loginUserAccount();

            }
        });

    }

//    private String givename() {
//        String a="";
//        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Customer");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String name=snapshot.child("name");
//                name="";
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private FirebaseDatabase mRef;
    String email,password;
    private void loginUserAccount() {

        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
        String ue=preferences.getString("userEmail","");
        String up=preferences.getString("userPassword","");

        if(!TextUtils.isEmpty(ue) && !TextUtils.isEmpty(up)){
            edt_email.setText(ue);
            edt_password.setText(up);
            email=ue;
            password=up;
        }
        else{
            email=edt_email.getText().toString().trim();
            password=edt_password.getText().toString().trim();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(Agent_Login_Activity.this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.length()<8){
            Toast.makeText(Agent_Login_Activity.this, "Password too short", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();

                        startActivity(new Intent(Agent_Login_Activity.this,Agent_Homepage.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Agent_Login_Activity.this, "Incorrect email or password, try again", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}