public abstract class AbstractStaff implements Staff{

    private final StaffID id;
    private final SmartCard card;
    private final String employmentStatus;
    private final String staffType;

    AbstractStaff(SmartCard card, StaffID id, String fixedOrContract) {
        this.card = card;
        this.id = id;
        this.employmentStatus = fixedOrContract;
        this.staffType = null;
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
        return staffType;
    }

}
