package ru.itis.contacts;

import java.io.Serializable;

/**
 * Created by ilmaz on 21.10.16.
 */

public class Contact implements Serializable{
    private long number;
    private boolean isDeleted = false;

    public Contact(long number) {
        this.number = number;
    }

    public Contact(long number, boolean isDeleted) {
        this.number = number;
        this.isDeleted = isDeleted;
    }

    public long getNumber() {
        return number;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return number + (isDeleted ? "t" : "f");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            return ((Contact) obj).number == number && ((Contact) obj).isDeleted == isDeleted;
        }
        return false;
    }

}
