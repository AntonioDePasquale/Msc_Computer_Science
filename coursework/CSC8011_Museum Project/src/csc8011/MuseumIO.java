package csc8011;
import java.io.File;
import java.util.Scanner;

//MuseumIO class for the input output functions and main function//
public class MuseumIO {
    public static void main(String[] args) {            //main function//
        Museum museum = new Museum();                   //constructs a new museum class to be used//
        Scanner scan = new Scanner(System.in);          //creates a new scanner class called scan//
        start(museum, scan);                            /*a start function shown below that takes the museum class
                                                          and scanner scan as parameters*/
    }

    public static void start(Museum museum, Scanner scan) {         //start function described above//
        read(museum);                                               //reader function called read, uses museum as param//
        while (true) {                                              //for loop to launch the program//
            museumOptionMenu(museum, scan);                         //will run the menu switch function on repeat//
        }
    }

    //reader function to read lines from the csv file as exhibit objects to be added to the arraylist//
    public static void read(Museum museum) {
        try {
            Scanner reader = new Scanner(new File("src/csc8011/exhibits.csv"));  //reads csv file location//
            while (reader.hasNextLine()) {                      //while there is a nextline assign it to line//
                String line = reader.nextLine();
                if (line.isEmpty()) {                           //if the line is empty continue out of while loop//
                continue;
                }
                String[] attributes = line.split(",");     //splits each line into an array of attributes//

                Integer tempId = Integer.parseInt(attributes[0].trim());        //parses index[0] to int, assigns own variable//
                String tempDesc = attributes[1].trim();                         //index[1] is already string, assigns own variable//
                Integer tempYear = Integer.parseInt(attributes[2].trim());      //parses index[2] to int, assigns own variable//
                Double tempValue = Double.parseDouble(attributes[3].trim());    //parses index[3] to double, assigns own variable//

                //individual attributes can now be passed into the Exhibit constructor to create an exhibit object//
                Exhibit tempExhibit = new Exhibit(tempId,tempDesc,tempYear,tempValue);
                museum.addExhibit(tempExhibit);               //adds the newly created exhibit object to the arraylist in museum//
            }
        }
        catch (Exception e) {                                 //if the try is unsuccessful prints the error message to the console//
            System.out.println("Error " + e.getMessage());
        }
    }

    //museum option menu function using a switch expression//

    public static void museumOptionMenu(Museum museum, Scanner scan) {
        System.out.println("\nWelcome to the " + museum.getMuseumName() + "!");  //welcoming message that prints museum name//
        System.out.println("Please enter the number for the corresponding option you wish to see\n");
        System.out.println("""
                1: Change the name of the Museum
                2: A list of all current exhibits and their information
                3: Details of exhibit with the highest value
                4: Details of the first exhibit acquired
                5: The average value of all exhibits
                """);                                               //enhanced switch expressions with 5 options//

        switch (scan.nextInt()) {
            case 1 -> {
                System.out.println(museum.getMuseumName());         //case 1 prints museum name and asks for input//
                System.out.println("Enter a new museum name");
                String nameInput = scan.next();                     //the museum name is set to the input using a setter function//
                museum.setMuseumName(nameInput);
            }
            case 2 -> {                                             //case 2 prints all objects in the arraylist as a string//
                System.out.println(museum.getMuseumName());
                int length = museum.getArrayList().size();
                for (int i = 0; i < length; i++) {                  //iterates over each exhibit object attribute//
                    System.out.print(museum.getArrayList().get(i).getExhibitId() + ", ");
                    System.out.print(museum.getArrayList().get(i).getDescription() + ", ");
                    System.out.print(museum.getArrayList().get(i).getYearAcquired() + ", ");
                    System.out.print(museum.getArrayList().get(i).getValue() + "\n");
                    //each attribute printed to console on a new line separated by comma//
                }
            }
            case 3 -> {                           //case 3 highest value is printed from the findHighestValue function//
                System.out.println("The highest value exhibit is shown below");
                System.out.println(museum.findHighestValue() + "\n");
            }
            case 4 -> {                           //case 4 first acquired is printed from the findFirstAcquired function//
                System.out.println("The first acquired exhibit is shown below");
                System.out.println(museum.findFirstAdded() + "\n");
            }                                     //case 5 average value found by the findFirstAdded function//
            case 5 -> System.out.println("The average value of all exhibits is " + museum.findAverageValue() + "\n");
        }
    }
}
