import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Index index = new Index();
        InvertedIndex invertedIndex = new InvertedIndex();
        InvertedIndexBST invertedIndexBST = new InvertedIndexBST();
        MyArrayList<String> stopWords = loadStopWords("data/stop-words.txt");
        processDocuments(index, invertedIndex, invertedIndexBST, stopWords);
        BooleanSearch booleanSearch = new BooleanSearch(index, invertedIndex, invertedIndexBST);
        RankedSearch rankedSearch = new RankedSearch(index, invertedIndex, invertedIndexBST);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Simple Search Engine Menu ===");
            System.out.println("1. Index Retrieval");
            System.out.println("2. Boolean Retrieval");
            System.out.println("3. Ranked Retrieval");
            System.out.println("4. Indexed Documents");
            System.out.println("5. Indexed Token");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    System.out.println("Select Index Type:");
                    System.out.println("1. Basic Index");
                    System.out.println("2. Inverted Index");
                    System.out.println("3. BST-enhanced Inverted Index");
                    System.out.print("Enter your choice: ");
                    int indexType = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter word to retrieve: ");
                    String word = scanner.nextLine();
                    long startTime = System.nanoTime();
                    if (indexType == 1) {
                        index.printWordDetails(word);
                    } else if (indexType == 2) {
                        invertedIndex.printWordDetails(word);
                    } else if (indexType == 3) {
                        invertedIndexBST.printWordDetails(word);
                    } else {
                        System.out.println("Invalid index type.");
                    }
                    long endTime = System.nanoTime(); // End time measurement

                    long duration = endTime - startTime; // Calculate the duration
                    System.out.println("Operation completed in " + duration + " nanoseconds.");
                    break;

                case 2:
                    System.out.println("\nSelect Index Type for Index Retrieval:");
                    System.out.println("1. Basic Index");
                    System.out.println("2. Inverted Index");
                    System.out.println("3. BST-enhanced Inverted Index");
                    System.out.print("Enter your choice: ");
                    int indexChoice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Boolean query: ");
                    String query1 = scanner.nextLine();

                    if (indexChoice == 1) {
                        booleanSearch.searchIndex(query1);
                    } else if (indexChoice == 2) {
                        booleanSearch.searchInvertedIndex(query1);
                    } else if (indexChoice == 3) {
                        booleanSearch.searchInvertedIndexBST(query1);
                    } else {
                        System.out.println("Invalid choice.");
                            }
                    break;
                case 3:
                     System.out.println("\nSelect Index Type for Ranked Retrieval:");
                     System.out.println("1. Basic Index");
                     System.out.println("2. Inverted Index");
                     System.out.println("3. BST-enhanced Inverted Index");
                     System.out.print("Enter your choice: ");
                     int rankedChoice = scanner.nextInt();
                     scanner.nextLine();  // consume the remaining newline
                    System.out.print("Enter Ranked query: ");
                    String rankedQuery = scanner.nextLine();

                    if (rankedChoice == 1) {
                        rankedSearch.searchIndex(rankedQuery);
                    } else if (rankedChoice == 2) {
                        rankedSearch.searchInvertedIndex(rankedQuery);
                    } else if (rankedChoice == 3) {
                        rankedSearch.searchInvertedIndexBST(rankedQuery);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 4:
                    System.out.println("Indexed Documents: All Documents with number of words in them");
                    index.printDocumentIndex();
                    break;
                case 5:
                    System.out.println("Indexed Token: All Words with number of documents they appear in");
                    invertedIndex.printInvertedIndex();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                    }
            }
            scanner.close();
        }

        // Load stopwords from file
        private static MyArrayList<String> loadStopWords (String filePath){
            MyArrayList<String> stopWords = new MyArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String word = line.trim().toLowerCase();
                    if (!stopWords.contains(word)) {
                        stopWords.insert(word);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stopWords;
        }

        // Process documents and add them to the indexes
        private static void processDocuments (Index index, InvertedIndex invertedIndex, InvertedIndexBST
        invertedIndexBST, MyArrayList < String > stopWords){
            try (BufferedReader reader = new BufferedReader(new FileReader("data/dataset.csv"))) {
                String line;
                boolean firstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (firstLine) {
                        firstLine = false;
                        continue;
                    }

                    String[] columns = line.split(",", 2);
                    if (columns.length < 2) continue;

                    try {
                        int docID = Integer.parseInt(columns[0].trim());
                        MyArrayList<String> words = cleanContent(columns[1], stopWords);
                        index.addDocument(docID, words);
                        invertedIndex.addWords(words, docID);
                        for (int i = 0; i < words.size(); i++) {
                            invertedIndexBST.addWord(words.retrieve(i), docID);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        // Clean content of each document by removing non-alphanumeric characters and filtering out stopwords
        private static MyArrayList<String> cleanContent (String text, MyArrayList < String > stopWords){
            String[] words = text.toLowerCase().split("\\s+");
            MyArrayList<String> processedWords = new MyArrayList<>();

            for (String word : words) {
                word = word.replaceAll("[^a-z0-9]", "");
                if (!stopWords.contains(word) && !word.isEmpty()) {
                    processedWords.insert(word);
                }
            }
            return processedWords;
        }
    }
