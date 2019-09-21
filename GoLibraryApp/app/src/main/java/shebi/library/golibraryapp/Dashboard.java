package shebi.library.golibraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    Button btn,dataget,buttonProfile1,buttonProfile2;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth=FirebaseAuth.getInstance();
        buttonProfile1=findViewById(R.id.buttonProfile1);
        buttonProfile2=findViewById(R.id.buttonProfile2);
        buttonProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,profileActivity.class));
            }
        });
        buttonProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,CategoryActivity.class));
            }
        });


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if (id==R.id.LogOut){
            auth.signOut();
            startActivity(new Intent(getBaseContext(),MainActivity.class));
        }
        else  {
            if (id==R.id.dataGet){
                startActivity(new Intent(Dashboard.this,DataGetActivity.class));
            }
        }
        return true;
    }
}
