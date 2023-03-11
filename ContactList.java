import java.util.Scanner;

class Contact {
  public String name;
  public String phone;
  public String email;

  @Override
  public String toString() {
    System.out.println(super.toString());
    return String.format(
        "name: %s\nphone: %s\nemail: %s\n",
        this.name, this.phone, this.email);
  }
}

class Node {
  public Contact contact;
  public Node next;
  public Node prev;

  public Node(Contact contact) {
    this.contact = contact;
    this.next = null;
    this.prev = null;
  }
}

class DoubleLinkedList {
  private Node head;
  private Node tail;

  public DoubleLinkedList() {
    this.head = null;
    this.tail = null;
  }

  // the new node will be the head node.
  public void addFirst(Contact contact) {
    Node node = new Node(contact);

    if (this.head == null && this.tail == null) {
      // if the linked list is empty
      // the new node will be the head and the tail.
      this.head = node;
      this.tail = node;
    } else {
      // there is at least a node inside linked list.
      node.next = this.head;
      this.head.prev = node;
      this.head = node;
    }
  }

  public void addLast(Contact contact) {
    Node node = new Node(contact);

    if (this.head == null && this.tail == null) {
      // if the linked list is empty
      // the new node will be the head and the tail.
      this.head = node;
      this.tail = node;
    } else {
      this.tail.next = node;
      node.prev = this.tail;
      this.tail = node;
    }
  }

  public void printList() {
    Node current = this.head;

    if (current == null) {
      System.out.println("List is empty!\n");
    } else {
      while (current != null) {
        System.out.println(current.contact);
        current = current.next;
      }
    }
  }

  public Node searchNode(String email) {
    Node current = this.head;

    while (current != null) {
      if (current.contact.email.equals(email)) {
        return current;
      }

      current = current.next;
    }

    return null;
  }

  public void delete(Node node) {
    if (node == this.head && node == this.tail) {
      this.head = null;
      this.tail = null;
    } else if (node == this.head) {
      this.head = this.head.next;
      this.head.prev = null;
    } else if (node == this.tail) {
      this.tail = this.tail.prev;
      this.tail.next = null;
    } else {
      node.next.prev = node.prev;
      node.prev.next = node.next;
    }
  }
}

public class ContactList {
  public static void menu() {
    System.out.println("\n******************************");
    System.out.println("(A)dd");
    System.out.println("(D)elete");
    System.out.println("(E)mail Search");
    System.out.println("(P)rint List");
    System.out.println("(Q)uit");
    System.out.println("******************************");
    System.out.print("Please enter a command: ");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    DoubleLinkedList list = new DoubleLinkedList();

    String choice;

    do {
      menu();
      choice = scanner.next();

      scanner.nextLine();

      if (choice.equals("A")) {
        Contact contact = new Contact();
        System.out.print("Input name: ");
        contact.name = scanner.nextLine();

        System.out.print("Input phone: ");
        contact.phone = scanner.nextLine();

        System.out.print("Input email: ");
        contact.email = scanner.nextLine();

        // append the newly created contact at the tail of the linked list.
        list.addLast(contact);
        System.out.println("Add contact successful!\n");
      } else if (choice.equals("D")) {
        System.out.print("Delete contact (by email): ");
        String email = scanner.nextLine();

        Node node = list.searchNode(email);

        if (node == null) {
          System.out.println("Email is not found!\n");
        } else {
          list.delete(node);

          System.out.println("Delete contact successful!\n");
        }

      } else if (choice.equals("E")) {
        System.out.print("Search email: ");
        String email = scanner.nextLine();

        Node node = list.searchNode(email);

        if (node == null) {
          System.out.println("Email is not found!\n");
        } else {
          System.out.println(node.contact);
        }
      } else if (choice.equals("P")) {
        list.printList();
      } else if (choice.equals("Q")) {
        System.out.println("Thank you for using our applications!");
      } else {
        System.out.println("Please input a proper command!");
      }
    } while (!choice.equals("Q"));

    scanner.close();
  }
}
