import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class CollectionsTest {
    public static final Function1<Integer, Integer> prodTwo = new Function1<Integer, Integer>() {
        public Integer apply(Integer x) {
            return x * 2;
        }
    };

    public static final Function2<Integer, Integer, Integer> sumTwo = new Function2<Integer, Integer, Integer>() {
        public Integer apply(Integer x, Integer y) {
            return x + y;
        }
    };

    public static final Predicate<Integer> isEven = new Predicate<Integer> () {
        public Boolean apply(Integer x) {
            return x % 2 == 0;
        }
    };

    public static final Predicate<Integer> isOdd = new Predicate<Integer> () {
        public Boolean apply(Integer x) {
            return x % 2 == 1;
        }
    };

    @Test
    public void testMap() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayList<Integer> newList = Collections.map(prodTwo, list);
        assertEquals(newList.get(0), (Integer)2);
        assertEquals(newList.get(1), (Integer)4);
        assertEquals(newList.get(2), (Integer)6);
    }

    @Test
    public void testFilter() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayList<Integer> newList = Collections.filter(isOdd, list);
        assertEquals((Integer)newList.size(), (Integer)2);
        assertEquals(newList.get(0), (Integer)1);
        assertEquals(newList.get(1), (Integer)3);
    }

    @Test
    public void testTake() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayList<Integer> newList = Collections.takeWhile(isOdd, list);
        assertEquals((Integer)newList.size(), (Integer)1);
        assertEquals(newList.get(0), (Integer)1);
        newList = Collections.takeUnless(isEven, list);
        assertEquals((Integer)newList.size(), (Integer)1);
        assertEquals(newList.get(0), (Integer)1);
    }

    @Test
    public void testFold() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Collections.foldl(sumTwo, 0, list), (Integer)6);
        assertEquals(Collections.foldr(sumTwo, 0, list), (Integer)6);
    }
}
