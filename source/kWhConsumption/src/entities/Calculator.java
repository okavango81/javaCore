package entities;

import enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Getter
public class Calculator
{
    public List<House> listProfileLow;
    public List<House> listProfileMid;
    public List<House> listProfileHig;
    public List<House> listHouseGeneral;
    private Screen screen;
    private String option = "-1";
    int idHouse = -1;
    private String dataBase = "/home/ed/Workspace/java/core/files/consumo.csv";
    private static final int LOW_CONSUMPTION = 100;
    private static final int MID_CONSUMPTION = 150;
    private static final int HIG_CONSUMPTION = 200;
    private Scanner scanner;

    public Calculator()
    {
        listProfileLow = new ArrayList<>();
        listProfileMid = new ArrayList<>();
        listProfileHig = new ArrayList<>();
        listHouseGeneral = new ArrayList<>();
        screen = new Screen();
        scanner = new Scanner(System.in);
    }

    public void menu() throws IOException
    {
        calculate();
        screen.line();
        listHouseGeneral.stream().forEach(System.out::println);
        screen.line();
        screen.br();

        while (!option.equals("0"))
        {
            options();
        }

        System.out.println("Fim do programa.");
    }

    public void options()
    {
        screen.br();
        screen.message("MENU DE OPÇÕES:");
        screen.message("1. Para escolher os detalhes da casa desejada: ");
        screen.message("2. Para a lista de casas BAIXO consumo");
        screen.message("3. Para a lista de casas MÉDIO consumo");
        screen.message("4. Para a lista de casas ALTO consumo");
        screen.message("0. Para FINALIZAR");
        screen.br();
        screen.messageNBR("Escolha uma opção: ");
        option = scanner.nextLine();
        find();
    }

    public void find(){

        switch (option){
            case "1":
                screen.messageNBR("Digite o id da casa desejada: ");
                int idHouse = scanner.nextInt();
                scanner.nextLine();
                House houseSought = listHouseGeneral.stream().filter(x -> x.getId() == idHouse).findFirst().orElse(null);

                if(houseSought != null){
                    screen.line();
                    screen.message(houseSought.details());
                    screen.line();
                }else{
                    screen.line();
                    screen.message("Casa não encontrada");
                    screen.line();
                }
                break;

            case "2":
                screen.line();
                screen.message("CASAS BAIXO CONSUMO");
                screen.line();
                listProfileLow.stream().forEach(System.out::println);
                screen.line();
                break;

            case "3":
                screen.line();
                screen.message("CASAS MÉDIO CONSUMO");
                screen.line();
                listProfileMid.stream().forEach(System.out::println);
                screen.line();
                break;

            case "4":
                screen.line();
                screen.message("CASAS ALTO CONSUMO");
                screen.line();
                listProfileHig.stream().forEach(System.out::println);
                screen.line();
                break;

            default:
                break;
        }
    }

    public void calculate() throws IOException
    {
        List<String> listHouses = Files.readAllLines(Paths.get(dataBase));

        IntStream.range(1, listHouses.size()).forEach(i ->
        {
            String[] columns = listHouses.get(i).split(";");
            int id = Integer.parseInt(columns[0]);
            String publicPlace = columns[1];
            int residents = Integer.parseInt(columns[2]);
            int kWh = Integer.parseInt(columns[3]);

            House[] houses = new House[listHouses.size()];
            Address[] addresses = new Address[listHouses.size()];

            addresses[i] = new Address(publicPlace);
            houses[i] = new House(id, addresses[i], residents, kWh);
            int avgHouse = avg(houses[i].getKWh(), houses[i].getResidents());

            if (avgHouse <= LOW_CONSUMPTION)
            {
                houses[i].setProfile(Profile.LOW);
                listProfileLow.add(houses[i]);
            } else if (avgHouse <= MID_CONSUMPTION)
            {
                houses[i].setProfile(Profile.MID);
                listProfileMid.add(houses[i]);
            } else
            {
                houses[i].setProfile(Profile.HIG);
                listProfileHig.add(houses[i]);
            }
            listHouseGeneral.add(houses[i]);
        });
    } // calculate


    public int avg(int kWh, int residents)
    {
        return kWh / residents;
    }

}
