package org.gla.LinkedList;


import java.util.Scanner;

class Movie_Management_System {
    static class Node {
        String title, director;
        int year;
        double rating;
        Node next, prev;

        Node(String t, String d, int y, double r) {
            title = t;
            director = d;
            year = y;
            rating = r;
        }
    }

    static Node head = null, tail = null;
    static Scanner sc = new Scanner(System.in);

    static void insert(int pos) {
        sc.nextLine();
        System.out.print("Movie Title: ");
        String t = sc.nextLine();
        System.out.print("Director: ");
        String d = sc.nextLine();
        System.out.print("Year: ");
        int y = sc.nextInt();
        System.out.print("Rating: ");
        double r = sc.nextDouble();

        Node n = new Node(t, d, y, r);

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

    static void delete(String t) {
        Node temp = head;

        while (temp != null && !temp.title.equalsIgnoreCase(t))
            temp = temp.next;

        if (temp == null) {
            System.out.println("Movie not found");
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
        sc.nextLine();
        System.out.println("1.Director 2.Rating");
        int ch = sc.nextInt();
        Node temp = head;

        if (ch == 1) {
            sc.nextLine();
            System.out.print("Director: ");
            String d = sc.nextLine();

            while (temp != null) {
                if (temp.director.equalsIgnoreCase(d))
                    show(temp);
                temp = temp.next;
            }
        } else {
            System.out.print("Rating: ");
            double r = sc.nextDouble();

            while (temp != null) {
                if (temp.rating == r)
                    show(temp);
                temp = temp.next;
            }
        }
    }

    static void update(String t) {
        Node temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(t)) {
                System.out.print("New Rating: ");
                temp.rating = sc.nextDouble();
                return;
            }
            temp = temp.next;
        }

        System.out.println("Movie not found");
    }

    static void show(Node n) {
        System.out.println(n.title + " " + n.director +
                " " + n.year + " " + n.rating);
    }

    static void displayForward() {
        Node temp = head;
        while (temp != null) {
            show(temp);
            temp = temp.next;
        }
    }

    static void displayReverse() {
        Node temp = tail;
        while (temp != null) {
            show(temp);
            temp = temp.prev;
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Insert Beginning");
            System.out.println("2.Insert End");
            System.out.println("3.Insert Position");
            System.out.println("4.Delete");
            System.out.println("5.Search");
            System.out.println("6.Update Rating");
            System.out.println("7.Forward Display");
            System.out.println("8.Reverse Display");
            System.out.println("9.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: insert(1); break;
                case 2: insert(Integer.MAX_VALUE); break;
                case 3:
                    System.out.print("Position: ");
                    insert(sc.nextInt());
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Title: ");
                    delete(sc.nextLine());
                    break;
                case 5: search(); break;
                case 6:
                    sc.nextLine();
                    System.out.print("Title: ");
                    update(sc.nextLine());
                    break;
                case 7: displayForward(); break;
                case 8: displayReverse(); break;
                case 9: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
