package org.gla.LinkedList;


import java.util.Scanner;

class Online_Ticket {
    static class Node {
        int id, seat;
        String customer, movie, time;
        Node next;

        Node(int i, String c, String m, int s, String t) {
            id = i;
            customer = c;
            movie = m;
            seat = s;
            time = t;
        }
    }

    static Node head = null, tail = null;
    static Scanner sc = new Scanner(System.in);

    static void addTicket() {
        System.out.print("Ticket ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Customer Name: ");
        String c = sc.nextLine();
        System.out.print("Movie Name: ");
        String m = sc.nextLine();
        System.out.print("Seat Number: ");
        int s = sc.nextInt();
        sc.nextLine();
        System.out.print("Booking Time: ");
        String t = sc.nextLine();

        Node n = new Node(id, c, m, s, t);

        if (head == null) {
            head = tail = n;
            n.next = head;
        } else {
            tail.next = n;
            tail = n;
            tail.next = head;
        }
    }

    static void deleteTicket(int id) {
        if (head == null) {
            System.out.println("No tickets");
            return;
        }

        Node temp = head, prev = tail;

        do {
            if (temp.id == id) {
                if (head == tail) {
                    head = tail = null;
                } else {
                    prev.next = temp.next;

                    if (temp == head)
                        head = temp.next;

                    if (temp == tail)
                        tail = prev;

                    tail.next = head;
                }

                System.out.println("Ticket Deleted");
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);

        System.out.println("Ticket not found");
    }

    static void display() {
        if (head == null) {
            System.out.println("No bookings");
            return;
        }

        Node temp = head;

        do {
            show(temp);
            temp = temp.next;
        } while (temp != head);
    }

    static void show(Node n) {
        System.out.println(n.id + " " + n.customer +
                " " + n.movie + " " + n.seat + " " + n.time);
    }

    static void search() {
        if (head == null) {
            System.out.println("No bookings");
            return;
        }

        System.out.println("1.Customer Name 2.Movie Name");
        int ch = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String value = sc.nextLine();

        Node temp = head;
        boolean found = false;

        do {
            if ((ch == 1 && temp.customer.equalsIgnoreCase(value)) ||
                    (ch == 2 && temp.movie.equalsIgnoreCase(value))) {
                show(temp);
                found = true;
            }

            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("Ticket not found");
    }

    static void count() {
        if (head == null) {
            System.out.println("Total Tickets: 0");
            return;
        }

        int count = 0;
        Node temp = head;

        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Total Booked Tickets: " + count);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add Ticket");
            System.out.println("2.Remove Ticket");
            System.out.println("3.Display Tickets");
            System.out.println("4.Search Ticket");
            System.out.println("5.Count Tickets");
            System.out.println("6.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addTicket(); break;
                case 2:
                    System.out.print("Ticket ID: ");
                    deleteTicket(sc.nextInt());
                    break;
                case 3: display(); break;
                case 4: search(); break;
                case 5: count(); break;
                case 6: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
