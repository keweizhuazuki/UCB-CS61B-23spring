package disc8;

public class disc8 {
    /*
     * Given an array of integers A and an integer k, return true if any two numbers
     * in the array sum up to k, and return false otherwise. How would you do this?
     * Give the main idea and what ADT you would use.
     * 
     * By using HashSet, create HashSet and added element to it. If in this HashSet,
     * there is k - i, i is the element in this array using for loop;
     */
    public boolean twosum(int[] A, int k) {
        Set<Interger> H = new HashSet<>();
        for (int i : A) {
            if (H.contains(k - i) && (k - i) != i) {
                return true;
            }
            H.add(i);
        }
        return false;
    }

    /*
     * Find the k most common words in a document. Assume that you can represent
     * this as an array of Strings, where each word is an element in the array. You
     * might find using multiple data structures useful.
     */
    public static void topKmostcommonwords(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (Strign word : words) {
            if (!counts.contains(word)) {
                counts.put(word, 1);
            } else {
                counts.put(word, counts.get(word) + 1);
            }
        }
        PriorityQueue<String> pq = new PriorityQUeue<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return counts.get(a) - counts.get(b);
            }
        });

        for (String word : counts.keySet()) {
            pq.add(word);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(pq.poll());
        }
    }

    /*
     * Define a Queue class that implements the push and poll methods of a queue
     * ADT using only a Stack class which implements the stack ADT.
     * Hint: Try using two stacks
     */

    public Queue<Item>{
    private Stack<Item> a;
    private Stack<Item> b;

    public Queue
    {
        a = new Stack<>();
        b = new Stack<>();
    }

    public void push(Item t) {
        while (!a.empty()) {
            b.push(a.poll());
        }
        a.push(t);
        while (!b.empty()) {
            a.push(b.poll());
        }
    }

    public Item poll() {
        a.poll();
    }
}

/*
 * Suppose we wanted a data structure SortedStack that takes in integers, and
 * maintains them in sorted order. SortedStack supports two operations: push(int
 * i) and pop(). Pushing puts an int onto our SortedStack, and popping returns
 * the next smallest item in the SortedStack. For example, if we inserted 10, 4,
 * 8,
 * 2, 14, and 3 into a SortedStack, and then popped everything off, we would get
 * 2, 3, 4, 8, 10, 14.
 */

public class SortedStack<Item extends Comparable<Item>>{
    private Stack<Item> a;
    private Stack<Item> b;

    public SortedStack{
        a = new Stack<>();
        b = new Stack<>();
    }

    public void push(Item t){
        while(!a.empty() && a.peek().compareTo(t) < 0){
            b.push(a.poll());
        }
        a.push(t);
        while(!b.empty()){
            a.push(b.poll());
        }
    }

    public Item poll(){
        return a.poll();
    }
}

}