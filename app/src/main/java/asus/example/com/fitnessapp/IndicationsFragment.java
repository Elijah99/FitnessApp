package asus.example.com.fitnessapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndicationsFragment extends Fragment {

    private Button save;
    private Button createGraph;
    private GraphView graphView;
    private EditText editText;
    private DBHelper dbHelper;
    int i = 0;
    public IndicationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_indications, container, false);
        save = view.findViewById(R.id.save);
        createGraph = view.findViewById(R.id.create_graph);
        graphView = view.findViewById(R.id.graph);
        editText = view.findViewById(R.id.pulse);
        dbHelper = new DBHelper(getContext());
        final ContentValues cv = new ContentValues();
        final SQLiteDatabase database = dbHelper.getWritableDatabase();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iPulse = Integer.parseInt(editText.getText().toString());
                Date date = new Date();
                int iDate = date.getDate()+i;
                i++;
                int iMonth = date.getMonth()+1;
                int iYear = date.getYear()+1900;
                cv.put("pulse", iPulse);
                cv.put("date", iDate);
                cv.put("month", iMonth);
                cv.put("year", iYear);
                long rowID = database.insert("myTable", null, cv);
                Toast.makeText(getContext(), "Information saved", Toast.LENGTH_SHORT).show();
            }
        });

        final ArrayList<Integer> pulses = new ArrayList<>();
        final ArrayList<Integer> dates = new ArrayList<>();
        createGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = database.query("myTable", null,null,null,null,null,null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int idPulseIndex = cursor.getColumnIndex("pulse");
                    int idDateIndex = cursor.getColumnIndex("date");
                    int idMonthIndex = cursor.getColumnIndex("month");
                    int idYearIndex = cursor.getColumnIndex("year");
                    Date date = new Date();
                    if ((cursor.getInt(idMonthIndex) == date.getMonth()+1) && (cursor.getInt(idYearIndex)==date.getYear()+1900)) {
                        pulses.add(cursor.getInt(idPulseIndex));
                        dates.add(cursor.getInt(idDateIndex));
                    }
                    cursor.moveToNext();
                }
                DataPoint[] arr = new DataPoint[dates.size()];
                for (int i = 0;i<arr.length;i++){
                    arr[i] = new DataPoint(dates.get(i), pulses.get(i));
                    System.out.print("Adding Data point: \n");
                }
                System.out.print("Data points: " + arr.length);
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(arr);
                graphView.addSeries(series);
                graphView.getViewport().setMaxX(31);
                graphView.getViewport().setMaxX(150);
                graphView.getViewport().setMinX(1);
                graphView.getViewport().setMinY(0);
            }
        });
        return view;
    }

    public class DBHelper extends SQLiteOpenHelper {

        DBHelper(Context context){
            super(context,"myDB", null, 1);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table myTable ("+"id integer primary key autoincrement,"+
                    "pulse integer,"+"date integer,"+"month integer,"+"year integer"+");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}