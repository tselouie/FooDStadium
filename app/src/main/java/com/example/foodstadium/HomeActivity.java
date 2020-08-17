package com.example.foodstadium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {
    final public static int SPLASH_DELAY_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {//Display for x seconds
                    sleep(SPLASH_DELAY_TIME);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(new Intent(HomeActivity.this,MainActivity.class));
                }
            }
        };
        timer.start();
    }


    //Destroy Welcome_screen.java after it goes to next activity
    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }
    }

