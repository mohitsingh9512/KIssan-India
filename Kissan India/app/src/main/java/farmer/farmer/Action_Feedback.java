package farmer.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Ankit Kumar on 31-07-2016.
 */
public class Action_Feedback extends AppCompatActivity {
    private static final int ANIMATION_DURATION = 1500;
    private static final int ANIMATION_OFFSET = 250;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.action_feedback);
        this.setTitle("Feedback");
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);
        slideAnimation.setDuration(ANIMATION_DURATION/2);

        findViewById(R.id.lyt_card).startAnimation(slideAnimation);

        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate();
            }
        });

    }
    /**
     * Makes the card to go inside the envelope animation.
     */
    public void animate() {

        final View cardView = findViewById(R.id.lyt_card);
        cardView.setVisibility(View.VISIBLE);

        final View frontView = findViewById(R.id.envelope_front_card);
        frontView.setVisibility(View.VISIBLE);

        final View backView = findViewById(R.id.envelope_back_card);
        backView.setVisibility(View.VISIBLE);

        final View flapContainerView = findViewById(R.id.envelope_flap_container);
        flapContainerView.setVisibility(View.VISIBLE);


        cardView.post(new Runnable() {
            @Override
            public void run() {
                final float animLength = cardView.getHeight() * .75f;

                TranslateAnimation animation1 = new TranslateAnimation(0, 0, 0, -animLength);
                animation1.setDuration(ANIMATION_DURATION);
                animation1.setStartOffset(ANIMATION_OFFSET);
                animation1.setFillAfter(true);
                animation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mailing_base);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout.getLayoutParams();
                        layoutParams.bottomMargin = (int) animLength;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                findViewById(R.id.mailing_base).startAnimation(animation1);

                TranslateAnimation animation = new TranslateAnimation(0, 0, 0, animLength * 1.75f);
                animation.setDuration(ANIMATION_DURATION);
                animation.setFillAfter(true);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        flapAnimate();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                cardView.startAnimation(animation);
            }
        });

    }

    /**
     * Performs the flying animation of the envelope.
     */
    public void flyEnvelope() {

        View mailingCard = findViewById(R.id.mailing_base);

        final View cardView = findViewById(R.id.lyt_card);

        int cardHeight = cardView.getHeight();

        final int animLength = cardHeight;

        int duration = (int) (ANIMATION_DURATION * 2f/3);
        RotateAnimation rotationAnimation = new RotateAnimation(0, 45, mailingCard.getWidth() / 2, mailingCard.getHeight() / 2);
        rotationAnimation.setDuration(duration);

        float scale = 0.2f;
//
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, scale, 1f, scale);
        scaleAnimation.setDuration(duration);


        int locationOnScreen[] = new int[2];
        mailingCard.getLocationOnScreen(locationOnScreen);


        int widthOfScreen = getResources().getDisplayMetrics().widthPixels;
        int heightOfScreen = getResources().getDisplayMetrics().heightPixels;
        int translateX = (int) (widthOfScreen * 2.5f);
        int translateY = (int) (heightOfScreen * 1.5f);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, translateX, -animLength, -translateY);
        translateAnimation.setDuration(duration);

        AnimationSet set = new AnimationSet(this, null);
        set.addAnimation(translateAnimation);
        set.addAnimation(rotationAnimation);
        set.addAnimation(scaleAnimation);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                onEmailFlew();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mailingCard.startAnimation(set);


    }

    /**
     * Callback after the email had flown
     */
    private void onEmailFlew() {

        String email = getString(R.string.support_email);
        String text = ((TextView)findViewById(R.id.email_msg_box)).getText().toString();
        String subject = ((TextView)findViewById(R.id.email_subject_box)).getText().toString();

        Intent intent = getIntentForEmail(email,subject,text);

        // start email activity only if this activity was not started with startActivityForResult(). Otherwise, return the results to the
        // previous activity.
        if( getCallingActivity() == null) {
            startActivity(intent);
        }
        else {
            setResult(RESULT_OK,intent);
        }

        finish();
    }

    /**
     * Performs an animation to close the envelope flap
     */
    public void flapAnimate() {


        View flapContainer = findViewById(R.id.envelope_flap_container);
        View front = findViewById(R.id.envelope_front_card);

        front.bringToFront();

        flapContainer.bringToFront();

        View openFlap = findViewById(R.id.flap_open);
        openFlap.setVisibility(View.VISIBLE);

        View closeFlap = findViewById(R.id.flap_close);
        closeFlap.setVisibility(View.GONE);

        FlipAnimator animator = new FlipAnimator(openFlap, closeFlap, flapContainer.getWidth() / 2, flapContainer.getHeight() / 2);
        animator.setDuration(200);
        animator.setTranslateDirection(FlipAnimator.DIRECTION_Z);
        animator.setRotationDirection(FlipAnimator.DIRECTION_X);
        animator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flyEnvelope();
                    }
                }, ANIMATION_DURATION / 3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        flapContainer.startAnimation(animator);

    }


    public static Intent getIntentForEmail(String to, String subject, String message) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.

                int id = item.getItemId();

                if (id==R.id.action_settings) {
                    Intent intent = new Intent(Action_Feedback.this, SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    return true;
                }

                else if (id == R.id.action_share_feedback) {
                    Intent intent = new Intent(Action_Feedback.this, Action_Feedback.class);
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
    }
}
