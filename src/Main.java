import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(rand.nextInt(100000));
            Student value = new Student("Student" + i);
            table.put(key, value);
        }

        table.printBucketSizes();

        System.out.println("\nTesting BST:");

        BST<Integer, String> bst = new BST<>();
        bst.put(10, "ten");
        bst.put(5, "five");
        bst.put(15, "fifteen");

        for (var elem : bst) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
