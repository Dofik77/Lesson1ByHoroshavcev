package com.develop.ofmydreamgmail.lesson1;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ContactDetailsFragment extends Fragment {
    private ContactsService service;
    private TextView detailedContactName;
    private TextView detailedContactPhone;
    private TextView detailedContactPhone2;
    private TextView detailedContactEmail;
    private TextView detailedContactEmail2;
    private TextView detailedContactDayOfBirth;
    private TextView detailedContactDescription;

    static ContactDetailsFragment newInstance(int index) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context ctx) {
        super.onAttach(ctx);
        if (ctx instanceof ServiceInterface) {
            this.service = ((ServiceInterface) ctx).getService();
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Contact Details");
        View view = inflater.inflate(R.layout.fragment_detailslist, container, false);
        int i = this.getArguments().getInt("index");
        detailedContactName = view.findViewById(R.id.detailedContactName);
        detailedContactPhone = view.findViewById(R.id.detailedContactPhoneNum);
        detailedContactPhone2 = view.findViewById(R.id.detailedContactPhoneNum2);
        detailedContactEmail = view.findViewById(R.id.detailedContactEmail);
        detailedContactEmail2 = view.findViewById(R.id.detailedContactEmail2);
        detailedContactDayOfBirth = view.findViewById(R.id.detailedContactBday);
        detailedContactDescription = view.findViewById(R.id.detailedContactDescription);
        service.getContact(callback, i);
        return view;
    }

    public interface ResultListener {
        void onComplete(Contact result);
    }

    private ContactDetailsFragment.ResultListener callback = new ContactDetailsFragment.ResultListener() {
        @Override
        public void onComplete(Contact result) {
            final Contact contact = result;
            if (detailedContactName != null) {
                detailedContactName.post(new Runnable() {
                    @Override
                    public void run() {
                        if (detailedContactName != null
                                && detailedContactPhone != null
                                && detailedContactPhone2 != null
                                && detailedContactEmail != null
                                && detailedContactEmail2 != null
                                && detailedContactDayOfBirth != null
                                && detailedContactDescription != null) {
                            detailedContactName.setText(contact.getName());
                            detailedContactPhone.setText(contact.getPhone());
                            detailedContactPhone.setText(contact.getPhone2());
                            detailedContactEmail.setText(contact.getEmail());
                            detailedContactEmail2.setText(contact.getEmail2());
                            detailedContactDayOfBirth.setText(contact.getBirthday());
                            detailedContactDescription.setText(contact.getData());
                        }
                    }
                });
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detailedContactName = null;
        detailedContactPhone = null;
        detailedContactPhone2 = null;
        detailedContactEmail = null;
        detailedContactEmail2 = null;
        detailedContactDayOfBirth = null;
        detailedContactDescription = null;
    }

}