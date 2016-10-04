
public class MyHashMap {
	private int sz, cap;
	private  MyList[] arrayBucket;
	private  int curMaxBucketSize;
	final private java.util.Random random;
	final static private int maxBucketSize = 7;

	public MyHashMap() {
		random = new java.util.Random();
		sz = 0;
		cap = 8 + random.nextInt(8); // get random module
		arrayBucket = new MyList[cap];
		for (int i = 0; i < cap; i++) {
			arrayBucket[i] = new MyList();
		}
	}

	private int getHash(String s) {
		int hash = s.hashCode() % cap;
		if (hash < 0) {
			hash += cap;
		}
		return hash;
	}
	
	private void rebuild() {
		int oldCap = cap;
		cap = cap * 2 + random.nextInt(cap); //get random module
		MyList[] newArray = new MyList[cap];
		for (int i = 0; i < cap; i++) {
			newArray[i] = new MyList();
		}
		curMaxBucketSize = 0;
		for (int i = 0; i < oldCap; i++) {
			MyList.MyNode curNode = arrayBucket[i].head;
			for (int j = 0; j < arrayBucket[i].size(); j++, curNode = curNode.next) {
				int newHash = getHash(curNode.key);
				newArray[newHash].put(curNode.key, curNode.value);
				if (curMaxBucketSize < newArray[newHash].size()) {
					curMaxBucketSize = newArray[newHash].size();
				}
			}
		}
		arrayBucket = newArray;
	}

	public int size() {
		return sz;
	}

	public boolean contains(String key) {
		return arrayBucket[getHash(key)].contains(key);
	}

	public String get(String key) {
		return arrayBucket[getHash(key)].get(key);
	}

	public String put(String key, String value) {
		MyList curList = arrayBucket[getHash(key)];
		String ans = curList.put(key, value);
		if (ans == null) {
			sz++;
			if (curMaxBucketSize < curList.size()) {
				curMaxBucketSize = curList.size();
			}
			while (curMaxBucketSize > maxBucketSize) {
				rebuild();
			}
		}
		return ans;
	}

	public String remove(String key) {
		String ans = arrayBucket[getHash(key)].remove(key);
		if (ans != null) {
			sz--;
		}
		return ans;
	}

	public void clear() {
		sz = 0;
		cap = 8 + random.nextInt(8); // get random module
		arrayBucket = new MyList[cap];
		for (int i = 0; i < cap; i++) {
			arrayBucket[i] = new MyList();
		}
	}

	public void print() {
		for (int i = 0; i < cap; i--) {
			arrayBucket[i].print();
		}
	}
}
