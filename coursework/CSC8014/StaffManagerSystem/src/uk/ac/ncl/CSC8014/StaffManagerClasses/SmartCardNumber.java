package uk.ac.ncl.CSC8014.StaffManagerClasses;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public final class SmartCardNumber {

    private final String nameInitials, fullCardNumber;
    private static Integer serialNumber = 10;
    private static final Map<String, SmartCardNumber> SMARTCARDNUMS = new HashMap<String, SmartCardNumber>();

    /**
     * Constructor for uk.ac.ncl.CSC8014_AntonioDePasquale.SmartCardNumber which takes a uk.ac.ncl.CSC8014_AntonioDePasquale.Name obj and fullCardNumber as parameters.
     * sets variables to appropriate parameters.
     * The serialNumber initialised as 10 is incremented by 1 with the serialNumberIncrement method.
     * As serial number is static each time an instance is created it will be unique to that instance.
     * constructor is private, only called in getInstance method.
     */
    private SmartCardNumber(Name nameObj, String fullCardNumber) {

        this.nameInitials = createInitialPrefix(nameObj);
        this.fullCardNumber = fullCardNumber;
        serialNumber = serialNumberIncrement();
    }

    /**
     * GetInstance factory method for creating instance of uk.ac.ncl.CSC8014_AntonioDePasquale.SmartCardNumber to ensure uniqueness.
     * The full card number is created in the method using the name initials, serial number and issue year.
     * @return the created uk.ac.ncl.CSC8014_AntonioDePasquale.SmartCardNumber instance if it doesn't currently exist in the static Map SMARTCARDNUMS.
     */
    public static SmartCardNumber getInstance(Name nameObj) {
        Calendar newCal = Calendar.getInstance();
        Integer issueYear = newCal.get(Calendar.YEAR);

        String fullCardNumber = createInitialPrefix(nameObj) + "-" + serialNumberIncrement() + "-" + issueYear;
        SmartCardNumber num = SMARTCARDNUMS.get(fullCardNumber);
        if (num == null) {
            num = new SmartCardNumber(nameObj, fullCardNumber);
            SMARTCARDNUMS.put(fullCardNumber, num);
        }
        return num;
    }

    /**
     * creates the initial prefix to be used in creation of the full card number.
     * @return the name initials as a String in upper case.
     */
    private static String createInitialPrefix(Name nameObj) {
        String firstName = nameObj.getFirstName();
        String lastName = nameObj.getLastName();

        return String.valueOf(firstName.charAt(0) + lastName.charAt(0)).toUpperCase();
    }

    /**
     * function which increments the serialNumber static variable which is shared by the class.
     * returns the serialnumber + 1.
     * this function is called when creating a new smartCardNumber.
     */
    public static Integer serialNumberIncrement() {
        return serialNumber++;
    }

    /**
     * Getter for the fullCardNumber of the uk.ac.ncl.CSC8014_AntonioDePasquale.SmartCardNumber instance
     * @return the fullCardNumber variable
     */
    public String getFullCardNumber() {
        return fullCardNumber;
    }

    /**
     * Getter for the name initials of the uk.ac.ncl.CSC8014_AntonioDePasquale.SmartCardNumber instance
     * @return the nameInitials variable
     */
    public String getNameInitials() {
        return nameInitials;
    }
}
