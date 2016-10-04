
import org.junit.Test;

import static org.junit.Assert.*;


public class MyListTest {
	java.util.Random random = new java.util.Random();
	String getRandomString() {
		return Integer.toString(random.nextInt());
	}
	
	String[][] getRandomArray(int n) {
		String[][] ar = new String[n][2];
		for (int i = 0; i < n; ++i) {
			ar[i][0] = getRandomString();
			ar[i][1] = getRandomString();
		}
		return ar;
	}
	
	@Test
	public void testSizeAndAdd() {
		MyList list = new MyList();
		int n = 100;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			list.put(ar[i][0], ar[i][1]);
		}
		assertEquals(list.size(), n);
		for (int i = 0; i < n / 2; i++) {
			list.remove(ar[i][0]);
		}
		assertEquals(list.size(), n - n / 2);
	}
	
	@Test
	public void testPut() {
		MyList list = new MyList();
		int n = 3;
		String[][] ar = getRandomArray(n);
		assertEquals(list.put(ar[0][0], ar[0][1]), null);
		list.put(ar[1][0], ar[1][1]);
		list.put(ar[2][0], ar[2][1]);
		assertEquals(list.put(ar[1][0], "a"), ar[1][1]);
	}
	
	@Test
	public void testContains() {
		MyList list = new MyList();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			list.put(ar[i][0], ar[i][1]);
		}
		for (int i = n - 1; i >= 0; i--) {
			assertTrue(list.contains(ar[i][0]));
		}
		for (int i = 0; i < 10; i++) {
			String curTestString = getRandomString();
			boolean exist = false;
			for (int j = 0; j < n; j++) {
				if (ar[j][0].equals(curTestString)) {
					exist = true;
					break;
				}
			}
			assertEquals(list.contains(curTestString), exist);
		}
		assertFalse(list.contains("a"));
	}
	
	@Test
	public void testGet() {
		MyList list = new MyList();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			list.put(ar[i][0], ar[i][1]);
		}
		for (int i = n - 1; i >= 0; i--) {
			assertEquals(list.get(ar[i][0]), ar[i][1]);
		}
		for (int i = 0; i < 10; i++) {
			String curTestKey = getRandomString();
			String value = null;
			for (int j = 0; j < n; j++) {
				if (ar[j][0].equals(curTestKey)) {
					value = ar[j][1];
				}
			}
			assertEquals(list.get(curTestKey), value);
		}
		assertEquals(list.get("a"), null);
	}
	
	@Test
	public void testRemove() {
		MyList list = new MyList();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			list.put(ar[i][0], ar[i][1]);
		}
		for (int i = n - 1;  i >= n / 2; i--) {
			String curVal = ar[i][1];
			for (int j = i + 1; j < n; j++) {
				if (ar[i][0].equals(ar[j][0])) {
					curVal = null;
					break;
				}
			}
			assertEquals(list.remove(ar[i][0]), curVal);
		}
		assertEquals(list.remove("a"), null);
	}
	
	@Test
	public void testClear() {
		MyList list = new MyList();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			list.put(ar[i][0], ar[i][1]);
		}
		list.clear();
		assertEquals(list.size(), 0);
		for (int i = 0; i < n; i++) {
			assertFalse(list.contains(ar[i][0]));
		}
	}
}
	
