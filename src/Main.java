import java.io.*;
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) throws IOException {
        int i;
        /* Start with the empty list */
        LinkedList list = new LinkedList(args);
        int id1 = 0,year1 = 0,count1 = 0,price1 = 0;
        String name1 = "",singer1 = "";

        //The contents of data.txt have been loaded into the program.
        try {
            Scanner scan = new Scanner(new File(args[1]));
            while (scan.hasNextLine()) {

                String data = scan.nextLine();
                String[] readedData = data.split(";");
                LinkedList.insert(list,id1 = Integer.parseInt(readedData[0]),price1 = Integer.parseInt(readedData[1]),name1 = readedData[2],singer1 = readedData[3],year1 = Integer.parseInt(readedData[4]),count1 = Integer.parseInt(readedData[5]),0);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //The object required to write to the file has been created.
        //FileWriter myWriter = new FileWriter(args[2]);
        PrintStream output1 = new PrintStream(args[2]);
        PrintStream output = new PrintStream(args[1]);

        //The input.txt file has started to be read.
        try {
            Scanner scan = new Scanner(new File(args[0]));
            while (scan.hasNextLine()) {
                String data = scan.nextLine();

                // First, split on whitespace
                //String[] parts = data.split("(?<!\"\\w\")\\s+(?!\\w+\")"); //Alternate Solution

                String[] parts = data.split("\\s(?=\\w+:)");
                String[] parts1 = data.split("(?<!\"\\w\")\\s+(?!\\w+\")");
                String command1 = parts1[0];

                // The first element in the array is the command
                String command = parts[0];
                // Split the remaining elements on ':'
                String[] keyVal;
                String key = " ";
                String value;
                for (i = 1; i < parts.length; i++) {
                    keyVal = parts[i].split(":");
                    if (keyVal.length == 2) {
                        key = keyVal[0];
                        value = keyVal[1];
                        switch (key) {
                            case "id" -> id1 = Integer.parseInt(value);
                            case "name" -> name1 = value.substring(1, value.length() - 1);
                            case "singer" -> singer1 = value.substring(1, value.length() - 1);
                            case "year" -> year1 = Integer.parseInt(value);
                            case "count" -> count1 = Integer.parseInt(value);
                            case "price" -> price1 = Integer.parseInt(value);
                        }
                    }
                }
                switch (command1) {
                    case "Add" -> {
                        LinkedList.insert(list, id1, price1, name1, singer1, year1, count1, 0);                //DONE!
                        output1.print("New CD added id: "+id1+" name: "+name1);
                        output1.println();
                    }
                    case "Search" -> {
                        String[] key1 = command.split(" ");
                        String SearchKey = key1[1];
                        SearchKey = SearchKey.substring(1, SearchKey.length() - 1);
                        output1.print("\n\nSearch:"+SearchKey);
                        LinkedList.searchAndFind(list,SearchKey,output1);
                        output1.println();
                    }
                    case "Remove" -> {
                        LinkedList.deleteNode(list,LinkedList.searchPosition(list,id1));                  //DONE!
                        output1.print("CD removed id: "+id1);
                        output1.println();
                    }
                    case "List" -> {
                        output1.print("List:\n");
                        LinkedList.printList(list,output1);
                        output1.println();
                    }
                    case "Edit" -> {
                        output1.print("Edit CD id: "+id1);
                        output1.println();
                        switch (key) {
                            case "singer" -> LinkedList.editSinger(list, id1, singer1,output1);
                            case "name" -> LinkedList.editName(list, id1, name1,output1);
                            case "year" -> LinkedList.editYear(list, id1, year1,output1);                         //DONE!
                            case "count" -> LinkedList.editCount(list, id1, count1,output1);
                            case "price" -> LinkedList.editPrice(list, id1, price1,output1);
                        }
                        output1.println();
                    }
                    case ("Sell") -> {
                        LinkedList.sell(list,id1,output1);
                        output1.println();
                    }
                    case "Quit" -> {
                        LinkedList.quit(list,output1);
                        LinkedList.save(list,output);
                    }
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        output1.close();
    }
}