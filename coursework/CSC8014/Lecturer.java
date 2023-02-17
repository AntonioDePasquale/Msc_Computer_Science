public final class Lecturer extends AbstractStaff{


    /**
     * Returns the Lecturer module list.
     * Module consists of a name, module code, a semester and number of credits.
     * @return module object list.
     */
    Module getModuleList();

    /**
     * Returns true if lecturer is teaching enough credits.
     * true if 40 credits in both semester or false otherwise.
     * @return true or false.
     */
    Boolean lecturerModuleCreditCheck();
}
