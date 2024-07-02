package entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle
{
    private String model;
    private String plateNumber;
    private double consumptionEthanol;
    private double consumptionGasoline;
    private double totalCapacity;

    public Vehicle(String model, String plateNumber, double consumptionEthanol, double consumptionGasoline, double totalCapacity){
        this.model = model;
        this.plateNumber = plateNumber;
        this.consumptionEthanol = consumptionEthanol;
        this.consumptionGasoline = consumptionGasoline;
        this.totalCapacity = totalCapacity;
    }

    public String toString( String type){

        if(type.equalsIgnoreCase("gasoline")){
            type = "GASOLINA";
        }
        if(type.equalsIgnoreCase("ethanol")){
            type = "ETANOL";
        }

        return String.format("Veículo modelo %s, placa %s foi abastecido com %s litros de %s.", model, plateNumber, totalCapacity, type);
    }
}
