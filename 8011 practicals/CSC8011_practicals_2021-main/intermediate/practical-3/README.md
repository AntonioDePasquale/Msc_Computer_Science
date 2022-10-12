# Practical 3

## Practical 3.1

In this practical we're going to practice object composition between
 different classes. In a nutshell, composition allows us to model objects
  that are made up of other objects, thus defining a “has-a” relationship
   between them [source](https://www.baeldung.com/java-inheritance-composition). 
   
   
We're going to do this using a Computer Example. A computer is made up of
 many components, each component can be represented in a class and an object. 
We're going to complete the following classes with the following fields.
 
- Processor
    - make
    - name
    - speed
- Memory (RAM)
    - make
    - name
    - storage
- Storage
    - make 
    - name
    - storage
    
Here is an example of the Processor Class. Complete the Memory and Storage
 Classes
 
 ```java
public class Processor {

    String make;
    String name;
    int speed;

    Processor(String make, String model, int speed){ // Constructor to Complete

    }

}

```
   

## Practical 3.2

For the Processor, Memory, and Storage classes create methods that get and
 set the fields.

## Practical 3.3

For the Processor, Memory, and Storage classes create a toString method that
 returns a String which suitably represents the object. 

## Practical 3.4

Create a new Class called `Computer`, and add the following main method to
 the class
 
 ```java
public class Computer {

    public static void main(String[] args) {
        Processor p1 = new Processor();
        Memory m1 = new Memory();
        Storage s1 = new Storage();
    }
}

```

Populate the object creation with dummy variables to pass to the constructor

## Practical 3.5

In the Computer Class, add the following field declarations
- private Processor p;
- private Memory m;
- private Storage s;

Create a constructor for Computer class, that takes the previously created
Processor, Memory, and Storage objects and assigns them to the internal
 state of a Computer object. This will successfully create composition.
 
 Write methods that allow you to return each of the fields from the Computer
  Class

