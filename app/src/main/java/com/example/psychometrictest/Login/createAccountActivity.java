package com.example.psychometrictest.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.psychometrictest.MycompleteListener;
import com.example.psychometrictest.R;
import com.example.psychometrictest.TEST.FirebaseQuery;
import com.example.psychometrictest.User;
import com.example.psychometrictest.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class createAccountActivity extends AppCompatActivity {
    private Button login;
    private String _firstname;
    private String _lastname;
    private String _username;
    private String _email;
    private String _password;
    private String _phonenumber;
    private TextInputLayout firstname, lastname, username, email, phonenumber, password;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        login = findViewById(R.id.loginbtn);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _firstname = firstname.getEditText().getText().toString();
                _lastname =lastname.getEditText().getText().toString();
                _username = username.getEditText().getText().toString();
                _email = email.getEditText().getText().toString();
                _phonenumber=phonenumber.getEditText().getText().toString();
                _password = password.getEditText().getText().toString();
                if (!validatefirstname() | !phonenoValidation() | !validatelastname() | !validateEmail() | !validateusername() | !ValidatePassword()) {
                    return;
                } else {

                    User user =new User(_firstname,_lastname,_username,_email,_password,_phonenumber);
                    db = FirebaseDatabase.getInstance();
                    reference=db.getReference("User");
                    reference.child(_username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            signupUser();
                           firstname.getEditText().setText("");
                           lastname.getEditText().setText("");
                           username.getEditText().setText("");
                           email.getEditText().setText("");
                           phonenumber.getEditText().setText("");
                           password.getEditText().setText("");
                            Toast.makeText(createAccountActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }
        });


    }


    private boolean validatefirstname() {
        String val = firstname.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            firstname.setError("Field cannot be empty");
            return false;
        } else {
            firstname.setError(null);
            firstname.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatelastname() {
        String value = lastname.getEditText().getText().toString().trim();
        if (value.isEmpty()) {
            lastname.setError("Field cannot be empty");
            return false;
        } else {
            lastname.setError(null);
            lastname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateusername() {
        String value = username.getEditText().getText().toString().trim();
        String space = "\\A\\w{1,20}\\z";
        if (value.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else if (value.length() > 20) {
            username.setError("Username is too large!");
            return false;
        }else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidatePassword() {
        String valpassword = password.getEditText().getText().toString().trim();
        String passwordVal = "^" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{4,}" + "$";
        if (valpassword.isEmpty()) {
            password.setError("field cannot be empty");
            return false;

        } else if (!valpassword.matches(passwordVal)) {
            password.setError("password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String valemail = email.getEditText().getText().toString().trim();
        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (valemail.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!valemail.matches(emailpattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean phonenoValidation() {
        String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{4})(?: *x(\\d+))?\\s*$";
        String numval = phonenumber.getEditText().getText().toString().trim();
        if (numval.isEmpty()) {
            phonenumber.setError("Field cannot be empty");
            return false;
        } else if (!numval.matches(pattern)) {
            phonenumber.setError("Invalid email address");
            return false;
        } else {
            phonenumber.setError(null);
            phonenumber.setErrorEnabled(false);
            return true;
        }
    }

    private void signupUser() {
        String valemail = email.getEditText().getText().toString().trim();
        String namestr = firstname.getEditText().getText().toString().trim();
        String valpassword = password.getEditText().getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(valemail, valpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(createAccountActivity.this, "user is succesfull login", Toast.LENGTH_SHORT).show();

                            // for create document in firebase
                            FirebaseQuery.createUserData(valemail, namestr, new MycompleteListener() {
                                @Override
                                public void onSuccess() {

                                    FirebaseQuery.loadCategories(new MycompleteListener() {
                                        @Override
                                        public void onSuccess() {
                                            Intent profileintent = new Intent(getApplicationContext(), ProfileActivity.class);
                                            startActivity(profileintent);
                                            createAccountActivity.this.finish();
                                        }

                                        @Override
                                        public void OnFailure() {
                                            Toast.makeText(createAccountActivity.this, "Something went Wrong !", Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                }

                                @Override
                                public void OnFailure() {
                                    Toast.makeText(createAccountActivity.this, "Something went Wrong !", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } else {

                            Toast.makeText(createAccountActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


}