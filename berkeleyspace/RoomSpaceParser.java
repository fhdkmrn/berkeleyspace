import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.TreeMap;

public class RoomSpaceParser {

	static TreeSet<String> class_set = new TreeSet<String>();
	static ArrayList<String> room_numbers = new ArrayList<String>();
	static ArrayList<String> times = new ArrayList<String>();
	static TreeMap<String,Classroom> class_map = new TreeMap<String,Classroom>();
	static HashMap<String,Integer> timeMap = new HashMap<String,Integer>();

	public static void Constructor() {
		timeMap.put("8A",0);
		timeMap.put("830A",1);
		timeMap.put("9A",2);
		timeMap.put("930A",3);
		timeMap.put("10A",4);
		timeMap.put("1030A",5);
		timeMap.put("11A",6);
		timeMap.put("1130A",7);
		timeMap.put("12P",8);
		timeMap.put("1230P",9);
		timeMap.put("1P",10);
		timeMap.put("130P",11);
		timeMap.put("2P",12);
		timeMap.put("230P",13);
		timeMap.put("3P",14);
		timeMap.put("330P",15);
		timeMap.put("4P",16);
		timeMap.put("430P",17);
		timeMap.put("5P",18);
		timeMap.put("530P",19);
		timeMap.put("6P",20);
		timeMap.put("630P",21);
		timeMap.put("7P",22);
		timeMap.put("730P",23);
		timeMap.put("8P",24);
		timeMap.put("830P",25);
		timeMap.put("9P",26);
		timeMap.put("930P",27);
		
	}
	public static void read(Scanner s) {

		while (s.hasNextLine()) {
			String time = s.findInLine("....... [0-9]+-[0-9]+A");
			if (time == null) {
				time = s.findInLine("....... [0-9]+-[0-9]+P");
			}
			int k = 0;
			if (time != null) {
				k = time.length() - 1;
			}
			while (time != null && time.charAt(k) != '\t' ) {
				k--;
			}
			if (time != null) {
				time = time.substring(k+1);
				times.add(time);
			}

			String room = s.findInLine("... DWINELLE");
			if (room != null) {
				int i = 0;
				while (!Character.isDigit(room.charAt(i))) {
					i++;
					room = room.substring(i);
				}
				room_numbers.add(room);
			}

			// Creates a temporary classroom 
			Classroom temp_class = null;
			// Check if the classroom is in the map, if not, then create a new classroom
			// After the classroom is created, put it into a map
			if (room != null && (!class_map.containsKey(room))) {
				Classroom new_class = new Classroom(room);
				class_map.put(room,new_class);
				//Sets temp_class to the current class
				temp_class = new_class;
				class_set.add(room);
			}

			else if (room != null) {
				temp_class = class_map.get(room);
			}
			if (time != null) {
				int pos = 0;
				String hour = "";
				String hourTwo = "";
				if (time.contains("M")) {
					hour = "";
					hourTwo = "";
					pos = 0;
					while (time.charAt(pos) != '-') {
						if (Character.isDigit(time.charAt(pos))) {
							hour += time.charAt(pos);
							pos++;
						}
						else {
							pos++;
						}
					}
					while (Character.isDigit(time.charAt(pos+1))) {
						hourTwo += time.charAt(pos+1);
						pos++;
					}
					// Gets index of time array 
					int indexHour = 0;
					int indexHourTwo = 0;
					// Adds A if class is in AM
					if (time.contains("A")) {
						indexHour = timeMap.get(hour+"A");
						indexHourTwo = timeMap.get(hourTwo+"A"); 

					}
					if (time.contains("P")) {
						if (!timeMap.containsKey(hour+"P")) {
							indexHour = timeMap.get(hour+"A");
						}
						else {
							indexHour = timeMap.get(hour+"P");
						}
						indexHourTwo = timeMap.get(hourTwo+"P"); 

					}
					for (int j = indexHour; j <= indexHourTwo; j++) {
						temp_class.schedule[0][j] = true;

					}

					
				}
				if (time.contains("Tu")) {
					hour = "";
					hourTwo = "";
					pos = 0;
					while (time.charAt(pos) != '-') {
						if (Character.isDigit(time.charAt(pos))) {
							hour += time.charAt(pos);
							pos++;
						}
						else {
							pos++;
						}
					}
					while (Character.isDigit(time.charAt(pos+1))) {
						hourTwo += time.charAt(pos+1);
						pos++;
					}
					// Gets index of time array 
					int indexHour = 0;
					int indexHourTwo = 0;
					// Adds A if class is in AM
					if (time.contains("A")) {
						indexHour = timeMap.get(hour+"A");
						indexHourTwo = timeMap.get(hourTwo+"A"); 

					}
					if (time.contains("P")) {
						if (!timeMap.containsKey(hour+"P")) {
							indexHour = timeMap.get(hour+"A");
						}
						else {
							indexHour = timeMap.get(hour+"P");
						}
						indexHourTwo = timeMap.get(hourTwo+"P"); 

					}
					for (int j = indexHour; j <= indexHourTwo; j++) {
						if (temp_class == null) {
							temp_class = new Classroom("???");
						}
						temp_class.schedule[1][j] = true;

					}
				}
				if (time.contains("Th")) {
					hour = "";
					hourTwo = "";
					pos = 0;
					while (time.charAt(pos) != '-') {
						
						if (Character.isDigit(time.charAt(pos))) {

							hour += time.charAt(pos);
							pos++;
						}
						else {
							pos++;
						}
					}
					while (Character.isDigit(time.charAt(pos+1))) {
						hourTwo += time.charAt(pos+1);
						pos++;
					}
					// Gets index of time array 
					int indexHour = 0;
					int indexHourTwo = 0;
					// Adds A if class is in AM
					if (time.contains("A")) {
						indexHour = timeMap.get(hour+"A");
						indexHourTwo = timeMap.get(hourTwo+"A"); 

					}
					if (time.contains("P")) {
						if (!timeMap.containsKey(hour+"P")) {
							indexHour = timeMap.get(hour+"A");
						}
						else {
							indexHour = timeMap.get(hour+"P");
						}
						indexHourTwo = timeMap.get(hourTwo+"P"); 

					}
					for (int j = indexHour; j <= indexHourTwo; j++) {
						temp_class.schedule[3][j] = true;

					}

				}
				if (time.contains("W")) {
					hour = "";
					hourTwo = "";
					pos = 0;
					while (time.charAt(pos) != '-') {
						if (Character.isDigit(time.charAt(pos))) {
							hour += time.charAt(pos);
							pos++;
						}
						else {
							pos++;
						}
					}
					while (Character.isDigit(time.charAt(pos+1))) {
						hourTwo += time.charAt(pos+1);
						pos++;
					}
					// Gets index of time array 
					int indexHour = 0;
					int indexHourTwo = 0;
					// Adds A if class is in AM
					if (time.contains("A")) {
						indexHour = timeMap.get(hour+"A");
						indexHourTwo = timeMap.get(hourTwo+"A"); 

					}
					if (time.contains("P")) {
						if (!timeMap.containsKey(hour+"P")) {
							indexHour = timeMap.get(hour+"A");
						}
						else {
							indexHour = timeMap.get(hour+"P");
						}
						indexHourTwo = timeMap.get(hourTwo+"P"); 

					}
					for (int j = indexHour; j <= indexHourTwo; j++) {
						temp_class.schedule[2][j] = true;

					}

				}
				if (time.contains("TWT")) {
					pos = 0;
					hour = "";
					hourTwo = "";
					while (time.charAt(pos) != '-') {
						if (Character.isDigit(time.charAt(pos))) {
							hour += time.charAt(pos);
							pos++;
						}
						else {
							pos++;
						}
					}
					while (Character.isDigit(time.charAt(pos+1))) {
						hourTwo += time.charAt(pos+1);
						pos++;
					}
					// Gets index of time array 
					int indexHour = 0;
					int indexHourTwo = 0;
					// Adds A if class is in AM
					if (time.contains("A")) {
						indexHour = timeMap.get(hour+"A");
						indexHourTwo = timeMap.get(hourTwo+"A"); 

					}
					if (time.contains("P")) {
						if (!timeMap.containsKey(hour+"P")) {
							indexHour = timeMap.get(hour+"A");
						}
						else {
							indexHour = timeMap.get(hour+"P");
						}
						indexHourTwo = timeMap.get(hourTwo+"P"); 

					}
					for (int j = indexHour; j <= indexHourTwo; j++) {
						temp_class.schedule[1][j] = true;
						temp_class.schedule[2][j] = true;
						temp_class.schedule[3][j] = true;

					}

				}
				if (time.contains("F")) {
					pos = 0;
					hour = "";
					hourTwo = "";
					while (time.charAt(pos) != '-') {
						if (Character.isDigit(time.charAt(pos))) {
							hour += time.charAt(pos);
							pos++;
						}
						else {
							pos++;
						}
					}
					while (Character.isDigit(time.charAt(pos+1))) {
						hourTwo += time.charAt(pos+1);
						pos++;
					}
					// Gets index of time array 
					int indexHour = 0;
					int indexHourTwo = 0;
					// Adds A if class is in AM
					if (time.contains("A")) {
						indexHour = timeMap.get(hour+"A");
						indexHourTwo = timeMap.get(hourTwo+"A"); 

					}
					if (time.contains("P")) {
						if (!timeMap.containsKey(hour+"P")) {
							indexHour = timeMap.get(hour+"A");
						}
						else {
							indexHour = timeMap.get(hour+"P");
						}
						indexHourTwo = timeMap.get(hourTwo+"P"); 

					}
					for (int j = indexHour; j <= indexHourTwo; j++) {
						temp_class.schedule[4][j] = true;

					}

				}
			}
			s.nextLine();

		}
	}

	
	public static void main(String[] args) {
		



		File dwinelle = new File("./dwinelle.txt");
		try {
			Scanner sc = new Scanner(dwinelle);
			RoomSpaceParser.Constructor();
			read(sc);
			System.out.println(class_map);
			// Testing 
			for (String i: class_map.keySet()) {
				System.out.println((class_map.get(i).schedule)[0][5]);
			}

		}
		catch (FileNotFoundException e) {



		}

	



	}
}