public class DocumentScore {
    private final int docID;
    private int score;

    public DocumentScore(int docID, int score) {
        this.docID = docID;
        this.score = score;
    }

    public int getDocID() {
        return docID;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int termFrequency) {
        this.score += termFrequency;
    }


    public String toString() {
        return "DocID: " + docID + ", Score: " + score;
    }
}
