package farmer.farmer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import farmer.farmer.data.User;
import farmer.farmer.data.service.UserService;

/**
 * Created by Ankit Kumar on 12-07-2016.
 */
public class Welcome_Activity_After_Log_In extends ActionBarActivity implements View.OnClickListener {

    private Button button;

    private ImageView profileImageView;
    private TextView greetingTextView;
    private Button go_to_main_screen;
    EditText edtitle, eddescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        this.setTitle("Welcome");
        setContentView(R.layout.welcome_activity_after_log_in);
        TextView textView = new TextView(this);


        greetingTextView = (TextView) findViewById(R.id.textview_greeting);
        go_to_main_screen = (Button) findViewById(R.id.go_to_main_screen);

        button = (Button) findViewById(R.id.buttonUrl);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Farmer_Posts.class);
                startActivity(intent);
            }

        });

        //Set a listener on English Button to a new Activity
        go_to_main_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcome_Activity_After_Log_In.this, Intro_Slider.class);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        User user = UserService.getInstance(this).getCurrentUser();

        if(user==null) {
            finish();
            return;
        }

        greetingTextView.setText(String.format("Hello, %s", user.getUsername()));

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), Farmer_Posts.class);
        Bundle b = new Bundle();

        b.putString("title",edtitle.getText().toString());
        b.putString("description",eddescription.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_profile) {
            goToMenuProfilePage();
            return true;
        }else if (id == R.id.menu_logout) {
            logout();
            return true;
        }
        else if (id==R.id.action_settings) {
            Intent intent = new Intent(Welcome_Activity_After_Log_In.this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            return true;
        }

        else if (id == R.id.action_share_feedback) {
            Intent intent = new Intent(Welcome_Activity_After_Log_In.this, Action_Feedback.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            return true;
        }

        else if (id == R.id.action_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Now, you can download Kisaan India app on google play store";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kisaan India");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToUserListPage()
    {
        Intent intent = new Intent(this,User_List_Activity.class);
        startActivity(intent);
    }

    private void goToMenuProfilePage()
    {
        Intent intent = new Intent(this,Profile_Activity.class);
        intent.putExtra("user",UserService.getInstance(this).getCurrentUser());
        startActivity(intent);
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    private void logout()
    {
        UserService.getInstance(this).logout();
        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("remembered",false);
        editor.remove("username");
        editor.remove("password");
        editor.commit();
        finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }
}