public abstract class AbstractStaff implements Staff{

    /**
     * Implementation of getStaffID outlined in the Staff interface.
     * Returns the staff ID.
     * All staff must have an ID
     * @return the StaffID object
     */
    StaffID getStaffID();

    /**
     * Implementation of getSmartCard outlined in the Staff interface.
     * Returns the smart card.
     * All staff must have a smart card
     * @return the SmartCard object
     */
    SmartCard getSmartCard();

    /**
     * Implementation of getStaffEmploymentStatus outlined in the Staff interface.
     * Returns the Staff employment status.
     * a Staff can be either on Permanent or fixed contract
     * @return a string (Permanent or fixed)
     */
    String getStaffEmploymentStatus();

    /**
     * Implementation of getStaffType outlined in the Staff interface.
     * Returns the Staff type.
     * a Staff can be either a Lecturer or a Researcher
     * @return a string (Lecturer or Researcher)
     */
    String getStaffType();
}
