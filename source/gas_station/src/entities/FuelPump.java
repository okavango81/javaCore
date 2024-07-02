package entities;

import enums.TypeOfFuel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FuelPump
{
    private int id;
    private TypeOfFuel type;
    private List<Vehicle> vehicles;
    private double fullyStocked;

    public FuelPump(int id, TypeOfFuel type){
        this.id = id;
        this.type = type;
        this.fullyStocked = 0;
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle)
    {
        this.vehicles.add(vehicle);
    }

    public void addFuel(double fuel){
        this.fullyStocked += fuel;
    }

}
