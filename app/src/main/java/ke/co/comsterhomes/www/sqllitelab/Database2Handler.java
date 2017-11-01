package ke.co.comsterhomes.www.sqllitelab;

/**
 * Created by collinsnyamao on 11/1/17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by collinsnyamao on 10/17/17.
 */

public class Database2Handler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MovieManager";
    private static final String TABLE_MOVIES = "contacts";


    private static final String KEY_ID= "id";
    private static final String KEY_NAME= "name";
    private static final String KEY_PH_NO= "genre";

    public Database2Handler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE" + TABLE_MOVIES + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_PH_NO + "TEXT," + ")";
        sqLiteDatabase.execSQL(CREATE_MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_version, int new_version) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" +TABLE_MOVIES+"'");

        onCreate(sqLiteDatabase);
    }


    public void add(Movie movie){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, movie.get_genre());
        values.put(KEY_PH_NO, movie.get_genre());

        sqLiteDatabase.insert(TABLE_MOVIES,null,values);
        sqLiteDatabase.close();
    }
    public Movie getMovie(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_MOVIES, new String[]{
                KEY_ID, KEY_NAME, KEY_PH_NO
        }, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Movie movie = new Movie(Integer.parseInt(cursor.getString(0)), cursor.getString(2), cursor.getString(3));
        return movie;
    }

    public List<Movie> getAllContacts(){
        List<Movie> movieList = new ArrayList<Movie>();
        String selectQuery = "SELECT * FROM " + TABLE_MOVIES;
        SQLiteDatabase sqLiteDatabase1 = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase1.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            Movie movie= new Movie(0,"","");
            movie.set_id(Integer.parseInt(cursor.getString(0)));
            movie.set_name(cursor.getString(1));
            movie.set_genre(cursor.getString(2));
            movieList.add(movie);

        }while (cursor.moveToNext());

        return movieList;
    }

    public int getTablescount(){
        String countQuery = "SELECT * FROM" + TABLE_MOVIES;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
    public int UpdateMovies(Movie movie){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,movie.get_name());
        values.put(KEY_PH_NO,movie.get_genre());

        return sqLiteDatabase.update(TABLE_MOVIES,values,KEY_ID+"=?",new String[]{String.valueOf(movie.get_id())});
    }
    public void deleteMovie(Movie movie){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_MOVIES,KEY_ID+"=?",new String[]{String.valueOf(movie.get_id())});

        sqLiteDatabase.close();
    }
}

