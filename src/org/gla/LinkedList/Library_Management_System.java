package org.gla.LinkedList;


import java.util.Scanner;

class Library_Management_System {
    static class Node {
        int id;
        String title, author, genre;
        boolean available;
        Node next, prev;

        Node(int i, String t, String a, String g, boolean av) {
            id = i;
            title = t;
            author = a;
            genre = g;
            available = av;
        }
    }

    static Node head = null, tail = null;
    static Scanner sc = new Scanner(System.in);

    static void insert(int pos) {
        System.out.print("Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Title: ");
        String t = sc.nextLine();
        System.out.print("Author: ");
        String a = sc.nextLine();
        System.out.print("Genre: ");
        String g = sc.nextLine();
        System.out.print("Available (true/false): ");
        boolean av = sc.nextBoolean();

        Node n = new Node(id, t, a, g, av);

        if (head == null) {
            head = tail = n;
            return;
        }

        if (pos == 1) {
            n.next = head;
            head.prev = n;
            head = n;
            return;
        }

        if (pos == Integer.MAX_VALUE) {
            tail.next = n;
            n.prev = tail;
            tail = n;
            return;
        }

        Node temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++)
            temp = temp.next;

        n.next = temp.next;
        n.prev = temp;

        if (temp.next != null)
            temp.next.prev = n;
        else
            tail = n;

        temp.next = n;
    }

    static void delete(int id) {
        Node temp = head;

        while (temp != null && temp.id != id)
            temp = temp.next;

        if (temp == null) {
            System.out.println("Book not found");
            return;
        }

        if (temp.prev != null)
            temp.prev.next = temp.next;
        else
            head = temp.next;

        if (temp.next != null)
            temp.next.prev = temp.prev;
        else
            tail = temp.prev;
    }

    static void search() {
        System.out.println("1.Title 2.Author");
        int ch = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter value: ");
        String value = sc.nextLine();

        Node temp = head;

        while (temp != null) {
            if ((ch == 1 && temp.title.equalsIgnoreCase(value)) ||
                    (ch == 2 && temp.author.equalsIgnoreCase(value)))
                show(temp);

            temp = temp.next;
        }
    }

    static void update(int id) {
        Node temp = head;

        while (temp != null) {
            if (temp.id == id) {
                System.out.print("Availability (true/false): ");
                temp.available = sc.nextBoolean();
                return;
            }
            temp = temp.next;
        }

        System.out.println("Book not found");
    }

    static void show(Node n) {
        System.out.println(n.id + " " + n.title + " " +
                n.author + " " + n.genre + " " + n.available);
    }

    static void forward() {
        Node temp = head;
        while (temp != null) {
            show(temp);
            temp = temp.next;
        }
    }

    static void reverse() {
        Node temp = tail;
        while (temp != null) {
            show(temp);
            temp = temp.prev;
        }
    }

    static void count() {
        int c = 0;
        Node temp = head;

        while (temp != null) {
            c++;
            temp = temp.next;
        }

        System.out.println("Total Books: " + c);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Insert Beginning");
            System.out.println("2.Insert End");
            System.out.println("3.Insert Position");
            System.out.println("4.Delete");
            System.out.println("5.Search");
            System.out.println("6.Update Availability");
            System.out.println("7.Forward Display");
            System.out.println("8.Reverse Display");
            System.out.println("9.Count Books");
            System.out.println("10.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: insert(1); break;
                case 2: insert(Integer.MAX_VALUE); break;
                case 3:
                    System.out.print("Position: ");
                    insert(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Book ID: ");
                    delete(sc.nextInt());
                    break;
                case 5: search(); break;
                case 6:
                    System.out.print("Book ID: ");
                    update(sc.nextInt());
                    break;
                case 7: forward(); break;
                case 8: reverse(); break;
                case 9: count(); break;
                case 10: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
