package main;

import entities.Calculator;

import java.io.IOException;

public class Principal
{
    public static void main(String[] args) throws IOException
    {
        Calculator calculator = new Calculator();
        calculator.menu();
    }
}
