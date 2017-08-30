package farmer.farmer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import farmer.farmer.data.User;

/**
 * Created by Ankit Kumar on 12-07-2016.
 */
public class Profile_Activity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView usernameTextView;
    private TextView emailTextVIew;
    private TextView genderTextView;
    private CompoundButton newsletterSubscriptionCompoundButton;
    private CompoundButton allowOtherEmailCompoundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Profile");
        setContentView(R.layout.profile_activity);
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = (User) getIntent().getExtras().getSerializable("user");

        if(user==null) {
            finish();
            return;
        }

        profileImageView = (ImageView) findViewById(R.id.imageview_profile);
        usernameTextView = (TextView) findViewById(R.id.textview_username);
        emailTextVIew = (TextView) findViewById(R.id.textview_email);
        genderTextView = (TextView) findViewById(R.id.textview_gender);

        if(newsletterSubscriptionCompoundButton==null)
        {
            newsletterSubscriptionCompoundButton = (CheckBox) findViewById(R.id.checkbox_subscription);
        }

        if(allowOtherEmailCompoundButton==null)
        {
            allowOtherEmailCompoundButton = (CheckBox) findViewById(R.id.checkbox_allow_email);
        }


        usernameTextView.setText(String.format("Username: %s",user.getUsername()));
        emailTextVIew.setText(String.format("Email: %s",user.getEmail()));
        genderTextView.setText(String.format("Gender: %s",user.getGender()== User.Gender.MALE?"Male":"Female"));
        newsletterSubscriptionCompoundButton.setChecked(user.isNewsletterSubscribed());
        allowOtherEmailCompoundButton.setChecked(user.isAllowedOtherEmail());

        if(!allowOtherEmailCompoundButton.isChecked())
        {
            emailTextVIew.setVisibility(View.GONE);
        }
    }

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
