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

    public String moduleNameList() {
        ;
    }

    public String totalModuleCredits() {
        ;
    }
}
