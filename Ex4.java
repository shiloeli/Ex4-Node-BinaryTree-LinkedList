
public class Ex4 {

	// 1
	public static boolean contains(LinkedList lst, BinaryTree bt) {
		return contains(lst.getHead(), bt.getRoot());
	}

	public static boolean contains(Node lst, BTNode bt) {
		if (bt == null && lst == null) {
			return true;
		} else if (bt == null && lst != null) {
			return false;
		} else if (bt != null && lst == null) {
			return true;
		} else {
			if (lst.getData() != bt.getData()) {

				return false || (contains(lst, bt.getLeft()) || contains(lst, bt.getRight()));
			} else {
				return true && (contains(lst.getNext(), bt.getLeft()) || contains(lst.getNext(), bt.getRight()));
			}
		}
	}

	// 3
	public static LinkedList[] ZigZagSplit(LinkedList lst) {
		if (lst == null || lst.getSize() == 0) {
			return null;
		}
		LinkedList lst2 = new LinkedList();
		LinkedList lst3 = new LinkedList();

		Node p = lst.getHead();
		if (lst.getHead() != null) {
			lst2.AddFirst(p.getData());
		}
		if (p.getNext() != null) {
			lst3.AddFirst(p.getNext().getData());
			p = p.getNext();
		}
		for (int i = 3; i <= lst.getSize(); i++) {
			if (p.getNext() != null) {
				lst2.AddLast(p.getNext().getData());
				p = p.getNext();
			}
			if (p.getNext() != null) {
				lst3.AddLast(p.getNext().getData());
				p = p.getNext();
			}
		}
		LinkedList[] exit = new LinkedList[2];
		exit[0] = lst2;
		exit[1] = lst3;
		return exit;
	}

	// 2
	public static BinaryTree getFamilyTree(String path) throws FileNotFoundException {
		if (path == null) {
			return null;
		}
		FileReader p = new FileReader(path);
		Scanner sc = new Scanner(p);
		BinaryTree newTree = new BinaryTree();
		while (sc.hasNext()) {
			String S = sc.nextLine();
			String name[] = S.split(",");
			insertToTree(newTree, name);
		}
		return newTree;
	}

	public static void insertToTree(BinaryTree tree, String[] smurfs) {
		String father = null;
		String left = null;
		String right = null;
		if (smurfs.length == 0)
			return;
		else {
			father = smurfs[0];
			if (tree.getRoot() == null) {
				tree.add(father);
			}
			if (smurfs.length > 1)
				left = smurfs[1];
			if (smurfs.length > 2)
				right = smurfs[2];

			BTNode fatherRootNode = findNode(tree.getRoot(), father);
			if (fatherRootNode == null)
				return;
			else {
				if (right != null)
					fatherRootNode.setRight(new BTNode(right));
				fatherRootNode.setLeft(new BTNode(left));
			}
		}
	}

	public static BTNode findNode(BTNode root, String s) {
		if (root == null)
			return null;
		if (root.getData().equals(s))
			return root;
		if (root.getLeft() != null) {
			BTNode leftAns = findNode(root.getLeft(), s);
			if (leftAns != null)
				return leftAns;
		}
		if (root.getRight() != null) {
			BTNode rightLeft = findNode(root.getRight(), s);
			if (rightLeft != null)
				return rightLeft;
		}
		return null;
	}
}