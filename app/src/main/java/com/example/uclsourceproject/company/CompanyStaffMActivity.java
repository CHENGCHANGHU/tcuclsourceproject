package com.example.uclsourceproject.company;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.uclsourceproject.JsonUtil;
import com.example.uclsourceproject.R;
import com.example.uclsourceproject.UCLadapters.ProductionStateAdapter;
import com.example.uclsourceproject.UCLadapters.StaffAdapter;
import com.example.uclsourceproject.UCLclasses.ProductionState;
import com.example.uclsourceproject.UCLclasses.Staff;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompanyStaffMActivity extends AppCompatActivity
        implements View.OnClickListener, ProductionStateAdapter.OnRecycleViewItemClickListener {
    private static final String TAG = "tigercheng";
    private Intent intent = null;

    private String staffJsonStr = "";
    private String staffesStr = "";

    private RecyclerView rv_staff = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_company_staff_m);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initUI();
    }

    private void initUI() {
        intent = getIntent();
        ((TextView) findViewById(R.id.title_text)).setText(intent.getStringExtra("title"));

        rv_staff = findViewById(R.id.rv_staff);

        staffJsonStr = intent.getStringExtra("staff");
        Log.d(TAG, "staffJsonStr: " + staffJsonStr);
        try {
            JSONObject staffJson = new JSONObject(staffJsonStr);
            staffesStr = staffJson.getString("json");
            Log.d(TAG, "staffesStr: " + staffesStr);
            Log.d(TAG, "staffesStr.replace: " +
                    JsonUtil.getJSONArray(
                            staffesStr.replace("[", "").replace("]", ""),
                            "\\},\\{"));
            ArrayList<JSONObject> staffes = JsonUtil.getJSONArray(
                    staffesStr.replace("[", "").replace("]", ""),
                    "\\},\\{");

            ArrayList<Staff> staffList = new ArrayList<>();
            for (int i = 0; i < staffes.size(); i++) {
                Log.d(TAG, "staffes[" + i + "]: " + staffes.get(i));
                Staff _s = new Staff(
                        staffes.get(i).getString("id"),
                        staffes.get(i).getString("name"),
                        staffes.get(i).getString("INo"),
                        staffes.get(i).getString("CNo")
                );
                staffList.add(_s);
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            rv_staff.setLayoutManager(layoutManager);

            StaffAdapter adapter = new StaffAdapter(staffList);
            adapter.setOnRecycleViewItemClickListener(this);

            rv_staff.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }
}
