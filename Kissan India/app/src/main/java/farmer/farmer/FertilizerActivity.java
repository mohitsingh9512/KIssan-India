package farmer.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class FertilizerActivity extends AppCompatActivity {
    Spinner spinner;
    String[] SPINNERVALUES = {"Cucumber","Lady Finger","Maize","Potato","Rice","Tomato","Wheat"};
    String SpinnerValue;
    Button GOTO;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Fertilizer");
        setContentView(R.layout.activity_fertilizer);
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner =(Spinner)findViewById(R.id.spinnercropfertilizer);

        GOTO = (Button)findViewById(R.id.btnCheckFertilizer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FertilizerActivity.this, android.R.layout.simple_list_item_1, SPINNERVALUES);

        spinner.setAdapter(adapter);

        //Adding setOnItemSelectedListener method on spinner.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                SpinnerValue = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        GOTO.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                switch(SpinnerValue){

                    case "Cucumber":
                        intent = new Intent(FertilizerActivity.this, CucumberFertilizer.class);
                        startActivity(intent);
                        break;

                    case "Lady Finger":
                        intent = new Intent(FertilizerActivity.this, LadyfingerFertilizer.class);
                        startActivity(intent);
                        break;
                    case "Maize":
                        intent = new Intent(FertilizerActivity.this, MaizeFertilizer.class);
                        startActivity(intent);
                        break;
                    case "Potato":
                        intent = new Intent(FertilizerActivity.this, PotatoFertilizer.class);
                        startActivity(intent);
                        break;
                    case "Rice":
                        intent = new Intent(FertilizerActivity.this, RiceFertilizer.class);
                        startActivity(intent);
                        break;
                    case "Tomato":
                        intent = new Intent(FertilizerActivity.this, TomatoFertilizer.class);
                        startActivity(intent);
                        break;

                    case "Wheat":
                        intent = new Intent(FertilizerActivity.this, WheatFertilizer.class);
                        startActivity(intent);
                        break;


                }
            }
        });

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
                    Intent intent = new Intent(FertilizerActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    return true;
                }



                else if (id == R.id.action_share_feedback) {
                    Intent intent = new Intent(FertilizerActivity.this, Action_Feedback.class);
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