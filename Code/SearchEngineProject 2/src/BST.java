public class BST {
    private BSTNode root; // Root node of the BST
    private BSTNode current; // Pointer to the current node

    public BST() {
        root = null;
        current = null;
    }

    // Insert a word and document ID into the BST
    public boolean insert(String word, int docID) {
        if (root == null) {
            root = new BSTNode(word);
            root.getDocumentBST().insert(docID);
            current = root;
            return true;
        }

        BSTNode parent = current;
        current = root;
        int cmp;
        while (current != null) {
            cmp = word.compareTo(current.getWord());
            parent = current;
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                // Word exists; add docID to the existing DocumentBST
                current.getDocumentBST().insert(docID);
                return false; // Word already exists
            }
        }

        // Create and attach new node
        BSTNode newNode = new BSTNode(word);
        newNode.getDocumentBST().insert(docID);
        if (word.compareTo(parent.getWord()) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        return true;
    }

    public boolean findWord(String word) {
        current = root;
        while (current != null) {
            int cmp = word.compareTo(current.getWord());
            if (cmp == 0) {
                return true; // Word found
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false; // Word not found
    }

    public BSTNode retrieve() {
        return current;
    }

    public void printBST() {
        printBSTRec(root);
    }

    private void printBSTRec(BSTNode node) {
        if (node != null) {
            printBSTRec(node.left);
            System.out.println("Word: " + node.getWord());
            node.getDocumentBST().print(); // Assuming DocumentBST has a print method
            printBSTRec(node.right);
        }
    }

    public BSTNode findWordNode(String word) {
        BSTNode node = root;
        while (node != null) {
            int cmp = word.compareTo(node.getWord());
            if (cmp == 0) {
                return node; // Word found
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null; // Word not found
    }
}
