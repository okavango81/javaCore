import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DatesForJava
{

    public static void main(String[] args)
    {
        //1. dia local
        LocalDate today = LocalDate.now();

        //2. dia e hora local
        LocalDateTime todayTime = LocalDateTime.now();

        //3. dia e hora local de londres
        Instant instant = Instant.now();

        //4. dia de nascimento
        LocalDate birthday = LocalDate.of(2023, Month.AUGUST, 7);

        //5. máscara para data local
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //6. dia e hora locais com fuso definido
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

        //7. máscara para o dia e hora locais com fuso definido
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        //8. calculo entre o dia de nascimento e o dia de hoje
        Period period = Period.between(birthday, today);

        //9. formatar moeda local
        NumberFormat here = NumberFormat.getCurrencyInstance();
        BigDecimal value = new BigDecimal("2500");

        //10. definindo outro zoneId
        Locale locale = new Locale("en", "US");
        NumberFormat country = NumberFormat.getCurrencyInstance(locale);

        System.out.println();
        System.out.println("1. Dia local: " + today);
        System.out.println("2. Dia e hora local: " + todayTime);
        System.out.println("3. Dia e hora local de londres: " + instant);
        System.out.println("4. Data de nascimento: " + birthday);
        System.out.println("5. Data de nascimento formatada: " + birthday.format(formatter));
        System.out.println("6. Dia e hora local com fuso definido: " + zonedDateTime);
        System.out.println("7. Data e hora local formatada: " + formatter2.format(zonedDateTime));
        System.out.println("8. Idade: " + period.getYears() + " ano(s), " + period.getMonths() + " mes(es) e " + period.getDays() + " dia(s)");
        System.out.println("9. Moeda local: " + here.format(value));
        System.out.println("10. Moeda de outro país: " + country.format( value));
    }
}
