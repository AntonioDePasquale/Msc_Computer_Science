import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public final class SmartCardNumber {

    private final String nameInitials, fullCardNumber;
    private static Integer serialNumber = 10;

    private static final Map<String, SmartCardNumber> SMARTCARDNUMS = new HashMap<String, SmartCardNumber>();

    private SmartCardNumber(Name nameObj, String fullCardNumber) {
        /*
    smart card numbers must be unique, use factory methods to create
     */
        this.nameInitials = createInitialPrefix(nameObj);
        this.fullCardNumber = fullCardNumber;
        serialNumber = serialNumberIncrement();
    }

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
    
    private static String createInitialPrefix(Name nameObj) {
        String firstName = nameObj.getFirstName();
        String lastName = nameObj.getLastName();

        return String.valueOf(firstName.charAt(0) + lastName.charAt(0)).toUpperCase();
    }

    public static Integer serialNumberIncrement() {
        return serialNumber++;
    }

    public String getFullCardNumber() {
        return fullCardNumber;
    }

    public String getNameInitials() {
        return nameInitials;
    }
}
