package asus.example.com.fitnessapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {

    TextView result;
    EditText eWeight, eHeight;
    int nWeight, nHeight;
    Button button;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        button = v.findViewById(R.id.count);
        eWeight = v.findViewById(R.id.weight);
        eHeight = v.findViewById(R.id.height);
        result = v.findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nWeight = Integer.parseInt(eWeight.getText().toString());
                nHeight = Integer.parseInt(eHeight.getText().toString());
                if (nHeight-nWeight>120) {
                    result.setText("Your weight is too low");
                }
                else if (nHeight-nWeight<100){
                    result.setText("Your weight is too big");
                }
                else {
                    result.setText("Your weight is normal");
                }
            }
        });

        return v;
    }

}
