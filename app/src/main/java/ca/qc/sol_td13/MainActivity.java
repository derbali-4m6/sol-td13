package ca.qc.sol_td13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final int REQUESTID_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //vérifier que la permission est accordée
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUESTID_READ_CONTACTS);
        }

        lireContacts();

    }

    private void lireContacts() {
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        //lancer la requête query()
        Cursor cursor = resolver.query(uri, projection, null, null, null );

        //affichage des données trouvées
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                String phone = cursor.getString(1);
                Log.d("ContentProvider", name + " ==> " + phone);
            }while(cursor.moveToNext());
        }
    }
}