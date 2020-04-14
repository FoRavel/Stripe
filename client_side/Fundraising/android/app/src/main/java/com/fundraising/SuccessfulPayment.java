package com.fundraising;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.react.ReactActivity;

/**
 * Created by Fanilo on 26/12/2018.
 */

public class SuccessfulPayment extends ReactActivity {

    private TextView mSuccessfulText;
    private Button mFinish;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.successful_payment);

        mSuccessfulText = findViewById(R.id.success_txt);
        mSuccessfulText.setText(getIntent().getStringExtra("customerId"));
        mFinish = findViewById(R.id.backToCrowdfunding_btn);

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessfulPayment.this, MyReactActivity.class);
                startActivity(intent);
            }
        });



    }
}
