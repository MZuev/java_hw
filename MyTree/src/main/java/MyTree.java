import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MyTree implements Trie, StreamSerializable{
	private class TreeNode {
		final static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!";
		private boolean isTerminal = false;
		private int sizeSubTree	= 0;
		private TreeNode parrent = null;
		private int passLet = 52;
		private TreeNode[] nextNode = null;
		TreeNode() {
			nextNode = new TreeNode[52];
			for (int i = 0; i < 52; i++) {
				nextNode[i] = null;
			}
		}
		private void serialize(OutputStream out) throws IOException {
			if (isTerminal) {
				out.write('.');
			}
	    	for (int i = 0; i < 52; i++) {
	    		if (nextNode[i] != null) {
	    			out.write(letters.charAt(i));
	    			nextNode[i].serialize(out);
	    		}
	    	}
	    	out.write(')');
	    	return;
	    }
		private void deserialize(InputStream in) throws IOException {
			int tmp;
			while (true) {
				tmp = in.read();
				if (tmp == '.') {
					isTerminal = true;
					sizeSubTree++;
					continue;
				}
				if (tmp == ')') {
					break;
				}
				int numLet = getNumLet((char)tmp);
				nextNode[numLet] = new TreeNode();
				nextNode[numLet].parrent = this;
				nextNode[numLet].passLet = numLet;
				nextNode[numLet].deserialize(in);
				sizeSubTree += nextNode[numLet].sizeSubTree;
			}
			return;
		}
		private int getCntNode() {
	    	int ans = 1;
	    	for (int i = 0; i < 52; i++) {
	    		if (nextNode[i] != null) {
	    			ans += nextNode[i].getCntNode();
	    		}
	    	}
	    	return ans;
	    }
	}
	private int getNumLet(char a) {
		if (a <= 'Z') {
			return a - 'A';
		}
		return a - 'a' + 26;
	}
	private TreeNode createWay(TreeNode curNode, String subString) {
		for (int i = 0; i < subString.length(); i++) {
			int numLet = getNumLet(subString.charAt(i));
			if (curNode.nextNode[numLet] == null) {
				curNode.nextNode[numLet] = new TreeNode();
				curNode.nextNode[numLet].parrent = curNode;
				curNode.nextNode[numLet].passLet = numLet;
			}
			curNode = curNode.nextNode[numLet];
		}
		return curNode;
	}
	private TreeNode tryWay(TreeNode curNode, String subString) {
		for (int i = 0; i < subString.length() && curNode != null; i++) {
			int numLet = getNumLet(subString.charAt(i));
			curNode = curNode.nextNode[numLet];
		}
		return curNode;
	}
	private TreeNode root = null;
	MyTree() {
		root = new TreeNode();
	}
    public boolean add(String element) {
    	TreeNode endNode = createWay(root, element);
    	if (endNode.isTerminal) {
    		return false;
    	}
    	for (endNode.isTerminal = true; endNode != null; endNode = endNode.parrent) {
    		endNode.sizeSubTree++;
    	}
    	return true;
    }
 
    public boolean contains(String element) {
    	TreeNode endNode = tryWay(root, element);
    	return endNode != null && endNode.isTerminal;
    }
    
    public boolean remove(String element) {
    	TreeNode endNode = tryWay(root, element);
    	if (endNode == null || !endNode.isTerminal) {
    		return false;
    	}
    	endNode.isTerminal = false;
    	for (TreeNode curNode = endNode; curNode != null; curNode = curNode.parrent) {
    		curNode.sizeSubTree--;
    		if (curNode.sizeSubTree == 0 && curNode.parrent != null) {
    			curNode.parrent.nextNode[curNode.passLet] = null;
    		}
    	}
    	return true;
    }
 
    public int size() {
    	return root.sizeSubTree;
    }
 
    
    public int howManyStartsWithPrefix(String prefix) {
    		TreeNode endNode = tryWay(root, prefix);
    		if (endNode == null) {
    			return 0;
    		}
    		return endNode.sizeSubTree;
    }
    
    
    public void serialize(OutputStream out) throws IOException {
    	root.serialize(out);
    	out.flush();
    	return;
    }
    
    public void deserialize(InputStream in) throws IOException {
    	if (root.sizeSubTree != 0) {
    		root = new TreeNode();
    	}
    	root.deserialize(in);
    	return;
    }
    public int getCntNode() {
    	return root.getCntNode();
    }
    
}
