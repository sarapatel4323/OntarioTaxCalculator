package com.sara.taxcalculationapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText incomeEditText;
    private SeekBar rrspSeekBar;
    private TextView rrspValueTextView;
    private TextView rrspLimitTextView;
    private TextView federalTaxTextView;
    private TextView provincialTaxTextView;
    private TextView totalTaxTextView;
    private Button calculateButton;
    private Button refreshButton;

    private TaxCalculator taxCalculator;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incomeEditText = findViewById(R.id.incomeInput);
        rrspSeekBar = findViewById(R.id.rrspSeekBar);
        rrspValueTextView = findViewById(R.id.rrspValue);
        rrspLimitTextView = findViewById(R.id.rrspLimitTextView);
        federalTaxTextView = findViewById(R.id.federalTaxTextView);
        provincialTaxTextView = findViewById(R.id.provincialTaxTextView);
        totalTaxTextView = findViewById(R.id.totalTaxTextView);
        calculateButton = findViewById(R.id.calculateButton);
        refreshButton = findViewById(R.id.refreshButton);

        taxCalculator = new TaxCalculator();
        sharedPreferences = getSharedPreferences("TaxCalculationApp", MODE_PRIVATE);

        // Initialize UI
        rrspLimitTextView.setText("RRSP Contribution Limit for Next Year: $" + taxCalculator.getRrspLimitNextYear());

        // Set up SeekBar listener
        rrspSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double rrspContribution = progress;
                rrspValueTextView.setText(String.format("RRSP Contribution: $%.2f", rrspContribution));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Set up Calculate button listener
        calculateButton.setOnClickListener(v -> calculateTax());

        // Set up Refresh button listener
        refreshButton.setOnClickListener(v -> refreshFields());
    }

    private void calculateTax() {
        try {
            double income = Double.parseDouble(incomeEditText.getText().toString());
            double rrspContribution = rrspSeekBar.getProgress();
            double taxableIncome = income - rrspContribution;

            double federalTax = taxCalculator.calculateFederalTax(taxableIncome);
            double provincialTax = taxCalculator.calculateProvincialTax(taxableIncome);
            double totalTax = federalTax + provincialTax;

            federalTaxTextView.setText(String.format("Federal Tax: $%.2f", federalTax));
            provincialTaxTextView.setText(String.format("Provincial Tax: $%.2f", provincialTax));
            totalTaxTextView.setText(String.format("Total Tax: $%.2f", totalTax));

            // Save current state
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("income", incomeEditText.getText().toString());
            editor.putInt("rrsp", rrspSeekBar.getProgress());
            editor.apply();
        } catch (NumberFormatException e) {
            federalTaxTextView.setText("Federal Tax: $0.00");
            provincialTaxTextView.setText("Provincial Tax: $0.00");
            totalTaxTextView.setText("Total Tax: $0.00");
        }
    }

    private void refreshFields() {
        // Clear input fields
        incomeEditText.setText("");
        rrspSeekBar.setProgress(0);
        rrspValueTextView.setText("RRSP Contribution: $0.00");

        // Reset tax text views
        federalTaxTextView.setText("Federal Tax: $0.00");
        provincialTaxTextView.setText("Provincial Tax: $0.00");
        totalTaxTextView.setText("Total Tax: $0.00");

        // Clear saved preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
