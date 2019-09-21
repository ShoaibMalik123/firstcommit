package shebi.library.golibraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataGetActivity extends AppCompatActivity {
EditText fnamed,lnamed,adressd,contactd;
    FirebaseAuth auth;
    DatabaseReference UserRef;
Button getd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_get);
        fnamed=findViewById(R.id.fnamed);
        lnamed=findViewById(R.id.lnamed);
        adressd=findViewById(R.id.adressd);
        contactd=findViewById(R.id.contactd);
        getd=findViewById(R.id.getd);
        auth=FirebaseAuth.getInstance();
        UserRef = FirebaseDatabase.getInstance().getReference("Users_Data");
        getd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=auth.getCurrentUser().getUid();
                UserRef.child(uid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        fnamed.setText(dataSnapshot.child("fname").getValue().toString());
                        lnamed.setText(dataSnapshot.child("lname").getValue().toString());
                        adressd.setText(dataSnapshot.child("adress").getValue().toString());
                        contactd.setText(dataSnapshot.child("phone").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
