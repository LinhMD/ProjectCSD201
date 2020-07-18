import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class nah {
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Testing t = new Testing("val1", false);
		Class<? extends Testing> tClass = t.getClass();

		Method gs1Method = tClass.getMethod("getString1");
		String str1 = (String) gs1Method.invoke(t, new Object[] {});
		System.out.println("getString1 returned: " + str1);

		Method ss1Method = tClass.getMethod("setString1", String.class);
		System.out.println("calling setString1 with 'val2'");
		ss1Method.invoke(t, "val2");

		str1 = (String) gs1Method.invoke(t, new Object[] {});
		System.out.println("getString1 returned: " + str1);
	}
}
