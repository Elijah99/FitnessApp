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

public class ArticlesFragment extends Fragment {
    private final String article = "ARTICLE";
    private String[] articlesNames = { "Бразилия", "Аргентина", "Чили", "Колумбия", "Уругвай", "Парагвай"};

    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        GridView countriesList = (GridView) view.findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, articlesNames);
        countriesList.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                switch (position){
                    case 0:
                        intent.putExtra(article,"Brazil");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra(article,"Argentina");
                        break;
                        default:
                            break;
                }
                startActivity(intent);
            }
        };
        countriesList.setOnItemClickListener(itemClickListener);
        return view;
    }

}
