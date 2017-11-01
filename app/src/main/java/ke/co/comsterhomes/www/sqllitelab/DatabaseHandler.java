package ke.co.comsterhomes.www.sqllitelab;

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

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";


    private static final String KEY_ID= "id";
    private static final String KEY_NAME= "name";
    private static final String KEY_PH_NO= "phone_number";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_CONTACTS + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_PH_NO + "TEXT," + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_version, int new_version) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" +TABLE_CONTACTS+"'");

        onCreate(sqLiteDatabase);
    }


    public void addContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        sqLiteDatabase.insert(TABLE_CONTACTS,null,values);
        sqLiteDatabase.close();
    }
    public Contact getContact(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_CONTACTS, new String[]{
                KEY_ID, KEY_NAME, KEY_PH_NO
        }, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(2), cursor.getString(3));
        return contact;
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();
            String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
            SQLiteDatabase sqLiteDatabase1 = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase1.rawQuery(selectQuery,null);

            if (cursor.moveToFirst()){
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);

            }while (cursor.moveToNext());

        return contactList;
    }

    public int getContactsCount(){
        String countQuery = "SELECT * FROM" + TABLE_CONTACTS;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
    public int updateContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PH_NO,contact.getPhoneNumber());

        return sqLiteDatabase.update(TABLE_CONTACTS,values,KEY_ID+"=?",new String[]{String.valueOf(contact.getID())});
    }
    public void deleteContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_CONTACTS,KEY_ID+"=?",new String[]{String.valueOf(contact.getID())});

        sqLiteDatabase.close();
    }
}
