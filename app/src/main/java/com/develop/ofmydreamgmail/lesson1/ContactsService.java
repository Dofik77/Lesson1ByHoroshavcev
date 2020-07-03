package com.develop.ofmydreamgmail.lesson1;

import android.os.Binder;
import android.os.IBinder;
import android.app.Service;
import android.content.Intent;
import java.lang.ref.WeakReference;

public class ContactsService extends Service {

    private final IBinder contactsBinder = new ContactsBinder();

    class ContactsBinder extends Binder {
        ContactsService getService() {
            return ContactsService.this;
        }
    }

    public void getContact(ContactDetailsFragment.ResultListener callback, final int id) {
        final WeakReference<ContactDetailsFragment.ResultListener> ref = new WeakReference(callback);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Contact result = Contact.contacts[id];
                ContactDetailsFragment.ResultListener local = ref.get();
                if (local != null) {
                    local.onComplete(result);
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return contactsBinder;
    }

    public void getContacts(ContactListFragment.ResultListener callback) {
        final WeakReference<ContactListFragment.ResultListener> ref = new WeakReference(callback);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Contact[] result = Contact.contacts;
                ContactListFragment.ResultListener local = ref.get();
                if (local != null) {
                    local.onComplete(result);
                }
            }
        }).start();
    }

}
