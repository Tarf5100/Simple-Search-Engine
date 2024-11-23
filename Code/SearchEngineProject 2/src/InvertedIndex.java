public class InvertedIndex {
    private MyArrayList<WordEntry> invertedIndex;

    public InvertedIndex() {
        this.invertedIndex = new MyArrayList<>();
    }

    // Add words to the inverted index, associating each word with document IDs and their frequencies
    public void addWords(MyArrayList<String> words, int docID) {
        for (int i = 0; i < words.size(); i++) {
            String word = words.retrieve(i);
            WordEntry entry = findWordEntry(word); // Find existing word entry
            if (entry == null) {
                // Create a new WordEntry if the word does not exist
                entry = new WordEntry(word, docID);
                invertedIndex.insert(entry); // Insert the new WordEntry
            } else {
                // Update the existing WordEntry
                entry.addDocID(docID);
            }
        }
    }


    // Helper method to add or update a word in the index
    private void addOrUpdateWord(String word, int docID) {
        WordEntry entry = findWordEntry(word);
        if (entry == null) {
            // If the word is not in the inverted index, add a new entry
            entry = new WordEntry(word, docID);
            invertedIndex.insert(entry);
        } else {
            // If the word is already in the inverted index, update the frequency
            entry.addDocID(docID);
        }
    }


    // Find the WordEntry for a given word
    private WordEntry findWordEntry(String word) {
        if (invertedIndex.empty()) { // Check if the list is empty
            return null; // No words in the index yet
        }

        for (int i = 0; i < invertedIndex.size(); i++) {
            WordEntry entry = invertedIndex.retrieve(i); // Pass the index
            if (entry.getWord().equals(word)) {
                return entry;
            }
        }
        return null; // Word not found
    }


    // Retrieve document entries for a specific word
    public MyArrayList<DocumentEntry> getDocumentsForWord(String word) {
        WordEntry entry = findWordEntry(word);
        if (entry != null) {
            return entry.getDocEntries();
        }
        return new MyArrayList<>(); // Return empty list if word is not found
    }
    public void printWordDetails(String word) {
        WordEntry entry = findWordEntry(word);
        if (entry != null) {
            System.out.println("Word: '" + entry.getWord() + "' appears in the following documents:");
            MyArrayList<DocumentEntry> documents = entry.getDocEntries();
            for (int i = 0; i < documents.size(); i++) {
                DocumentEntry docEntry = documents.retrieve(i);
                System.out.println(" - DocID: " + docEntry.getDocID() + ", Frequency: " + docEntry.getFrequency());
            }
        } else {
            System.out.println("Word '" + word + "' does not appear in any documents.");
        }
    }



    public void printInvertedIndex() {

        invertedIndex.findFirst();

        while (!invertedIndex.last()) {
            WordEntry entry = invertedIndex.retrieve();
            if (entry != null && entry.getDocEntries() != null) {
                MyArrayList<DocumentEntry> docEntries = entry.getDocEntries();
                System.out.println("Word '"  + entry.getWord() + "' Appears In " + docEntries.size() + " Documents");
                for (int i = 0; i < docEntries.size(); i++) {
                    DocumentEntry docEntry = docEntries.retrieve(i);
                    if (docEntry != null) {
                        System.out.println(" - DocID: " + docEntry.getDocID() + ", Frequency: " + docEntry.getFrequency());
                    }
                }
            }
            invertedIndex.findNext();  // Move to the next element before checking if it's the last
        }

        WordEntry entry = invertedIndex.retrieve();
        if (entry != null && entry.getDocEntries() != null) {
            MyArrayList<DocumentEntry> docEntries = entry.getDocEntries();
            System.out.println("Word: " + entry.getWord() + " Appears In " + docEntries.size() + " Documents");
            for (int i = 0; i < docEntries.size(); i++) {
                DocumentEntry docEntry = docEntries.retrieve(i);
                if (docEntry != null) {
                    System.out.println(" - DocID: " + docEntry.getDocID() + ", Frequency: " + docEntry.getFrequency());
                }
            }
        }
    }
}
