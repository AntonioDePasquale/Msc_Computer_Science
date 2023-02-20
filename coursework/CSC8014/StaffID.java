import java.util.HashMap;
import java.util.Map;

public final class StaffID {
    /*
    staff ID's need to be unique use factory methods
     */
    private final String letter;
    private final Integer idNumber;
    private final String fullID;

    private static final Map<String, StaffID> STAFFIDS = new HashMap<String, StaffID>();

    private StaffID(String letter, Integer idNumber, String fullID) {
        this.letter = letter;
        this.idNumber = idNumber;
        this.fullID = fullID;
    }

    public static StaffID getInstance(String letter, Integer idNumber) {
        String fullID = letter + idNumber;
        StaffID id = STAFFIDS.get(fullID);
        if (id == null) {
            id = new StaffID(letter, idNumber, fullID);
            STAFFIDS.put(fullID, id);
        }
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public String toString() {
        return fullID;
    }
}
