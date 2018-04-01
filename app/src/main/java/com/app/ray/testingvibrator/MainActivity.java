package com.app.ray.testingvibrator;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinnner);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,new String[]{"長震動","短震動","連續短震動"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                //取得震動服務
                Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
                switch(position) {
                    case 0:
                        //震動3秒
                        myVibrator.vibrate(3000);
                        break;
                    case 1:
                        //震動0.1秒
                        myVibrator.vibrate(100);
                        break;
                    case 2:
                        //停0.01秒之後震動0.1秒(重覆三次)
                        myVibrator.vibrate(new long[]{10, 100, 10, 100, 10, 100}, -1);
                        break;
                    default:
                        break;
                }
            }
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(MainActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });
    }
}
