package com.example.helloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // define variables outside of methods
    private Button button_hi;
    private Button button_up;
    private Button button_down;
    private int number = 0;
    private TextView textView_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // part of the android life cycle
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // links the activity to the xml layout called activity.main

        // look up the button by its id
        button_hi = findViewById(R.id.button_hello);
        button_up = findViewById((R.id.button_countUp));
        button_down = findViewById((R.id.button_countDown));
        textView_count = findViewById(R.id.textView_count);
        System.out.println("We are in this loop.");
        // create logs to keep track of the errors
        // add an event listener to listen for the click
        button_hi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // handle what happens after I click
                // sayHello(v);
                // countUp(v);
                launchNextActivity(v);
            }
        });
        // when clicked, something happens

        /*
        button_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // handle what happens after I click
                countUp(v);
            }
        });

         */

        button_down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // handle what happens after I click
                countDown(v);
            }
        });
    }

    public void sayHello(View view) {
        // create a toast with a message saying hello
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        // short/long - duration toast shows
        toast.show();
    }

    public void countUp(View view){
        // increment the value and display in the text view
        // set the text of the textView to the number
        number++;
        // when you see an object, you want to check to make sure its not null
        if (textView_count != null){
            textView_count.setText(Integer.toString(number)); // setText takes a string
        }
    }

    public void countDown(View view){
        // increment the value and display in the text view
        // set the text of the textView to the number
        if (number > 0) {
            number--;
        }

        // when you see an object, you want to check to make sure its not null
        if (textView_count != null){
            textView_count.setText(Integer.toString(number)); // setText takes a string
        }
    }

    public void launchNextActivity(View view){
        // create an intent and you need to specify from and to
        Intent intent = new Intent(this, SecondActivity.class);

        // date field
        // intent extras --> Celia's choice
        // both can pack data and send to the targeted activity

        // intent extras:
        // key/value pairs in a Bundle

        // want to pass the count number through intent to second activity
        // and display in a second activity
        // ex: 5 was passed.

        String message = textView_count.getText().toString();
        intent.putExtra("count", message);

        // startActivity(intent);
        startActivityForResult(intent, 1); // < 0 reply is no requested
    }

    // do something when the result is received
    // a lifecycle method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String reply = data.getStringExtra("replyCount");
                textView_count.setText(reply);
            }
        }
    }
}