package com.example.felan_000.bee;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random rand1 = new Random();
    public static final Random rand2 = new Random();
    public static final Random rand3 = new Random();
    JSONArray myJSON_array;
    JSONArray myJSON_array_yes;
    JSONArray myJSON_array_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.bee_intro_theme);
        ring.start();

        Resources res = getResources();
        final String[] description = res.getStringArray(R.array.description);

        final TextView tvWord = (TextView) findViewById(R.id.tvWord);
        final TextView tvDefinition = (TextView) findViewById(R.id.tvDefinition);
        final TextView tvSentence = (TextView) findViewById(R.id.tvSentence);
        final TextView tvCost = (TextView) findViewById(R.id.tvCost);
        final TextView tvRules = (TextView) findViewById(R.id.tvRules);

        final Button btnYes = (Button) findViewById(R.id.btnYes);
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        final Button btnNo = (Button) findViewById(R.id.btnNo);
        final Button btnEnd = (Button) findViewById(R.id.btnEnd);

        final EditText etName1 = (EditText) findViewById(R.id.etName1);
        final EditText etScore1 = (EditText) findViewById(R.id.etScore1);
        final EditText etName2 = (EditText) findViewById(R.id.etName2);
        final EditText etScore2 = (EditText) findViewById(R.id.etScore2);
        final EditText etName3 = (EditText) findViewById(R.id.etName3);
        final EditText etScore3 = (EditText) findViewById(R.id.etScore3);
        final EditText etName4 = (EditText) findViewById(R.id.etName4);
        final EditText etScore4 = (EditText) findViewById(R.id.etScore4);

        //this try will create an array of the words that will display for readers
        try {
            InputStream is = getResources().openRawResource(R.raw.words);
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);

            String myText = new String(buffer);
            final JSONObject myJSON_object = new JSONObject(myText);

            myJSON_array = myJSON_object.getJSONArray("words");

            String[] word_ndsc = new String[myJSON_array.length()];
            for (int i = 0; i < myJSON_array.length(); i++) {
                try {
                    JSONObject aJSON_element = myJSON_array.getJSONObject(i);
                    String word_name = aJSON_element.getString("wordName");
                    String word_definition = aJSON_element.getString("wordDefinition");
                    String word_sentence = aJSON_element.getString("wordSentence");
                    String word_cost = aJSON_element.getString("wordCost");
                    word_ndsc[i] = word_name + ". " + word_definition + ". " + word_sentence + ". " + word_cost;
                }
                catch (JSONException e) {
                    Toast.makeText(getBaseContext(), "Dude, you have to know what JSON looks like to parse it", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getResources().openRawResource(R.raw.yes);
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1) ;

            String myText = new String(buffer);
            final JSONObject myJSON_object = new JSONObject(myText);
            myJSON_array_yes = myJSON_object.getJSONArray("yes");

            String[] yes = new String[myJSON_array_yes.length()];
            for (int i = 0; i < myJSON_array_yes.length(); i++) {
                try {
                    JSONObject aJSON_element = myJSON_array_yes.getJSONObject(i);
                    String yes_url = aJSON_element.getString("yesLink");
                    yes[i] = yes_url;
                } catch (JSONException e) {
                    Toast.makeText(getBaseContext(), "Dude, you have to know what JSON looks like to parse it", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getResources().openRawResource(R.raw.no);
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1) ;

            String myText = new String(buffer);
            final JSONObject myJSON_object = new JSONObject(myText);
            myJSON_array_no = myJSON_object.getJSONArray("no");

            String[] no = new String[myJSON_array_no.length()];
            for (int i = 0; i < myJSON_array_no.length(); i++) {
                try {
                    JSONObject aJSON_element = myJSON_array_no.getJSONObject(i);
                    String no_url = aJSON_element.getString("noLink");
                    no[i] = no_url;
                } catch (JSONException e) {
                    Toast.makeText(getBaseContext(), "Dude, you have to know what JSON looks like to parse it", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = randomWordValue();

                try {
                    tvWord.setText(" " + myJSON_array.getJSONObject(i).getString("wordName"));
                    tvDefinition.setText(" " + myJSON_array.getJSONObject(i).getString("wordDefinition"));
                    tvSentence.setText(" " + myJSON_array.getJSONObject(i).getString("wordSentence"));
                    tvCost.setText(" " + myJSON_array.getJSONObject(i).getString("wordCost"));

                }catch(Exception e){

                }
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int value = randomYesValue();

                try {
                    String ytc = myJSON_array_yes.getJSONObject(value).getString("yesLink");
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + ytc)));
                }catch(JSONException e){

                }

            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int value = randomNoValue();

                try {
                    String ytc = myJSON_array_no.getJSONObject(value).getString("noLink");
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+ ytc )));
                }catch(JSONException e){

                }

            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                for (int i = 0; i <=4; i++) {

                    int score1 = Integer.parseInt(etScore1.getText().toString());
                    int score2 = Integer.parseInt(etScore2.getText().toString());
                    int score3 = Integer.parseInt(etScore3.getText().toString());
                    int score4 = Integer.parseInt(etScore4.getText().toString());

                    String name1 = etName1.getText().toString();
                    String name2 = etName2.getText().toString();
                    String name3 = etName3.getText().toString();
                    String name4 = etName4.getText().toString();

                    String highest_name;
                    String[] name = {name1, name2, name3, name4};
                    int[] score = {score1, score2, score3, score4};

                    if(score[i] > score[i + 1]) {
                        highest_name = name[i];
                    } else {
                        highest_name = name[i + 1];
                    }

                    Toast.makeText(getApplicationContext(), "The winner is " + highest_name + "! Everyone else drink right now.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Spinner spRules = (Spinner) findViewById(R.id.spRules);
        ArrayAdapter aaRules = (ArrayAdapter.createFromResource(this, R.array.rules,android.R.layout.simple_spinner_item));
        spRules.setAdapter(aaRules);

        spRules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvUserItem = (TextView) view;
                Toast.makeText(getApplicationContext(), "You chose "+tvUserItem.getText().toString(), Toast.LENGTH_SHORT).show();

                String drawable_resource_name = tvUserItem.getText().toString();
                drawable_resource_name = drawable_resource_name.toLowerCase();
                drawable_resource_name = drawable_resource_name.replace(' ', '_');

                tvRules.setText(description[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public static int randomYesValue() {
        return rand1.nextInt(8) + 1;
    }
    
    public static int randomNoValue() {
        return rand2.nextInt(8) + 1;
    }

    public static int randomWordValue() {
        return rand3.nextInt(26) + 1;
    }
}
