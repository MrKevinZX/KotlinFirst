package com.bmsr.tree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class ViewModelActivity extends AppCompatActivity implements View.OnClickListener {
    MyViewModel myViewModel;
    TextView mTitleView;
    Button mBtn1, mBtn2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_model);
        mTitleView = findViewById(R.id.textView);
        mBtn1 = findViewById(R.id.button);
        mBtn2 = findViewById(R.id.button2);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        mTitleView.setText(String.valueOf(myViewModel.number));
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
