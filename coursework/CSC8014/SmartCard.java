import java.util.Calendar;
import java.util.Date;

public final class SmartCard {

    private final Name staffName;

    private final Date dateOfBirth;

    private final SmartCardNumber smartCardNumber;

    private final Date dateIssued;

    private Date expiryDate;


    private SmartCard(Name staffName, SmartCardNumber smartCardNumber, Date dateIssued, Date dateOfBirth) {
        this.staffName = staffName;
        this.smartCardNumber = smartCardNumber;
        this.dateIssued = dateIssued;
        this.dateOfBirth = dateOfBirth;
    }

    public static SmartCard getInstance(Name staffName, Date dateOfBirth) {

        Calendar newCal = Calendar.getInstance();
        Date issuedDate = newCal.getTime();

        SmartCardNumber smartCardNumber = SmartCardNumber.getInstance(staffName);

        return new SmartCard(staffName, smartCardNumber, issuedDate, dateOfBirth);
    }

    public String getStaffName() {
        return staffName.getFullName();
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSmartCardNumber() {
        return smartCardNumber.getFullCardNumber();
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    private void setExpiryDate(AbstractStaff staff) {
        Calendar expiryCal = StaffManager.dateToCalendar(getDateIssued());

        if (staff.getStaffEmploymentStatus().equals("fixed-term")) {
            expiryCal.add(Calendar.YEAR, 2);
            expiryDate = expiryCal.getTime();
        } else if (staff.getStaffEmploymentStatus().equals("permanent")) {
            expiryCal.add(Calendar.YEAR, 10);
            expiryDate = expiryCal.getTime();
        }
    }

    public Date getExpiryDate() {
        if (expiryDate == null) {
            throw new IllegalArgumentException("expiryDate is null and has not been set");
        } else {
            return expiryDate;
        }
    }
}
