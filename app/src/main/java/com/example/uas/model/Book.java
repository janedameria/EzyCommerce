package com.example.uas.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book implements Parcelable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("price")
    @Expose
    public Double price;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("img")
    @Expose
    public String img;
    @SerializedName("inCart")
    @Expose
    public Boolean inCart;
    @SerializedName("category")
    @Expose
    public String category;

    protected Book(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        author = in.readString();
        type = in.readString();
        img = in.readString();
        byte tmpInCart = in.readByte();
        inCart = tmpInCart == 0 ? null : tmpInCart == 1;
        category = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(description);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        dest.writeString(author);
        dest.writeString(type);
        dest.writeString(img);
        dest.writeByte((byte) (inCart == null ? 0 : inCart ? 1 : 2));
        dest.writeString(category);
    }
}