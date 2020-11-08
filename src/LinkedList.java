import java.io.*;

public class LinkedList {
    Node head; // head of list

    public String[] args;

    public LinkedList(String[] args){
        this.args = args;
    }

    static class Node {

        int id;
        int year;
        int count;
        int price;
        int howMuchSold;
        String name;
        String singer;

        Node next;

        // Constructor
        Node(int i, int p, String n, String s, int y, int c, int k) {
            id = i;
            year = y;
            count = c;
            price = p;
            name = n;
            singer = s;
            howMuchSold = k;

            next = null;
        }
    }

    public static void insert(LinkedList list, int i, int p, String n, String s, int y, int c, int k)
    {
        // Create a new node with given data
        Node new_node = new Node(i,p,n,s,y,c,k);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null && last.id != i) {
                last = last.next;
            }
            if(last.id != i)
                last.next = new_node; // Insert the new_node at last node
        }

        // Return the list by head
    }

    public static void printList(LinkedList list, PrintStream x)
    {
        Node currNode = list.head;

        x.printf("%s %10s %30s %25s %15s %10s", "ID", "Price", "Name", "Singer", "Year", "Count");
        x.println("\n-------------------------------------------------------------------------------------------------");
        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            x.printf("%s %10s %30s %25s %15s %10s", currNode.id, currNode.price, currNode.name, currNode.singer, currNode.year, currNode.count);

            // Go to next node
            currNode = currNode.next;
            x.println();
        }
        x.println();
    }

    public static void deleteNode(LinkedList list ,int position)
    {
        // If linked list is empty
        if (list.head == null)
            return;

        // Store head node
        Node temp = list.head;

        // If head needs to be removed
        if (position == 0)
        {
            list.head = temp.next;   // Change head
            return;
        }

        // Find previous node of the node to be deleted
        for (int i=0; temp!=null && i<position-1; i++)
            temp = temp.next;

        // If position is more than number of nodes
        if (temp == null || temp.next == null)
            return;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        Node next = temp.next.next;
        temp.next = temp.next.next;  // Unlink the deleted node from list
    }

    static int search = 0;
    static int cash = 0;
    public static int searchPosition(LinkedList list, int x)
    {
        search = 0;
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.id == x){
                break;
            }
            current = current.next;
            search++;
        }
        return search;
    }

    public static void sell(LinkedList list, int x, PrintStream y)
    {
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.id == x && current.count > 0){
                cash += current.price;
                current.count--;
                current.howMuchSold++;
                y.print("CD Sold. ID: "+x);
                break;
            }
            else if(current.count <= 0 && current.id == x)
                y.print("Not avalible");
            current = current.next;
        }
    }

    public static void editName(LinkedList list, int x, String a, PrintStream y)
    {
        boolean flag = false;
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.id == x){
                current.name = a;
                y.printf("%s %10s %30s %25s %15s %10s", "ID", "Price", "Name", "Singer", "Year", "Count");
                y.println("\n-------------------------------------------------------------------------------------------------");
                y.printf("%s %10s %30s %25s %15s %10s", current.id, current.price, current.name, current.singer, current.year, current.count);
                flag = true;
                break;
            }
            current = current.next;
        }
        if(!flag)
            y.print("No Entry Was Found!");
    }
    public static void editSinger(LinkedList list, int x, String a, PrintStream y)
    {
        boolean flag = false;
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.id == x){
                current.singer = a;
                y.printf("%s %10s %30s %25s %15s %10s", "ID", "Price", "Name", "Singer", "Year", "Count");
                y.println("\n-------------------------------------------------------------------------------------------------");
                y.printf("%s %10s %30s %25s %15s %10s", current.id, current.price, current.name, current.singer, current.year, current.count);
                flag = true;
                break;
            }
            current = current.next;
        }
        if(!flag)
            y.print("No Entry Was Found!");
    }
    public static void editYear(LinkedList list, int x, int a, PrintStream y)
    {
        boolean flag = false;
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.id == x){
                current.year = a;
                y.printf("%s %10s %30s %25s %15s %10s", "ID", "Price", "Name", "Singer", "Year", "Count");
                y.println("\n-------------------------------------------------------------------------------------------------");
                y.printf("%s %10s %30s %25s %15s %10s", current.id, current.price, current.name, current.singer, current.year, current.count);
                flag = true;
                break;
            }
            current = current.next;
        }
        if(!flag)
            y.print("No Entry Was Found!");
    }
    public static void editCount(LinkedList list, int x, int a, PrintStream y)
    {
        boolean flag = false;
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.id == x){
                current.count = a;
                y.printf("%s %10s %30s %25s %15s %10s", "ID", "Price", "Name", "Singer", "Year", "Count");
                y.println("\n-------------------------------------------------------------------------------------------------");
                y.printf("%s %10s %30s %25s %15s %10s", current.id, current.price, current.name, current.singer, current.year, current.count);
                flag = true;
                break;
            }
            current = current.next;
        }
        if(!flag)
            y.print("No Entry Was Found!");
    }
    public static void editPrice(LinkedList list, int x, int a, PrintStream y)
    {
        boolean flag = false;
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.id == x){
                current.price = a;
                y.printf("%s %10s %30s %25s %15s %10s", "ID", "Price", "Name", "Singer", "Year", "Count");
                y.println("\n-------------------------------------------------------------------------------------------------");
                y.printf("%s %10s %30s %25s %15s %10s", current.id, current.price, current.name, current.singer, current.year, current.count);
                flag = true;
                break;
            }
            current = current.next;
        }
        if(!flag)
            y.print("No Entry Was Found!");
    }
    public static void searchAndFind(LinkedList list, String a, PrintStream y)
    {
        Node current = list.head;    //Initialize current
        y.printf("\n%s %10s %30s %25s %15s %10s", "ID", "Price", "Name", "Singer", "Year", "Count");
        y.println("\n-------------------------------------------------------------------------------------------------");
        while (current != null)
        {
            if (current.name.contains(a)){
                y.printf("%s %10s %30s %25s %15s %10s", current.id, current.price, current.name, current.singer, current.year, current.count);
                y.println();
            }
            current = current.next;
        }
    }
    public static void quit(LinkedList list, PrintStream y)
    {
        String winner = "";
        int max = 0;
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            if (current.howMuchSold >max){
              max = current.howMuchSold;
              winner = current.name;
            }
            current = current.next;
        }
        y.print("Quit");
        y.println();
        y.print("Cash: "+LinkedList.cash);
        y.println();
        y.print("Best Seller: "+winner);
    }

    public static void save(LinkedList list, PrintStream y)
    {
        Node current = list.head;    //Initialize current
        while (current != null)
        {
            y.print(current.id+ ";" + current.price+ ";" + current.name+ ";" + current.singer+ ";" + current.year+ ";" + current.count);
            y.println();
            current = current.next;
        }
    }
}