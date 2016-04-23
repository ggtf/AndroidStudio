package com.ggtf.grouplistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.ggtf.grouplistview.adapter.CategoryListViewAdapter;
import com.ggtf.grouplistview.models.ListViewData;
import com.ggtf.grouplistview.tools.ConstantViewType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private List<ListViewData> groups;
    private CategoryListViewAdapter adapter;
    /**
     * isGroups.size():代表分组数;
     * HashMap的key:代表分组的组名;
     * HashMap的List<ListViewData>代表着当前key指定的分组名下的联系人集合
     */
    private HashMap<String,List<ListViewData>> isGroups;
    private List<ListViewData> groupContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        isGroups = new LinkedHashMap<>();
        ListView category = (ListView) findViewById(R.id.category_list_view);
        groups = new LinkedList<>();
        adapter = new CategoryListViewAdapter(this, groups,this,this);
        category.setAdapter(adapter);
        initListView();
    }

    /**
     * 初始化最初的联系人;全未分组;
     */
    private void initListView() {
       List<ListViewData> data = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            ListViewData listViewData = new ListViewData();
            listViewData.setContent("联系人"+i);
            data.add(listViewData);
        }
        groups.addAll(data);
        adapter.notifyDataSetChanged();
    }

    private void initGroupsData(){
        groupContacts = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            ListViewData listViewData = new ListViewData();
            listViewData.setContent("分组联系人"+i);
            groupContacts.add(listViewData);
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        int id = v.getId();
        switch (id){
            /**
             * 点击了分组
             */
            case R.id.group_name:
//                TODO 首次点击,展开这个分组中的联系人;再次点击,收起联系人;
                ListViewData listViewData = groups.get(position);
                String key = listViewData.getContent();
                /**
                 * 包含组名和其联系人的集合
                 */
                ;
                List<ListViewData> listViewDataList = new LinkedList<>();
                listViewDataList.addAll(isGroups.get(key));
                /**
                 * 去掉分组这一条的信息
                 */
                listViewDataList.remove(0);
                if (!listViewData.isExpanding()){
                    /**
                     * 展开 分组
                     */
                    /**
                     * 将分组中的联系人加入到集合中,显示出来,达到展开分组的效果
                     */
                    groups.addAll(position+1,listViewDataList);
                    listViewData.setIsExpanding(true);
                }else {
                    /**
                     * 关闭分组
                      */
                    /**
                     * 移除该分组的联系人,达到收起分组效果
                     */
                    groups.removeAll(listViewDataList);
                    listViewData.setIsExpanding(false);
                }
                adapter.notifyDataSetChanged();
                break;
            /**
             * 点击了具体的联系人
             */
            case R.id.contacts:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.group_add:
                /**
                 * 创建一个组
                 */
                HashMap<String ,List<ListViewData>> createdGroup = new LinkedHashMap<>();
                /**
                 * 创建这个组的联系人存储集合,包含组名作为这个集合的第一位;
                 */
                List<ListViewData> contacts = new LinkedList<>();

                /**
                 * 分组
                 */
                ListViewData listViewData = new ListViewData();
                listViewData.setContent("分组"+isGroups.size());
                listViewData.setGroupId(isGroups.size() + 1);
                listViewData.setIsGrouped(true);
                listViewData.setViewType(ConstantViewType.GROUP_VIEW_TYPE);
                contacts.add(listViewData);
                /**
                 * 添加这组分组的联系人数据
                 */
                initGroupsData();
                contacts.addAll(1,groupContacts);
                /**
                 * 添加这个组的 组名 和 联系人数据
                 */
                createdGroup.put("分组"+isGroups.size(),contacts);

                /**
                 * 根据当前分组的个数,来确定分组添加到那个位置
                 */
                groups.add(isGroups.size(), contacts.get(0));
                /**
                 * 添加一个分组,isGroups也就添加了一条数据;
                 */
                isGroups.putAll(createdGroup);

                adapter.notifyDataSetChanged();
                break;
            case R.id.add_in_group:
                /**
                 * 添加数据到某一个分组中
                 */
                for (ListViewData group : groups) {
                    /**
                     * 分组的Item项
                     */
                    if (group.getGroupId()!=-1){
                        group.setIsCheckable(false);
                    }else {
                        /**
                         * 普通联系人Item项
                         */
                    group.setIsCheckable(true);
                    }
                }
                adapter.notifyDataSetChanged();
                break;

        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        buttonView.setChecked(!isChecked);
        int position = (int) buttonView.getTag();
        groups.get(position).setIsChecked(buttonView.isChecked());
    }
}
