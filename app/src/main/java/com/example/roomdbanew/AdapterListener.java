package com.example.roomdbanew;

import com.example.roomdbanew.DBA.Users;

public interface AdapterListener {

    void OnUpdate(Users users);
    void OnDelete(int id,int pos);
}
