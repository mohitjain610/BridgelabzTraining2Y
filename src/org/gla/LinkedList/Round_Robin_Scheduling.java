package org.gla.LinkedList;


import java.util.Scanner;

class Round_Robin_Scheduling {
    static class Node {
        int id, burst, remaining, waiting, turnaround;
        Node next;

        Node(int i, int b) {
            id = i;
            burst = b;
            remaining = b;
        }
    }

    static Node head = null, tail = null;
    static Scanner sc = new Scanner(System.in);

    static void add(int id, int burst) {
        Node n = new Node(id, burst);

        if (head == null) {
            head = tail = n;
            n.next = head;
        } else {
            tail.next = n;
            tail = n;
            tail.next = head;
        }
    }

    static void display() {
        if (head == null) {
            System.out.println("Queue is empty");
            return;
        }

        Node temp = head;
        do {
            System.out.println("P" + temp.id +
                    " Remaining: " + temp.remaining);
            temp = temp.next;
        } while (temp != head);
    }

    static void schedule(int quantum) {
        if (head == null) return;

        int time = 0, totalWaiting = 0, totalTurnaround = 0;
        int count = 0;

        Node current = head;
        Node prev = tail;

        while (head != null) {
            int execute = Math.min(quantum, current.remaining);
            current.remaining -= execute;
            time += execute;

            System.out.println("P" + current.id +
                    " executes for " + execute);

            if (current.remaining == 0) {
                current.turnaround = time;
                current.waiting = time - current.burst;

                totalWaiting += current.waiting;
                totalTurnaround += current.turnaround;
                count++;

                System.out.println("Completed P" + current.id);

                if (current == head && current == tail) {
                    head = tail = null;
                    break;
                }

                if (current == head)
                    head = current.next;
                if (current == tail)
                    tail = prev;

                prev.next = current.next;
                current = prev.next;
            } else {
                prev = current;
                current = current.next;
            }

            System.out.println("Queue after round:");
            display();
        }

        if (count > 0) {
            System.out.println("Average Waiting Time: " +
                    (double) totalWaiting / count);
            System.out.println("Average Turnaround Time: " +
                    (double) totalTurnaround / count);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add Process");
            System.out.println("2.Display Queue");
            System.out.println("3.Run Round Robin");
            System.out.println("4.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Process ID: ");
                    int id = sc.nextInt();
                    System.out.print("Burst Time: ");
                    int b = sc.nextInt();
                    add(id, b);
                    break;

                case 2: display(); break;

                case 3:
                    System.out.print("Time Quantum: ");
                    schedule(sc.nextInt());
                    break;

                case 4: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
