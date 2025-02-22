# Car Price Prediction App

## Overview

The Car Price Prediction app is an Android application designed to predict the selling price of a car based on specific input details provided by the user. The app uses a machine learning model trained on a dataset of car prices to make predictions. The model processes features such as the year of manufacture, present price, kilometers driven, fuel type, seller type, transmission type, and owner type to predict an accurate selling price.

## Features

- **User Inputs**:
  - Year of Manufacture
  - Present Price (Current Ex-Showroom Price)
  - Kilometers Driven
  - Fuel Type (Petrol/Diesel/CNG)
  - Seller Type (Dealer/Individual)
  - Transmission Type (Manual/Automatic)
  - Owner Type (First Owner, Second Owner, etc.)
  
- **Prediction Output**: The app calculates and displays the predicted selling price based on the input details provided by the user.

- **Machine Learning Model**: The prediction model has been trained using a dataset of car prices and is integrated into the app using TensorFlow Lite.

## How the App Works

1. The user enters specific details about the car they want to sell or buy, such as the year, present price, kilometers driven, etc.
2. Once the user provides all required details, the app processes the data using the machine learning model.
3. The predicted selling price is displayed to the user.

### User Inputs:
- **Year of Manufacture**: Year when the car was manufactured.
- **Present Price**: The current ex-showroom price of the car.
- **Kilometers Driven**: How many kilometers the car has been driven so far.
- **Fuel Type**: The type of fuel the car uses, such as Petrol, Diesel, or CNG.
- **Seller Type**: Whether the car is being sold by a dealer or an individual owner.
- **Transmission Type**: Transmission of the car, either Manual or Automatic.
- **Owner Type**: The number of previous owners of the car.

### Predicted Output:
- The app predicts the **Selling Price** of the car based on the provided inputs, showing it to the user instantly.

## Technologies Used

- **Android Studio**: The primary IDE used for building the Android application.
- **TensorFlow Lite**: Used to run the machine learning model for price prediction on Android devices.
- **Java**: The programming language used for the Android app development.
- **Google Colab**: Used for training the machine learning model.
- **Python**: For data preprocessing and TensorFlow model development.
- **Firebase**: (Optional) For potential user data management and cloud storage.

## Dataset

The app uses a pre-trained model based on a dataset of car prices (`car_prices.csv`), which contains the following columns:
- **Car_Name**: The name of the car.
- **Year**: The year of manufacture.
- **Selling_Price**: The price at which the car is being sold.
- **Present_Price**: The current ex-showroom price of the car.
- **Kms_Driven**: The total kilometers driven by the car.
- **Fuel_Type**: Type of fuel used by the car (e.g., Petrol, Diesel).
- **Seller_Type**: Dealer or individual selling the car.
- **Transmission**: Transmission type (Manual or Automatic).
- **Owner**: The number of previous owners.

**Dataset Link**: [Download car_prices.csv](https://drive.google.com/file/d/1Y8ixWf7wSDYBCKgMQ_LgebJVkyczEXFK/view?usp=sharing)


## Model Training

The machine learning model is trained on the `car_prices.csv` dataset using **Google Colab**. The model is built using a regression algorithm and then converted to TensorFlow Lite for integration into the Android app.

### Steps for Training:
1. Load the dataset (`car_prices.csv`) in Google Colab.
2. Preprocess the data (handle missing values, encode categorical variables, etc.).
3. Train a regression model using `scikit-learn` or `tensorflow.keras`.
4. Convert the trained model to TensorFlow Lite (`car_price_model.tflite`).
5. Use the TensorFlow Lite model in the Android app for real-time predictions.

**Colab Notebook**: The model training code is available in the `Car Price Prediction.ipynb` notebook.

## Screenshots

### Splash Screen
![Splash Screen](https://github.com/Kunalp92/Car-Price-Prediction-App/blob/69318fade1fc7a179d9fa9a659a9c123589ed7df/image/WhatsApp%20Image%202024-10-22%20at%2012.40.05%20PM.jpeg)
### Main Screen
![Main Screen](https://github.com/Kunalp92/Car-Price-Prediction-App/blob/69318fade1fc7a179d9fa9a659a9c123589ed7df/image/WhatsApp%20Image%202024-10-22%20at%2012.40.04%20PM.jpeg)
### About Screen
![About Screen](https://github.com/Kunalp92/Car-Price-Prediction-App/blob/69318fade1fc7a179d9fa9a659a9c123589ed7df/image/WhatsApp%20Image%202024-10-22%20at%2012.43.32%20PM.jpeg)


## App Link

The Car Price Prediction App is available for download on the Google Play Store:

[Download the app](https://drive.google.com/file/d/1SzmZa57hgP2Mm6RbA_g7KJZ_WdVoJemN/view?usp=sharing)


## How to Run the App

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/car-price-prediction-app.git

2. **Open the project in Android Studio.**

3. **Add TensorFlow Lite model: Ensure the car_price_model.tflite is placed in the assets folder of the Android project.**

4. **Run the app on an Android device or emulator.**

## Requirements
- **Android Studio:** For app development and testing.
- **TensorFlow Lite:** For machine learning model inference on Android.
- **Google Colab:** For model training and testing.
- **Python:** For model training and preprocessing.
  
## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments
- The dataset used in this project is based on publicly available car price datasets.
- TensorFlow Lite documentation for Android integration.
- Android Studio community for supporting the development.
