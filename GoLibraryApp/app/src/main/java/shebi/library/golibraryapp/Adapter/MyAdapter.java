package shebi.library.golibraryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import shebi.library.golibraryapp.Model.AddCategoryModel;
import shebi.library.golibraryapp.Model.UserModel;
import shebi.library.golibraryapp.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
Context context;
ArrayList<UserModel> profiles;

public MyAdapter(Context c, ArrayList<UserModel> p){
    context=c;
    profiles=p;
}

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
holder.name.setText(profiles.get(position).getFname());
holder.lname.setText(profiles.get(position).getLname());
holder.address.setText(profiles.get(position).getAdress());
holder.phone.setText(profiles.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
    TextView name,lname,address,phone;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            lname=(TextView)itemView.findViewById(R.id.lname);
            address=(TextView)itemView.findViewById(R.id.address);
            phone=(TextView)itemView.findViewById(R.id.phone);
        }
    }


}
