package ru.itis.contactsv2.entities;

import java.io.Serializable;

/**
 * Created by ilmaz on 23.10.16.
 */

public class Contact implements Serializable{
    private long number;
    private String name;
    private boolean isDeleted = false;

    public Contact(String name, long number) {
        this.number = number;
        this.name = name;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public long getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Contact) {
            return ((Contact) obj).name.equals(name) &&
                    ((Contact) obj).number == number &&
                    ((Contact) obj).isDeleted == isDeleted;
        }
        return false;
    }

    @Override
    public String toString() {
        return name+number+(isDeleted ? "t" : "f");
    }

}
