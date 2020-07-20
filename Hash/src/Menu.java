import java.util.ArrayList;

public class Menu extends ArrayList<String> {
	public Menu(){
		super();
	}
	int getUserChoice(){
		for (int i = 0; i < this.size(); i++) {
			System.out.println((i + 1) + "-" + this.get(i));
		}
		return InputValidation.getAnInteger("input your option: ", "option invalid", 0, this.size());
	}

}
