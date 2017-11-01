package ke.co.comsterhomes.www.sqllitelab;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by collinsnyamao on 10/18/17.
 */

public class AndroidSqlLiteTutorialActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Log.d("insert","inserting...");
        databaseHandler.addContact(new Contact("ravi","542309271"));
        databaseHandler.addContact(new Contact("stuper","83167167"));
        databaseHandler.addContact(new Contact("Gratuit","71864871"));
        databaseHandler.addContact(new Contact("Festinas","273648264"));

        Log.d("Reading" , "Reading...");
        List<Contact> contacts = databaseHandler.getAllContacts();
        for (Contact cn: contacts){
            String log = "Id: " + cn.getID() + "Name : " + cn.getName() + "Phone number: " + cn.getPhoneNumber();

            Log.d("Name: " , log);
        }
    }
}
