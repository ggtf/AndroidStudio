package com.ggtf.grouplistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ggtf.grouplistview.adapter.GroupAdapter;
import com.ggtf.grouplistview.adapter.ListViewAdapter;
import com.ggtf.grouplistview.custom.CircleImageView;
import com.ggtf.grouplistview.custom.CustomListView;
import com.ggtf.grouplistview.models.GroupsInListView;
import com.ggtf.grouplistview.utils.ChineseToWords;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomListView groupListView;
    private List<String> listViewGroups;
    private ListViewAdapter groupAdapter;
    private boolean isExpanding;
    private List<GroupsInListView> groupLists;
    private GroupAdapter adapter;
    private List<GroupsInListView> groupItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initListViewGroup();
        groupItems = new LinkedList<>();
        groupLists = new LinkedList<>();
        initGroup();
    }

    private void initGroup() {

        groupListView = (CustomListView) findViewById(R.id.list_view_group);
        adapter = new GroupAdapter(this, groupLists);
        groupListView.setAdapter(adapter);
        adapter.setOnClickListener(this);

        groupLists.addAll(initGroupInListView());
        adapter.notifyDataSetChanged();

    }

    private List<GroupsInListView> initGroupInListView() {
        List<GroupsInListView> groupsInListViews = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            GroupsInListView group = new GroupsInListView();
            group.setGroupName("Apple" + i);
            group.setViewType(GroupsInListView.VIEW_GROUP_TYPE);
            groupsInListViews.add(group);
        }
        return groupsInListViews;
    }


    private void initListViewGroup() {
        groupListView = (CustomListView) findViewById(R.id.list_view_group);
        listViewGroups = new LinkedList<>();
//        initSubListView();
        groupAdapter = new ListViewAdapter(this, listViewGroups);
        groupAdapter.setOnClickListener(this);
        groupListView.setAdapter(groupAdapter);

        listViewGroups.addAll(initSubListView());
        groupAdapter.notifyDataSetChanged();
    }

    private List<String> initSubListView() {
        List<String> arrayGroup = new LinkedList<>();
        for (int i = 'A'; i <= 'C'; i++) {
            arrayGroup.add(String.valueOf((char) i));
        }
        return arrayGroup;
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
//        if (v instanceof TextView){
//            Integer pos = (Integer) v.getTag();
//            if (!isExpanding){
//                Toast.makeText(MainActivity.this, ""+pos, Toast.LENGTH_SHORT).show();
//                for (int i = 0; i < 5; i++) {
//                    listViewGroups.add(pos+i+1,"NEW");
//                }
//                groupAdapter.notifyDataSetChanged();
//                isExpanding = true;
//            }else {
//                for (int i =0 ; i <5 ; i++) {
//                    listViewGroups.remove(pos.intValue());
//            }
//                groupAdapter.notifyDataSetChanged();
//                isExpanding = false;
////                char name = '张';
////                String pingYin = ChineseToWords.toWords(name);
////                Toast.makeText(MainActivity.this, pingYin, Toast.LENGTH_SHORT).show();
//            }
//    }

        if (v instanceof TextView){
            int position = (Integer) v.getTag();
            if (!isExpanding){
                GroupsInListView childView = groupLists.get(position);
                int groupId = childView.getGroupId();
//                根据分组ID 获取相对应的Items
                getGroupItem();
                if (groupItems.size()>0){
                    groupLists.addAll(position+1,groupItems);
                }
                isExpanding = true;
            }else {
                GroupsInListView childView = groupLists.get(position);
                int groupId = childView.getGroupId();
                if (groupItems.size()>0){
                    groupLists.removeAll(groupItems);
                    groupItems.clear();
                }
                isExpanding = false;
            }
            adapter.notifyDataSetChanged();
        }
    }
    private void getGroupItem() {
        List<GroupsInListView> groupsInListViews = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            GroupsInListView group = new GroupsInListView();
            group.setGroupName("Orange" + i);
            group.setViewType(GroupsInListView.VIEW_GROUP_ITEM_TYPE);
            groupsInListViews.add(group);
        }
        groupItems.addAll(groupsInListViews);
    }

    public void imageClick(View view) {
        if (view instanceof CircleImageView){
            CircleImageView circleImageView = (CircleImageView) view;
            circleImageView.setBorderColor(0xff00ff00);
        }
    }
}
