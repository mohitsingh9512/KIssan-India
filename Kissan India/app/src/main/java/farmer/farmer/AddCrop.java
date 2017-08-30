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
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mohit Singh on 7/18/2016.
 */
public class AddCrop extends AppCompatActivity {

    private Button addTodoBtn;
    private EditText variety,harvestedMonth,quantity,expectedPrice;
    Spinner spinner;
    String item;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_record);
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        variety=(EditText)findViewById(R.id.editVariety);
        harvestedMonth=(EditText)findViewById(R.id.editHarvMonth);
        quantity=(EditText)findViewById(R.id.editQuantity);
        expectedPrice=(EditText)findViewById(R.id.editPriceExp);
        addTodoBtn = (Button) findViewById(R.id.btnaddcrop);
        spinner=(Spinner)findViewById(R.id.spinnerCropName);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strCropName=item;
                final String strVariety=variety.getText().toString();
                final String strHarvestedMonth=harvestedMonth.getText().toString();
                final String strQuantity=quantity.getText().toString();
                final String strExpectedPrice=expectedPrice.getText().toString();

                dbManager.insert(strCropName,strVariety,strHarvestedMonth,strQuantity,strExpectedPrice);

                Intent main = new Intent(AddCrop.this, MyCrops.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);

            }
        });
        //spinner click Listener

        spinner.setOnItemSelectedListener(new SpinnerHandler());

        //Spinner drop down elements

        List<String> product = new ArrayList<String>();
        product.add("Potato");
        product.add("Tomato");
        product.add("LadyFinger");
        product.add("Wheat");
        product.add("Rice");
        product.add("Cucumber");
        product.add("Maize");

        //adapter for spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, product);

        //drop down layout style-list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private class SpinnerHandler implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //on selecting a spinner item
             item = parent.getItemAtPosition(position).toString();

            //showing selected spinner item
        }

        public void onNothingSelected(AdapterView<?> arg0) {


        }
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
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return true;
            default:

                int id = item.getItemId();

                if (id==R.id.action_settings) {
                    Intent intent = new Intent(AddCrop.this, SettingsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    return true;
                }

                else if (id == R.id.action_share_feedback) {
                    Intent intent = new Intent(AddCrop.this, Action_Feedback.class);
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
    }
}
