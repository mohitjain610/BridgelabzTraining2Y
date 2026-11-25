package org.gla.LinkedList;


import java.util.Scanner;

class Task_Scheduler {
    static class Node {
        int id, priority;
        String name, date;
        Node next;

        Node(int i, String n, int p, String d) {
            id = i;
            name = n;
            priority = p;
            date = d;
        }
    }

    static Node head = null, current = null;
    static Scanner sc = new Scanner(System.in);

    static void insert(int pos) {
        System.out.print("Task ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Task Name: ");
        String name = sc.nextLine();
        System.out.print("Priority: ");
        int p = sc.nextInt();
        sc.nextLine();
        System.out.print("Due Date: ");
        String d = sc.nextLine();

        Node n = new Node(id, name, p, d);

        if (head == null) {
            head = n;
            n.next = head;
            current = head;
            return;
        }

        if (pos == 1) {
            Node temp = head;
            while (temp.next != head)
                temp = temp.next;

            n.next = head;
            temp.next = n;
            head = n;
            return;
        }

        Node temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++)
            temp = temp.next;

        n.next = temp.next;
        temp.next = n;
    }

    static void delete(int id) {
        if (head == null) return;

        Node temp = head, prev = null;

        do {
            if (temp.id == id) {
                if (temp == head && temp.next == head) {
                    head = current = null;
                    return;
                }

                if (prev == null) {
                    Node last = head;
                    while (last.next != head)
                        last = last.next;

                    head = head.next;
                    last.next = head;
                } else {
                    prev.next = temp.next;
                }

                if (current == temp)
                    current = temp.next;

                return;
            }

            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task not found");
    }

    static void viewCurrent() {
        if (current == null) {
            System.out.println("No tasks");
            return;
        }

        show(current);
    }

    static void nextTask() {
        if (current == null) {
            System.out.println("No tasks");
            return;
        }

        current = current.next;
        show(current);
    }

    static void show(Node n) {
        System.out.println(n.id + " " + n.name +
                " " + n.priority + " " + n.date);
    }

    static void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;
        do {
            show(temp);
            temp = temp.next;
        } while (temp != head);
    }

    static void search(int p) {
        if (head == null) return;

        Node temp = head;
        do {
            if (temp.priority == p)
                show(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Insert Beginning");
            System.out.println("2.Insert End");
            System.out.println("3.Insert Position");
            System.out.println("4.Delete Task");
            System.out.println("5.Current Task");
            System.out.println("6.Next Task");
            System.out.println("7.Display");
            System.out.println("8.Search Priority");
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
                    System.out.print("Task ID: ");
                    delete(sc.nextInt());
                    break;
                case 5: viewCurrent(); break;
                case 6: nextTask(); break;
                case 7: display(); break;
                case 8:
                    System.out.print("Priority: ");
                    search(sc.nextInt());
                    break;
                case 9: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
