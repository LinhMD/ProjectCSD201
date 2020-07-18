import java.util.ArrayList;
import java.util.Random;

public class Recursion {
    ArrayList<Double>fibonacci;

    public Recursion(){
        this.fibonacci = new ArrayList<>();
        this.fibonacci.add(1D);
        this.fibonacci.add(1D);
    }

    public static double factorial(int i){
        if(i < 2) return 1;
        return i * factorial(--i);
    }

    public Double findFibonacci(int index){
        if(fibonacci.size() <= index){
            this.fibonacci.add(fibonacci.get(this.fibonacci.size() - 1) + fibonacci.get(this.fibonacci.size() - 2));
            try{
                return this.findFibonacci(index);
            }catch (StackOverflowError error){
                return this.findFibonacci(index);
            }
        }else return this.fibonacci.get(index);
    }

    public static double sum(ArrayList<Double> arr, int index){
        index--;
        if(index > -1)
            return sum(arr, index) + arr.get(index);
        return 0;
    }

    public static int max(ArrayList<Integer> arrayList, int index){
        if(index == 1) return arrayList.get(0);
        int max = max(arrayList, index - 1);
        return max > arrayList.get(index - 1) ? max : arrayList.get(index - 1);
    }
    public static int min(ArrayList<Integer> arrayList, int index){
        if(index == 1) return arrayList.get(0);
        int min = min(arrayList, index - 1);
        return min < arrayList.get(index - 1) ? min : arrayList.get(index - 1);
    }

    public static String convert(int number, int base){
        if(number == 0) return "0";
        return convert(number/base, base) + Character.forDigit(number % base, base);
    }

    public static void main(String[] args) {
//        Random r = new Random();
//        System.out.println(factorial(6));
//        Recursion recursion = new Recursion();
//        ArrayList<Integer> arr = new ArrayList<>();
//        System.out.println(recursion.findFibonacci(100000));
//        for (Double aDouble : recursion.fibonacci) {
//            System.out.println(aDouble);
//        }
//        System.out.println(sum(recursion.fibonacci, recursion.fibonacci.size()));
//        for (int i = 0; i < 10; i++) arr.add(r.nextInt(1000));
//        for (Integer integer : arr) System.out.println(integer);
//        System.out.println(max(arr, arr.size()));
//        System.out.println(min(arr, arr.size()));
        System.out.println(convert(10164, 16));
    }
}
