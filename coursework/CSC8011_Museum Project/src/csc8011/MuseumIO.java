package csc8011;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MuseumIO {
    public static void main(String[] args) throws FileNotFoundException {
        Museum museum = new Museum();
        Scanner scan = new Scanner(System.in);
        start(museum, scan);
    }

    public static void start(Museum museum, Scanner scan) throws FileNotFoundException {
        while (true) {
            museumOptionMenu(museum, scan);
            String input = scan.nextLine();
            read(museum);
        }
    }

    public static void read(Museum museum) throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(new File("csc8011/exhibits.csv"));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.isEmpty()) {
                continue;
                }
                String[] attributes = line.split(",");

                Integer tempId = Integer.parseInt(attributes[0].trim());
                String tempDesc = attributes[1].trim();
                Integer tempYear = Integer.parseInt(attributes[2].trim());
                Double tempValue = Double.parseDouble(attributes[3].trim());

                Exhibit tempExhibit = new Exhibit(tempId,tempDesc,tempYear,tempValue);
                museum.addExhibit(tempExhibit);
            }
        }
        catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    //museum options function//

    public static void museumOptionMenu(Museum museum, Scanner scan) {
        System.out.println("Welcome to Museum online portal!");
        System.out.println("Please enter the number for the corresponding option you wish to see");
        System.out.println("1: The name of the Museum" +
                "\n2: A list of all current exhibits and their information" +
                "\n3: Details of exhibit with the highest value" +
                "\n4: Details of the first exhibit acquired" +
                "\n5: The average value of all exhibits");

        switch (scan.nextInt()) {
            case 1:
                System.out.println(museum.getMuseumName());
                System.out.println("Do you wish to change the name?" +
                        "\n(enter yes to confirm or any other input to return to the menu)");

                if (scan.nextLine().equalsIgnoreCase("yes")) {
                    System.out.println("Please enter the new museum name");
                    String nameInput = scan.nextLine();
                    museum.setMuseumName(nameInput);
                } else {
                    break;
                }
                break;

            case 2:
                for (int i = 0; i < museum.exhibits.size(); i++) {
                    System.out.println(museum.exhibits.get(i));
                }
                break;

            case 3:
                System.out.println("The highest value exhibit is shown below");
                System.out.println(museum.findHighestValue());
                break;

            case 4:
                System.out.println("The first acquired exhibit is shown below");
                System.out.println(museum.findFirstAdded());
                break;

            case 5:
                System.out.println("The average value of all exhibits is " + museum.findAverageValue());
                break;
        }
    }

}
