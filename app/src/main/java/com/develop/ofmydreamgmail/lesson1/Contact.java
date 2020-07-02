package com.develop.ofmydreamgmail.lesson1;

import androidx.annotation.Nullable;

    public class Contact {
        final private String name;
        final private String phone;
        final private String phone2;
        final private String email;
        final private String email2;
        final private String birthday;
        final private String data;
        final private int image;

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

        public String getPhone2() { return this.phone2; }

        public String getEmail() { return this.email; }

        public String getEmail2() { return this.email2; }

        public String getBirthday() { return this.birthday; }

        public String getData() { return this.data; }

        public int getImage() { return this.image; }
    }

