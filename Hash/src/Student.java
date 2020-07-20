public class Student implements Comparable<Student>{
	String code = "", name = "";
	int mark = 0;

	public Student (){
	}

	public Student(String code, String name, int mark) {
		this.code = code;
		this.name = name;
		this.mark = mark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public void update(){
		String newName = InputValidation.getString("Input new Student Name", "Name can not be empty");
		int newMark = InputValidation.getAnInteger("Input Student mart", "mark invalid", 0, Integer.MAX_VALUE);
		this.setName(newName);
		this.setMark(newMark);
	}

	public static Student getInstance(String code){
		String name = InputValidation.getString("Input new Student Name", "Name can not be empty");
		int mark = InputValidation.getAnInteger("Input Student mart", "mark invalid", 0, Integer.MAX_VALUE);
		return new Student(code, name, mark);
	}

	@Override
	public int compareTo(Student o) {
		return this.getCode().compareTo(o.getCode());
	}

	@Override
	public String toString() {
		return code +"," + name + "," + mark;
	}
}
