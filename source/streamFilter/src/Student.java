import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student
{
    private int id;
    private String name;

    public List<Student> generateList(int count, List<String> nameStudent, List<String> lastNameStudent)
    {
        List<Student> students = new ArrayList<>(count);
        List<String> firstNames = nameStudent;
        List<String> lastNames = lastNameStudent;
        Random random = new Random();

        IntStream.range(1, count + 1).forEach(i ->
        {
            String name = firstNames.get(random.nextInt(nameStudent.size()));
            String lastName = lastNames.get(random.nextInt(lastNameStudent.size()));
            String fullName = name + " " + lastName;
            students.add(new Student(i, fullName));
        });

        return students;
    }
}
