package com.company;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    private int number1,number2;
    private char operation;
    private int result;
    private boolean isArabic;
    private boolean status=true;
    Converter converter = new Converter();

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        data = data.toUpperCase();
        if (data.equals("!")){
            status=false;
        }
        else {
            operation = getOperation(data);
            number1 = getNumber1(data);
            number2 = getNumber2(data);
            calculate();
        }
    }

    private char getOperation(String data){
        if (data.contains("+") && !data.startsWith("+") && !data.endsWith("+")) {
            return '+';
        }
        else if (data.contains("-") && !data.startsWith("-") && !data.endsWith("-")) {
            return '-';

        }
        else if (data.contains("*") && !data.startsWith("*") && !data.endsWith("*")) {
            return '*';
        }
        else if (data.contains("/") && !data.startsWith("/") && !data.endsWith("/")) {
            return '/';
        }
        else throw new InputMismatchException("Wrong Input");
    }

    private int getNumber1(String data){
        String number = data.substring(0,data.indexOf(operation));
        if (converter.checkArabic(number)){
            isArabic=true;
            return Integer.valueOf(number);
        }
        else if (converter.checkRoman(number)) {
            isArabic=false;
            return converter.toArabic(number);
        }
        throw new InputMismatchException("Wrong Input");

    }

    private int getNumber2(String data){
        String number = data.substring(data.indexOf(operation)+1);
        if (converter.checkArabic(number)){
            if (isArabic) {
                return Integer.valueOf(number);
            }
            throw new InputMismatchException("Different format");
        }
        else if (converter.checkRoman(number)) {
            if (!isArabic) {
                return converter.toArabic(number);
            }
            throw new InputMismatchException("Different format");
        }

        throw new InputMismatchException("Wrong Input");
    }

    private void calculate(){
        switch (operation){
            case '+' : result = number1 + number2; break;
            case '-' : result = number1 - number2; break;
            case '*' : result = number1 * number2; break;
            case '/' : result = number1 / number2; break;
        }

        if (isArabic){
            System.out.println(result);
        }
        else {
            System.out.println(converter.toRoman(result));
        }
    }

    public boolean getStatus(){
        return status;
    }
}