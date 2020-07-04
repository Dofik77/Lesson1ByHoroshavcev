package com.develop.ofmydreamgmail.lesson1;

import androidx.annotation.Nullable;

    public class Contact {

        final private String name;
        final private String phone;
        @Nullable final private String phone2;
        @Nullable final private String email;
        @Nullable final private String email2;
        @Nullable final private String birthday;
        @Nullable final private String data;
        @Nullable final private int image;

        static final Contact[] contacts = {
                new Contact("Stanislav Khorochavcev", "+89820987121", null, "khorochavcev@mail.ru", null, "10.10.03", null, R.mipmap.ic_launcher),
                new Contact("Vladimir Putin", "+88005553535", "+8666666666", "VVP.PUT_IN@gmail.com" , "ovshor_zhurih@mail.com", "01.02.99", null, R.mipmap.ic_launcher)
        };

        Contact(String name, String phone,
                @Nullable String phone2,
                @Nullable String email,
                @Nullable String email2,
                @Nullable String birthday,
                @Nullable String data,
                int image) {
            this.name = name;
            this.phone = phone;
            this.phone2 = phone2;
            this.email = email;
            this.email2 = email2;
            this.birthday = birthday;
            this.data = data;
            this.image = image;
        }


         public String getName() { return this.name; }

         public String getPhone() { return this.phone; }

        @Nullable public String getPhone2() { return this.phone2; }

        @Nullable public String getEmail() { return this.email; }

        @Nullable public String getEmail2() { return this.email2; }

        @Nullable public String getBirthday() { return this.birthday; }

        @Nullable public String getData() { return this.data; }

        @Nullable public int getImage() { return this.image; }
    }

