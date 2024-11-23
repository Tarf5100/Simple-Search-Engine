public class BooleanSearch {
    private final Index index;
    private final InvertedIndex invertedIndex;
    private final InvertedIndexBST invertedIndexBST;

    public BooleanSearch(Index index, InvertedIndex invertedIndex, InvertedIndexBST invertedIndexBST) {
        this.index = index;
        this.invertedIndex = invertedIndex;
        this.invertedIndexBST = invertedIndexBST;
    }

    // Determines the precedence of Boolean operators
    private static int precedence(String operator) {
        switch (operator) {
            case "AND":
                return 2;
            case "OR":
                return 1;
            default:
                return 0;
        }
    }

    // Converts infix Boolean expression to postfix expression
    private static String infixToPostfix(String expression) {
        MyStack<String> stack = new MyStack<>();
        MyArrayList<String> postfix = new MyArrayList<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (!token.equals("AND") && !token.equals("OR")) {
                postfix.insert(token);
            } else {
                while (!stack.isEmpty()) {
                    String top = stack.pop();
                    if (precedence(top) >= precedence(token)) {
                        postfix.insert(top);
                    } else {
                        stack.push(top);
                        break;
                    }
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            postfix.insert(stack.pop());
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < postfix.size(); i++) {
            if (i > 0) result.append(" ");
            result.append(postfix.retrieve(i));
        }
        return result.toString();
    }

    // Evaluates the postfix expression using the appropriate index type
    private MyArrayList<Integer> evaluatePostfix(String postfix, String indexType) {
        MyStack<MyArrayList<Integer>> stack = new MyStack<>();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (!token.equals("AND") && !token.equals("OR")) {
                MyArrayList<Integer> docList;

                switch (indexType) {
                    case "index":
                        docList = getDocumentsFromRegularIndex(token);
                        break;
                    case "inverted index":
                        docList = getDocumentsFromInvertedIndex(token);
                        break;
                    case "BST inverted index":
                        docList = getDocumentsFromInvertedIndexBST(token);
                        break;
                    default:
                        docList = new MyArrayList<>();
                }
                stack.push(docList);
            } else {
                // Pop two lists from the stack and perform the Boolean operation
                MyArrayList<Integer> right = stack.pop();
                MyArrayList<Integer> left = stack.pop();
                MyArrayList<Integer> result = token.equals("AND") ? intersect(left, right) : union(left, right);
                stack.push(result);
            }
        }
        return stack.isEmpty() ? new MyArrayList<>() : stack.pop();
    }

    // Get document IDs from the regular index
    private MyArrayList<Integer> getDocumentsFromRegularIndex(String word) {
        MyArrayList<Integer> docList = new MyArrayList<>();
        MyArrayList<Document> documents = index.getDocuments();

        for (int i = 0; i < documents.size(); i++) {
            Document doc = documents.retrieve(i);
            if (doc.getWords().contains(word)) {
                docList.insert(doc.getDocID());
            }
        }

        return docList;
    }

    // Get document IDs from the inverted index
    private MyArrayList<Integer> getDocumentsFromInvertedIndex(String word) {
        MyArrayList<Integer> docIDs = new MyArrayList<>();
        MyArrayList<DocumentEntry> docEntries = invertedIndex.getDocumentsForWord(word);

        for (int i = 0; i < docEntries.size(); i++) {
            DocumentEntry entry = docEntries.retrieve(i);
            docIDs.insert(entry.getDocID());
        }
        return docIDs;
    }

    private MyArrayList<Integer> getDocumentsFromInvertedIndexBST(String word) {
        return invertedIndexBST.getDocuments(word);
    }

    // Intersects two lists of document IDs
    private static MyArrayList<Integer> intersect(MyArrayList<Integer> list1, MyArrayList<Integer> list2) {
        MyArrayList<Integer> result = new MyArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            int id1 = list1.retrieve(i);
            int id2 = list2.retrieve(j);
            if (id1 == id2) {
                result.insert(id1);
                i++;
                j++;
            } else if (id1 < id2) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    // Unions two lists of document IDs
    private static MyArrayList<Integer> union(MyArrayList<Integer> list1, MyArrayList<Integer> list2) {
        MyArrayList<Integer> result = new MyArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() || j < list2.size()) {
            if (i < list1.size() && (j >= list2.size() || list1.retrieve(i) < list2.retrieve(j))) {
                result.insert(list1.retrieve(i++));
            } else if (j < list2.size() && (i >= list1.size() || list2.retrieve(j) < list1.retrieve(i))) {
                result.insert(list2.retrieve(j++));
            } else {
                result.insert(list1.retrieve(i));
                i++;
                j++;
            }
        }
        return result;
    }

    // Perform search and measure performance on the regular index
    public void searchIndex(String query) {
        String postfix = infixToPostfix(query);
        System.out.println("Query: " + query);

        long startTime = System.nanoTime();
        MyArrayList<Integer> searchResults = evaluatePostfix(postfix, "index");
        long endTime = System.nanoTime();

        System.out.print("Search Results: ");
        for (int i = 0; i < searchResults.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(searchResults.retrieve(i));
        }
        System.out.println(" .. " + (endTime - startTime) + " ns\n");
    }

    public void searchInvertedIndex(String query) {
        String postfix = infixToPostfix(query);
        System.out.println("Query: " + query);

        long startTime = System.nanoTime();
        MyArrayList<Integer> searchResults = evaluatePostfix(postfix, "inverted index");
        long endTime = System.nanoTime();

        System.out.print("Search Results: ");
        for (int i = 0; i < searchResults.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(searchResults.retrieve(i));
        }
        System.out.println(" .. " + (endTime - startTime) + " ns\n");
    }

    public void searchInvertedIndexBST(String query) {
        String postfix = infixToPostfix(query);
        System.out.println("Query: " + query);

        long startTime = System.nanoTime();
        MyArrayList<Integer> searchResults = evaluatePostfix(postfix, "BST inverted index");
        long endTime = System.nanoTime();

        System.out.print("Search Results: ");
        for (int i = 0; i < searchResults.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(searchResults.retrieve(i));
        }
        System.out.println(" .. " + (endTime - startTime) + " ns\n");
    }
}
