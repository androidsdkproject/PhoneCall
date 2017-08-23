package com.example.android1.phone;

/**
 * Created by Android1 on 7/21/2017.
 */

public class FavouriteModelClass {
    String name_,phone_;

    FavouriteModelClass(String name_,String phone_)
    {
    this.name_=name_;
    this.phone_=phone_;
    }
    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getPhone_() {
        return phone_;
    }

    public void setPhone_(String phone_) {
        this.phone_ = phone_;
    }
}
