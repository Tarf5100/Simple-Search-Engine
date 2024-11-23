public class Document {
    private int docID;
    private MyArrayList<String> words;

    public Document(int docID, MyArrayList<String> words) {
        this.docID = docID;
        this.words = words;
    }

    public int getDocID() {
        return docID;
    }

    public MyArrayList<String> getWords() {
        return words;
    }
}
