package ca.qc.sol_td13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;

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

    }
}