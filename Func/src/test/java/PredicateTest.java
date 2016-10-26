import static org.junit.Assert.*;
import org.junit.Test;

public class PredicateTest {

    @Test
    public void testOr(){
        assertTrue(Predicate.ALWAYS_TRUE().or
                (Predicate.ALWAYS_TRUE()).apply(1));
        assertTrue(Predicate.ALWAYS_FALSE().or
                (Predicate.ALWAYS_TRUE()).apply(1));
        assertTrue(Predicate.ALWAYS_TRUE().or
                (Predicate.ALWAYS_FALSE()).apply(1));
        assertFalse(Predicate.ALWAYS_FALSE().or
                (Predicate.ALWAYS_FALSE()).apply(1));
    }


    @Test
    public void testAnd() {
        assertTrue(Predicate.ALWAYS_TRUE().and
                (Predicate.ALWAYS_TRUE()).apply(1));
        assertFalse(Predicate.ALWAYS_FALSE().and
                (Predicate.ALWAYS_TRUE()).apply(1));
        assertFalse(Predicate.ALWAYS_TRUE().and
                (Predicate.ALWAYS_FALSE()).apply(1));
        assertFalse(Predicate.ALWAYS_FALSE().and
                (Predicate.ALWAYS_FALSE()).apply(1));
    }

    @Test
    public void testNot() {
        assertTrue(Predicate.ALWAYS_FALSE().not().apply(1));
        assertFalse(Predicate.ALWAYS_TRUE().not().apply(1));
    }

    @Test
    public void testALWAYS_FALSE() {

        assertFalse(Predicate.ALWAYS_FALSE().apply(1));
    }

    @Test
    public void testALWAYS_TRUE() {
        assertTrue(Predicate.ALWAYS_TRUE().apply(1));
    }

}
