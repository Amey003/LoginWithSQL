package com.example.loginwithsql;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginwithsql.data.LocalDB;
import com.example.loginwithsql.data.User;

public class SignUp extends AppCompatActivity {

    private EditText edUsername;
    private EditText edPassword;
    private EditText edConfirmPassowrd;
    private Button btnCreateUser;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.passwd1);
        edConfirmPassowrd = findViewById(R.id.passwd2);
        btnCreateUser = findViewById(R.id.btn_up1);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strPassword = edPassword.getText().toString();
                String strConfirmPassword = edConfirmPassowrd.getText().toString();
                final String strUsername = edUsername.getText().toString();

                if (strPassword != null && strConfirmPassword != null && strPassword.equalsIgnoreCase(strConfirmPassword)) {
/*                    SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = credentials.edit();
                    editor.putString("Password", strPassword);
                    editor.putString("Username", strUsername);
                    editor.commit();*/

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User();
                            user.setUsername(strUsername);
                            user.setPassword(strPassword);

                            LocalDB dbInstance = RoomImplementation.getmInstance().getDbInstance();
                            dbInstance.userDao().createUser(user);
                        }
                    }).start();
                    SignUp.this.finish();
                }
            }
        });
    }
}
