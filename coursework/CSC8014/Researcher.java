public final class Researcher extends AbstractStaff{



    /**
     * Returns the names of students supervised by the researcher.
     * @return list of student name objects.
     */
     Name getStudentList();

    /**
     * Returns true if supervisor is supervising enough students, false otherwise.
     * 10 students are required in total.
     * @return true or false.
     */
    Boolean supervisingTenStudentsCheck();
}
