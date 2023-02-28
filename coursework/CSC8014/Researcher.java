import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class Researcher extends AbstractStaff {

    private static final Set<Name> studentNameSet = new HashSet<>();

    public Researcher(SmartCard card, String employmentStatus, String staffType, StaffID id) {
        super(card, employmentStatus, staffType, id);
    }

    /**
     * Implementation of getStaffType outlined in the Staff interface.
     * Returns the Staff type.
     * a Staff can be either a Lecturer or a Researcher
     * @return a string (Lecturer or Researcher)
     */

    /**
     * Returns the names of students supervised by the researcher.
     *
     * @return Set of student name Strings.
     */
    Set<Name> getStudentNameSet() {
        return studentNameSet;
    }

    public Set<Name> addNameList(Set<Name> names) {
        studentNameSet.addAll(names);
        return studentNameSet;
    }

    /**
     * Returns true if supervisor is supervising enough students, false otherwise.
     * 10 students are required in total.
     *
     * @return true or false.
     */
    static Boolean supervisingTenStudentsCheck() {
        if (studentNameSet.size() >= 10) {
            return true;
        } else {
            return false;
        }
    }

    public static void addStudentNames(Set<Name> studentNames) {
        Iterator<Name> studentNamesIterator = studentNames.iterator();

        while (studentNamesIterator.hasNext()) {
            Name currentName = studentNamesIterator.next();
            if (!supervisingTenStudentsCheck()) {
                studentNameSet.add(currentName);
            }
        }
    }
}
