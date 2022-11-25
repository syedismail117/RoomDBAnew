package com.example.roomdbanew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbanew.AdapterListener;
import com.example.roomdbanew.DBA.Users;
import com.example.roomdbanew.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private List<Users>usersList;

    private AdapterListener adapterListener;

    public UserAdapter(Context context,AdapterListener adapterListener){
        this.context=context;
        usersList=new ArrayList<> ();
        this.adapterListener=adapterListener;
    }

    public void AddUser(Users users){
        usersList.add ( users );
        notifyDataSetChanged ();
    }


    public void RemoveUser(int position){
        usersList.remove ( position );
        notifyDataSetChanged ();
    }
    public void clearData(){
        usersList.clear ();
        notifyDataSetChanged ();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from ( parent.getContext () )
                .inflate ( R.layout.userlist,parent,false );

        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Users users=usersList.get ( position );
        holder.Name.setText ( users.getName () );
        holder.Number.setText ( users.getNumber () );


        holder.edit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                adapterListener.OnUpdate (users );

            }
        } );

        holder.delete.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                adapterListener.OnDelete (users.getId (),position  );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return usersList.size ();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name,Number;
        ImageView edit,delete;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            Name=itemView.findViewById ( R.id.name2 );
            Number=itemView.findViewById ( R.id.phone_number2 );

            edit=itemView.findViewById ( R.id.editImg);
            delete=itemView.findViewById ( R.id.deleteImg);

        }
    }
}
