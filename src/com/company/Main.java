package com.company;

public class Main {

    public static void main(String[] args) {
        Calculator c = new Calculator();
        while (true) {
            if (c.getStatus()) {
                c.run();
            }
            else {
                break;
            }
        }
    }
}