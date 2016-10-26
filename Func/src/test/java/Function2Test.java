import static org.junit.Assert.*;
import org.junit.Test;

public class Function2Test {
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

    public static final Function2<Integer, Integer, Integer> multTwo = new Function2<Integer, Integer, Integer>() {
        public Integer apply(Integer x, Integer y) {
            return x * y;
        }
    };

    @Test
    public void testCompose() {
        assertEquals(sumTwo.apply(1, 2), (Integer)3);
        assertEquals(multTwo.apply(2, 3), (Integer)6);
        assertEquals(sumTwo.compose(prodTwo).apply(3, 4), (Integer)14);
        assertEquals(multTwo.compose(prodTwo).apply(3, 4), (Integer)24);
    }

    @Test
    public void testBind() {
        assertEquals(sumTwo.bind1(1).apply(2), sumTwo.apply(1, 2));
        assertEquals(multTwo.bind2(3).apply(2), multTwo.apply(2, 3));
    }


}
