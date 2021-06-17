package android.example.midterm.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.midterm.RawInfo;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "android.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public String getTitles(String table_name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select Title from " + table_name, null);
        StringBuffer stringBuffer = new StringBuffer();

        while(c.moveToNext()){
            String title = c.getString(c.getColumnIndex("Title"));
            stringBuffer.append("\n" + title);
        }

        c.close();

        return stringBuffer.toString();
    }

    public int getTotalAmount(String table_name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(Amount) AS Total FROM " + table_name, null);
        int total = 0;
        if(c.moveToFirst()){
            total = c.getInt(c.getColumnIndex("Total"));
        }

        c.close();
        return total;
    }

    public List<RawInfo> retrieveTable(String table_name){
        List<RawInfo> tempList = new ArrayList<RawInfo>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + table_name, null);
        while(c.moveToNext()){
            String tempTitle = c.getString(c.getColumnIndex("Title"));
            int tempAmount = c.getInt(c.getColumnIndex("Amount"));
            String tempImg = c.getString(c.getColumnIndex("Img_Res"));
            tempList.add(new RawInfo(tempTitle, tempAmount, tempImg));
        }
        c.close();

        return tempList;
    }

    public List<RawInfo> retrieveTableCur(String table_name){
        List<RawInfo> tempList = new ArrayList<RawInfo>();
        SimpleDateFormat year_filter = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_filter = new SimpleDateFormat("MM");
        Date date = new Date();
        String year = year_filter.format(date);
        String month = month_filter.format(date);

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + table_name + " WHERE Year = '" + year + "' AND Month = '" + month +"'", null);
        while(c.moveToNext()){
            String tempTitle = c.getString(c.getColumnIndex("Title"));
            int tempAmount = c.getInt(c.getColumnIndex("Amount"));
            String tempImg = c.getString(c.getColumnIndex("Img_Res"));
            tempList.add(new RawInfo(tempTitle, tempAmount, tempImg));
        }
        c.close();

        return tempList;
    }

    public List<RawInfo> retrieveTableCurWithDates(String table_name){
        List<RawInfo> tempList = new ArrayList<RawInfo>();
        SimpleDateFormat year_filter = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_filter = new SimpleDateFormat("MM");
        Date date = new Date();
        String year = year_filter.format(date);
        String month = month_filter.format(date);

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + table_name + " WHERE Year = '" + year + "' AND Month = '" + month +"'", null);
        while(c.moveToNext()){
            int _ID = c.getInt(c.getColumnIndex("_ID"));
            String tempTitle = c.getString(c.getColumnIndex("Title"));
            int tempAmount = c.getInt(c.getColumnIndex("Amount"));
            String tempImg = c.getString(c.getColumnIndex("Img_Res"));
            String dateFromDB = c.getString(c.getColumnIndex("Year")) + "-" + c.getString(c.getColumnIndex("Month")) + "-" + c.getString(c.getColumnIndex("Day"));
            tempList.add(new RawInfo(tempTitle, tempAmount, tempImg, dateFromDB, _ID));
        }
        c.close();

        return tempList;
    }


    public int getTotalAmountCur(String table_name){
        SimpleDateFormat year_filter = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_filter = new SimpleDateFormat("MM");
        Date date = new Date();
        String year = year_filter.format(date);
        String month = month_filter.format(date);

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(Amount) AS Total FROM "  + table_name + " WHERE Year = '" + year + "' AND Month = '" + month +"'", null);
        int total = 0;
        if(c.moveToFirst()){
            total = c.getInt(c.getColumnIndex("Total"));
        }

        c.close();
        return total;
    }

    public boolean deleteByID(String table_name, int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(table_name, "_ID = " + String.valueOf(id), null) > 0;
    }

    public boolean insert(String table_name, String title, int amount){
        SQLiteDatabase db = getWritableDatabase();

        SimpleDateFormat year_filter = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_filter = new SimpleDateFormat("MM");
        SimpleDateFormat day_filter = new SimpleDateFormat("dd");
        Date date = new Date();
        String year = year_filter.format(date);
        String month = month_filter.format(date);
        String day = day_filter.format(date);

        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Amount", amount);
        values.put("Year", year);
        values.put("Month", month);
        values.put("Day", day);

        return db.insert(table_name,null, values) > 0;
    }

    public boolean modify(String table_name, int id, String img_res){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Img_Res", img_res);

        return db.update(table_name, values, String.format("%s = ?", "_ID"), new String[]{String.format("%d", id)}) > 0;
    }
}
