package farmer.farmer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public   class MarketPrice extends AppCompatActivity {
    Spinner state, district, mandi;
    ArrayAdapter<String> adapter1, adapter2, adapter3;
    Button button;
    SQLiteDatabase db;
    String strState,strDistrict,strMandi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Market Price");
        setContentView(R.layout.market_price);
        //Set back button on action bar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=this.openOrCreateDatabase("Mandi.db", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists mandi(state text,district text,mandi text,image blob)");

        /*
         * Adding database
         */
        Bitmap bmp1= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.image_azadpur);

        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        byte[] image1 = stream1.toByteArray();
        ContentValues values1=new ContentValues();
        values1.put("state", "Delhi");
        values1.put("district", "Azadpur");
        values1.put("mandi", "Azadpur");
        values1.put("image",image1);
        db.insert("Mandi", null, values1);

        Bitmap bmp2= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.image_shahdara);

        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bmp2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
        byte[] image2 = stream2.toByteArray();
        ContentValues values2=new ContentValues();
        values2.put("state","Delhi");
        values2.put("district","Shahdara");
        values2.put("mandi","Shahdara");
        values2.put("image",image2);
        db.insert("Mandi", null, values2);

        Bitmap bmp3= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.image_agra);

        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
        bmp3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
        byte[] image3 = stream3.toByteArray();
        ContentValues values3=new ContentValues();
        values3.put("state","Uttar Pradesh");
        values3.put("district","Agra");
        values3.put("mandi","Agra");
        values3.put("image",image3);
        db.insert("Mandi", null, values3);

        Bitmap bmp4= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.image_ghazipur);

        ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
        bmp4.compress(Bitmap.CompressFormat.PNG, 100, stream4);
        byte[] image4 = stream4.toByteArray();
        ContentValues values4=new ContentValues();
        values4.put("state","Uttar Pradesh");
        values4.put("district","Gazipur");
        values4.put("mandi","Gazipur");
        values4.put("image",image4);
        db.insert("Mandi", null, values4);

        Bitmap bmp5= BitmapFactory.decodeResource(this.getResources(),
                R.drawable.image_shahjahanpur);

        ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
        bmp5.compress(Bitmap.CompressFormat.PNG, 100, stream5);
        byte[] image5 = stream4.toByteArray();
        ContentValues values5=new ContentValues();
        values5.put("state","Uttar Pradesh");
        values5.put("district","Meerut");
        values5.put("mandi","Shahjahanpur");
        values5.put("image",image5);
        db.insert("Mandi", null, values5);

        /*
         * Added database
         */

        state = (Spinner) findViewById(R.id.state);
        district = (Spinner) findViewById(R.id.district);
        mandi = (Spinner) findViewById(R.id.mandi);
        button=(Button)findViewById(R.id.button);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        state.setAdapter(adapter1);
        district.setAdapter(adapter2);
        mandi.setAdapter(adapter3);
        adapter1.add("Delhi");
        adapter1.add("Uttar Pradesh");
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strState = parent.getSelectedItem().toString();
                adapter2.clear();
                adapter3.clear();
                if ("Delhi".equals(strState)) {
                    adapter2.add("Azadpur");
                    adapter2.add("Shahdara");
                } else if ("Uttar Pradesh".equals(strState)) {
                    adapter2.add("Agra");
                    adapter2.add("Gazipur");
                    adapter2.add("Meerut");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strDistrict = parent.getSelectedItem().toString();
                adapter3.clear();
                if ("Azadpur".equals(strDistrict)) {
                    adapter3.add("Azadpur");
                    strMandi="Azadpur";

                } else if ("Shahdara".equals(strDistrict)){
                    adapter3.add("Shahdara");
                    strMandi="Shahdara";

                } else if ("Agra".equals(strDistrict)){
                    adapter3.add("Agra");
                    strMandi="Agra";

                } else if ("Gazipur".equals(strDistrict)) {
                    adapter3.add("Gazipur");
                    strMandi="Gazipur";

                } else if ("Meerut".equals(strDistrict)) {
                    adapter3.add("Shahjahanpur");
                    strMandi="Shahjahanpur";

                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor;
                cursor=db.rawQuery("select * from Mandi where state='"+strState+ "' And "+"district='"+strDistrict+"' And "+"mandi='"+strMandi+"'",null);
                if(cursor.moveToNext())
                {
                    byte[] image=cursor.getBlob(3);
                    MandiImage.bmp= BitmapFactory.decodeByteArray(image,0,image.length);
                    Intent i=new Intent(MarketPrice.this,MandiImage.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Got the image",Toast.LENGTH_LONG).show();
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
                    Intent intent = new Intent(MarketPrice.this, SettingsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    return true;
                }



                else if (id == R.id.action_share_feedback) {
                    Intent intent = new Intent(MarketPrice.this, Action_Feedback.class);
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

