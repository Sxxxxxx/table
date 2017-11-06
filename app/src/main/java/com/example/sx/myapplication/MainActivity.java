package com.example.sx.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableView table = (TableView) findViewById(R.id.table);
        TopView x = (TopView) findViewById(R.id.ll_x);
        LeftView y = (LeftView) findViewById(R.id.ll_y);
        table.setBothScrollView(x, y);
        table.setData(80, new Date(), new ArrayList<Room>());
        y.setData(80);
    }


    class In implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Log.i("source: ", source.toString());
            Log.i("dest: ", dest.toString());
            if (dstart > 0) {
                if (source.equals("+"))
                    return "";
            }
            return null;
        }
    }
}
