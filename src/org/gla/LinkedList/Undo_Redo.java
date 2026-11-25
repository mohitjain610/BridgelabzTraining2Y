package org.gla.LinkedList;


import java.util.Scanner;

class Main {
    static class Node {
        String text;
        Node next, prev;

        Node(String t) {
            text = t;
        }
    }

    static Node head = null, tail = null, current = null;
    static int size = 0;
    static final int LIMIT = 10;
    static Scanner sc = new Scanner(System.in);

    static void addState(String text) {
        if (current != null) {
            current.next = null;
            tail = current;
        }

        Node n = new Node(text);

        if (head == null) {
            head = tail = current = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = current = n;
        }

        size++;

        if (size > LIMIT) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    static void undo() {
        if (current != null && current.prev != null)
            current = current.prev;
        else
            System.out.println("Cannot Undo");
    }

    static void redo() {
        if (current != null && current.next != null)
            current = current.next;
        else
            System.out.println("Cannot Redo");
    }

    static void display() {
        if (current == null)
            System.out.println("Empty Text");
        else
            System.out.println("Current Text: " + current.text);
    }

    static void history() {
        Node temp = head;

        while (temp != null) {
            System.out.println(temp.text);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        addState("");

        while (true) {
            System.out.println("\n1.Type Text");
            System.out.println("2.Undo");
            System.out.println("3.Redo");
            System.out.println("4.Display Current Text");
            System.out.println("5.Display History");
            System.out.println("6.Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter Text: ");
                    addState(sc.nextLine());
                    break;
                case 2: undo(); break;
                case 3: redo(); break;
                case 4: display(); break;
                case 5: history(); break;
                case 6: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
