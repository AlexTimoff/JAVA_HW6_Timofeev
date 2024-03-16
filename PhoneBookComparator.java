import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class PhoneBookComparator implements Comparator<Map.Entry<String, Set<String>>> {
    public int compare(Map.Entry<String, Set<String>> o1, Map.Entry<String, Set<String>> o2) {
        int obj1=o1.getValue().size();
        int obj2=o2.getValue().size();
        int difference = obj2 - obj1;
        if (difference == 0) {
            // если равны
            return 0;
        }
        else if (difference < 0) {
            // obj1 > obj2
            return -1;
        }
        else {
            // obj1 < obj2
            return 1;
        }

        // Фактически можно было описать метод одной строкой.
        //  return o2.getValue().size()-o1.getValue().size();

    }
}
