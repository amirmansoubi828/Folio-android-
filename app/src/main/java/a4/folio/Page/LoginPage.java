package a4.folio.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import a4.folio.ConnectionManager;
import a4.folio.DataType.ResultMessage;
import a4.folio.Listeners.ResultListener;
import a4.folio.R;

/**
 * Created by amir on 7/9/2018.
 */

public class LoginPage extends AppCompatActivity {
    private EditText username, password;
    private Button signIn, goToSignUp;
    private ConnectionManager connectionManager;

    //// FIXME: 7/9/2018
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username = (EditText) findViewById(R.id.login_username_editText);
        password = (EditText) findViewById(R.id.login_password_editText);
        signIn = (Button) findViewById(R.id.login_signIn_button);
        goToSignUp = (Button) findViewById(R.id.login_createAccount_button);
        connectionManager = new ConnectionManager();
        connectionManager.setLoginListener(new ResultListener() {
            @Override
            public void onResultReceived(ResultMessage message) {
                Toast.makeText(LoginPage.this, String.valueOf(message.getResult() + " " + message.getInfo()), Toast.LENGTH_SHORT).show();
                if (message.getResult()) {
                    ConnectionManager.setUsername(username.getText().toString());
                    Intent intent = new Intent(LoginPage.this, HomePage.class);
                    startActivity(intent);
                }
            }
        });
        // buttons need listener
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginPage.this, "wait", Toast.LENGTH_SHORT).show();
                connectionManager.requestLogin(String.valueOf(username.getText()), String.valueOf(password.getText()));
            }
        });
    }
}
