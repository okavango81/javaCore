package entities;

import enums.TypeOfFuel;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class Supply
{
    private String dataFile;
    private FuelPump ethanolPump;
    private FuelPump gasolinePump;
    private Screen screen;
    private static final BigDecimal ETHANOL_PRICE = new BigDecimal("2.27");
    private static final BigDecimal GASOLINE_PRICE = new BigDecimal("2.90");
    private static final double GASOLINE_SUPPLY_SPEED = 10.0;
    private static final double ETHANOL_SUPPLY_SPEED = 12.0;
    private int timer = 0;
    private int hours = 0;
    private int minutes = 0;
    private String typeOfFuel = "";


    public Supply(String dataFile)
    {
        this.dataFile = dataFile;
        gasolinePump = new FuelPump(1, TypeOfFuel.GASOLINE);
        ethanolPump = new FuelPump(2, TypeOfFuel.ETHANOL);
        screen = new Screen();
    }

    public void toFuel() throws IOException
    {
        screen.underLine();
        screen.br();
        screen.message("RESULTADO DA SIMULAÇÃO");
        screen.underLine();
        screen.br();

        List<String> list = Files.readAllLines(Paths.get(dataFile));
        Vehicle[] vehicles = new Vehicle[list.size()];

        IntStream.range(1, list.size()).forEach(i ->
        {
            String line = list.get(i).replace(",", ".");
            String[] column = line.split(";");

            String model = column[0];
            String plateNumber = column[1];
            double consumptionEthanol;
            if (!column[2].isEmpty())
            {
                consumptionEthanol = Double.parseDouble(column[2]);
            } else
            {
                consumptionEthanol = 0.0;
            }
            double consumptionGasoline;
            if (!column[3].isEmpty())
            {
                consumptionGasoline = Double.parseDouble(column[3]);
            } else
            {
                consumptionGasoline = 0.0;
            }
            double totalCapacity = Double.parseDouble(column[4]);

            vehicles[i] = new Vehicle(model, plateNumber, consumptionEthanol, consumptionGasoline, totalCapacity);

            BigDecimal costEthanol = verifyCost(ETHANOL_PRICE, vehicles[i].getConsumptionEthanol());
            BigDecimal costGasoline = verifyCost(GASOLINE_PRICE, vehicles[i].getConsumptionGasoline());


            if (costEthanol.compareTo(costGasoline) > 0)
            {
                gasolinePump.addVehicle(vehicles[i]);
                gasolinePump.addFuel(vehicles[i].getTotalCapacity());
                timer += timeOfSupply(vehicles[i].getTotalCapacity(), GASOLINE_SUPPLY_SPEED);
                typeOfFuel = gasolinePump.getType().name();
            } else
            {
                ethanolPump.addVehicle(vehicles[i]);
                ethanolPump.addFuel(vehicles[i].getTotalCapacity());
                timer += timeOfSupply(vehicles[i].getTotalCapacity(), ETHANOL_SUPPLY_SPEED);
                typeOfFuel = ethanolPump.getType().name();
            }

            // imprime cada linha da lista de veiculos
            screen.result(formatterTimer(timer), vehicles[i].toString(typeOfFuel));

        }); // IntStream

        screen.underLine();
        screen.br();
        screen.message("RESUMO DA SIMULAÇÃO");
        screen.underLine();
        screen.br();
        screen.resume("Total abastecido na bomba", gasolinePump.getId(), gasolinePump.getType().name(), gasolinePump.getFullyStocked());
        screen.resume("Total abastecido na bomba", ethanolPump.getId(), ethanolPump.getType().name(), ethanolPump.getFullyStocked());
        screen.underLine();
    } // toFuel


    // verifica o custo por km
    public BigDecimal verifyCost(BigDecimal price, double autonomy)
    {
        if (autonomy > 0)
        {
            BigDecimal cost = new BigDecimal(String.valueOf(price)).divide(new BigDecimal(autonomy), 2, BigDecimal.ROUND_HALF_UP);
            return cost;
        } else
        {
            return BigDecimal.ZERO;
        }
    }

    // incrementa o tempo de abastecimeno
    public int timeOfSupply(double totalCapacity, double speedPerLiter)
    {
        int time = 0;
        time += (int) totalCapacity / speedPerLiter;
        return time;
    }

    // formata o tempo de abastecimento
    public String formatterTimer(int timer)
    {
        hours = timer / 60;
        minutes = timer % 60;
        return String.format("[%02d:%02d]", hours, minutes);
    }

}
