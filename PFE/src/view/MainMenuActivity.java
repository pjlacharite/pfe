package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Serie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pfe.R;

import controller.ControllerDispatcher;
import controller.SerieController;

/**
 * Main Activity
 * @author pjlacharite
 *
 */
public class MainMenuActivity extends Activity {

    private static final String KEY_SERIE = "serie";
    private static final String CONTROLLER_SERIE = "SerieController";
    private Map<String, String> seriesMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        SetupSeriesSpinner();

        //WS TEST
        //Find out how to use those fucking asyntasks. s
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void SetupSeriesSpinner(){
        Spinner seriesSpinner = (Spinner)findViewById(R.id.mainSeriesSpinner);
        ControllerDispatcher dispatcher = ControllerDispatcher.getDispatcher();
        SerieController serieController = (SerieController)dispatcher.getController(CONTROLLER_SERIE);
        List<Serie> seriesList = serieController.fetchAllSeries();
        List<String> seriesNameList = new ArrayList<String>();
        seriesNameList.add("Pick a serie");
        for (Serie serie : seriesList){
            seriesMap.put(serie.getName(), serie.getId());
            seriesNameList.add(serie.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, seriesNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);;
        seriesSpinner.setAdapter(dataAdapter);

        seriesSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            private boolean firstPass = true;
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                    int position, long id) {
                if (firstPass){
                    firstPass = false;
                    return;
                }else if (!parentView.getSelectedItem().equals("Pick a serie")){
                    Intent intent = new Intent(MainMenuActivity.this, SerieDetailsActivity.class);
                    System.out.println("Main Activity " + parentView.getSelectedItemId());
                    intent.putExtra(KEY_SERIE, seriesMap.get(String.valueOf(parentView.getSelectedItem())));
                    MainMenuActivity.this.startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }

        });
    }
}