import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) throws IOException {

        /*completely unfinished*/

        Activity.letterFileWiper();
        Scanner scan = new Scanner(System.in);
        SortedArrayList<Activity> activityArrayList = new SortedArrayList<>();
        SortedArrayList<Customer> customerArrayList = new SortedArrayList<>();
        reader(activityArrayList, customerArrayList);
        start(activityArrayList, customerArrayList, scan);
    }

    /*start*/
    public static void start(SortedArrayList<Activity> activity, SortedArrayList<Customer> customer, Scanner scan) throws IOException {

        while (optionMenu(activity, customer,scan)){
            optionMenu(activity, customer,scan);
        }
    }

    /*reader function*/
    public static void reader(SortedArrayList<Activity> activityArrayList, SortedArrayList<Customer> customerArrayList) {

        ArrayList<String> fileLineArray = new ArrayList<>();
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("src/input.txt"));
            String line = buffReader.readLine();

            while (line != null && line.length() > 0) {
                fileLineArray.add(line);
                line = buffReader.readLine();
            }
            Integer activityNumber = (Integer.parseInt(fileLineArray.get(0)) * 2);

            for (int i = 1; i < activityNumber; i = i + 2) {
                String activityName = fileLineArray.get(i);
                Integer ticketNo = Integer.parseInt(fileLineArray.get(i + 1));
                Activity tempActivity = new Activity(activityName, ticketNo);
                activityArrayList.insert(tempActivity);
            }
            for (int i = (activityNumber + 2); i < (fileLineArray.size()); i++) {
                String[] splitName = fileLineArray.get(i).split(" ");
                String firstName = splitName[0];
                String lastName = splitName[1];
                Customer tempCustomer = new Customer(firstName, lastName);
                customerArrayList.insert(tempCustomer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //number format exception//
    }
    public static boolean optionMenu(SortedArrayList<Activity> activity, SortedArrayList<Customer> customer, Scanner scan) {

        System.out.println("""
                    f - to finish running the program.
                    a - to display on the screen information about all the activities.
                    c - to display on the screen information about all the customers.
                    t - to update the stored data when tickets are bought by one of the registered customers.
                    r - to update the stored data when a registered customer cancels tickets for a booking.
                    """);

        switch (scan.next().charAt(0)) {
            case 'f':
                System.out.println("Exiting the program");
                return false;

            case 'a':
                System.out.println("The available activities and their ticket numbers are listed below.\n");
                System.out.println("--------------------------------");

                Activity.activityPrint(activity);
                break;

            case 'c':
                System.out.println("The current registered customers are listed below.\n");
                System.out.println("--------------------------------");

                Customer.customerPrint(customer);
                break;

            case 't':
                System.out.println("\nEnter the first name");
                String first = scan.next();
                System.out.println("\nEnter the last name");
                String last = scan.next();

                if (Customer.registered(customer, first, last)) {
                    System.out.println("Would you like to update this customers tickets?\n(Yes to update, any other key to return)\n");
                    String updateAnswer = scan.next().toLowerCase();
                    scan.nextLine();
                    if (!updateAnswer.equals("yes")) {
                        break;
                    } else {
                        System.out.println("Enter the Activity this customer wishes to buy tickets for\n");
                        String enteredActivity = scan.nextLine().toLowerCase();

                        if (Activity.activityExists(activity, enteredActivity)) {
                            System.out.println("To update this customers tickets enter the number of tickets they want to buy\n");
                            Integer ticketsBought = 0;

                            try {
                                ticketsBought = Integer.parseInt(scan.next());
                            } catch (NumberFormatException e) {
                                System.out.println("Enter a valid number only\n");
                                break;
                            }

                            try {
                                if (Activity.buyUpdate(activity, enteredActivity, ticketsBought, first)) {
                                    Customer.activityBuyUpdate(customer, ticketsBought, enteredActivity, first, last);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
                break;

            case 'r':
                System.out.println("\nEnter the first name");
                String firstName = scan.next();
                System.out.println("\nEnter the last name");
                String lastName = scan.next();

                if (Customer.registered(customer, firstName, lastName)) {
                    System.out.println("Would you like to update this customers tickets?\n(Yes to update, any other key to return)\n");
                    String updateAnswer = scan.next().toLowerCase();
                    scan.nextLine();
                    if (!updateAnswer.equals("yes")) {
                        break;
                    } else {
                        if (Customer.registered(customer, firstName, lastName)) {
                            System.out.println("Enter the Activity this customer wants to cancel tickets for\n");
                            String enteredActivity = scan.nextLine().toLowerCase();

                            if (Activity.activityExists(activity, enteredActivity)) {
                                System.out.println("To update this customers tickets enter the number of tickets you wish to cancel\n");
                                Integer ticketsCancelled = 0;

                                try {
                                    ticketsCancelled = Integer.parseInt(scan.next());
                                } catch (NumberFormatException e) {
                                    System.out.println("Enter a valid number only\n");
                                    break;
                                }

                                if (Customer.activityCancelUpdate(customer, ticketsCancelled, enteredActivity, firstName, lastName)) {
                                    Activity.cancelUpdate(activity, enteredActivity, ticketsCancelled);
                                }
                            }
                        }
                    }
                }
                break;

            default:
                break;
        }
        return true;
    }
}

