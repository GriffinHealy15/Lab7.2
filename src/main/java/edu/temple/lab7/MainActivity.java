package edu.temple.lab7;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

// Nov 13 | Recent New Edit -10:00 PM -
import org.w3c.dom.Text;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    EditText urlTextView;
    ViewPager myViewPager;
    myWebPageAdapter myWebPageAdapter;

    String url_string = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Web Browser");

       // Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.concretepage.com"));
       // startActivity(implicit);

        // Get implicit intent
        Uri url = getIntent().getData();

        // Catch if url exists, meaning passed through implicit intent
        if (url != null) {
            url_string = url.toString();
            Log.i("url passed", url_string);
        }

        urlTextView = (EditText) findViewById(R.id.urlEditText); // Link URL text
        myWebPageAdapter = new myWebPageAdapter(getSupportFragmentManager()); // Web Adapter which uses fragments
        myViewPager = findViewById(R.id.aViewPager); // set view pager to link to layout
        myViewPager.setAdapter(myWebPageAdapter); // set adapter of myViewPager to be myWebPageApdapter
        myViewPager.setOffscreenPageLimit(50);


        if (url != null) {
            Log.i("url to load", url_string);
            //Special case for implicit intent
            myWebPageAdapter.createAWebFragment();
            Toast.makeText(MainActivity.this,
                    "Your app opened this browser", Toast.LENGTH_LONG).show();
            myViewPager.setCurrentItem(myWebPageAdapter.getCount() - 1); // count - 1 because index begins at 0, not 1
            myWebFragment webFragmentInstance = (myWebFragment) myWebPageAdapter.getItem(myViewPager.getCurrentItem());
            webFragmentInstance.setUrlText(url_string);
            urlTextView.setText(webFragmentInstance.getUrlText(), TextView.BufferType.EDITABLE);
        }

        // Swiping function implemented
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                myWebFragment webFragmentInstance = (myWebFragment) myWebPageAdapter.getItem(myViewPager.getCurrentItem());
                urlTextView.setText(webFragmentInstance.getUrlText());
            }

            @Override
            public void onPageSelected(int i) {
                myWebFragment webFragmentInstance = (myWebFragment) myWebPageAdapter.getItem(myViewPager.getCurrentItem());
                urlTextView.setText(webFragmentInstance.getUrlText());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        Button goButton = findViewById(R.id.goButton);
        final EditText urlEditText = findViewById(R.id.urlEditText);

        goButton.setBackgroundColor(Color.parseColor("Blue"));
        goButton.setTextColor(Color.parseColor("White"));

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myWebPageAdapter.getCount() > 0) {
                    myWebFragment webFragmentInstance = (myWebFragment) myWebPageAdapter.getItem(myViewPager.getCurrentItem());
                    webFragmentInstance.setUrlText(urlEditText.getText().toString());
                    webFragmentInstance.goToUrl();
                }

                }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {

                case R.id.newPage:
                myWebPageAdapter.createAWebFragment();
                String something = myWebPageAdapter.something();
                Toast.makeText(MainActivity.this,
                        something, Toast.LENGTH_LONG).show();

                myViewPager.setCurrentItem(myWebPageAdapter.getCount() - 1); // count - 1 because index begins at 0, not 1
                    myWebFragment webFragmentInstance = (myWebFragment) myWebPageAdapter.getItem(myViewPager.getCurrentItem());

                    webFragmentInstance.setUrlText("");
                    urlTextView.setText(webFragmentInstance.getUrlText(), TextView.BufferType.EDITABLE);
                return true;

                case R.id.previousPage:
                Toast.makeText(MainActivity.this,
                        "Prev", Toast.LENGTH_LONG).show();

                    if (myViewPager.getCurrentItem() > 0){
                        myViewPager.setCurrentItem(myViewPager.getCurrentItem() - 1);
                        webFragmentInstance = (myWebFragment) myWebPageAdapter.getItem(myViewPager.getCurrentItem());
                        urlTextView.setText(webFragmentInstance.getUrlText(), TextView.BufferType.EDITABLE);
                        return true;
                    }

                return true;

                case R.id.nextPage:
                Toast.makeText(MainActivity.this,
                        "Next", Toast.LENGTH_LONG).show();
                    if (myViewPager.getCurrentItem() < myWebPageAdapter.getCount() -1){
                        myViewPager.setCurrentItem(myViewPager.getCurrentItem() + 1);
                        webFragmentInstance = (myWebFragment) myWebPageAdapter.getItem(myViewPager.getCurrentItem());
                        urlTextView.setText(webFragmentInstance.getUrlText(), TextView.BufferType.EDITABLE);
                        return true;
                    }

                return true;


                default: return super.onOptionsItemSelected(menuItem);

        }

    }
}

