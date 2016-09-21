
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyHashMapTest {
	@Test
	public void testAll() {
		MyHashMap map = new MyHashMap();
		int n = 10;
		String[][] ar = new String[n][2];
		java.util.Random random = new java.util.Random();
		for (int i = 0; i < n; ++i) {
			ar[i][0] = Integer.toString(random.nextInt());
			ar[i][1] = Integer.toString(random.nextInt());
		}
		for (int i = 0; i < n; ++i)
			map.put(ar[i][0], ar[i][1]);
		assertTrue(map.size() == n);
		for (int i = 0; i < n; ++i)
			assertTrue(map.contains(ar[i][0]) && map.get(ar[i][0]).equals(ar[i][1]));
		boolean[] flagRemove = new boolean[n];
		int sz = n;
		for (int i = 0; i < n; ++i) {
			if (random.nextBoolean()) {
				flagRemove[i] = true;
				map.remove(ar[i][0]);
				sz--;
			}
		}
		assertTrue(map.sz == sz);
		for (int i = 0; i < n; ++i)
			assertTrue(map.contains(ar[i][0]) ^ flagRemove[i]);

	}
}
