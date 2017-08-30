package farmer.farmer;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Ankit Kumar on 25-07-2016.
 */
public class popup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.popup);
        {
            final Dialog dialog = new Dialog(popup.this);
            dialog.getWindow();
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.forgot_search);
            dialog.show();

            final EditText security=(EditText) dialog.findViewById(R.id.ed_username_sign_up);
            final TextView getpass = (TextView) dialog.findViewById(R.id.textView3);

            Button ok= (Button) dialog.findViewById(R.id.getpassword_btn);
            TextView cancel=(Button) dialog.findViewById(R.id.cancel_btn);

            dialog.show();
        }
    }
}
