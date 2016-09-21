
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class MyListTest {

	@Test
	public void testAdd() {
		MyList list = new MyList();
		list.add("q", "w");
		list.add("a", "s");
		list.add("z", "x");
		assertTrue(list.sz == 3);
		assertTrue(list.head.value == "w" && list.head.next.value == "s" && list.tail.value == "x"
				&& list.head.next.next.value == "x" && list.tail.next.value == "w");
	}

	@Test
	public void testFindAndContainsAndGet() {
		MyList list = new MyList();
		list.add("a", "b");
		list.add("c", "d");
		assertTrue(list.sz == 2 && list.find("a") == list.head && list.find("c") == list.tail);
	}

	@Test
	public void testPut() {
		MyList list = new MyList();
		list.put("1", "1");
		list.put("2", "2");
		list.put("123", "asd");
		assertTrue(list.size() == 3);
		MyList.MyNode cur = list.find("1");
		assertTrue(cur != null && cur.value.equals("1"));
		cur = list.find("2");
		assertTrue(cur != null && cur.value.equals("2"));
		cur = list.find("123");
		assertTrue(cur != null && cur.value.equals("asd"));
		list.put("2", "3");
		cur = list.find("2");
		assertTrue(cur != null && cur.value.equals("3"));
	}
}
