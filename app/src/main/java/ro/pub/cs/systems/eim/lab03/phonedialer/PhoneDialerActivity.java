package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private ImageButton callImageButton;
    private ImageButton hangupImageButton;
    private ImageButton backspaceImageButton;
    private Button genericButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View.OnClickListener genericButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + ((Button)view).getText().toString());
            }
        };

        View.OnClickListener hangupImageButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        View.OnClickListener backspaceButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                if (phoneNumber.length() > 0) {
                    phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
                    phoneNumberEditText.setText(phoneNumber);
                }
            }
        };

        View.OnClickListener callImageButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PhoneDialerActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                    startActivity(intent);
                }
            }
        };

        phoneNumberEditText = (EditText) findViewById(R.id.phone_number);
        callImageButton = (ImageButton)findViewById(R.id.call);
        callImageButton.setOnClickListener(callImageButtonClickListener);
        hangupImageButton = (ImageButton)findViewById(R.id.hangup);
        hangupImageButton.setOnClickListener(hangupImageButtonClickListener);
        backspaceImageButton = (ImageButton)findViewById(R.id.backspace);
        backspaceImageButton.setOnClickListener(backspaceButtonClickListener);

        final int nr_buttons = 10;
        int[] buttonsIds = new int[] {
                R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9
        };
        for (int index = 0; index < nr_buttons; index++) {
            genericButton = (Button)findViewById(buttonsIds[index]);
            genericButton.setOnClickListener(genericButtonClickListener);
        }
    }



}
