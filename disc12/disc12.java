// Is This a BST?

public static boolean isBSTBad(TreeNode T) {
    if (T == null) {
        return true;
    } else if (T.left != null && T.left.val > T.val) {
        return false;
    } else if (T.right != null && T.right.val < T.val) {
        return false;
    } else {
        return isBSTBad(T.left) && isBSTBad(T.right);
    }
    } 
/* 
No since it only checks if the left side is smaller and the right side is greater than the node value. Which isn't correct
since the value in the left side of the tree should always smaller than the main node. 
*/

public static boolean isBSTGood(TreeNode T) {
    return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE );
    

}

public static boolean isBSTHelper(TreeNode T, int min, int max) {
    mainnum = T.val;
    if (T == null){
        return true;
    }else if (T.val < min || T.val > max){
        return false;
    }else {
        return isBSTHelper(T.left, min, T.val) && isBSTHelper(T.right, max, T.val);
    }
}

/* 
Here are three potential implementations of the Integer’s hashCode() function.
Categorize each as either a valid or an invalid hash function. If it is invalid, explain
why. If it is valid, point out a flaw or disadvantage. 
*/

public int hashCode() {
    return -1;
}

/* 
 * Valid althought it will always return -1;
 */

public int hashCode() {
    return intValue() * intValue();
}

/* 
 * Valid althought positive and negative numbers will have the same hashcode.
 */

public int hashCode() {
    return super.hashCode();
}

/* 
 * Not valid since same number will have difference hasCode. 
 */


/* 
Extra, but highly recommended: For each of the following questions, answer Always,
Sometimes, or Never.
    (a) When you modify a key that has been inserted into a HashMap will you be able
    to retrieve that entry again? Explain.
    
        Sometimes

    (b) When you modify a value that has been inserted into a HashMap will you be
    able to retrieve that entry again? Explain.  

        Always, change the value doesn't affect the look up process.
*/
