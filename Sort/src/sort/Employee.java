package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Vector;

public class Employee implements Comparable<Employee> {
	public String id = "", name = "";
	public int salary = 0;

	public Employee(){
	}

	public Employee(String id, String name, int salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return salary == employee.salary &&
				id.equals(employee.id) &&
				name.equals(employee.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, salary);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", salary=" + salary +
				'}';
	}

	@Override
	public int compareTo(Employee o) {
		return this.id.compareTo(o.id);
	}

	public static Comparator<Employee> BY_NAME = Comparator.comparing(Employee::getName);
	public static Comparator<Employee> BY_SALARY = Comparator.comparing(Employee::getSalary);

	public static void main(String[] args) {
		Vector<Employee> employees = new Vector<>();
		employees.add(new Employee("id003", "linh", 2000));
		employees.add(new Employee("id001", "hoang", 1000));
		employees.add(new Employee("id002", "nam", 3000));
		employees.sort(Employee::compareTo);
		System.out.println(employees);
		employees.sort(BY_NAME);
		System.out.println(employees);
		employees.sort(BY_SALARY);
		System.out.println(employees);
	}
}
