package shebi.library.golibraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import shebi.library.golibraryapp.Model.UserModel;

public class SignUpActivity extends AppCompatActivity {
    EditText EmailET, PasswordET, PhoneET, AddressET, FNameET, LNameET;
    String mFNameStr, mLNameStr, mEmailStr, mPasswordStr, mPhoneStr, mAddressStr;
    ProgressBar progressbar;

    Button mSignupBtn;
    TextView nextpage;
    FirebaseAuth auth;
    DatabaseReference UserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        UserRef = FirebaseDatabase.getInstance().getReference("Users_Data");
        FNameET = findViewById(R.id.FNameET);
        progressbar = findViewById(R.id.progressbar);
        LNameET = findViewById(R.id.LNameET);
        EmailET = findViewById(R.id.EmailET);
        PasswordET = findViewById(R.id.PasswordET);
        mSignupBtn = findViewById(R.id.mSignupBtn);
        PhoneET = findViewById(R.id.PhoneET);
        AddressET = findViewById(R.id.AddressET);
        nextpage = findViewById(R.id.nextPage);
        Wave waves=new Wave();
        progressbar.setIndeterminateDrawable(waves);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStrings();
                if (mFNameStr.isEmpty()) {
                    FNameET.setError("Enter First Name");
                } else if (mLNameStr.isEmpty()) {
                    LNameET.setError("Enter Last Name");
                } else if (mEmailStr.isEmpty()) {
                    EmailET.setError("Enter Email");
                } else if (mPasswordStr.isEmpty()) {
                    PasswordET.setError("Enter Password");
                } else if (mPhoneStr.isEmpty()) {
                    PhoneET.setError("Enter Your Number");
                } else if (mAddressStr.isEmpty()) {
                    AddressET.setError("Enter Your Address");
                } else {
                    mSignupBtn.setEnabled(false);
                    progressbar.setVisibility(View.VISIBLE);

                    auth.createUserWithEmailAndPassword(EmailET.getText().toString(), PasswordET.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String Uid=auth.getCurrentUser().getUid();
                                        UserModel model=new UserModel(PhoneET.getText().toString(), AddressET.getText().toString(),
                                                FNameET.getText().toString(),LNameET.getText().toString(),"Admin");
                                        UserRef.child(Uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progressbar.setVisibility(View.GONE);
                                                Snackbar snackbar = Snackbar
                                                        .make(findViewById(android.R.id.content), "User Registered Successfully", Snackbar.LENGTH_LONG)
                                                        .setAction("Ok", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                startActivity(new Intent(SignUpActivity.this,Dashboard.class));
                                                            }
                                                        });
                                                snackbar.setDuration(8000);
                                                snackbar.show();
                                                // Toast.makeText(SignUpActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                                                // startActivity(new Intent(SignUpActivity.this,DashboardActivity.class));
                                                FNameET.setText("");
                                                LNameET.setText("");
                                                PhoneET.setText("");
                                                EmailET.setText("");
                                                PasswordET.setText("");
                                                AddressET.setText("");

                                            }//on complete task void wali
                                        });//complete listener void waali

                                    }//if wali h ye
                                }//on complete task auth result wali
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressbar.setVisibility(View.GONE);
                            mSignupBtn.setEnabled(true);
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(android.R.id.content), "Failed To Register", Snackbar.LENGTH_LONG)
                                    .setAction("Retry", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(SignUpActivity.this,SignUpActivity.class));
                                        }
                                    });
                            snackbar.setDuration(8000);
                            snackbar.show();
                            //Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });//add on complete listener wali h
                }//else waali h ye

            }//onclick view view waali
        });//listener main backet


    }//on create ki h ye

    void getStrings() {
        mFNameStr = FNameET.getText().toString();
        mLNameStr = LNameET.getText().toString();
        mEmailStr = EmailET.getText().toString();
        mPasswordStr = PasswordET.getText().toString();
        mPhoneStr = PhoneET.getText().toString();
        mAddressStr = AddressET.getText().toString();

    }//get strings wala method h ye

}
