import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<String> names = List.of("Maria","Robert","Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hank", "Ivy", "Jack");
        List<String> lastNames = List.of("Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor");

        Student student = new Student();
        List<Student> students = student.generateList(15, names, lastNames);
        students.stream().forEach(System.out::println);

        System.out.print("\nPesquise pelo id do aluno: ");
        int id = scanner.nextInt();

        Student studentSought = students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        String findStudent = (studentSought != null) ? studentSought.toString() : "Not found";

        System.out.println(findStudent);

        scanner.close();
    }
}
