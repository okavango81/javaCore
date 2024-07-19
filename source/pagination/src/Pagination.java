import java.util.ArrayList;
import java.util.List;

public class Pagination
{

    public static String methodToPage(int[] data, int pageNumber, int pageSize)
    {
        List<Integer> page = new ArrayList<>();
        int startIndex = (pageNumber - 1) * pageSize; // 8(7) * 5 = 35
        int endIndex = Math.min(startIndex + pageSize, data.length); // (35 + 5) = retorna o tamanho do array = 39

        for (int i = startIndex; i < endIndex; i++)
        {
            page.add(data[i]);
        }
        return page.toString();
    }

    public static void main(String[] args)
    {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        System.out.println(methodToPage(data, 8, 5));
    }
}
