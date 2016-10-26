import static org.junit.Assert.*;
import org.junit.Test;

public class Function1Test {
    public static final Function1<Integer, Integer> addTwo = new Function1<Integer, Integer>() {
        public Integer apply(Integer x) {
            return x + 2;
        }
    };

    public static final Function1<Integer, Integer> prodTwo = new Function1<Integer, Integer>() {
        public Integer apply(Integer x) {
            return x * 2;
        }
    };

    @Test
    public void testCompose() {
        assertEquals(addTwo.apply(1), (Integer)3);
        assertEquals(prodTwo.apply(2), (Integer)4);
        assertEquals(addTwo.compose(prodTwo).apply(1), (Integer)6);
        assertEquals(prodTwo.compose(addTwo).apply(1), (Integer)4);
    }


}
