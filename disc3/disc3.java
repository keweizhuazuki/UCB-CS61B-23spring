public class SLList {
     private class IntNode {
     public int item;
     public IntNode next;
     public IntNode(int item, IntNode next) {
        this.item = item;
        this.next = next;
        }
     }
    
     private IntNode first;
    
     public void addFirst(int x) {
        first = new IntNode(x, first);
        }

/** 
Implement SLList.insert which takes in an integer x and inserts it at the given
position. If the position is after the end of the list, insert the new node at the end.
For example, if the SLList is 5 → 6 → 2, insert(10, 1) results in 5 → 10 → 6 → 2.
*/
    public void insert(int item, int position) {
        if (first == null || position == 0){
            addFirst(item);
            return ;
        }
        
        IntNode CurrentNode = first;
            while (position > 1 && CurrentNode.next != null){
                position --;
                CurrentNode = CurrentNode.next;
            }
            IntNode finalNode = new IntNode(item, CurrentNode.next);
            CurrentNode.next = finalNode;
    }
    
/** 
Add another method to the SLList class that reverses the elements. Do this using
the existing IntNodes (you should not use new).
*/
    public void reverse_iter() {
        IntNode x = null;
        IntNode y = first;
        while (y != null){
            IntNode z = y.next;
            y.next = x;
            x = y;
            y = z;
        }
        first = x;
        } 
    
    public void reverse_recr() {
        first = reverseRecursiveHelper(first);
    }
    private IntNode reverseRecursiveHelper(IntNode front) {
        if (front == null || front.next == null) {
            return front;
        } else {
            IntNode reversed = reverseRecursiveHelper(front.next);
            front.next.next = front;
            front.next = null;
            return reversed;
            }
    }

/**
Consider a method that inserts item into array arr at the given position. The
method should return the resulting array. For example, if x = [5, 9, 14, 15],
item = 6, and position = 2, then the method should return [5, 9, 6, 14, 15].
If position is past the end of the array, insert item at the end of the array.
Is it possible to write a version of this method that returns void and changes arr
in place (i.e., destructively)?

No since we can't change the array, only can create new one. 
 */
    public static int[] insert(int[] arr, int item, int position) {
        int[] answer = new int[arr.length + 1];
        position = Math.min(position, arr.length);
        for (int i = 0; i < position; i++){
            answer[i] = arr[i];
        }
        answer[position] = item;
        for (int i = position; i < arr.length; i++){
            answer[i+1] = arr[i];
        }
        return answer;
    }

/**
Consider a method that destructively reverses the items in arr. For example calling
reverse on an array [1, 2, 3] should change the array to be [3, 2, 1].
What is the fewest number of iteration steps you need? What is the fewest number
of additional variables you need?

1/2 of the array length needed.
1 number of additionla variables needed.
 */
    public static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++){
            int j = arr.length - i - 1;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
/**
Extra: Write a non-destructive method replicate(int[] arr) that replaces the
number at index i with arr[i] copies of itself. For example, replicate([3, 2,
1]) would return [3, 3, 3, 2, 2, 1].
 */
    public static int[] replicate(int[] arr) {
        int total = 0;
        for (int item : arr){
            total += item;
        }
        int[] answer = new int[total];
        int x = 0;
        for (int item : arr){
            for (int i = 0; i < item; i++){
                answer[x] = item;
                x ++;
            }
        }
        return answer;
    }
}
