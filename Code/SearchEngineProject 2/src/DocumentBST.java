public class DocumentBST {
    private DocumentBSTNode root;   // Root of the BST
    private DocumentBSTNode current; // Pointer to the current node

    public DocumentBST() {
        this.root = null;
        this.current = null;
    }

    // Insert or update frequency for a document ID
    public void insert(int docID) {
        if (root == null) {
            root = new DocumentBSTNode(docID); // Tree is empty, create root
            current = root; // Set current to the root
            return;
        }

        DocumentBSTNode parent = null;
        current = root;

        while (current != null) {
            parent = current;
            if (docID < current.getDocID()) {
                current = current.left;
            } else if (docID > current.getDocID()) {
                current = current.right;
            } else {
                // If docID already exists, increment its frequency
                current.incrementFrequency();
                return;
            }
        }

        // Create a new node and attach it to the correct parent
        DocumentBSTNode newNode = new DocumentBSTNode(docID);
        if (docID < parent.getDocID()) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        current = newNode; // Set current to the newly inserted node
    }

    // Print the document BST (docID and frequency)
    public void print() {
        printRec(root);
    }

    private void printRec(DocumentBSTNode node) {
        if (node != null) {
            printRec(node.left);
            System.out.println("DocID: " + node.getDocID() + ", Frequency: " + node.getFrequency());
            printRec(node.right);
        }
    }

    // Print details for all documents
    public void printDocumentDetails() {
        System.out.println("Document details:");
        print(root);
    }

    // Helper method to print document details recursively
    private void print(DocumentBSTNode node) {
        if (node != null) {
            print(node.left);
            System.out.println("Document ID: " + node.getDocID() + ", Frequency: " + node.getFrequency());
            print(node.right);
        }
    }

    public MyArrayList<Integer> getAllDocumentIDs() {
        MyArrayList<Integer> docIDs = new MyArrayList<>();
        getAllDocumentIDsRec(root, docIDs);
        return docIDs;
    }
    private void getAllDocumentIDsRec(DocumentBSTNode node, MyArrayList<Integer> docIDs) {
        if (node != null) {
            getAllDocumentIDsRec(node.left, docIDs);  // Visit left child
            docIDs.insert(node.getDocID());           // Visit node itself
            getAllDocumentIDsRec(node.right, docIDs); // Visit right child
        }
    }

    public int findFrequency(int docID) {
        DocumentBSTNode node = findNode(docID); // Method to find a node based on docID
        return (node != null) ? node.getFrequency() : 0; // If found, return its frequency, otherwise return 0
    }

    private DocumentBSTNode findNode(int docID) {
        DocumentBSTNode current = root;
        while (current != null) {
            if (docID < current.getDocID()) {
                current = current.left; // Search in the left subtree
            } else if (docID > current.getDocID()) {
                current = current.right; // Search in the right subtree
            } else {
                return current; // Document ID found
            }
        }
        return null; // Document ID not found
    }

}
