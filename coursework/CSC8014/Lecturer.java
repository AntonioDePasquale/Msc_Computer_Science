import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class Lecturer extends AbstractStaff {

    private static final Set<Module> moduleSet = new HashSet<>();

    public Lecturer(SmartCard card, String employmentStatus, String staffType, StaffID id) {
        super(card, employmentStatus, staffType, id);
    }

    /**
     * Returns the Lecturer module Set.
     * Module consists of a name, module code, a semester and number of credits.
     * @return module object Set.
     */
    public Set<Module> getModuleList() {
        return moduleSet;
    }

    public static void addModuleList(Set<Module> modules) {
        Iterator<Module> modulesIterator = modules.iterator();

        while(modulesIterator.hasNext()) {
            Module currentModule = modulesIterator.next();
            if(!lecturerModuleCreditCheck(currentModule))
                moduleSet.add(currentModule);
        }
    }


    /**
     * Returns true if lecturer is teaching enough credits.
     * true if 40 credits in both semester or false otherwise.
     * @return true or false.
     */
    Boolean lecturerModuleCreditCheck() {
        Iterator<Module> modulesIterator = moduleSet.iterator();
        Integer moduleCreditCount = 0;

        while(modulesIterator.hasNext()) {
            moduleCreditCount += modulesIterator.next().getCredits();
        }

        if (moduleCreditCount >= 40) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Overloaded method of the lecturerModuleCreditCheck above which takes a parameter
     * returns true if lecturer is teaching enough credits.
     * true if 40 credits in both semesters including the credits of the parameter module or false otherwise.
     * @return true or false.
     */
    static Boolean lecturerModuleCreditCheck(Module module) {
        Iterator<Module> modulesIterator = moduleSet.iterator();
        Integer moduleCreditCount = 0;

        while(modulesIterator.hasNext()) {
            moduleCreditCount += modulesIterator.next().getCredits();
        }

        if (moduleCreditCount + module.getCredits() > 40) {
            return true;
        } else {
            return false;
        }
    }
}
