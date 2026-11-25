package org.gla.LinkedList;


import java.util.Scanner;

class Inventory {
    static class Node {
        int id, quantity;
        String name;
        double price;
        Node next;

        Node(int i, String n, int q, double p) {
            id = i;
            name = n;
            quantity = q;
            price = p;
        }
    }

    static Node head = null;
    static Scanner sc = new Scanner(System.in);

    static void insert(int pos) {
        System.out.print("Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Item Name: ");
        String name = sc.nextLine();
        System.out.print("Quantity: ");
        int q = sc.nextInt();
        System.out.print("Price: ");
        double p = sc.nextDouble();

        Node n = new Node(id, name, q, p);

        if (head == null || pos == 1) {
            n.next = head;
            head = n;
            return;
        }

        Node temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++)
            temp = temp.next;

        n.next = temp.next;
        temp.next = n;
    }

    static void delete(int id) {
        if (head == null) return;

        if (head.id == id) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.id != id)
            temp = temp.next;

        if (temp.next != null)
            temp.next = temp.next.next;
        else
            System.out.println("Item not found");
    }

    static void update(int id) {
        Node temp = head;

        while (temp != null) {
            if (temp.id == id) {
                System.out.print("New Quantity: ");
                temp.quantity = sc.nextInt();
                return;
            }
            temp = temp.next;
        }

        System.out.println("Item not found");
    }

    static void search() {
        System.out.println("1.Search by ID  2.Search by Name");
        int ch = sc.nextInt();
        sc.nextLine();

        Node temp = head;

        if (ch == 1) {
            System.out.print("Item ID: ");
            int id = sc.nextInt();

            while (temp != null) {
                if (temp.id == id) show(temp);
                temp = temp.next;
            }
        } else {
            System.out.print("Item Name: ");
            String name = sc.nextLine();

            while (temp != null) {
                if (temp.name.equalsIgnoreCase(name))
                    show(temp);
                temp = temp.next;
            }
        }
    }

    static void totalValue() {
        double total = 0;
        Node temp = head;

        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }

        System.out.println("Total Inventory Value: " + total);
    }

    static void sort(int ch, boolean asc) {
        for (Node i = head; i != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                boolean swap;

                if (ch == 1)
                    swap = asc ? i.name.compareToIgnoreCase(j.name) > 0
                            : i.name.compareToIgnoreCase(j.name) < 0;
                else
                    swap = asc ? i.price > j.price : i.price < j.price;

                if (swap) {
                    int id = i.id;
                    String name = i.name;
                    int q = i.quantity;
                    double p = i.price;

                    i.id = j.id;
                    i.name = j.name;
                    i.quantity = j.quantity;
                    i.price = j.price;

                    j.id = id;
                    j.name = name;
                    j.quantity = q;
                    j.price = p;
                }
            }
        }
    }

    static void show(Node n) {
        System.out.println(n.id + " " + n.name + " " +
                n.quantity + " " + n.price);
    }

    static void display() {
        Node temp = head;
        while (temp != null) {
            show(temp);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Insert Beginning");
            System.out.println("2.Insert End");
            System.out.println("3.Insert Position");
            System.out.println("4.Delete");
            System.out.println("5.Update Quantity");
            System.out.println("6.Search");
            System.out.println("7.Total Value");
            System.out.println("8.Sort");
            System.out.println("9.Display");
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
                    System.out.print("Item ID: ");
                    delete(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Item ID: ");
                    update(sc.nextInt());
                    break;
                case 6: search(); break;
                case 7: totalValue(); break;
                case 8:
                    System.out.println("1.Name 2.Price");
                    int type = sc.nextInt();
                    System.out.println("1.Ascending 2.Descending");
                    boolean asc = sc.nextInt() == 1;
                    sort(type, asc);
                    break;
                case 9: display(); break;
                case 10: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
