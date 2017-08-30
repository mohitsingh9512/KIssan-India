package farmer.farmer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import farmer.farmer.data.User;
import farmer.farmer.data.service.UserService;

/**
 * Created by Ankit Kumar on 11-07-2016.
 */
public class Sign_Up extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private CompoundButton newsletterSubscriptionCompoundButton;
    private CompoundButton allowOtherEmailCompoundButton;
    private ProgressDialog progressDialog;
    private Bitmap bitmap;
    private Button btn_skip_in_sign_up_activity;
    private TextView tv_login_in_sign_up_activity;
    private CheckBox checkbox1;

    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        this.setTitle("Sign Up!");
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CheckBox iHaveAnEmailCheckBox = (CheckBox) findViewById(R.id.checkbox_i_have_an_email);

        // Button Skip
        btn_skip_in_sign_up_activity = (Button) findViewById(R.id.btn_skip_in_sign_up_activity);
        btn_skip_in_sign_up_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_Up.this, Intro_Slider.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //clear all the previous activities
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        //TextView Login in SignUp Activity
        tv_login_in_sign_up_activity = (TextView) findViewById(R.id.tv_login_in_sign_up_activity);
        tv_login_in_sign_up_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_Up.this, Log_In.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP); //clear all the previous activities
                startActivity(i);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        iHaveAnEmailCheckBox.setOnCheckedChangeListener(iHaveAnEmailCheckBoxCheckedChange);

        if(UserService.getInstance(this).getCurrentUser()!=null)
        {
            goToWelcome_Activity_After_Log_In();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);


        usernameEditText = (EditText) findViewById(R.id.ed_username_sign_up);
        passwordEditText = (EditText) findViewById(R.id.ed_password_sign_up);
        emailEditText = (EditText) findViewById(R.id.ed_email_in_signup);
        genderRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_gender);
        maleRadioButton = (RadioButton) findViewById(R.id.radiobutton_male);
        femaleRadioButton = (RadioButton) findViewById(R.id.radiobutton_female);
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);

        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO Auto-generated method stud
                if (!isChecked)
                {
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        if(newsletterSubscriptionCompoundButton == null)
        {
            newsletterSubscriptionCompoundButton = (CompoundButton) findViewById(R.id.checkbox_subscription);
        }

        if(allowOtherEmailCompoundButton == null) {
            allowOtherEmailCompoundButton = (CompoundButton) findViewById(R.id.checkbox_allow_email);
        }
        maleRadioButton.setChecked(true);



    }

    CompoundButton.OnCheckedChangeListener iHaveAnEmailCheckBoxCheckedChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            if (checked)
            {
                findViewById(R.id.ed_email_in_signup).setVisibility(View.VISIBLE);
            }
            else
            {
                findViewById(R.id.ed_email_in_signup).setVisibility(View.GONE);
            }
        }
    };

    View.OnClickListener onClickProfileImageViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            chooseImage();
        }
    };

    private void chooseImage()
    {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return true;
            default:

        int id = item.getItemId();
        if (id == R.id.action_submit) {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String email = emailEditText.getText().toString();

            User.Gender gender;
            if(genderRadioGroup.getCheckedRadioButtonId() == R.id.radiobutton_male)
            {
                gender = User.Gender.MALE;
            }
            else
            {
                gender = User.Gender.FEMALE;
            }
            boolean newsletterSubscribed = newsletterSubscriptionCompoundButton.isChecked();
            boolean allowedOtherEmail = allowOtherEmailCompoundButton.isChecked();

            register(username,password,email,gender,newsletterSubscribed,allowedOtherEmail);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }

    private void register(String username,String password, String email, User.Gender gender, boolean newsletterSubscribed, boolean allowedOtherEmail)
    {
        progressDialog.show();
        UserService.getInstance(Sign_Up.this).register(username,password,email,gender,newsletterSubscribed,allowedOtherEmail,registerListener,bitmap);
    }

    UserService.RegisterListener registerListener = new UserService.RegisterListener() {
        @Override
        public void onResponce(boolean registered, String message, User user) {
            progressDialog.dismiss();
            Toast.makeText(Sign_Up.this, message, Toast.LENGTH_SHORT).show();
            if(registered)
            {
                goToWelcome_Activity_After_Log_In();
            }
        }
    };

    private void goToWelcome_Activity_After_Log_In()
    {
        Intent intent = new Intent(this,Welcome_Activity_After_Log_In.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}