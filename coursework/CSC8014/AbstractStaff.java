import java.util.Date;

public abstract class AbstractStaff implements Staff{

    private final StaffID id;
    private final SmartCard card;
    private final String employmentStatus;
    private final String staffType;

    AbstractStaff(SmartCard card, String employmentStatus, String staffType, StaffID id) {
        this.card = card;
        this.staffType = staffType;
        this.id = id;

        if (employmentStatus == null) {
            throw new IllegalArgumentException("Contract type is null");
        }
        if (employmentStatus.equalsIgnoreCase("fixed-term")) {
            this.employmentStatus = employmentStatus;
        } else if (employmentStatus.equalsIgnoreCase("permanent")) {
            this.employmentStatus = employmentStatus;
        } else {
            throw new IllegalArgumentException("\"Enter either \"fixed-term\" or \"permanent\"");
        }
    }

    public static Staff getInstance(String employmentStatus, String staffType, Date dob, Name staffName, SmartCard newCard) {

        StaffID newStaffId = StaffID.getInstance();
        Staff result;

        if (staffType == null) {
            throw new IllegalArgumentException("Staff type is null");
        }
        if (employmentStatus.equalsIgnoreCase("lecturer")) {
            result = new Lecturer(newCard,employmentStatus, staffType, newStaffId);
        } else if (employmentStatus.equalsIgnoreCase("researcher")) {
           result = new Researcher(newCard, employmentStatus, staffType, newStaffId);
        } else {
            throw new IllegalArgumentException("\"Enter either \"lecturer\" or \"researcher\"");
        }
        return result;
    }

    /**
     * Implementation of getStaffID outlined in the Staff interface.
     * Returns the staff ID.
     * All staff must have an ID
     * @return the StaffID object
     */
    public StaffID getStaffID() {
        return this.id;
    };

    /**
     * Implementation of getSmartCard outlined in the Staff interface.
     * Returns the smart card.
     * All staff must have a smart card
     * @return the SmartCard object
     */
    public SmartCard getSmartCard() {
        return this.card;
    }

    /**
     * Implementation of getStaffEmploymentStatus outlined in the Staff interface.
     * Returns the Staff employment status.
     * a Staff can be either on Permanent or fixed contract
     * @return a string (Permanent or fixed)
     */
    public String getStaffEmploymentStatus() {
        return this.employmentStatus;
    }

    /**
     * Implementation of getStaffType outlined in the Staff interface.
     * Returns the Staff type.
     * a Staff can be either a Lecturer or a Researcher
     * @return a string (Lecturer or Researcher)
     */
    public String getStaffType() {
        return this.staffType;
    }

}
