package asus.example.com.fitnessapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndicationsFragment extends Fragment {
    private EditText pulse;
    private Button save;

    public IndicationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_indications, container, false);
        pulse = view.findViewById(R.id.pulse);
        save = view.findViewById(R.id.save);
        final Date date = new Date();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int iPulse = Integer.parseInt(pulse.getText().toString());
                    int iDate = date.getDate();
                    int iMonth = date.getMonth()+1;
                    int iYear = date.getYear()+1900;
                    Toast.makeText(getContext(), "Information saved", Toast.LENGTH_LONG).show();
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(),"Wrong input!", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

}
