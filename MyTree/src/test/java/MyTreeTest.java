
import org.junit.Test;
 

import static org.junit.Assert.*;

public class MyTreeTest {
	
	@Test
	public void testSize() {
		MyTree tree = new MyTree();
		int n = 10;
		String[] ar = {"asd", "qwe", "zxc", "qaz", "wsx", "edc", "ewq", "dsa", "cxz", "qsc"};
		for (int i = 0; i < n; i++) {
			tree.add(ar[i]);
		}
		assertEquals(tree.size(), n);
		for (int i = 0; i < n / 2; i++) {
			tree.remove(ar[i]);
		}
		assertEquals(tree.size(), n - n / 2);
	}
	
	@Test
	public void testAdd() {
		MyTree tree = new MyTree();
		int n = 10;
		String[] ar = {"asd", "qwe", "zxc", "qaz", "wsx", "edc", "ewq", "dsa", "cxz", "qsc"};
		for (int i = 0; i < n; i++) {
			assertTrue(tree.add(ar[i]));
		}
		for (int i = 0; i < n; i++) {
			assertFalse(tree.add(ar[i]));
		}
	}
	
	@Test
	public void testRemove() {
		MyTree tree = new MyTree();
		int n = 10;
		String[] ar = {"asd", "qwe", "zxc", "qaz", "wsx", "edc", "ewq", "dsa", "cxz", "qsc"};
		for (int i = 0; i < n; i++) {
			tree.add(ar[i]);
		}
		for (int i = 0; i < n / 2; i++) {
			assertTrue(tree.remove(ar[i]));
		}
		for (int i = 0; i < n / 2; i++) {
			assertFalse(tree.remove(ar[i]));
		}
		assertFalse(tree.remove("QQQ"));
	}
	
	@Test
	public void testContains() {
		MyTree tree = new MyTree();
		int n = 10;
		String[] ar = {"asd", "qwe", "zxc", "qaz", "wsx", "edc", "ewq", "dsa", "cxz", "qsc"};
		for (int i = 0; i < n; i++) {
			tree.add(ar[i]);
		}
		for (int i = 0; i < n; i++) {
			assertTrue(tree.contains(ar[i]));
		}
		for (int i = 0; i < n / 2; i++) {
			tree.remove(ar[i]);
		}
		for (int i = 0; i < n / 2; i++) {
			assertFalse(tree.contains(ar[i]));
		}
		for (int i = n / 2; i < n; i++) {
			assertTrue(tree.contains(ar[i]));
		}
		assertFalse(tree.contains("QQQ"));
	}
	    
	@Test
	public void testPrefix() {
		MyTree tree = new MyTree();
		int n = 10;
		String[] ar = {"aasd", "aqwe", "azxc", "aqaz", "awsx", "bedc", "bewq", "bdsa", "bcxz", "bqsc"};
		for (int i = 0; i < n; i++) {
			tree.add(ar[i]);
		}
		assertEquals(tree.howManyStartsWithPrefix("a"), 5);
		assertEquals(tree.howManyStartsWithPrefix("b"), 5);
		for (int i = 0; i < 3; i++) {
			tree.remove(ar[i]);
		}
		assertEquals(tree.howManyStartsWithPrefix("a"), 2);
		assertEquals(tree.howManyStartsWithPrefix("ca"), 0);
		tree.add("bbqq");
		tree.add("bbee");
		tree.add("bbee");
		assertEquals(tree.howManyStartsWithPrefix("bb"), 2);
	}
	
	@Test
	public void testTypical() {
		MyTree tree = new MyTree();
		assertTrue(tree.add("AaAaA"));
		assertTrue(tree.contains("AaAaA"));
		assertEquals(tree.size(), 1);
		assertEquals(tree.howManyStartsWithPrefix("AaA"), 1);
		assertTrue(tree.remove("AaAaA"));
		assertFalse(tree.contains("AaAaA"));
		assertEquals(tree.size(), 0);
		assertEquals(tree.howManyStartsWithPrefix("AaA"), 0);
	}
	
	@Test
	public void testSpec() {
		MyTree tree = new MyTree();
		assertTrue(tree.add("aaaa"));
		assertTrue(tree.add("aaa"));
		assertTrue(tree.add("aabb"));
		assertEquals(tree.size(), 3);
		assertEquals(tree.howManyStartsWithPrefix("aa"), 3);
		assertTrue(tree.remove("aaaa"));
		assertEquals(tree.howManyStartsWithPrefix("aa"), 2);
		assertFalse(tree.add("aaa"));
		assertEquals(tree.howManyStartsWithPrefix("aaa"), 1);
		assertEquals(tree.howManyStartsWithPrefix(""), 2);
		assertTrue(tree.add(""));
		assertEquals(tree.howManyStartsWithPrefix(""), 3);
		assertTrue(tree.remove(""));
		assertEquals(tree.howManyStartsWithPrefix(""), 2);
	}
}
