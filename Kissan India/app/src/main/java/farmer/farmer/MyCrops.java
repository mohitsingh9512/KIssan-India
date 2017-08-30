package farmer.farmer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Mohit Singh on 7/17/2016.
 */
public class MyCrops extends ActionBarActivity {
    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.KEY_NAME, DatabaseHelper.KEY_VARIETY,DatabaseHelper.KEY_DATE_H,DatabaseHelper.KEY_QUANTITY,DatabaseHelper.KEY_EXP_PRICE };

    final int[] to = new int[] { R.id.id, R.id.title,R.id.variety,R.id.dateH,R.id.quantity,R.id.price};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView varietyTextView = (TextView) view.findViewById(R.id.variety);
                TextView dateHTextView = (TextView) view.findViewById(R.id.dateH);
                TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
                TextView priceTextView = (TextView) view.findViewById(R.id.price);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String variety =varietyTextView.getText().toString();
                String dateH =dateHTextView.getText().toString();
                String quantity =quantityTextView.getText().toString();
                String price =priceTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), UpdateCrop.class);
                modify_intent.putExtra("price", price);
                modify_intent.putExtra("quantity", quantity);
                modify_intent.putExtra("dateH", dateH);
                modify_intent.putExtra("variety", variety);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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

                int id = item.getItemId();
                if (id == R.id.add_record) {
                    Intent add_mem = new Intent(this, AddCrop.class);
                    startActivity(add_mem);
                }

                else if (id==R.id.action_settings) {
                Intent intent = new Intent(MyCrops.this, SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                return true;
                }

                else if (id == R.id.action_share_feedback) {
                    Intent intent = new Intent(MyCrops.this, Action_Feedback.class);
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
