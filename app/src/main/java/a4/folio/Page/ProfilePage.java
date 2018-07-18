package a4.folio.Page;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import a4.folio.ConnectionManager;
import a4.folio.DataType.ResultMessage;
import a4.folio.Listeners.LogoutResultListener;
import a4.folio.R;

/**
 * Created by amir on 7/9/2018.
 */

public class ProfilePage extends AppCompatActivity {
    private Button goToHomePage, goToNewsListPage, goToMovieListPage;
    private TextView username;
    private ConnectionManager connectionManager;
    private FloatingActionButton logoutFAB;
    private Typeface BNtypeface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        username = (TextView) findViewById(R.id.ProfilePage_textView_username);
        goToHomePage = (Button) findViewById(R.id.ProfilePage_button_goToHomePage);
        goToMovieListPage = (Button) findViewById(R.id.ProfilePage_button_goToMovieListPage);
        goToNewsListPage = (Button) findViewById(R.id.ProfilePage_button_goToNewsListPage);
        logoutFAB = (FloatingActionButton) findViewById(R.id.ProfilePage_FAB);

        BNtypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "BNaznnBd.ttf");

        goToHomePage.setTypeface(BNtypeface);
        goToMovieListPage.setTypeface(BNtypeface);
        goToNewsListPage.setTypeface(BNtypeface);

        username.setText(ConnectionManager.getUsername());

        connectionManager = new ConnectionManager();
        connectionManager.setLogoutResultListener(new LogoutResultListener() {
            @Override
            public void onResultReceived(ResultMessage message) {
                Toast.makeText(ProfilePage.this, message.getResult() + " " + message.getInfo(), Toast.LENGTH_SHORT).show();
                if (message.getResult()) {
                    finish();
                }
            }
        });

        goToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, HomePage.class));
            }
        });
        goToMovieListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, MovieListPage.class));
            }
        });
        goToNewsListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, NewsListPage.class));
            }
        });

        logoutFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectionManager.requestLogout(ConnectionManager.getUsername());
            }
        });


    }

}
