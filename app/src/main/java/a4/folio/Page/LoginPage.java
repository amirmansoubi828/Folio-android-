package a4.folio.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.FlatButton;
import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;

import a4.folio.ConnectionManager;
import a4.folio.DataType.ResultMessage;
import a4.folio.Listeners.ResultListener;
import a4.folio.R;

/**
 * Created by amir on 7/9/2018.
 */

public class LoginPage extends AppCompatActivity {
    private EditText username, password;
    private ActionProcessButton signIn;
    private Button goToSignUp;
    private ConnectionManager connectionManager;

    //// FIXME: 7/9/2018
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username = (EditText) findViewById(R.id.login_username_editText);
        password = (EditText) findViewById(R.id.login_password_editText);
        signIn = (ActionProcessButton) findViewById(R.id.login_signIn_button);
        goToSignUp = (Button) findViewById(R.id.login_createAccount_button);
        connectionManager = new ConnectionManager(username.getText().toString(), password.getText().toString());
        connectionManager.setLoginListener(new ResultListener() {
            @Override
            public void onResultReceived(ResultMessage message) {
                Toast.makeText(LoginPage.this, String.valueOf(message.getResult() + " " + message.getInfo()), Toast.LENGTH_SHORT).show();
                if (message.getResult()) {
                    signIn.setProgress(100);
                    ConnectionManager.setUsername(username.getText().toString());
                    Intent intent = new Intent(LoginPage.this, ProfilePage.class);
                    startActivity(intent);
                    //finish();
                }{
                    signIn.setProgress(-1);
                }
                signIn.setEnabled(true);
                goToSignUp.setEnabled(true);
            }
        });
        // buttons need listener
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn.setProgress(50);
                signIn.setMode(ActionProcessButton.Mode.ENDLESS);
                signIn.setEnabled(false);
                goToSignUp.setEnabled(false);
                connectionManager.requestLogin(String.valueOf(username.getText()), String.valueOf(password.getText()));
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signIn.setProgress(0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signIn.setProgress(0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
