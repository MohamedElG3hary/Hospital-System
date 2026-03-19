import java.util.ArrayList;

public class Search {





    public static int binarySearch(int target, ArrayList<Integer> collections, int l, int r) {
        while (l <= r) {

            int m = (l + r) / 2;

            if (collections.get(m) == target) {
                return m;

            } else if (collections.get(m) > (target)) {
                r = m - 1;

            } else {
                l = m + 1;
            }
        }
        return -1;

    }

}
