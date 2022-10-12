# practical-5

## practical 5.1

The following code has been added to `src/Power`, open it and have a look at
 the errors

```java

/**
 * Purpose: Calculating Powers
 *
 * @author Phillip Lord
 * @author Jordan Barnes
 */
public class Power {
    public static void main(String[] args) {
        Power p = new Power();
        int length = 6;
        p.square(length);
        p.cube(length);
        p.hypercube(length);
    }
}

```

## practical 5.2

The errors are due to missing methods. Write these methods, to calculate the square, cube and hypercube as shown in the comments. For example, you could define square
as follows:

```java
    public void square(int length) {
        System.out.println("The square of " + length + " is:"
                + (length * length));
    }
```

## Practical 5.3

The program that you have now works, but it is not an example of good design. We will see later why this approach is very
limiting. Instead of placing println statements inside the methods, let’s move them to the main method.

## Practical 5.4

Firstly, length is a bit long for a variable name, particular as square and
 cube methods can meaningful operate over things other than lengths. So
 , first, change length to x. IntelliJ will do this all for you, if you ask
  it nicely. Secondly, you probably have the same statements at several
   places in your code. Let’s replace these with calls to the appropriate
method. So, in your cube method, make the following change.

```java
 // replace
x*x*x

// with
x * square(x)
```

## Practical 5.5

This is still quite long winded. It would be nice to change the square, cube, and hypercube methods into a single method.
We are going to achieve this in several steps. First, introduce a method called power as follows:

```java
// Define a power method, with two parameters x and n

// if n == 2 call the square method with x as parameter

// if n == 3 call the cube method

// if n == 4 call the hypercube method
```

Add to the power method an appropriate condition for n==1.

```java
// Define a power method, with two parameters x and n

// if n == 1 the appropriate condition

// if n == 2 call the square method with x as parameter

// if n == 3 call the cube method

// if n == 4 call the hypercube method
```
