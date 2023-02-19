import java.util.Map;

public final class Lecturer extends AbstractStaff {

    private final Module module;

    private final String staffType;

    Lecturer(SmartCard card, StaffID id, String fixedOrContract, Module taughtModules) {
        super(card, id, fixedOrContract);
        this.module = taughtModules;
        this.staffType = "Lecturer";
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

    /**
     * Returns the Lecturer module list.
     * Module consists of a name, module code, a semester and number of credits.
     * @return module object list.
     */
    public Module getModuleList() {
        return this.module.moduleNameList;
    }

    /**
     * Returns true if lecturer is teaching enough credits.
     * true if 40 credits in both semester or false otherwise.
     * @return true or false.
     */
    Boolean lecturerModuleCreditCheck() {
        return this.module.totalModuleCredits;
    }
}
