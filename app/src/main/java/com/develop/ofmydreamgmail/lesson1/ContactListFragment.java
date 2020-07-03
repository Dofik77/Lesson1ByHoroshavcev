package com.develop.ofmydreamgmail.lesson1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.ListView;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactListFragment extends ListFragment {

    private ContactsService service;
    private View view;

    public interface ResultListener {
        void onComplete(Contact[] contacts);
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Contact List");
        view = getView();
        service.getContacts(callback);
    }

    @Override
    public void onAttach(@NonNull Context ctx) {
        super.onAttach(ctx);
        if (ctx instanceof ServiceInterface) {
            this.service = ((ServiceInterface) ctx).getService();
        }
    }

    @Override
    public void onListItemClick (@NonNull ListView listView, @Nullable View view, int i, long id) {
        ContactDetailsFragment detailsFragment = ContactDetailsFragment.newInstance(i);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentLayout, detailsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }

    @Override
    public void onResume () {
        super.onResume();
    }


    private ResultListener callback = new ResultListener() {
        @Override
        public void onComplete(Contact[] result) {
            final Contact[] contacts = result;
            if (view != null){
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        final ArrayAdapter<Contact> contactAdapter = new ArrayAdapter<Contact>(getActivity(), 0, contacts){
                            @SuppressLint("NewApi")
                            @NonNull
                            @Override
                            public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
                                if (parent != null) {
                                    if (convertView == null) {
                                        convertView = getLayoutInflater().inflate(R.layout.fragment_contactlist, null, false);
                                    }
                                    ImageView imageView = convertView.findViewById(R.id.contactImage);
                                    TextView nameView = convertView.findViewById(R.id.contactName);
                                    TextView phoneNumberView = convertView.findViewById(R.id.contactNum);
                                    Contact currentContact = contacts[i];
                                    nameView.setText(currentContact.getName());
                                    phoneNumberView.setText(currentContact.getPhone());
                                    imageView.setImageResource(currentContact.getImage());
                                }
                                return convertView;
                            }
                        };
                        setListAdapter(contactAdapter);
                    }
                });
            }
        }
    };



}
