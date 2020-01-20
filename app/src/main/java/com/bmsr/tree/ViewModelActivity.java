package com.bmsr.tree;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bmsr.tree.databinding.ActivitiyModelBinding;

public class ViewModelActivity extends AppCompatActivity implements View.OnClickListener {
    MyViewModel myViewModel;
    TextView mTitleView;
    Button mBtn1, mBtn2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitiyModelBinding bing = DataBindingUtil.setContentView(this, R.layout.activitiy_model);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        bing.button.setOnClickListener(this);
        bing.textView.setText(String.valueOf(myViewModel.number));
        SharedPreferences preferences = getApplication().getSharedPreferences("test", Context.MODE_PRIVATE);
        preferences.edit().putInt("test", 1).apply();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button) {
            myViewModel.number ++;
            mTitleView.setText(String.valueOf(myViewModel.number));
        } else if (id ==R.id.button2) {
            myViewModel.number +=2;
            mTitleView.setText(String.valueOf(myViewModel.number));
        }
    }
}
