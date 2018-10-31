package asus.example.com.fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramsFragment extends Fragment {

    private final String[] programs = {"Slim body for a four weeks", "Exercises for woman at home", "Effective exercises for biceps at home",
            "Program of effective trainings twice a week", "Training on a horizontal bar for increasing muscle mass",
            "Fitness program for woman: trainings at home at in gym"};
    private final String PROGRAM ="PROGRAM";
    public ProgramsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_programs, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,programs);
        GridView gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProgramActivity.class);
                String value="";
                switch (position){
                    case 0:
                        value="p1";
                        break;
                    case 1:
                        value="p2";
                        break;
                    case 2:
                        value="p3";
                        break;
                    case 3:
                        value="p4";
                        break;
                    case 4:
                        value="p5";
                        break;
                    case 5:
                        value="p6";
                        break;
                }
                intent.putExtra(PROGRAM,value);
                startActivity(intent);
            }
        };
        gridView.setOnItemClickListener(itemClickListener);
        return view;
    }

}
