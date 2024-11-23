public class Index {
    private MyArrayList<Document> documents;

    public Index() {
        this.documents = new MyArrayList<>();
    }

    public MyArrayList<Document> getDocuments() {
        return documents;
    }

    public void addDocument(int docID, MyArrayList<String> words) {
        documents.insert(new Document(docID, words));
    }

    public MyArrayList<String> getDocumentWords(int docID) {
        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.retrieve(i);
            if (doc.getDocID() == docID) {
                return doc.getWords();
            }
        }
        return null;
    }

    public void printDocumentIndex() {


        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.retrieve(i);
            MyArrayList<String> words = doc.getWords();
            StringBuilder sentence = new StringBuilder();
            int wordCount = words.size();  // Cache the size to avoid multiple calls

            for (int j = 0; j < words.size(); j++) {
                sentence.append(words.retrieve(j));
                if (j < words.size() - 1) {
                    sentence.append(" ");
                }
            }
            System.out.println("Document " + doc.getDocID() + " has (" + wordCount + ") words");
            System.out.println(" \"" + sentence.toString() + "\"");
        }
    }
    public void printWordDetails(String word) {
        StringBuilder docIds = new StringBuilder();
        boolean found = false;

        // Loop over all documents in the index
        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.retrieve(i);
            MyArrayList<String> words = doc.getWords();

            // Check if the word is present in the current document's word list
            if (words.contains(word)) {
                if (found) {
                    docIds.append(", ");
                }
                docIds.append(doc.getDocID());  // Append the document ID to the StringBuilder
                found = true;
            }
        }

        if (found) {
            System.out.println("Word '" + word + "' appears in the following document IDs: " + docIds);
        } else {
            System.out.println("Word '" + word + "' does not appear in any documents.");
        }
    }





}
