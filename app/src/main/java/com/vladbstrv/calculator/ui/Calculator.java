package com.vladbstrv.calculator.ui;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.vladbstrv.calculator.R;

public class Calculator implements Parcelable {

    private double firstArg;
    private double secondArg;

    private StringBuilder inputStr = new StringBuilder();

    private int actionSelected;

    private State state;

    protected Calculator(Parcel in) {
        firstArg = in.readDouble();
        secondArg = in.readDouble();
        actionSelected = in.readInt();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(firstArg);
        dest.writeDouble(secondArg);
        dest.writeInt(actionSelected);
    }

    private enum State {
        FIRST_ARG_INPUT,
        SECOND_ARG_INPUT,
        RESULT_SHOW,
        OPERATION_SELECTED
    }


    public Calculator() {
        state = State.FIRST_ARG_INPUT;
    }

    @SuppressLint("NonConstantResourceId")
    public void onDigitPressed(int digitId) {
        //onNumPressed
        if (state == State.RESULT_SHOW) {
            state = State.FIRST_ARG_INPUT;
            inputStr.setLength(0);
        }

        if (state == State.OPERATION_SELECTED) {
            state = State.SECOND_ARG_INPUT;
            inputStr.setLength(0);
        }

        if (inputStr.length() < 9) {
            switch (digitId) {
                case R.id.btn0:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;

                case R.id.btn1:
                    inputStr.append("1");
                    break;
                case R.id.btn2:
                    inputStr.append("2");
                    break;
                case R.id.btn3:
                    inputStr.append("3");
                    break;
                case R.id.btn4:
                    inputStr.append("4");
                    break;
                case R.id.btn5:
                    inputStr.append("5");
                    break;
                case R.id.btn6:
                    inputStr.append("6");
                    break;
                case R.id.btn7:
                    inputStr.append("7");
                    break;
                case R.id.btn8:
                    inputStr.append("8");
                    break;
                case R.id.btn9:
                    inputStr.append("9");
                    break;
            }
        }

    }

    @SuppressLint("NonConstantResourceId")
    public void onOperationPressed(int actionId) {
        //onActionPressed

        if (actionId == R.id.btnEqually && state == State.SECOND_ARG_INPUT && inputStr.length() > 0) {
            secondArg = Double.parseDouble(inputStr.toString());
            state = State.RESULT_SHOW;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.btnPlus:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.btnMinus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.btnMultiply:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.btnDivide:
                    inputStr.append(firstArg / secondArg);
                    break;
            }
        } else if (inputStr.length() > 0 && state == State.FIRST_ARG_INPUT && actionId != R.id.btnEqually) {
            firstArg = Double.parseDouble(inputStr.toString());
            state = State.OPERATION_SELECTED;
            actionSelected = actionId;

        }
    }

    public String getText() {
//        return inputStr.toString();
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return inputStr.toString();
            case OPERATION_SELECTED:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case SECOND_ARG_INPUT:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr)
                        .toString();
            case RESULT_SHOW:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(inputStr.toString())
                        .toString();
        }
    }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.btnPlus:
                return '+';
            case R.id.btnMinus:
                return '-';
            case R.id.btnMultiply:
                return '*';
            case R.id.btnDivide:
            default:
                return '/';

        }
    }


    public void onClear() {
        state = State.FIRST_ARG_INPUT;
        inputStr.setLength(0);
    }
}
//    private void onStringInput(int digit) {
//        if (process.equals("0")) {
//            process = String.valueOf(digit);
//        } else {
//            process = process + digit;
//        }
//
//        view.showInput(process);
//    }
