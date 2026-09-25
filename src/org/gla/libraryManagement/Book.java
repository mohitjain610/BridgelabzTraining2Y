package org.gla.libraryManagement;



    public class Book {
        int bookId;
        String title;
        String author;
        double price;

        public Book(int bookId, String title, String author, double price) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.price = price;
        }


        // Task 1: Remove duplicate books in-place
        public static int removeDuplicates(Book[] books, int n) {
            if (n == 0) {
                return 0;
            }

            int unique = 1;

            for (int i = 1; i < n; i++) {
                if (books[i].bookId != books[unique - 1].bookId) {
                    books[unique] = books[i];
                    unique++;
                }
            }

            return unique;
        }

        // Task 2: Search books by partial title
        public static void searchByTitle(Book[] books, int count, String query) {
            query = query.toLowerCase();

            System.out.println("Search Results for '" + query + "':");

            for (int i = 0; i < count; i++) {
                if (books[i].title.toLowerCase().contains(query)) {
                    System.out.println("- Found: [" + books[i].bookId + "] "
                            + books[i].title + " (Rs. " + books[i].price + ")");
                }
            }
        }

        // Task 3: Selection sort by price
        public static void sortByPrice(Book[] books, int count) {
            int swaps = 0;

            for (int i = 0; i < count - 1; i++) {
                int minIndex = i;

                for (int j = i + 1; j < count; j++) {
                    if (books[j].price < books[minIndex].price) {
                        minIndex = j;
                    }
                }

                if (minIndex != i) {
                    Book temp = books[i];
                    books[i] = books[minIndex];
                    books[minIndex] = temp;
                    swaps++;
                }
            }

            System.out.println("Books Sorted by Price:");

            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". [" + books[i].bookId + "] "
                        + books[i].title + " - Rs. " + books[i].price);
            }

            System.out.println("Total Swaps: " + swaps);
        }

        // Task 4: Binary search by price
        public static int searchByPrice(Book[] books, int count, double targetPrice) {
            int low = 0;
            int high = count - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (books[mid].price == targetPrice) {
                    return mid;
                }

                if (books[mid].price < targetPrice) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return -1;
        }

        // Task 5: Sliding Window
        public static int minBooksForTargetCost(Book[] books, int count, double targetCost) {
            double currentSum = 0;
            int left = 0;
            int minLength = Integer.MAX_VALUE;

            for (int right = 0; right < count; right++) {
                currentSum += books[right].price;

                while (currentSum >= targetCost) {
                    minLength = Math.min(minLength, right - left + 1);
                    currentSum -= books[left].price;
                    left++;
                }
            }

            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }

        public static void main(String[] args) {

            Book[] books = {
                    new Book(101, "Data Structures", "Mark", 400.0),
                    new Book(101, "Data Structures", "Mark", 400.0),
                    new Book(102, "Java Basics", "James", 300.0),
                    new Book(103, "Python Guide", "Guido", 600.0),
                    new Book(104, "Database Systems", "Raghu", 500.0),
                    new Book(105, "Computer Networks", "Andrew", 700.0)
            };

            int n = books.length;

            // Task 1
            n = removeDuplicates(books, n);

            System.out.println("Unique Books Count: " + n);

            for (int i = 0; i < n; i++) {
                System.out.println("[" + books[i].bookId + "] "
                        + books[i].title + " - Rs. " + books[i].price);
            }

            System.out.println();

            // Task 2
            searchByTitle(books, n, "data");

            System.out.println();

            // Task 3
            sortByPrice(books, n);

            System.out.println();

            // Task 4
            double targetPrice = 500.0;

            System.out.println("Searching for Price Rs. " + targetPrice + "...");

            int index = searchByPrice(books, n, targetPrice);

            if (index != -1) {
                System.out.println("Result: Book found at index " + index + ": ["
                        + books[index].bookId + "] "
                        + books[index].title + " (Rs. "
                        + books[index].price + ")");
            } else {
                System.out.println("Result: Book not found");
            }

            System.out.println();

            // Task 5
            double targetCost = 1000.0;

            System.out.println("Finding minimum consecutive books whose total price >= Rs. "
                    + targetCost + "...");

            int result = minBooksForTargetCost(books, n, targetCost);

            System.out.println("Minimum Consecutive Books Needed: " + result);
        }
    }

