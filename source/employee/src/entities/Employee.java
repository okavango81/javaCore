package entities;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Getter
@ToString
public class Employee
{
    private int id;
    private String name;
    private BigDecimal salary;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public Employee(int id, String name, BigDecimal salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void increaseSalary(double percent)
    {
        BigDecimal increase = salary.multiply(BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100)));
        salary = salary.add(increase);
    }

    public String toString(){
        return String.format("%s, %s, %s", id, name, formatter.format(salary));
    }
}
