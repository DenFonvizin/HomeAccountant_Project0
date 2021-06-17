package android.example.midterm;

import android.media.Image;

public class RawInfo {
    private int _ID;
    private String title;
    private int amount;
    private String photo;
    private String date;
    private boolean imgSet;


    public RawInfo(String title, int amount){
        this.title = title;
        this.amount = amount;
    }

    public RawInfo(String title, int amount, String photo){
        this.title = title;
        this.amount = amount;
        this.photo = photo;
    }

    public RawInfo(String title, int amount, String photo, String date, int _ID){
        this._ID = _ID;
        this.title = title;
        this.amount = amount;
        this.photo = photo;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        String temp = String.valueOf(amount);
        return temp;
    }

    public int getAmountint(){
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public boolean isImgSet() {
        return imgSet;
    }

    public void setImgSet(boolean imgSet) {
        this.imgSet = imgSet;
    }
}
