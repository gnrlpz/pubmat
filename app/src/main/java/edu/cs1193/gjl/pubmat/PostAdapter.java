package edu.cs1193.gjl.pubmat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;

public class PostAdapter extends BaseAdapter {

    ArrayList<OrgPost> data;
    OrgProfile context;

    public PostAdapter(OrgProfile context, ArrayList<OrgPost> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //Layout
        LinearLayout linearLayout;
        if(convertView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            View v = inflater.inflate(R.layout.post_layout, null);
            linearLayout = (LinearLayout) v;
        }
        else
        {
            linearLayout = (LinearLayout) convertView;
        }


        //Data input
        TextView orgName = linearLayout.findViewById(R.id.orgName);
        TextView postDesc = linearLayout.findViewById(R.id.postDesc);

        ImageView orgPic = linearLayout.findViewById(R.id.imageView4);
        ImageView postImg = linearLayout.findViewById(R.id.imageView6);

        OrgPost currItem = (OrgPost) getItem(position);
        Organization org = context.getOrgByName(currItem.getOriginalPoster());

        orgName.setText(org.getOrgName());
        postDesc.setText(currItem.getPostCaption());

        //Image stuffs

        //Events
        //AddButtonsHere

        return linearLayout;
    }

}
