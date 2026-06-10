package Java.mohamedproject.util;



import Java.mohamedproject.Entity.Order;
import java.util.Comparator;

public class SortByValue implements Comparator<Order> {

    public int compare(Order obj1 , Order obj2){
        return  obj2.getValue() - obj1.getValue();
    }
}
