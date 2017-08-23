package com.example.android1.phone;

/**
 * Created by Android1 on 7/5/2017.
 */

public class ContactList {
    String callerName;
    String callerPhone;
    String callerDate;
    String callerDuration;
    String callerType;


    public ContactList(String callerName, String callerPhone, String callerDate, String callerDuration, String callerType) {
        this.callerName = callerName;
        this.callerPhone = callerPhone;
        this.callerDate = callerDate;
        this.callerDuration = callerDuration;
        this.callerType = callerType;

    }


    public ContactList(String callerName, String callerPhone) {
        this.callerName = callerName;
        this.callerPhone = callerPhone;
    }

    public String getCallerDate() {
        return callerDate;
    }

    public void setCallerDate(String callerDate) {
        this.callerDate = callerDate;
    }

    public String getCallerDuration() {
        return callerDuration;
    }

    public void setCallerDuration(String callerDuration) {
        this.callerDuration = callerDuration;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getCallerPhone() {
        return callerPhone;
    }

    public void setCallerPhone(String callerPhone) {
        this.callerPhone = callerPhone;
    }

    public String getCallerType() {
        return callerType;
    }

    public void setCallerType(String callerType) {
        this.callerType = callerType;
    }
}
