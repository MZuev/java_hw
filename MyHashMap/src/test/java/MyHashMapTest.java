
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapTest {
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
	public void testSizeAndPut() {
		MyHashMap map = new MyHashMap();
		int n = 100;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			map.put(ar[i][0], ar[i][1]);
		}
		assertEquals(map.size(), n);
		for (int i = 0; i < n / 2; i++) {
			map.remove(ar[i][0]);
		}
		assertEquals(map.size(), n - n / 2);
	}
	
	@Test
	public void testPut() {
		MyHashMap map = new MyHashMap();
		int n = 3;
		String[][] ar = getRandomArray(n);
		assertEquals(map.put(ar[0][0], ar[0][1]), null);
		map.put(ar[1][0], ar[1][1]);
		map.put(ar[2][0], ar[2][1]);
		assertEquals(map.put(ar[1][0], "a"), ar[1][1]);
	}
	
	@Test
	public void testContains() {
		MyHashMap map = new MyHashMap();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			map.put(ar[i][0], ar[i][1]);
		}
		for (int i = n - 1; i >= 0; i--) {
			assertTrue(map.contains(ar[i][0]));
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
			assertEquals(map.contains(curTestString), exist);
		}
		assertFalse(map.contains("a"));
	}
	
	@Test
	public void testGet() {
		MyHashMap map = new MyHashMap();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			map.put(ar[i][0], ar[i][1]);
		}
		for (int i = n - 1; i >= 0; i--) {
			assertEquals(map.get(ar[i][0]), ar[i][1]);
		}
		for (int i = 0; i < 10; i++) {
			String curTestKey = getRandomString();
			String value = null;
			for (int j = 0; j < n; j++) {
				if (ar[j][0].equals(curTestKey)) {
					value = ar[j][1];
				}
			}
			assertEquals(map.get(curTestKey), value);
		}
		assertEquals(map.get("a"), null);
	}
	
	@Test
	public void testRemove() {
		MyHashMap map = new MyHashMap();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			map.put(ar[i][0], ar[i][1]);
		}
		for (int i = n - 1;  i >= n / 2; i--) {
			String curVal = ar[i][1];
			for (int j = i + 1; j < n; j++) {
				if (ar[i][0].equals(ar[j][0])) {
					curVal = null;
					break;
				}
			}
			assertEquals(map.remove(ar[i][0]), curVal);
		}
		assertEquals(map.remove("a"), null);
	}
	
	@Test
	public void testClear() {
		MyHashMap map = new MyHashMap();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; i++) {
			map.put(ar[i][0], ar[i][1]);
		}
		map.clear();
		assertEquals(map.size(), 0);
		for (int i = 0; i < n; i++) {
			assertFalse(map.contains(ar[i][0]));
		}
	}
	
	@Test
	public void testAll() {
		MyHashMap map = new MyHashMap();
		int n = 10;
		String[][] ar = getRandomArray(n);
		for (int i = 0; i < n; ++i) {
			map.put(ar[i][0], ar[i][1]);
		}
		assertEquals(map.size(), n);
		for (int i = 0; i < n; i++) {
			assertTrue(map.contains(ar[i][0]));
			assertEquals(map.get(ar[i][0]), ar[i][1]);
		}
		boolean[] flagRemove = new boolean[n];
		int sz = n;
		for (int i = 0; i < n; i++) {
			if (random.nextBoolean()) {
				flagRemove[i] = true;
				map.remove(ar[i][0]);
				sz--;
			}
		}
		assertEquals(map.size(), sz);
		for (int i = 0; i < n; i++) {
			assertTrue(map.contains(ar[i][0]) ^ flagRemove[i]);
		}

	}
}
