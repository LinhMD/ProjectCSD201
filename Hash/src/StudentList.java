import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class StudentList extends HashMap<String, Student> {
	public StudentList(){
		super();
	}

	public boolean loadFromFile(String fileName){
		try{
			FileInputStream stream = new FileInputStream(fileName);
			InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while(bufferedReader.ready()){
				line = bufferedReader.readLine().trim();
				String[] split = line.split("[,]");
				Student student = new Student(split[0], split[1], Integer.parseInt(split[2]));
				this.put(student.code, student);

			}
			stream.close();
			reader.close();
			bufferedReader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveToFile(String fileName){
		try {
			FileOutputStream stream = new FileOutputStream(fileName);
			OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
			PrintWriter printWriter = new PrintWriter(writer);
			Collection<Student> values = this.values();
			for (Student student : values) {
				printWriter.println(student);
			}
			stream.close();
			writer.close();
			printWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Student search(){
		String code = InputValidation.getString("Input student code", "Student code can be empty");
		return this.get(code);
	}

	public String getNewCode(){
		String code;
		do{
			code = InputValidation.getString("Input Student code", "Student code can not be empty");
			if(this.containsKey(code))
				System.out.println("code duplicate");
		}while (this.containsKey(code));
		return code;
	}

	public void addNewStudent(){
		Student student = Student.getInstance(getNewCode());
		this.put(student.code, student);
		System.out.println("add new " + student.getName() + " student successfully");
	}

	public void deleteStudent(){
		Student student = search();
		Student remove = this.remove(student.code);
		if(remove != null)
			System.out.println("remove student " + remove.getName() + " student successfully");
	}

	public void searchStudent(){
		if(this.isEmpty())
			System.out.println("Student list is empty");
		else{
			Student student = this.search();
			if(student != null)
				System.out.println(student);
			else System.out.println("student did not existed");
		}
	}
	public void updateStudent(){
		if(this.isEmpty())
			System.out.println("Student list is empty");
		else{
			Student student = this.search();
			if(student != null)
				student.update();
			else System.out.println("student did not existed");
		}
	}

	public void print(){
		List<Student> studentList = new Vector<>(this.values());
		studentList.stream().sorted().forEach(System.out::println);
	}

	public static void main(String[] args) {
		StudentList studentList = new StudentList();
		String fileName = "E:\\Classes\\csd201\\Project\\Hash\\src\\student.txt";
		boolean isLoaded = studentList.loadFromFile(fileName);
		if(!isLoaded){
			System.out.println("something went wrong");
		}
		Menu menu = new Menu();
		menu.add("add new student");
		menu.add("search student");
		menu.add("remove student");
		menu.add("update student");
		menu.add("print list of student");
		menu.add("save to file");
		menu.add("exit");
		do{
			switch (menu.getUserChoice()){
				case 1: studentList.addNewStudent(); break;
				case 2: studentList.search(); break;
				case 3: studentList.deleteStudent(); break;
				case 4: studentList.updateStudent(); break;
				case 5: studentList.print(); break;
				case 6: studentList.saveToFile(fileName);
				case 7: System.exit(0);
			}
		}while (true);

	}
}
