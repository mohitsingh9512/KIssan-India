package farmer.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import farmer.farmer.data.User;
import farmer.farmer.data.adapter.UserListAdapter;
import farmer.farmer.data.service.UserService;

/**
 * Created by Ankit Kumar on 12-07-2016.
 */
public class User_List_Activity extends ActionBarActivity {

    List<User> users = new ArrayList<User>();
    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_activity);

        adapter = new UserListAdapter(this, users);

        UserService.getInstance(this).getUserList(getUserListListener);

        ListView userListView = (ListView) findViewById(android.R.id.list);
        userListView.setAdapter(adapter);
        userListView.setOnItemClickListener(onItemClickListener);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(User_List_Activity.this,Profile_Activity.class);
            intent.putExtra("user",adapter.getItem(i));
            startActivity(intent);
        }
    };

    UserService.GetUserListListener getUserListListener = new UserService.GetUserListListener() {
        @Override
        public void onResponce(boolean success, String message, List<User> userList) {
            if(success)
            {
                users.clear();
                adapter.notifyDataSetChanged();
                users.addAll(userList);
                adapter.notifyDataSetChanged();
            }
        }
    };
}
