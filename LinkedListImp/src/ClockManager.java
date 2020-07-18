import java.io.File;

public class ClockManager {

    public static void main(String[] args) {
        ClockList  clocks = new ClockList();
        do{
            System.out.println("1. Add new clock");
            System.out.println("2. Remove a clock");
            System.out.println("3. Update a clock");
            System.out.println("4. List all clock");
            System.out.println("5. Quit");
            switch (InputValidation.getAnInteger("", "", 1, 5)){
                case 1: clocks.addClock(); break;
                case 2: clocks.removeClock(); break;
                case 3: clocks.updateClock();
                case 4: clocks.printAll();
                case 5: return;
            }
        }while(true);
    }
}
