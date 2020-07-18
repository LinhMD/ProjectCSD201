import java.util.LinkedList;

public class ClockList extends LinkedList<Clock> {
    public void addClock(){
        if(this.add(new Clock().input()))
            System.out.println("add successfully");
        else
            System.out.println("Added failed");
    }
    private int search(String id){
        return this.indexOf(new Clock(id));
    }
    public void removeClock(){
        String id = InputValidation.getString("input id", "");
        int pos = this.search(id);
        if(pos < 0){
            System.out.println("not found");
        }else {
            this.remove(pos);
            System.out.println("Clock " + id + " was delete");
        }
    }
    public void updateClock(){
        String id = InputValidation.getString("input id", "");
        int pos = this.search(id);
        if(pos < 0)
            System.out.println("not found");
        else{
            this.get(pos).input();
            System.out.println("Clock " + id + " was updated");
        }
    }
    public void printAll(){
        for (Clock clock : this) {
            System.out.println(clock);
        }
    }


}
