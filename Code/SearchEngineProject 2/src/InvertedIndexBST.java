public class InvertedIndexBST {
    private BST bst; // The main BST for words

    public InvertedIndexBST() {
        this.bst = new BST();
    }

    // Add a word and a corresponding document ID to the index
    public boolean addWord(String word, int docID) {
        return bst.insert(word, docID); // Delegate to BST's insert method
    }

    // Get all document IDs associated with a given word
    public MyArrayList<Integer> getDocuments(String word) {
        BSTNode node = bst.findWordNode(word); // Method to find node directly from BST
        if (node != null) {
            return node.getDocumentBST().getAllDocumentIDs();
        }
        return new MyArrayList<>();
    }

    public int getDocumentFrequency(String word, int docID) {
        BSTNode node = bst.findWordNode(word); // Find node for the word
        if (node != null) {
            return node.getDocumentBST().findFrequency(docID);
        }
        return 0;
    }

    // Print the entire inverted index (words and their corresponding DocumentBSTs)
    public void printInvertedIndex() {
        bst.printBST(); // Assumes printBST is correctly implemented in BST
    }

    public void printWordDetails(String word) {
        BSTNode node = bst.findWordNode(word); // Access node using BST's method
        if (node != null) {
            System.out.println("Word: '" + word + "' appears in the following documents:");
            node.getDocumentBST().printDocumentDetails();
        } else {
            System.out.println("Word '" + word + "' does not appear in any documents.");
        }
    }
    public BSTNode findWordNode(String word) {
        return bst.findWordNode(word);  // Delegate to BST's findWordNode method
    }

}