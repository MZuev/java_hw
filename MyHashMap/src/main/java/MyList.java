

public class MyList {
	public class MyNode {
		String key, value;
		public MyNode next;

		public MyNode() {
			next = this;
		}

		public MyNode(String k, String v) {
			next = this;
			key = k;
			value = v;
		}
	}

	public MyNode head, tail;
	int sz;

	public MyList() {
		head = tail = null;
		sz = 0;
	}

	public int size() {
		return sz;
	}

	public void add(String key, String value) {
		if (sz == 0) {
			head = tail = new MyNode(key, value);
		} else {
			tail.next = new MyNode(key, value);
			tail = tail.next;
			tail.next = head;
		}
		sz++;
	}

	public MyNode find(String key) {
		MyNode cur = head;
		for (int i = 0; i < sz; i++, cur = cur.next)
			if (cur.key.equals(key))
				return cur;
		return null;
	}

	public boolean contains(String key) {
		return find(key) != null;
	}

	public String get(String key) {
		MyNode cur = find(key);
		if (cur == null)
			return null;
		return cur.value;
	}

	public String put(String key, String value) {
		MyNode cur = find(key);
		String old = null;
		if (cur == null) {
			add(key, value);
		} else {
			old = cur.value;
			cur.value = value;
		}
		return old;
	}

	public String remove(String key) {
		MyNode cur = find(key);
		if (cur == null)
			return null;
		sz--;
		if (sz == 0) {
			head = tail = null;
			return cur.value;
		}
		MyNode prev = tail;
		while (prev.next != cur)
			prev = prev.next;
		prev.next = cur.next;
		if (cur == head)
			head = cur.next;
		if (cur == tail)
			tail = prev;
		return cur.value;
	}

	public void clear() {
		sz = 0;
		tail = head = null;
		return;
	}

	public void print() {
		MyNode cur = head;
		for (int i = 0; i < sz; i++, cur = cur.next)
			System.out.println(cur.value);
		return;
	}
}
