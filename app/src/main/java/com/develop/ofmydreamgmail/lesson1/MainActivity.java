package com.develop.ofmydreamgmail.lesson1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;



public class MainActivity extends AppCompatActivity implements ServiceInterface {

    private ContactsService contactsService;
    private ServiceConnection conn;
    private boolean previousState;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, ContactsService.class);
        previousState = savedInstanceState == null;
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ContactsService.ContactsBinder binder = (ContactsService.ContactsBinder) service;
                contactsService = binder.getService();
                mBound = true;
                if (previousState) {
                    ContactListFragment contactListFragment = new ContactListFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragmentLayout, contactListFragment);
                    fragmentTransaction.commit();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBound = false;
                contactsService = null;
            }
        };
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public ContactsService getService() {
        return contactsService;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            unbindService(conn);
            mBound = false;
        }
    }

}
