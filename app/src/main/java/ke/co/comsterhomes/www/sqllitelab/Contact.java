package ke.co.comsterhomes.www.sqllitelab;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by collinsnyamao on 10/17/17.
 */

public class Contact {

    int _id;
    String _name;
    String _phone_number;

    //Empty constructor
    public Contact() {

    }

    //constructor
    public Contact(int id, String name, String _phone_number) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;

    }
    //constructor

    public Contact(String name, String _phone_number) {
        this._name = name;
        this._phone_number = _phone_number;
    }
    //getting ID

    public int getID() {
        return this._id;
    }
    //getting name

    public String getName() {
        return this._name;
    }
    //getting phonenumber

    public String getPhoneNumber() {
        return this._phone_number;
    }

    //SETTERS
    //setting id

    public void setID(int _id) {
        this._id = _id;
    }

    //setting name

    public void setName(String _name) {
        this._name = _name;
    }

    //setting phone


    public void setPhoneNumber(String _phone_number) {
        this._phone_number = _phone_number;
    }
}
