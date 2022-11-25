package com.example.roomdbanew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdbanew.Adapter.UserAdapter;
import com.example.roomdbanew.DBA.UserDao;
import com.example.roomdbanew.DBA.UserDataBase;
import com.example.roomdbanew.DBA.Users;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {

    EditText name,number;
    Button addBtn;

    RecyclerView recyclerView;

    private UserDataBase userDataBase;
    private UserDao userDao;

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        name=findViewById ( R.id.Name );
        number=findViewById ( R.id.phone_number );

        addBtn=findViewById ( R.id.submit );

        recyclerView=findViewById ( R.id.RecyclerView );

        //user dba instance
        userDataBase=UserDataBase.getInstance ( this );
        userDao= userDataBase.getDao ();

        userAdapter=new UserAdapter ( this,this );
        recyclerView.setAdapter ( userAdapter );
        recyclerView.setLayoutManager ( new LinearLayoutManager (this ) );

        addBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                String Name=name.getText ().toString ();
                String Number=number.getText ().toString ();

                Users users = new Users ( 0,Name,Number );
                userDao.insert (users);
                userAdapter.AddUser (users);
                name.setText ( "" );
                number.setText ( "" );
                Toast.makeText ( MainActivity.this, "Inserted", Toast.LENGTH_SHORT ).show ();

            }
        } );
    }
    private void fetchData(){
        userAdapter.clearData ();
        List<Users>userList=userDao.getAllUsers ();
        for (int i=0;i<userList.size ();i++){
            Users users=userList.get ( i );
            userAdapter.AddUser ( users );
        }
    }

    @Override
    public void OnUpdate(Users users) {
        Intent intent=new Intent (this,updateActivity.class);
        intent.putExtra ( "mode",users );
        startActivity (intent );

    }

    @Override
    public void OnDelete(int id, int pos) {
        userDao.delete ( id );
        userAdapter.RemoveUser ( pos );
    }

    @Override
    protected void onPostResume() {
        super.onPostResume ();
        fetchData();
    }
}