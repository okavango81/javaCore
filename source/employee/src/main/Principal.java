package main;

import entities.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Principal
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many employees do you want to add? ");
        int n = scanner.nextInt();
        List<Employee> employees = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {

            System.out.println();
            System.out.println("Employee #" + (i + 1));
            System.out.print("Id: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Salary: ");
            String s = scanner.next();
            s = s.replace(",", ".");
            BigDecimal salary = new BigDecimal(s);

            employees.add(new Employee(id, name, salary));
        });

        System.out.println();
        System.out.print("Enter the employee id that will have salary increase: ");
        int idEmployee = scanner.nextInt();

        Employee employee = employees.stream().filter(x -> x.getId() == idEmployee).findFirst().orElse(null);

        if (employee != null){
            System.out.print("Enter the percentage: ");
            String p = scanner.next();
            double percentage = Double.parseDouble(p.replace(",", "."));
            employee.increaseSalary(percentage);
        }
        System.out.println();
        System.out.println("List of employees: ");
        employees.forEach(System.out::println);

        scanner.close();
    }
}
