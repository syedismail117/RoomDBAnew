package com.example.roomdbanew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.roomdbanew.DBA.UserDao;
import com.example.roomdbanew.DBA.UserDataBase;
import com.example.roomdbanew.DBA.Users;

public class updateActivity extends AppCompatActivity {

    TextView NameUp,NumberUp;
    Button updateBtn;
    private Users users;
    private UserDataBase userDataBase;
    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update );

        userDataBase=UserDataBase.getInstance ( this );
        userDao=userDataBase.getDao ();

        NameUp=findViewById ( R.id.NameUp );
        NumberUp=findViewById ( R.id.phone_numberUp);
        updateBtn=findViewById ( R.id.update );

        //get the user

        users=(Users) getIntent ().getSerializableExtra ( "mode" );

        NameUp.setText ( users.getName () );
        NumberUp.setText ( users.getNumber () );




        updateBtn.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                Users usersModel=new Users ( users.getId ()
                        ,NameUp.getText ().toString (),NumberUp.getText ().toString () );
                userDao.update ( usersModel );
                finish ();

            }
        } );

    }
}