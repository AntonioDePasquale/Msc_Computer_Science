import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class StaffID {
    /*
    staff ID's need to be unique use factory methods
     */
    private final String fullID;

    private static final Map<String, StaffID> STAFFIDS = new HashMap<String, StaffID>();

    private StaffID(String fullID) {

        this.fullID = fullID;
    }

    public static StaffID getInstance() {

        Random r = new Random();
        char randomChar = (char)(r.nextInt(26) + 'a');
        int randomThreeNumbers = r.nextInt(900) + 100;
        String fullID = String.valueOf(randomChar + randomThreeNumbers);

        StaffID id = STAFFIDS.get(fullID);
        if (id == null) {
            id = new StaffID(fullID);
            STAFFIDS.put(fullID, id);
        } else {
            getInstance();
        }
        return id;
    }

    public String toString() {
        return fullID;
    }
}
