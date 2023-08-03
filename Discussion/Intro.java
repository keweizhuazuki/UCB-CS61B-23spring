/* Implement fib which takes in an integer n and returns the nth Fibonacci number.
The Fibonacci sequence is 0,1,1,2,3,5,8,13,21,... 
*/
public class Intro {
    public static void main(String[] args) {
        System.out.println(fib(5));
        System.out.println(fib2(5, 0, 0, 1));
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 2) + fib(n - 1);
        }
    }

    /*Extra: Implement fib in 5 lines or fewer. Your answer must be efficient.
    */
    public static int fib2(int n, int k, int f0, int f1) {
        if (n == k){
            return f0;
        } else {
            return fib2(n, k + 1, f1, f0 + f1);
        }
    }
}
