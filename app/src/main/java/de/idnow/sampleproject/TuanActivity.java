package de.idnow.sampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.idnow.R;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TuanActivity extends AppCompatActivity {

    public static final String TUAN_MESSAGE = "tuan.message";

    @BindView(R.id.tuan_button)
    protected Button submitButton;

    @BindView(R.id.tuan_email)
    protected EditText emailInput;

    @BindView(R.id.tuan_password)
    protected EditText passwordInput;

    String msg = "Android";

    public TuanActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuan);
        ButterKnife.bind(this);
        init();
        Log.d(msg, "This is from onCreate");
        emailInput.setText("abc@gmail.com");
        passwordInput.setText("12312");
    }

    private void init() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // To be refractored
                String emailString = emailInput.getText().toString();
                String passwordString = passwordInput.getText().toString();

                if (!emailString.isEmpty()) {
                    if (isEmailValid(emailString)) {
                        login(emailString, passwordString);
                    } else {
                        Toast.makeText(TuanActivity.this, "Email format is not correct", 10).show();
                    }
                } else {
                    Toast.makeText(TuanActivity.this, "Please input your text", 10).show();
                }
            }
        });
    }

    private boolean isEmailValid(String email)
    {
        String emailExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence emailString = email;

        Pattern pattern = Pattern.compile(emailExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailString);

        return (matcher.matches()) ? true : false;
    }

    private void login(String email, String password) {
        Intent intent = new Intent(this, TodoListActivity.class);
        intent.putExtra(TUAN_MESSAGE, email);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "This is from onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "This is from onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "This is from onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "This is from onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(msg, "This is from onDestroy");
    }

}
