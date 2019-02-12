package nikita.shekhovtsov.starwarsexplorer.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "people.db"; // Название БД
    public static final int DATABASE_VERSION = 1; // Версия базы данных
    public static final String TABLE_BEINGS = "beings"; // Название таблицы в БД

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_MASS = "mass";
    public static final String COLUMN_HAIRCOLOR = "hairColor";
    public static final String COLUMN_SKINCOLOR = "skinColor";
    public static final String COLUMN_EYECOLOR = "eyeColor";
    public static final String COLUMN_BIRTHYEAR = "birthYear";
    public static final String COLUMN_GENDER = "gender";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_BEINGS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_HEIGHT + " TEXT,"
                + COLUMN_MASS + " TEXT,"
                + COLUMN_HAIRCOLOR + " TEXT,"
                + COLUMN_SKINCOLOR + " TEXT,"
                + COLUMN_EYECOLOR + " TEXT,"
                + COLUMN_BIRTHYEAR + " TEXT,"
                + COLUMN_GENDER + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
