import java.util.Set;

public final class Researcher extends AbstractStaff{

    private final Set<String> studentNameList;

    private final String staffType;

    Researcher(SmartCard card, StaffID id, String fixedOrContract, Name allNames) {
        super(card, id, fixedOrContract);
        this.studentNameList = allNames.getStudentNames();
        this.staffType = "Researcher";
    }

    /**
     * Implementation of getStaffType outlined in the Staff interface.
     * Returns the Staff type.
     * a Staff can be either a Lecturer or a Researcher
     * @return a string (Lecturer or Researcher)
     */
    public String getStaffType(){
        return staffType;
    }

    /**
     * Returns the names of students supervised by the researcher.
     * @return Set of student name Strings.
     */
     Set<String> getStudentList() {
         return studentNameList;
     }

    /**
     * Returns true if supervisor is supervising enough students, false otherwise.
     * 10 students are required in total.
     * @return true or false.
     */
    Boolean supervisingTenStudentsCheck() {
        if (studentNameList.size() >= 10) {
            return true;
        } else {
            return false;
        }
    }
}
