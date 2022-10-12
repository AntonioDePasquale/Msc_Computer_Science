# practical-4

## practical 4.1

In the class called `src/StudentNames`, add the following code

```java

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
        String[] names;/*Complete*/
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter student name:");
            names[i] = s.next();
        }
        System.out.println(Arrays.toString(names));
    }
}

```

## practical 4.1

At the moment this code will not run, as the array is not initialised. Create an array of size 5 at the appropriate location and make sure that
the program runs.

## Practical 4.2

Half-way through your class three new students register. Change your code to accommodate your new students. While doing
this, fix your code so that the maximum number of students is only stated in one place.

## Practical 4.3

Now that your program asks for the correct number of students, you want to display the names of some specific students. Write
three println() statements that display the values of the 1st, 3rd and 8th student that you entered. Please use a suitable
message to tell the user what they are seeing.

## Practical 4.4

The Arrays.toString() call produces output that tells us we are doing the right thing. But it is ugly, and it is not the sort
of thing you want a user to see. So, let’s remove it.

Now change your program again, so that after entering all the student names, we now display them all back to the user, using a
second for loop. Print out the index of each student name as well.

## Practical 4.5

Change your program, so that instead of displaying all the array elements, it asks the user to input a number, then displays just
that element. 




