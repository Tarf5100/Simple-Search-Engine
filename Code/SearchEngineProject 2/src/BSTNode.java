public class BSTNode {
    private String word; // Word stored in the node
    private DocumentBST documentBST; // DocumentBST for the word
    BSTNode left, right;

    public BSTNode(String word) {
        this.word = word;
        this.documentBST = new DocumentBST();
        this.left = this.right = null;
    }

    public String getWord() {
        return word;
    }

    public DocumentBST getDocumentBST() {
        return documentBST;
    }

}
