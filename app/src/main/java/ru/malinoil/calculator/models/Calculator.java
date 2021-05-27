package ru.malinoil.calculator.models;

import android.util.Log;

public class Calculator {

    private static final String TAG = "@@@ Calculator";

    private StringBuilder textOnCalculator;
    private StringBuilder builderNumber;
    private float result;
    private String operation;

    private boolean isValid;

    public Calculator() {
        textOnCalculator = new StringBuilder();
        builderNumber = new StringBuilder();
        isValid = false;
        operation = "";
    }

    public StringBuilder getTextOnCalculator() {
        return textOnCalculator;
    }

    public void addNumber(String number) {
        builderNumber.append(number);
        textOnCalculator.append(number);
        isValid = true;
    }

    public void addDot() {
        if (isValid && !builderNumber.toString().contains(".")) {
            builderNumber.append(".");
            textOnCalculator.append(".");
            Log.d(TAG, builderNumber.toString());
            isValid = false;
        }
    }

    public void addNullNumber() {
        if (!builderNumber.toString().equals("0")) {
            builderNumber.append("0");
            textOnCalculator.append("0");
            Log.d(TAG, builderNumber.toString());
            isValid = true;
        }
    }

    public void addOperation(String operation) {
        if (isValid) {
            if(this.operation.length() > 0) {
                calculation(Float.parseFloat(builderNumber.toString()));
                textOnCalculator.setLength(0);
                textOnCalculator.append(result);
            } else if(builderNumber.toString().length() > 0) {
                result = Float.parseFloat(builderNumber.toString());
            }

            if(!operation.equals("=")) {
                textOnCalculator.append(operation);
                this.operation = operation;
                isValid = false;
            } else {
                this.operation = "";
            }
            builderNumber.setLength(0);
        }
    }

    public void clear() {
        textOnCalculator.setLength(0);
        builderNumber.setLength(0);
        result = 0;
        operation = "";
        isValid = false;
    }

    private void addition(float number) {
        result += number;
    }

    private void subtraction(float number) {
        result -= number;
    }

    private void multiplication(float number) {
        result *= number;
    }

    private void division(float number) {
        if(number != 0) {
            result /= number;
        }
    }

    private void calculation(float number) {
        switch (operation) {
            case "+":
                addition(number);
                break;
            case "-":
                subtraction(number);
                break;
            case "/":
                division(number);
                break;
            case "x" :
                multiplication(number);
                break;
        }
    }

    public boolean isValid() {
        return isValid;
    }
}
