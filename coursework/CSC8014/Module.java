import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Module {

    private final String moduleCode;
    private final String moduleName;
    private final Integer semester;
    private final Integer credits;

    public Module(String moduleCode, String moduleName, Integer semester, Integer credits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.semester = semester;
        this.credits = credits;
    }

    public Integer getCredits() {
        return this.credits;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Integer getSemester() {
        return semester;
    }
}
