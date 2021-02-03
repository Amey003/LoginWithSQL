package com.example.loginwithsql;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginwithsql.data.LocalDB;
import com.example.loginwithsql.data.User;

public class MainActivity extends AppCompatActivity {

    private EditText edUsername;
    private EditText edPassword;
    private Button btnLogin;
    private Button btnSignUp;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.passwd);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
                String strUsername = credentials.getString("Username", null);
                String strPassword = credentials.getString("Password", null);*/

                final String username_from_ed = edUsername.getText().toString();
                final String password_from_ed = edPassword.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LocalDB dbInstance = RoomImplementation.getmInstance().getDbInstance();
                        User user = dbInstance.userDao().getUserByUsername(username_from_ed);

                        if (user != null && user.getUsername() != null && username_from_ed != null && user.getUsername().equalsIgnoreCase(username_from_ed)) {
                            if (user.getPassword() != null && password_from_ed != null && user.getPassword().equalsIgnoreCase(password_from_ed)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

}
