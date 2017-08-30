package farmer.farmer.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import farmer.farmer.R;
import farmer.farmer.data.User;
import farmer.farmer.data.service.UserService;

public class UserListAdapter extends BaseAdapter {

    Context mContext;
    List<User> mItems;
    public UserListAdapter(Context context,List<User> users)
    {
        mContext = context;
        mItems = users;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public User getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.listitem_user,null);
        }
        User user = getItem(i);
        ImageView profileImageView = (ImageView)view.findViewById(R.id.imageview_profile);
        TextView usernameTextView = (TextView)view.findViewById(R.id.tv_username_listitem_user);

        profileImageView.setImageBitmap(UserService.getInstance(mContext).getProfileImage(user));
        usernameTextView.setText(user.getUsername());
        return view;
    }
}
