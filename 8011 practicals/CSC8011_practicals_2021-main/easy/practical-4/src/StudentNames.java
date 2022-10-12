import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * Purpose: Introduction to data structures
 * @author Phillip Lord
 * @author Jordan Barnes
 *
 */
public class StudentNames {
    public static void main(String[] args) {
        String[] names;/*Complete*/;
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter student name:");
            names[i] = s.next();
        }
        System.out.println(Arrays.toString(names));
    }
}
