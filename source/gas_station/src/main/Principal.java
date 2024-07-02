package main;

import entities.Supply;

import java.io.IOException;

public class Principal
{
    public static void main(String[] args) throws IOException
    {
        Supply supply = new Supply("/home/ed/Workspace/java/core/files/veiculos.csv");
        supply.toFuel();
    }
}
