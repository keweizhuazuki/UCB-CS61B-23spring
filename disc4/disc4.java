/**
Write a method flatten that takes in a 2-D array x and returns a 1-D array that
contains all of the arrays in x concatenated together.
For example, flatten({{1, 2, 3}, {}, {7, 8}}) should return {1, 2, 3, 7, 8}.
(Summer 2016 MT1)
 */

public static int[] flatten(int[][] x) {
    int totalLength = 0;
    for (int i = 0; i < x.length; i++) {
        totalLength += x[i].length;
    }
    
    int[] a = new int[totalLength];
    int aIndex = 0;
    
    for (int i = 0; i < x.length; i++) {
        for (int y = 0; y < x[i].length; y++){
            a[aIndex] = x[i][y];
            aIndex ++;
        }
    }
    
    return a;
    }

/**
Suppose we have the following IntList class, as defined in lecture and lab, with an
added skippify function.
Suppose that we define two IntLists as follows.
1 IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
2 IntList B = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);
Fill in the method skippify such that the result of calling skippify on A and B
are as below:
- After calling A.skippify(), A: (1, 3, 6, 10)
- After calling B.skippify(), B: (9, 7, 4)
(Spring ’17, MT1)
 */

public class IntList {
    public int first;
    public IntList rest;
    
    @Override
    public boolean equals(Object o) { ... }
    public static IntList list(int... args) { ... }
    
    public void skippify() {
     IntList p = this;
     int n = 1;
     while (p != null) {
    
     IntList next = p.rest;
    
     for (int i = 0; i < n; i ++ ) {
        if (next == null) {
            break;
        }
        next = next.rest;
    }
    p.rest = next;
    p = p.rest;
    n ++;

     }
     }
}

/**
Fill in the blanks below to correctly implement removeDuplicates.
(Spring ’17, MT1)
 */
public class IntList {
    public int first;
    public IntList rest;
     public IntList (int f, IntList r) {
     this.first = f;
     this.rest = r;
     }
    
     /**
     * Given a sorted linked list of items - remove duplicates.
     * For example given 1 -> 2 -> 2 -> 2 -> 3,
     * Mutate it to become 1 -> 2 -> 3 (destructively)
     */
     public static void removeDuplicates(IntList p) {
        if (p == null) {
        return;
     }
    
     IntList current = p.rest;
    
     IntList previous = p;
    
     while (p.rest != null) {

     if (previous.first == current.first) {
    
        previous.rest = current.rest;
     } else {
        previous = current;
    }
    current = current.rest;
     }
     }
    }