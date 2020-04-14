package com.fundraising;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.stripe.android.TokenCallback;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.Stripe;
import com.stripe.android.model.Token;
import com.stripe.android.model.Card;

import com.facebook.react.ReactActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateCard extends ReactActivity {
    private Button mButton;
    private EditText mNumCard;
    private EditText mCVV;
    private EditText mMonth;
    private EditText mYear;
    private CheckBox mSaveCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_card);


        //CardInputWidget mCardInputWidget = (CardInputWidget) findViewById(R.id.card_input_widget);
        mButton = findViewById(R.id.valider_btn);
        mNumCard = findViewById(R.id.cardNumber);
        mCVV = findViewById(R.id.CVV);
        mMonth = findViewById(R.id.month);
        mYear = findViewById(R.id.year);
        mSaveCard = findViewById(R.id.saveCard_cb);
        Intent intent = getIntent();

        final String textButton = intent.getStringExtra("amount");
        mButton.setText("VERSER " +textButton + " €");


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String numCard;
              String CVV;
              int month;
              int year;
              numCard = String.valueOf(mNumCard.getText());
              CVV = String.valueOf(mCVV.getText());
              month = Integer.parseInt(String.valueOf(mMonth.getText()));
              year = Integer.parseInt(String.valueOf(mYear.getText()));
              Card card = new Card(
                numCard,
                month,
                year,
                CVV
              );

              if (!card.validateCard()) {
                Toast.makeText(CreateCard.this, "erreur", Toast.LENGTH_SHORT).show();
              }
              else{
                Stripe stripe = new Stripe(CreateCard.this, "pk_test_Q4mqDHJenzlISLNLULm55q3b");
                stripe.createToken(card, new TokenCallback() { //IMPORTTOKENCALLBACK CLASS
                  public void onSuccess(Token token) {
                    //Toast.makeText(CreateCard.this, String.valueOf(token.getId()), Toast.LENGTH_SHORT).show();
                      if(mSaveCard.isChecked()){
                          new AsyncCardCreation().execute(token.getId(), "checked", textButton);
                      }
                      else
                      {
                          new AsyncCardCreation().execute(token.getId(), "unchecked", textButton);
                      }

                  }
                  public void onError(Exception error) {
                    Toast.makeText(CreateCard.this, String.valueOf(error.getMessage()), Toast.LENGTH_SHORT).show();
                  }
                });
              }
          }
    });
    }
    private class AsyncCardCreation extends AsyncTask<String, String, String>{

        //ProgressBar mProgressBar = findViewById(R.id.progressBar);
        URL url;
        HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            //Spécifier l'URL où se situe le fichier php
            try {
                url = new URL("http://192.168.0.17/fundraising/STRIPE/create_charge_customer.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            //Ouvrir une connection vers l'URL en écriture et en lecture
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
            //Encoder le(s) paramètre(s) à transmettre à la page php
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("stripeToken", strings[0]);
            builder.appendQueryParameter("checkboxState", strings[1]);
            builder.appendQueryParameter("amount", strings[2]);

            String query = builder.build().getEncodedQuery();

            try {
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write(query);
                writer.flush();
                writer.close();
                conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }

            try {
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder result = new StringBuilder();
                String line;

                while((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //mProgressBar.setVisibility(View.GONE);
            if(s != null) {
                //Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SuccessfulPayment.class);
                intent.putExtra("customerId", s.toString());
                startActivity(intent);
            }
            else
            {
                //Toast.makeText(getApplicationContext(), s.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
