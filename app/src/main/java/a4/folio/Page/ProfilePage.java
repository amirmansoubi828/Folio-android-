package a4.folio.Page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import a4.folio.ConnectionManager;
import a4.folio.R;

/**
 * Created by amir on 7/9/2018.
 */

public class ProfilePage extends AppCompatActivity {
    private Button goToHomePage, goToNewsListPage, goToMovieListPage;
    private TextView username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        username = (TextView) findViewById(R.id.ProfilePage_textView_username);
        goToHomePage = (Button) findViewById(R.id.ProfilePage_button_goToHomePage);
        goToMovieListPage = (Button) findViewById(R.id.ProfilePage_button_goToMovieListPage);
        goToNewsListPage = (Button) findViewById(R.id.ProfilePage_button_goToNewsListPage);
        username.setText(ConnectionManager.getUsername());
        goToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this,HomePage.class));
            }
        });
        goToMovieListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this,MovieListPage.class));
            }
        });
        goToNewsListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this,NewsListPage.class));
            }
        });


    }
}
