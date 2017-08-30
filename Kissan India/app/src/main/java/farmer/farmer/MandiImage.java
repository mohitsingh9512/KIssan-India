package farmer.farmer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Mohit Singh on 7/22/2016.
 */
public class MandiImage extends Activity {
    static Bitmap bmp;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mandi_image);
        imageView=(ImageView)findViewById(R.id.imageView);

        imageView.setImageBitmap(bmp);

    }
}
