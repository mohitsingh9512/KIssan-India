package farmer.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Created by Ankit Kumar on 11-07-2016.
 */
public class Intro_Slider extends AppCompatActivity {
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    ImageView iv1,iv2,iv3,iv4;
    Button btnNext,btnPrevious,btnfertilizer , btnmy_crops_in_intro_slider, btnmarket_price, btnFollowUsOn;
    int[] resources = {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("DASHBOARD");
        setContentView(R.layout.intro_slider);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.dashboard);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        iv1 = (ImageView)findViewById(R.id.imgmandirate);
        iv2 = (ImageView)findViewById(R.id.imgweather);
        iv3 = (ImageView)findViewById(R.id.imgnews);
        iv4 = (ImageView)findViewById(R.id.imgaskanexpert);

        btnfertilizer = (Button)findViewById(R.id.btnfertilizer);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnPrevious = (Button)findViewById(R.id.btnPrevious);
        btnmy_crops_in_intro_slider = (Button) findViewById(R.id.btnmy_crops_in_intro_slider);
        btnmarket_price = (Button) findViewById(R.id.btnmarket_price);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnNext==v){
                    mViewFlipper.showNext();
                }
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnPrevious==v){
                    mViewFlipper.showPrevious();
                }
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Slider.this,MarketPrice.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Slider.this,Weather.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Slider.this,NewsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Slider.this,AskExpertActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        btnfertilizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Slider.this,FertilizerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        btnmy_crops_in_intro_slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Slider.this, MyCrops.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        btnmarket_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Slider.this, MarketPrice.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resources[i]);
            mViewFlipper.addView(imageView);
        }

        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);


        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getX() > e2.getX()) {
                mViewFlipper.setInAnimation(Intro_Slider.this, R.anim.left_in);
                mViewFlipper.setOutAnimation(Intro_Slider.this, R.anim.left_out);


                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.setInAnimation(Intro_Slider.this, R.anim.right_in);
                mViewFlipper.setOutAnimation(Intro_Slider.this, R.anim.right_out);


                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id==R.id.action_settings) {
            Intent intent = new Intent(Intro_Slider.this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            return true;
        }


        else if (id == R.id.action_share_feedback) {
            Intent intent = new Intent(Intro_Slider.this, Action_Feedback.class);
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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
