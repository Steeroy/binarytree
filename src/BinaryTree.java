import java.io.*;
import java.util.Scanner;

/**
* Siyanda Mvunyiswa
* BinaryTree Exercise
*/

public class BinaryTree {
    static int number = 0;

    /** Creates the Binary tree taking in the array and the starting and end point of the array */
    static BinaryNode buildBinaryTree(int[] array, int begin, int end){
        if(begin > end){  //checks if the array is still in bound
            return null;  //returns null so the next node can be null
        }

        int mid = (begin + end)/2;  //finds the middle index

        /** This adds and returns the node we are going to/finished add/added the array item */
        return new BinaryNode(array[mid],  //Assigns the root node of the true/subtree
                buildBinaryTree(array, begin, mid-1),  //recursively assigns the left node of the tree/subtree
                buildBinaryTree(array, mid+1, end));  //recursively assigns the right node of the tree/subtree
    }

    /** This method when called, returns the number of odd items contained in the binary tree,
     * if there are none it will return 0, which is the variable number initialised with 0
     * if there is, variable number will increment and be returns and will give us the number of odd items */
    static int addEntries(BinaryNode node){
        if(node == null){  //checks if the node is null, returns -1 since there are none/this also goes for no root node
            return -1;
        }

        addEntries(node.getLeft());  //recursively checks the left node
        addEntries(node.getRight()); //recursively checks the right node
        if(node.getElement()%2 == 1) {  //checks the int element in our node if it is an odd number
            //increments and returns the variable number, which is number of odd items
            number++;
            return number;
        }
        return number;
    }
    public static void main(String[] args){
        try{
            int numItems = 0;   //This variable will be used to store number of item in our file
            File myData = new File("Input.txt");  //Locate the file that has our integer numbers and assigns it to myData
            FileReader file = new FileReader(myData);      //Reads the data on the file and assign it to file
            LineNumberReader num = new LineNumberReader(file);  //Reads the lines in our data, which is file

            while(num.readLine() != null){  //Checks if there is a next line, and will stop if there is none
                numItems++;                 //Increments numItems which shows the number of lines our file has
            }
            int[] treeItems = new int[numItems];  //an array with a size equal to the number of line/items in our file

            Scanner scan = new Scanner(myData);

            for(int i = 0; i < treeItems.length; i++){
                treeItems[i] = scan.nextInt();    //assigns each integer value to its place in our array
            }

            BinaryNode root = buildBinaryTree(treeItems, 0, treeItems.length - 1); //This constructs a binary tree where root is our root node/reference
            assert root != null;
            System.out.println("These are our contents using printInOrder method:");
            root.printInOrder();       //This prints the items in InOrder method, calling the printInOrder in our BinaryNode, and being our root node
            System.out.println();
            System.out.println("These are our contents using printPreOrder method:");
            root.printPreOrder();      //This prints the items in InOrder method, calling the printPreOrder in our BinaryNode, and being our root node
            System.out.println();
            System.out.println("This is the number of odd numbers in our tree: " + addEntries(root));  //Calls our addEntries method which return the int value number of odd number
        } catch (IOException e) {
            System.out.println("An error has occured, file not found");  //If our file is not found it will print this
        }
    }
}