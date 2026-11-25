package org.gla.LinkedList;


import java.util.Scanner;

class Main {
    static class Node {
        int roll, age;
        String name, grade;
        Node next;

        Node(int r, String n, int a, String g) {
            roll = r;
            name = n;
            age = a;
            grade = g;
            next = null;
        }
    }

    static Node head = null;
    static Scanner sc = new Scanner(System.in);

    static void insert(int pos) {
        System.out.print("Roll No: ");
        int r = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String n = sc.nextLine();
        System.out.print("Age: ");
        int a = sc.nextInt();
        sc.nextLine();
        System.out.print("Grade: ");
        String g = sc.nextLine();

        Node newNode = new Node(r, n, a, g);

        if (pos == 1 || head == null) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++)
            temp = temp.next;

        newNode.next = temp.next;
        temp.next = newNode;
    }

    static void delete(int r) {
        if (head == null) return;

        if (head.roll == r) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.roll != r)
            temp = temp.next;

        if (temp.next != null)
            temp.next = temp.next.next;
        else
            System.out.println("Record not found");
    }

    static void search(int r) {
        Node temp = head;

        while (temp != null) {
            if (temp.roll == r) {
                System.out.println(temp.roll + " " + temp.name +
                        " " + temp.age + " " + temp.grade);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Student not found");
    }

    static void update(int r) {
        Node temp = head;

        while (temp != null) {
            if (temp.roll == r) {
                System.out.print("Enter new grade: ");
                temp.grade = sc.next();
                return;
            }
            temp = temp.next;
        }

        System.out.println("Student not found");
    }

    static void display() {
        Node temp = head;

        if (temp == null) {
            System.out.println("List is empty");
            return;
        }

        while (temp != null) {
            System.out.println(temp.roll + " " + temp.name +
                    " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Insert Beginning");
            System.out.println("2.Insert End");
            System.out.println("3.Insert Position");
            System.out.println("4.Delete");
            System.out.println("5.Search");
            System.out.println("6.Update Grade");
            System.out.println("7.Display");
            System.out.println("8.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: insert(1); break;
                case 2: insert(Integer.MAX_VALUE); break;
                case 3:
                    System.out.print("Position: ");
                    insert(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Roll No: ");
                    delete(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Roll No: ");
                    search(sc.nextInt());
                    break;
                case 6:
                    System.out.print("Roll No: ");
                    update(sc.nextInt());
                    break;
                case 7: display(); break;
                case 8: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}

