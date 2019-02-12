package nikita.shekhovtsov.starwarsexplorer.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;
import java.io.IOException;

import nikita.shekhovtsov.starwarsexplorer.Model.Being;

public class BeingDataSource implements Closeable {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private BeingDataReader beingDataReader;

    public BeingDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        // Создать читателя и открыть его
        beingDataReader = new BeingDataReader(database);
        beingDataReader.open();
    }

    // Добваить запись
    public Being addBeing(String name, String height,
                          String mass, String hairColor,
                          String skinColor, String eyeColor,
                          String birthYear, String gender) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_HEIGHT, height);
        values.put(DatabaseHelper.COLUMN_MASS, mass);
        values.put(DatabaseHelper.COLUMN_HAIRCOLOR, hairColor);
        values.put(DatabaseHelper.COLUMN_SKINCOLOR, skinColor);
        values.put(DatabaseHelper.COLUMN_EYECOLOR, eyeColor);
        values.put(DatabaseHelper.COLUMN_BIRTHYEAR, birthYear);
        values.put(DatabaseHelper.COLUMN_GENDER, gender);
        // Добавление записи
        long insertId = database.insert(DatabaseHelper.TABLE_BEINGS, null,
                values);
        Being newBeing = new Being();
        newBeing.setName(name);
        newBeing.setHeight(height);
        newBeing.setMass(mass);
        newBeing.setHairColor(hairColor);
        newBeing.setSkinColor(skinColor);
        newBeing.setEyeColor(eyeColor);
        newBeing.setBirthYear(birthYear);
        newBeing.setGender(gender);
        newBeing.setId(insertId);
        return newBeing;
    }

    // Вернуть читателя (он потребуется в других местах)
    public BeingDataReader getNoteDataReader(){
        return beingDataReader;
    }


    @Override
    public void close() throws IOException {
        beingDataReader.close();
        dbHelper.close();
    }
}
