package shebi.library.golibraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(getBaseContext(),Dashboard.class));

        }
    }

    EditText EmailET, PasswordET;
    TextView FPasswordTV,caccount;
    String EmailStr, PasswordStr;
    Button mLoginBtn;
    FirebaseAuth auth;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        EmailET=findViewById(R.id.EmailET);
        progressbar=findViewById(R.id.progressbar);
        PasswordET=findViewById(R.id.PasswordET);
        mLoginBtn=findViewById(R.id.mLoginBtn);
        caccount=findViewById(R.id.caccount);
        FPasswordTV=findViewById(R.id.FPasswordTV);
        Wave waves=new Wave();
        progressbar.setIndeterminateDrawable(waves);
        caccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });
        FPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,ResetPasswordActivity.class));
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStrings();
                if (EmailStr.isEmpty()) {
                    EmailET.setError("Enter Email");
                } else if (PasswordStr.isEmpty()) {
                    PasswordET.setError("Enter Password");
                }else {
                    progressbar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(EmailET.getText().toString(), PasswordET.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String Uid=auth.getCurrentUser().getUid();
                                Toast.makeText(MainActivity.this, Uid, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getBaseContext(),Dashboard.class));
                                EmailET.setText("");
                                PasswordET.setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });}
            }
        });
    }
    void getStrings() {
        EmailStr = EmailET.getText().toString();
        PasswordStr = PasswordET.getText().toString();
    }
}

