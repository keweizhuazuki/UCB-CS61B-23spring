import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item : q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted A Queue of unsorted items
     * @param pivot    The item to pivot on
     * @param less     An empty Queue. When the function completes, this queue will
     *                 contain
     *                 all of the items in unsorted that are less than the given
     *                 pivot.
     * @param equal    An empty Queue. When the function completes, this queue will
     *                 contain
     *                 all of the items in unsorted that are equal to the given
     *                 pivot.
     * @param greater  An empty Queue. When the function completes, this queue will
     *                 contain
     *                 all of the items in unsorted that are greater than the given
     *                 pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for (Item x : unsorted) {
            int cmp = pivot.compareTo(x);
            if (cmp > 0) {
                less.enqueue(x);
            } else if (cmp < 0) {
                greater.enqueue(x);
            } else {
                equal.enqueue(x);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        }
        Item pivot = getRandomItem(items);
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        partition(items, pivot, less, equal, greater);
        if (!less.isEmpty()) {
            less = quickSort(less);
        }
        if (!greater.isEmpty()) {
            greater = quickSort(greater);
        }
        return catenate(catenate(less, equal), greater);
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        q.enqueue("Cat");
        q.enqueue("Pig");
        q.enqueue("Dog");
        q.enqueue("Bird");
        q.enqueue("Fish");
        q.enqueue("Lion");
        q.enqueue("Tiger");
        q.enqueue("Elephant");
        q.enqueue("Monkey");
        q.enqueue("Zebra");
        q.enqueue("Giraffe");
        q.enqueue("Horse");
        q.enqueue("Cow");
        q.enqueue("Sheep");
        q.enqueue("Chicken");
        q.enqueue("Duck");
        q.enqueue("Goose");
        q.enqueue("Pigeon");
        q.enqueue("Rabbit");
        q.enqueue("Turtle");
        q.enqueue("Frog");
        q.enqueue("Snake");
        q.enqueue("Lizard");
        q.enqueue("Crocodile");
        q.enqueue("Alligator");
        q.enqueue("Dinosaur");
        q.enqueue("Spider");
        q.enqueue("Scorpion");
        q.enqueue("Worm");
        q.enqueue("Ant");
        q.enqueue("Bee");
        q.enqueue("Butterfly");
        q.enqueue("Dragonfly");
        q.enqueue("Mosquito");
        q.enqueue("Fly");
        q.enqueue("Beetle");
        q.enqueue("Ladybug");
        q.enqueue("Cockroach");
        q.enqueue("Cricket");
        q.enqueue("Centipede");
        q.enqueue("Millipede");
        q.enqueue("Snail");
        q.enqueue("Slug");
        q.enqueue("Starfish");
        System.out.println("The unsorted queue is: " + q);
        System.out.println("The sorted queue is: " + quickSort(q));
    }
}
