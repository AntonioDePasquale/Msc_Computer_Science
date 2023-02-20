import java.util.Date;

public final class SmartCard {

    private final Name staffName;

    private final DateObj dateOfBirth;

    private final String smartCardNumber;

    private final DateObj dateIssued;


    public SmartCard(Name staffName, String smartCardNumber, DateObj dateIssued, DateObj dateOfBirth) {
        this.staffName = staffName;
        this.smartCardNumber = smartCardNumber;
        this.dateIssued = dateIssued;
        this.dateOfBirth = dateOfBirth;
    }
}
