package nikita.shekhovtsov.starwarsexplorer.DataBases;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;
import java.io.IOException;

import nikita.shekhovtsov.starwarsexplorer.Model.Being;


public class BeingDataReader implements Closeable {

    private Cursor cursor;
    private SQLiteDatabase database;
    private String[] beingsAllColumn = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_NAME,
            DatabaseHelper.COLUMN_HEIGHT,
            DatabaseHelper.COLUMN_MASS,
            DatabaseHelper.COLUMN_HAIRCOLOR,
            DatabaseHelper.COLUMN_SKINCOLOR,
            DatabaseHelper.COLUMN_EYECOLOR,
            DatabaseHelper.COLUMN_BIRTHYEAR,
            DatabaseHelper.COLUMN_GENDER
    };

    public BeingDataReader(SQLiteDatabase database){
        this.database = database;
    }

    public void open(){
        query();
        cursor.moveToFirst();
    }

    private void query(){
        cursor = database.query(DatabaseHelper.TABLE_BEINGS,
                beingsAllColumn, null, null, null, null, null);
    }

    public void Refresh(){
        int position = cursor.getPosition();
        query();
        cursor.moveToPosition(position);
    }

    public Being getPosition(int position){
        cursor.moveToPosition(position);
        return cursorToBeing();
    }

    public int getCount(){
        return cursor.getCount();
    }


    private Being cursorToBeing() {
        Being being = new Being();
        being.setId(cursor.getLong(0));
        being.setName(cursor.getString(1));
        being.setHeight(cursor.getString(2));
        being.setMass(cursor.getString(3));
        being.setHairColor(cursor.getString(4));
        being.setSkinColor(cursor.getString(5));
        being.setEyeColor(cursor.getString(6));
        being.setBirthYear(cursor.getString(7));
        being.setGender(cursor.getString(8));
        return being;
    }



    @Override
    public void close() throws IOException {
        cursor.close();
    }

}
