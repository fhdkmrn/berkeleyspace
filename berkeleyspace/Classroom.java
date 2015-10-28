public class Classroom {
	public boolean[][] schedule; 
	public String classes;
	public Classroom(String class_number) {
		classes = class_number;

		schedule = new boolean[5][28];
	}

}