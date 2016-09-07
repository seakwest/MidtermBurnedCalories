package com.evaristo.midtermburnedcalories;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity implements TextView.OnEditorActionListener,
        SeekBar.OnSeekBarChangeListener, Spinner.OnItemSelectedListener, View.OnKeyListener {


    //variable declaration for widgts
    private EditText weightEditText;
    private EditText nameEditText;
    private TextView milesTextView;
    private TextView caloriesTextView;
    private TextView bMITextView;
    private SeekBar milesSeekBar;
    private Spinner feetSpinner;
    private Spinner inchesSpinner;

    //instance variables
    private int weight;
    private String weightString;
    private int miles;
   // private float caloriesBurned;
    private double caloriesBurned; //android studio told me it wanted this to be a double and not a float
    private float bmi;
    private int feet;
    private int inches;
    private int height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to widgets
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        milesTextView = (TextView) findViewById(R.id.milesTextView);
        caloriesTextView = (TextView) findViewById(R.id.caloriesTextView);
        bMITextView = (TextView) findViewById(R.id.bMITextView);
        milesSeekBar = (SeekBar) findViewById(R.id.milesSeekBar);
        feetSpinner = (Spinner) findViewById(R.id.feetSpinner);
        inchesSpinner = (Spinner) findViewById(R.id.inchesSpinner);

        //array adapter for feet spinner
        ArrayAdapter feetAdapter = ArrayAdapter.createFromResource(this, R.array.feet_Array, android.R.layout.simple_spinner_item);
        feetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feetSpinner.setAdapter(feetAdapter);

        //adapter for inches
        ArrayAdapter inchesAdapter = ArrayAdapter.createFromResource(this, R.array, android.R.layout.simple_spinner_item);
        inchesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inchesSpinner.setAdapter(inchesAdapter);


        //set the listeners
        weightEditText.setOnEditorActionListener(this);
        weightEditText.setOnKeyListener(this);
        milesSeekBar.setOnSeekBarChangeListener(this);
        milesSeekBar.setOnKeyListener(this);
        feetSpinner.setOnItemSelectedListener(this);
        inchesSpinner.setOnItemSelectedListener(this);

        //shared references
        //savedValues = getSharedPreferences("Saved Values", MODE_PRIVATE);

    }



        /*


        @Override
        public void onPause() {
            // save the instance variables


            //add values ot save

            editor.commit();

            super.onPause();
        }
        */




        //event handler for editor action
    public void calculateAndDisplay() {
        // get the weight amount
        weightString = weightEditText.getText().toString();
        float weight;
        if (weightString.equals("")) {
            weight = 0;
        } else {
            weight = Float.parseFloat(weightString);
        }


        //get seekbar progress in miles
        int progress = milesSeekBar.getProgress();
        miles =  progress / 10;

        //calculate calories burned
        caloriesBurned = 0.75 * weight * miles;
        bmi = weight * 703 / ((12 * feet + inches) * (12*feet*inches));


        //display calculation
        NumberFormat decimal = NumberFormat.getInstance();
       caloriesTextView.setText(decimal.format(caloriesBurned));











    }





    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
//Seekbar Handler
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        milesTextView.setText(progress);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        calculateAndDisplay();
    }


    //
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }
}
