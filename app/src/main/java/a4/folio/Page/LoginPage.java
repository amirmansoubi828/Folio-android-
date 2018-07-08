package a4.folio.Page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import a4.folio.R;

/**
 * Created by amir on 7/9/2018.
 */

public class LoginPage extends AppCompatActivity {
    private EditText username, password;
    private Button signIn, goToSignUp;

    //// FIXME: 7/9/2018
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username = (EditText) findViewById(R.id.login_username_editText);
        password = (EditText) findViewById(R.id.login_password_editText);
        signIn = (Button) findViewById(R.id.login_signIn_button);
        goToSignUp = (Button) findViewById(R.id.login_createAccount_button);
        // buttons need listener
    }
}
