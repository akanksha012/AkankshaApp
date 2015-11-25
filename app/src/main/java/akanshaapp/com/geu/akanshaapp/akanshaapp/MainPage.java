package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

                new Thread()
                {


                    public void run()
                    {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent i=new Intent(MainPage.this,HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                }.start();





    }
}
