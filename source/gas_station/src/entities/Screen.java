package entities;

public class Screen
{
    public void br()
    {
        System.out.println();
    }

    public void underLine()
    {
        System.out.println("_____________________________________________________________________________________________________");
    }

    public void message(String message)
    {
        System.out.println(message);
    }

    public void result(String time, String vehivle)
    {
        System.out.println(time + " " + vehivle);
    }

    public void resume(String message, int id, String type, double total)
    {
        if (type.equalsIgnoreCase("gasoline"))
        {
            type = "GASOLINA";
        }
        if (type.equalsIgnoreCase("ethanol"))
        {
            type = "ETANOL";
        }

        System.out.println(message + " " + id + " " + "(" + type + ")" + ": " + total + " " + "litros");
    }
}
