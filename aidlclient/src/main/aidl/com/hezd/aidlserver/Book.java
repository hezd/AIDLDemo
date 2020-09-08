package com.hezd.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hezd on 20-8-25 下午3:23
 */
public class Book implements Parcelable {
    private String name;
    private double price;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.price);
    }

    public Book() {
    }

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    protected Book(Parcel in) {
        this.name = in.readString();
        this.price = in.readDouble();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
