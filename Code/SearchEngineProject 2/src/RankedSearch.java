public class RankedSearch {
    private final Index index;
    private final InvertedIndex invertedIndex;
    private final InvertedIndexBST invertedIndexBST;

    // Constructor to initialize all indexes
    public RankedSearch(Index index, InvertedIndex invertedIndex, InvertedIndexBST invertedIndexBST) {
        this.index = index;
        this.invertedIndex = invertedIndex;
        this.invertedIndexBST = invertedIndexBST;
    }

    // Ranked search using the regular index
    public void searchIndex(String query) {
        System.out.println("Query: " + query);

        long startTime = System.nanoTime();

        // MyArrayList to store document scores
        MyArrayList<DocumentScore> docScores = new MyArrayList<>();

        // Split query into individual terms
        String[] queryTerms = query.split(" ");

        // Iterate through all documents in the index
        for (int i = 0; i < index.getDocuments().size(); i++) {
            Document doc = index.getDocuments().retrieve(i);
            int docID = doc.getDocID();
            MyArrayList<String> words = doc.getWords();

            // Calculate the score for this document
            int score = 0;
            for (String term : queryTerms) {
                score += countOccurrences(words, term);
            }

            // If the document has a score, add it to the result
            if (score > 0) {
                docScores.insert(new DocumentScore(docID, score));
            }
        }

        // Sort by score in descending order
        sortDocScoresDescending(docScores);

        long endTime = System.nanoTime();

        System.out.println("Ranked Results:");
        for (int i = 0; i < docScores.size(); i++) {
            System.out.println(docScores.retrieve(i));
        }

        System.out.println("Time taken: " + (endTime - startTime) + " ns\n");
    }

    // Ranked search using the inverted index
    public void searchInvertedIndex(String query) {
        System.out.println("Query: " + query);

        long startTime = System.nanoTime();

        // MyArrayList to store document scores
        MyArrayList<DocumentScore> docScores = new MyArrayList<>();

        // Split query into individual terms
        String[] queryTerms = query.split(" ");

        // For each query term, update scores in documents
        for (String term : queryTerms) {
            MyArrayList<DocumentEntry> docEntries = invertedIndex.getDocumentsForWord(term);

            // Update the score for each document that contains this term
            for (int i = 0; i < docEntries.size(); i++) {
                DocumentEntry entry = docEntries.retrieve(i);
                int docID = entry.getDocID();
                int termFrequency = entry.getFrequency();

                // Find or create a DocumentScore entry for this document ID
                DocumentScore docScore = findOrCreateDocScore(docScores, docID);

                // Add term frequency to the document's score
                docScore.addScore(termFrequency);
            }
        }

        // Sort by score in descending order
        sortDocScoresDescending(docScores);

        long endTime = System.nanoTime();

        System.out.println("Ranked Results:");
        for (int i = 0; i < docScores.size(); i++) {
            System.out.println(docScores.retrieve(i));
        }

        System.out.println("Time taken: " + (endTime - startTime) + " ns\n");
    }

    // Ranked search using the BST inverted index
    public void searchInvertedIndexBST(String query) {
        System.out.println("Query: " + query);

        long startTime = System.nanoTime();

        MyArrayList<DocumentScore> docScores = new MyArrayList<>();
        String[] queryTerms = query.split(" ");

        for (String term : queryTerms) {
            MyArrayList<Integer> docIDs = invertedIndexBST.getDocuments(term);
            for (int i = 0; i < docIDs.size(); i++) {
                int docID = docIDs.retrieve(i);
                int frequency = invertedIndexBST.getDocumentFrequency(term, docID); // Retrieve frequency
                DocumentScore docScore = findOrCreateDocScore(docScores, docID);
                docScore.addScore(frequency); // Add the frequency to the score
            }
        }

        // Sort by score in descending order
        sortDocScoresDescending(docScores);

        long endTime = System.nanoTime();

        System.out.println("Ranked Results:");
        for (int i = 0; i < docScores.size(); i++) {
            System.out.println(docScores.retrieve(i));
        }

        System.out.println("Time taken: " + (endTime - startTime) + " ns\n");
    }

    // Helper method to count occurrences of a term in a MyArrayList of words
    private int countOccurrences(MyArrayList<String> words, String term) {
        int count = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.retrieve(i).equalsIgnoreCase(term)) {
                count++;
            }
        }
        return count;
    }

    // Helper method to find or create a DocumentScore entry
    private DocumentScore findOrCreateDocScore(MyArrayList<DocumentScore> docScores, int docID) {
        for (int i = 0; i < docScores.size(); i++) {
            DocumentScore docScore = docScores.retrieve(i);
            if (docScore.getDocID() == docID) {
                return docScore;
            }
        }
        // If not found, create a new one
        DocumentScore newDocScore = new DocumentScore(docID, 0);
        docScores.insert(newDocScore);
        return newDocScore;
    }

    // Helper method to sort document scores in descending order using selection sort
    private void sortDocScoresDescending(MyArrayList<DocumentScore> docScores) {
        int n = docScores.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;

            // Find the index of the maximum score
            for (int j = i + 1; j < n; j++) {
                if (docScores.retrieve(j).getScore() > docScores.retrieve(maxIdx).getScore()) {
                    maxIdx = j;
                }
            }

            // Swap docScores[i] with docScores[maxIdx]
            DocumentScore temp = docScores.retrieve(i);
            docScores.update(docScores.retrieve(maxIdx), i); // Update at index i
            docScores.update(temp, maxIdx); // Update at index maxIdx
        }
    }
}

