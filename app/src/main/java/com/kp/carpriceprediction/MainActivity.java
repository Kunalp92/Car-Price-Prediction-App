package com.kp.carpriceprediction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    private EditText editTextYear, editTextPresentPrice, editTextKmsDriven, editTextOwner;
    private Spinner spinnerFuelType, spinnerSellerType, spinnerTransmission;
    private Button buttonPredict;
    private TextView textViewResult;
    private Interpreter tflite;

    private String[] fuelTypes = {"Petrol", "Diesel", "CNG"};
    private String[] sellerTypes = {"Dealer", "Individual"};
    private String[] transmissions = {"Manual", "Automatic"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        imageView = findViewById(R.id.image);

        editTextYear = findViewById(R.id.editTextYear);
        editTextPresentPrice = findViewById(R.id.editTextPresentPrice);
        editTextKmsDriven = findViewById(R.id.editTextKmsDriven);
        spinnerFuelType = findViewById(R.id.spinnerFuelType);
        spinnerSellerType = findViewById(R.id.spinnerSellerType);
        spinnerTransmission = findViewById(R.id.spinnerTransmission);
        editTextOwner = findViewById(R.id.editTextOwner);
        buttonPredict = findViewById(R.id.buttonPredict);
        textViewResult = findViewById(R.id.textViewResult);

        // Set up the spinners
        setupSpinners();

        // Load the TensorFlow Lite model
        loadModel();

        // Set up the predict button
        buttonPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                predictCarPrice();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupSpinners() {
        ArrayAdapter<String> fuelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fuelTypes);
        fuelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(fuelAdapter);

        ArrayAdapter<String> sellerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sellerTypes);
        sellerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSellerType.setAdapter(sellerAdapter);

        ArrayAdapter<String> transmissionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, transmissions);
        transmissionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTransmission.setAdapter(transmissionAdapter);
    }

    private void loadModel() {
        try {
            tflite = new Interpreter(loadModelFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = getAssets().openFd("car_price_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private void predictCarPrice() {
        float year = Float.parseFloat(editTextYear.getText().toString());
        float presentPrice = Float.parseFloat(editTextPresentPrice.getText().toString());
        float kmsDriven = Float.parseFloat(editTextKmsDriven.getText().toString());
        float owner = Float.parseFloat(editTextOwner.getText().toString());

        // Convert categorical features to numerical values
        float fuelType = spinnerFuelType.getSelectedItemPosition(); // 0 for Petrol, 1 for Diesel, 2 for CNG
        float sellerType = spinnerSellerType.getSelectedItemPosition(); // 0 for Dealer, 1 for Individual
        float transmission = spinnerTransmission.getSelectedItemPosition(); // 0 for Manual, 1 for Automatic

        // Prepare the input features for the model
        float[] inputFeatures = new float[]{year, presentPrice, kmsDriven, fuelType, sellerType, transmission, owner};

        // Change output array to match expected shape [1, 1]
        float[][] outputPrice = new float[1][1];

        // Run the model
        tflite.run(inputFeatures, outputPrice);

        // Display the predicted price
        textViewResult.setText(String.format("Predicted Selling Price: %.2f", outputPrice[0][0]));
    }

}