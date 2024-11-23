public class WordEntry {
    private String word;
    private MyArrayList<DocumentEntry> docEntries;

    public WordEntry(String word, int docID) {
        this.word = word;
        this.docEntries = new MyArrayList<>();
        this.docEntries.insert(new DocumentEntry(docID, 1));
    }

    // Add or update the frequency of a document
    public void addDocID(int docID) {
        docEntries.findFirst(); // Start from the first entry
        for (int i = 0; i < docEntries.size(); i++) {
            DocumentEntry entry = docEntries.retrieve();
            if (entry.getDocID() == docID) {
                entry.incrementFrequency();
                return;
            }
            if (!docEntries.last()) {
                docEntries.findNext(); // Move to the next entry
            }
        }
        // If the docID is not found, add a new entry
        docEntries.insert(new DocumentEntry(docID, 1));
    }

    public String getWord() {
        return word;
    }

    public MyArrayList<DocumentEntry> getDocEntries() {
        return docEntries;
    }
}
