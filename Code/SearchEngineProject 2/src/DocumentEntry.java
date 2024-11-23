public class DocumentEntry {
    private int docID;
    private int frequency;

    public DocumentEntry(int docID, int frequency) {
        this.docID = docID;
        this.frequency = frequency;
    }

    public int getDocID() {
        return docID;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incrementFrequency() {
        this.frequency++;
    }
}
