import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Activity implements Comparable<Activity> {
    private String activityName;
    private Integer tickets;

    /*constructor for activity class*/
    public Activity(String activityName, Integer tickets) {
        this.activityName = activityName;
        this.tickets = tickets;
    }

    /* Setters and getters for Activity*/

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public String getActivityName() {
        return activityName;
    }

    public Integer getTicketNo() {
        return tickets;
    }

    /* Boolean method to return true if both Activity objects have the same name and false otherwise
     * takes a parameter to differentiate from the default equals() */

    public boolean equals(Activity otherActivity) {
        return (activityName.equals(otherActivity.activityName));
    }

    /* activityExists */

    public static Boolean activityExists(SortedArrayList<Activity> activityArray, String activityName) {

        boolean activityExistsInfo = false;

        for (int i = 0; i < activityArray.size(); i++) {
            if (activityArray.get(i).getActivityName().equalsIgnoreCase(activityName)) {
                activityExistsInfo = true;
                break;
            }
        }
        if (activityExistsInfo) {
            System.out.println("\nThe activity entered is an available activity\n");
            return true;
        } else {
            System.out.println("\nThe activity entered is not an available activity\n");
            return false;
        }
    }

    /* Update ticket number of the activity the user has input by reducing the amount by the number
    of tickets the user wishes to buy */

    public static Boolean buyUpdate(SortedArrayList<Activity> activityArray, String activityName, Integer ticketsBought, String firstName) throws IOException {

        for (int i = 0; i < activityArray.size(); i++) {

            if (activityArray.get(i).getActivityName().toLowerCase().equals(activityName)) {
                Integer oldTicketNo = activityArray.get(i).getTicketNo();
                Integer newTicketNo = oldTicketNo - ticketsBought;

                if (newTicketNo >= 0) {
                    activityArray.get(i).setTickets(newTicketNo);
                    System.out.println(ticketsBought + " tickets have been successfully bought");
                    System.out.println("The new number of remaining tickets for " + activityName + " is " + activityArray.get(i).getTicketNo() + " \n");
                    return true;
                } else {
                    System.out.println("There are not enough tickets left to buy that many");
                    System.out.println(activityName + " has " + oldTicketNo + " tickets left\n");

                    Activity.letterOutput(firstName);
                    return false;
                }
            }
        }
        return false;
    }

    public static void letterOutput(String firstName) throws IOException {
        String letterText = "I am sorry to inform you that the recent tickets you wished to buy are either unavailable or" +
                " there are not enough tickets to fulfill your request. We are very sorry for this inconvenience" +
                " and have made sure to refund your transaction. If you still wish to purchase tickets you may be" +
                " able to try again at a later date or may purchase a lower number of tickets if they are available\n";

        try (FileWriter letterWriter = new FileWriter("src\\letters.txt", true);
                BufferedWriter bw = new BufferedWriter(letterWriter);
                PrintWriter pw = new PrintWriter(bw);) {

            pw.println("dear " + firstName);
            pw.println(letterText);
            pw.close();
            System.out.println("A letter has been printed to the customer file to let them know that the tickets could not be purchased.\n");

        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void letterFileWiper() throws IOException {
        PrintWriter printWiper = new PrintWriter("src\\letters.txt");
        printWiper.print("");
        printWiper.close();
    }

    public static void cancelUpdate(SortedArrayList<Activity> activityArray, String activityName, Integer ticketsCancelled) {
        for (int i = 0; i < activityArray.size(); i++) {

            if (activityArray.get(i).getActivityName().toLowerCase().equals(activityName)) {
                Integer oldTicketNo = activityArray.get(i).getTicketNo();
                Integer newTicketNo = oldTicketNo + ticketsCancelled;

                activityArray.get(i).setTickets(newTicketNo);
                System.out.println("The new number of remaining tickets for " + activityName + " is " + activityArray.get(i).getTicketNo() + " \n");
            }
        }
    }

    public static void activityPrint(SortedArrayList<Activity> activity) {
        for (int i = 0; i < activity.size(); i++) {
            String activityName = activity.get(i).getActivityName();
            Integer ticketNo = activity.get(i).getTicketNo();
            System.out.println("Name:      " + activityName);
            System.out.println("Tickets:   " + ticketNo);
            System.out.println("--------------------------------");
        }
        System.out.println("\n");
    }

    /*Method that overrides the compareTo method in the Comparable interface to compare Activity class activity names */

    @Override
    public int compareTo(Activity activity) {
        return this.getActivityName().compareTo(activity.getActivityName());
    }
}
