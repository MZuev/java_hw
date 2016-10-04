

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

	public MyNode head = null, tail = null;
	private int sz = 0;

	public int size() {
		return sz;
	}

	private void pushBack(String key, String value) {
		if (sz == 0) {
			head = tail = new MyNode(key, value);
		} 
		else {
			tail.next = new MyNode(key, value);
			tail = tail.next;
			tail.next = head;
		}
		sz++;
	}

	private MyNode find(String key) {
		MyNode curNode = head;
		for (int i = 0; i < sz; i++, curNode = curNode.next) {
			if (curNode.key.equals(key)) {
				return curNode;
			}
		}
		return null;
	}

	public boolean contains(String key) {
		return find(key) != null;
	}

	public String get(String key) {
		MyNode curNode = find(key);
		if (curNode == null) {
			return null;
		}
		return curNode.value;
	}

	public String put(String key, String value) {
		MyNode curNode = find(key);
		String oldValue = null;
		if (curNode == null) {
			pushBack(key, value);
		} 
		else {
			oldValue = curNode.value;
			curNode.value = value;
		}
		return oldValue;
	}

	public String remove(String key) {
		MyNode curNode = find(key);
		if (curNode == null) {
			return null;
		}
		sz--;
		if (sz == 0) {
			head = tail = null;
			return curNode.value;
		}
		MyNode prevNode = tail;
		while (prevNode.next != curNode)
			prevNode = prevNode.next;
		prevNode.next = curNode.next;
		if (curNode == head)
			head = curNode.next;
		if (curNode == tail)
			tail = prevNode;
		return curNode.value;
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
