class DocumentBSTNode {
    int docID; // Document ID
    int frequency; // Frequency of the document ID
    DocumentBSTNode left, right; // Left and right children

    public DocumentBSTNode(int docID) {
        this.docID = docID;
        this.frequency = 1; // Initial frequency is 1
        this.left = this.right = null;
    }

    public int getDocID() {
        return docID;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incrementFrequency() {
        frequency++; // Increment frequency if the docID is already in the BST
    }

}
