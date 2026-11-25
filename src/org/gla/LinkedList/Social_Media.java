package org.gla.LinkedList;


import java.util.*;

class Social_Media {
    static class User {
        int id, age;
        String name;
        ArrayList<Integer> friends = new ArrayList<>();
        User next;

        User(int i, String n, int a) {
            id = i;
            name = n;
            age = a;
        }
    }

    static User head = null;
    static Scanner sc = new Scanner(System.in);

    static User findUser(int id) {
        User temp = head;

        while (temp != null) {
            if (temp.id == id)
                return temp;
            temp = temp.next;
        }

        return null;
    }

    static void addUser() {
        System.out.print("User ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();

        User n = new User(id, name, age);

        if (head == null) {
            head = n;
        } else {
            User temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = n;
        }
    }

    static void addFriend() {
        System.out.print("First User ID: ");
        int id1 = sc.nextInt();
        System.out.print("Second User ID: ");
        int id2 = sc.nextInt();

        User u1 = findUser(id1);
        User u2 = findUser(id2);

        if (u1 == null || u2 == null || id1 == id2) {
            System.out.println("Invalid users");
            return;
        }

        if (!u1.friends.contains(id2))
            u1.friends.add(id2);

        if (!u2.friends.contains(id1))
            u2.friends.add(id1);
    }

    static void removeFriend() {
        System.out.print("First User ID: ");
        int id1 = sc.nextInt();
        System.out.print("Second User ID: ");
        int id2 = sc.nextInt();

        User u1 = findUser(id1);
        User u2 = findUser(id2);

        if (u1 != null && u2 != null) {
            u1.friends.remove(Integer.valueOf(id2));
            u2.friends.remove(Integer.valueOf(id1));
        }
    }

    static void mutualFriends() {
        System.out.print("First User ID: ");
        int id1 = sc.nextInt();
        System.out.print("Second User ID: ");
        int id2 = sc.nextInt();

        User u1 = findUser(id1);
        User u2 = findUser(id2);

        if (u1 == null || u2 == null) return;

        System.out.println("Mutual Friends:");

        for (int id : u1.friends) {
            if (u2.friends.contains(id))
                System.out.println(id);
        }
    }

    static void displayFriends() {
        System.out.print("User ID: ");
        int id = sc.nextInt();

        User u = findUser(id);

        if (u == null) {
            System.out.println("User not found");
            return;
        }

        for (int friend : u.friends)
            System.out.println(friend);
    }

    static void search() {
        System.out.println("1.Search by ID 2.Search by Name");
        int ch = sc.nextInt();
        sc.nextLine();

        User temp = head;

        if (ch == 1) {
            int id = sc.nextInt();

            while (temp != null) {
                if (temp.id == id) {
                    System.out.println(temp.id + " " +
                            temp.name + " " + temp.age);
                    return;
                }
                temp = temp.next;
            }
        } else {
            String name = sc.nextLine();

            while (temp != null) {
                if (temp.name.equalsIgnoreCase(name)) {
                    System.out.println(temp.id + " " +
                            temp.name + " " + temp.age);
                    return;
                }
                temp = temp.next;
            }
        }

        System.out.println("User not found");
    }

    static void countFriends() {
        User temp = head;

        while (temp != null) {
            System.out.println(temp.name + ": " +
                    temp.friends.size() + " friends");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add User");
            System.out.println("2.Add Friend");
            System.out.println("3.Remove Friend");
            System.out.println("4.Mutual Friends");
            System.out.println("5.Display Friends");
            System.out.println("6.Search User");
            System.out.println("7.Count Friends");
            System.out.println("8.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addUser(); break;
                case 2: addFriend(); break;
                case 3: removeFriend(); break;
                case 4: mutualFriends(); break;
                case 5: displayFriends(); break;
                case 6: search(); break;
                case 7: countFriends(); break;
                case 8: return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
