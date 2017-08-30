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
public class UpdateCrop extends AppCompatActivity implements View.OnClickListener {

    private EditText variety,harvestedMonth,quantity,expectedPrice;
    Spinner spinner;
    private Button updateBtn, deleteBtn;
    String item;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Record");
        setContentView(R.layout.activity_modify_record);
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        spinner=(Spinner)findViewById(R.id.spinnerCropName);
        dbManager = new DBManager(this);
        dbManager.open();

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
        variety=(EditText)findViewById(R.id.editVariety);
        harvestedMonth=(EditText)findViewById(R.id.editHarvMonth);
        quantity=(EditText)findViewById(R.id.editQuantity);
        expectedPrice=(EditText)findViewById(R.id.editPriceExp);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String strname = intent.getStringExtra("title");
        String strvariety = intent.getStringExtra("variety");
        String strdateH = intent.getStringExtra("dateH");
        String strquantity = intent.getStringExtra("quantity");
        String strprice = intent.getStringExtra("price");

        _id = Long.parseLong(id);

        variety.setText(strvariety);
        harvestedMonth.setText(strdateH);
        quantity.setText(strquantity);
        expectedPrice.setText(strprice);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String cropname = item;
                String strvariety = variety.getText().toString();
                String strdateH = harvestedMonth.getText().toString();
                String strquantity = quantity.getText().toString();
                String strprice = expectedPrice.getText().toString();

                dbManager.update(_id,cropname,strvariety,strdateH,strquantity,strprice);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MyCrops.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
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
                    Intent intent = new Intent(UpdateCrop.this, SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    return true;
                }

                else if (id == R.id.action_share_feedback) {
                    Intent intent = new Intent(UpdateCrop.this, Action_Feedback.class);
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
