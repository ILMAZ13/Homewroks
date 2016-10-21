package ru.itis.contacts;

import java.io.Serializable;

/**
 * Created by ilmaz on 21.10.16.
 */

public class Contact implements Serializable{
    private long number;

    public Contact(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }
}
