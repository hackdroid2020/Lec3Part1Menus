package edu.tomerbu.lec3part1menus;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private Button btnBack;
    private ConstraintLayout layout;
    private ImageView imageView;
    private Random r = new Random();
    //an array of pointers:
    //pointers to our drawable in resources folder
    private int[] images = {R.drawable.magic_ball, R.drawable.pencil_icon, R.drawable.smurf_sprite};

    int index = -1;

    //Activity onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        //set toolbar as our menu
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.m_layout);
        imageView = findViewById(R.id.imageView);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener((v) -> {
            index++;
            if (index >= images.length) {
                index = 0;
            }
            imageView.setImageResource(images[index]);
        });


        btnBack.setOnClickListener((v) -> {
            index--;

            if (index < 0) {
                index = images.length - 1;
            }

            imageView.setImageResource(images[index]);
        });
    }

    //Menu onCreate
    //create the menu:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //select the xml file for our menu:
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //the listener for menu clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Toast.makeText(this, "Settings!", Toast.LENGTH_LONG).show();

            //Context-> android os is linux based.
            //security is built in.
            //each app is a Linux user
            //as a result only the app can access it's resources (also files, and database)
            //if we want access to an apps res (files) -> we need to provide a Context
            //how to control our toast?

            Toast t = Toast.makeText(this,"Settings", Toast.LENGTH_SHORT);

            //gravity sets the position of the toast -> Top, Left, Right, Bottom, START, END
            t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0); //Bitwise OR

            //Button extends View, TextView extend View, ConstraintLayout extends View
            LinearLayout layout = (LinearLayout) t.getView(); //?
            layout.setBackgroundColor(Color.BLACK);

            TextView tvToast = (TextView) layout.getChildAt(0);

            tvToast.setTextColor(Color.WHITE);

            //START vs left
            t.show();
        } else if (id == R.id.action_hi) {
            //random color?

            int color = Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            layout.setBackgroundColor(color); //0xFFFF5722
        } else if (id == R.id.action_about) {

            // random number between 0 - 3
            int randomIndex = r.nextInt(images.length);

            //get a random element from the array:
            int randomImageRes = images[randomIndex]; //imageRes = array[2],

            //set it as an image resource:
            imageView.setImageResource(randomImageRes);

        }
        return super.onOptionsItemSelected(item);
    }
}
