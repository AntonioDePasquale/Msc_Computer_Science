package uk.ac.ncl.CSC8014.StaffManagerClasses;

public final class Module {

    private final String moduleCode;
    private final String moduleName;
    private final Integer semester;
    private final Integer credits;

    /**
     * Constructor for the uk.ac.ncl.CSC8014_AntonioDePasquale.Module class
     * takes in the following parameters and sets them to variables.
     */

    public Module(String moduleCode, String moduleName, Integer semester, Integer credits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.semester = semester;
        this.credits = credits;
    }

    /**
     * Getter for the credits of the uk.ac.ncl.CSC8014_AntonioDePasquale.Module
     * @return the credits variable
     */
    public Integer getCredits() {
        return this.credits;
    }

    /**
     * Getter for the moduleCode of the uk.ac.ncl.CSC8014_AntonioDePasquale.Module
     * @return the moduleCode variable
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Getter for the moduleName of the uk.ac.ncl.CSC8014_AntonioDePasquale.Module
     * @return the moduleName variable
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Getter for the semester of the uk.ac.ncl.CSC8014_AntonioDePasquale.Module
     * @return the semester variable
     */
    public Integer getSemester() {
        return semester;
    }

    /**
     * ValueOf function that takes a module and concatenates its variables into a string.
     * @return the module variables as a full string.
     */
    public static String valueOf(Module module) {
         String moduleCode = module.getModuleCode();
         String moduleName = module.getModuleName();
         String semester = String.valueOf(module.getSemester());
         String credits = String.valueOf(module.getCredits());

        return moduleCode + ", " + moduleName + ", " + semester + ", " + credits;
    }
}
