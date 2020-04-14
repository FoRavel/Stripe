package com.fundraising;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.react.ReactActivity;

/**
 * Created by Fanilo on 26/12/2018.
 */

public class AmountForm extends ReactActivity {
    private Button mValidAmount_btn;
    private EditText mInputAmount_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amount_form);

        mValidAmount_btn = findViewById(R.id.validAmount_btn);
        mInputAmount_edt = findViewById(R.id.inputAmount_edt);

        mValidAmount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountValue = String.valueOf(mInputAmount_edt.getText());
                Intent intent = new Intent(AmountForm.this, CreateCard.class);
                intent.putExtra("amount", amountValue);
                startActivity(intent);
            }
        });





    }
}