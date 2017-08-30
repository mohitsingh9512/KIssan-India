package farmer.farmer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import farmer.farmer.data.User;
import farmer.farmer.data.service.UserService;


/**
 * Created by Ankit Kumar on 11-07-2016.
 */
public class Log_In extends ActionBarActivity {

    User loginDataBaseAdapter;
    private EditText ed_username_log_in;
    private EditText ed_paswords_log_in;
    private CheckBox checkbox_remember_me;
    private TextView tv_forgot_password;
    private Button btnlog_in;
    private ProgressDialog progressDialog;
    private TextView tvcreate_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        this.setTitle("Welcome Back!");
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(UserService.getInstance(this).getCurrentUser()!=null)
        {
            goToWelcomeActivity();
            return;
        }

        progressDialog = new ProgressDialog(Log_In.this);
        progressDialog.setIndeterminate(true);

        ed_username_log_in = (EditText) findViewById(R.id.ed_username_log_in);
        ed_paswords_log_in = (EditText) findViewById(R.id.ed_password_log_in);
        checkbox_remember_me = (CheckBox) findViewById(R.id.checkbox_remember_me);
        tv_forgot_password =  (TextView) findViewById(R.id.tv_forgot_password);
        btnlog_in =  (Button) findViewById(R.id.btnlog_in);
        tvcreate_one = (TextView) findViewById(R.id.tvcreate_one);

        btnlog_in.setOnClickListener(onClickLoginButtonListener);
        tvcreate_one.setOnClickListener(onClickCreateOneTextViewListener);
        tv_forgot_password.setOnClickListener(onClickForgotPasswordTextViewListener);

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Log_In.this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forgot_search);
                dialog.show();

                final EditText security=(EditText) dialog.findViewById(R.id.ed_username_sign_up);
                final TextView getpass = (TextView) dialog.findViewById(R.id.textView3);

                Button ok= (Button) dialog.findViewById(R.id.getpassword_btn);
                TextView cancel=(Button) dialog.findViewById(R.id.cancel_btn);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userName=security.getText().toString();
                        if (userName.equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Please enter your username", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            String storedPassword=loginDataBaseAdapter.getPassword();
                            if (storedPassword==null)
                            {
                                Toast.makeText(getApplicationContext(), "please enter correct username",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Log.d("GET PASSWORD", storedPassword);
                                getpass.setText(storedPassword);
                            }
                        }
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
        boolean remembered = sharedPreferences.getBoolean("remembered",false);
        if(remembered)
        {
            checkbox_remember_me.setChecked(true);
            String username = sharedPreferences.getString("username", null);
            String password = sharedPreferences.getString("password", null);
            login(username,password);
        }
    }

    private void login(String username,String password)
    {
        progressDialog.show();
        UserService.getInstance(Log_In.this).login(username, password, loginListener);
    }

    private void goToSignupActivity()
    {
        Intent intent = new Intent(this,Sign_Up.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP); //clear all the previous activities
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void goToWelcomeActivity()
    {
        Intent intent = new Intent(this,Welcome_Activity_After_Log_In.class);
        startActivity(intent);
        finish();
    }

    View.OnClickListener onClickLoginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String username = ed_username_log_in.getText().toString();
            String password = ed_paswords_log_in.getText().toString();

            login(username, password);
        }
    };

    View.OnClickListener onClickCreateOneTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToSignupActivity();
        }
    };

    View.OnClickListener onClickForgotPasswordTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Log_In.this);
            builder.setTitle("Warning");
            builder.setIcon(R.drawable.ic_error);
            builder.setMessage("Not implement");
            builder.setPositiveButton("OK",null);
            builder.show();
        }
    };

    UserService.LoginListener loginListener = new UserService.LoginListener() {
        @Override
        public void onResponce(boolean loggedin, String message, User user) {
            progressDialog.dismiss();
            Toast.makeText(Log_In.this,message,Toast.LENGTH_SHORT).show();
            if(loggedin)
            {
                SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(checkbox_remember_me.isChecked())
                {
                    editor.putBoolean("remembered", true);
                    editor.putString("username", user.getUsername());
                    editor.putString("password", user.getPassword());
                }
                else
                {
                    editor.putBoolean("remembered",false);
                    editor.remove("username");
                    editor.remove("password");
                }
                editor.commit();
                goToWelcomeActivity();
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
