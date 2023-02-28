import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Calendar;
import java.util.Date;


public class StaffManager {

	//you can add attributes and other methods if needed. 
	//you can throw an exception if needed

	private final Map<StaffID, Staff> staffMap = new HashMap<>();
	private static final Set<Name> studentNameSet = new HashSet<Name>();
	private static final Set<Module> moduleSet = new HashSet<Module>();

	public static void main(String[] args) {

		readInModules("CSC8014/modules.TXT");
		readInStudents("CSC8014/students.TXT");
	}

	public static Set<Module> readInModules(String path) {
		//add your code here. Do NOT change the method signature

		try {
			Scanner scan = new Scanner(new File(path));  //reads csv file location//
			while (scan.hasNextLine()) {                                            //while there is a nextline assign it to line//
				String line = scan.nextLine();
				if (line.isEmpty()) {                                                 //if the line is empty continue out of while loop//
					continue;
				}
				String[] moduleAttributes = line.split(",");                           //splits each line into an array of attributes//

				String moduleCode = moduleAttributes[0].trim();               //parses index[0] to int, assigns own variable//
				String moduleName = moduleAttributes[1].trim();                                //index[1] is already string, assigns own variable//
				Integer semester = Integer.parseInt(moduleAttributes[2].trim());             //parses index[2] to int, assigns own variable//
				Integer credits = Integer.parseInt(moduleAttributes[3].trim());           //parses index[3] to double, assigns own variable//

				//individual attributes can now be passed into the Exhibit constructor to create an exhibit object//
				Module tempModule = new Module(moduleCode, moduleName, semester, credits);
				moduleSet.add(tempModule);               //adds the newly created exhibit object to the arraylist in museum//
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return moduleSet;
	}

	public static Set<Name> readInStudents(String path) {
		//add your code here. Do NOT change the method signature

		try {
			Scanner scan = new Scanner(new File(path));  //reads csv file location//
			while (scan.hasNextLine()) {                                            //while there is a nextline assign it to line//
				String line = scan.nextLine();
				if (line.isEmpty()) {                                                 //if the line is empty continue out of while loop//
					continue;
				}
				String[] nameAttributes = line.split(" ");                           //splits each line into an array of attributes//

				String firstName = nameAttributes[0].trim();               //parses index[0] to int, assigns own variable//
				String secondName = nameAttributes[1].trim();                                //index[1] is already string, assigns own variable//

				//individual attributes can now be passed into the Exhibit constructor to create an exhibit object//
				Name studentName = new Name(firstName, secondName);
				studentNameSet.add(studentName);               //adds the newly created exhibit object to the arraylist in museum//
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return studentNameSet;
	}

	public int noOfStaff(String type) {
		//add your code here. Do NOT change the method signature
		int count = 0;
		String staffType = null;

		if (type.equalsIgnoreCase("lecturer")) {
			staffType = "lecturer";
		} else if (type.equalsIgnoreCase("researcher")) {
			staffType = "researcher";
		} else {
			throw new IllegalArgumentException("\"Enter either \"lecturer\" or \"researcher\"");
		}

		for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
			Integer mapSize = staffMap.size();

			if (entry.getValue().getStaffType().equalsIgnoreCase(staffType)) {
				count++;
			}
		}
		return count;
	}

	public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {
		//add your code here. Do NOT change the method signature
		for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
			if (entry.getKey().toString().equalsIgnoreCase(id.toString())) {

				String type = entry.getValue().getStaffType();

				if (type.equalsIgnoreCase("lecturer")) {
					Lecturer.addModuleList(modules);
					return true;
				} else if (type.equalsIgnoreCase("researcher")) {
					Researcher.addStudentNames(students);
					return true;
				}
			}
		}
		return false;
	}

		public Staff employStaff (String firstName, String lastName, Date dob, String staffType, String employmentStatus)
		{
			//add your code here. Do NOT change the method signature
			Name staffName = new Name(firstName, lastName);
			SmartCard staffSmartCard = SmartCard.getInstance(staffName, dob);
			Staff staffMember = AbstractStaff.getInstance(employmentStatus, staffType, dob, staffName, staffSmartCard);

			if (calculateAge(dob) >= 22 && calculateAge(dob) <= 67) {
				for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
					if (entry.getValue().getSmartCard().compareTo(staffSmartCard) != 0) {
						StaffID id = staffMember.getStaffID();
						staffMap.put(id, staffMember);
					} else {
						throw new IllegalArgumentException("A staff member with this name of " + staffType + "already exists");
					}
				}
			} else {
				throw new IllegalArgumentException("A staff member must be at least 22 and at most 67");
			}
			return staffMember;
		}
		public Collection<Staff> getAllStaff () {
			//add your code here. Do NOT change the method signature
			return staffMap.values();
		}


		public void terminateStaff (StaffID id){
			//add your code here. Do NOT change the method signature
			for (Map.Entry<StaffID, Staff> entry : staffMap.entrySet()) {
				if (entry.getKey().toString().equalsIgnoreCase(id.toString())) {
					staffMap.remove(id);
				}
			}
		}

		public static Calendar dateToCalendar (Date date){
			Calendar newCal = Calendar.getInstance();
			newCal.setTime(date);
			return newCal;
		}

		public Integer calculateAge(Date dob) {
			int birthYear = dateToCalendar(dob).get(Calendar.YEAR);
			int birthMonth = dateToCalendar(dob).get(Calendar.MONTH);
			int birthDay = dateToCalendar(dob).get(Calendar.DAY_OF_YEAR);
			Calendar currentDate = Calendar.getInstance();

			currentDate.add(Calendar.YEAR, -birthYear);
			currentDate.add(Calendar.MONTH, -birthMonth);
			currentDate.add(Calendar.DAY_OF_YEAR, -birthDay);

			return currentDate.get(Calendar.YEAR);
		}


	}

