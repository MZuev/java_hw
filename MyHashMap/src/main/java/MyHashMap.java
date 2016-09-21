
public class MyHashMap {
	int sz, cap;
	public MyList[] p;
	public int mx;
	public java.util.Random random;

	public MyHashMap() {
		sz = cap = 0;
		p = null;
		mx = 0;
		random = new java.util.Random();
	}

	public void checkCap() {
		if (cap != 0)
			return;
		cap = 8 + random.nextInt(8);
		p = new MyList[cap];
		for (int i = 0; i < cap; ++i)
			p[i] = new MyList();
	}

	public void rebuild() {
		int newcap = cap * 2 + random.nextInt(cap);
		MyList[] newp = new MyList[newcap];
		for (int i = 0; i < newcap; ++i)
			newp[i] = new MyList();
		mx = 0;
		for (int i = 0; i < cap; ++i) {
			MyList.MyNode cur = p[i].head;
			for (int j = 0; j < p[i].size(); ++j, cur = cur.next) {
				int newhash = cur.hashCode() % newcap;
				if (newhash < 0)
					newhash += newcap;
				newp[newhash].add(cur.key, cur.value);
				if (mx < newp[newhash].size())
					mx = newp[newhash].size();
			}
		}
		p = newp;
		cap = newcap;
	}

	public int getHash(String s) {
		checkCap();
		int hash = s.hashCode() % cap;
		if (hash < 0)
			hash += cap;
		return hash;
	}

	public int size() {
		return sz;
	}

	public boolean contains(String key) {
		checkCap();
		return p[getHash(key)].contains(key);
	}

	public String get(String key) {
		checkCap();
		return p[getHash(key)].get(key);
	}

	public String put(String key, String value) {
		checkCap();
		MyList cur = p[getHash(key)];
		String ans = cur.put(key, value);
		if (ans == null) {
			++sz;
			if (mx < cur.size())
				mx = cur.size();
			while (mx > 7)
				rebuild();
		}
		return ans;
	}

	public String remove(String key) {
		checkCap();
		String ans = p[getHash(key)].remove(key);
		if (ans != null)
			--sz;
		return ans;
	}

	public void clear() {
		p = null;
		sz = cap = mx = 0;
	}

	public void print() {
		for (int i = 0; i < cap; ++i)
			p[i].print();
	}
}
