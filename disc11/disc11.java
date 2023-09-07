package disc11;

public class disc11 {

    // Order the following big-O runtimes from smallest to largest.
    // Θ (1) ⇢ Θ (logn) ⇢ Θ (n) ⇢ Θ (nlogn) ⇢ Θ (n^2 logn) ⇢ Θ (n^3) ⇢ Θ (2^n) ⇢ Θ
    // (n!) ⇢ Θ (n^n)

    // Give the worst case and best case runtime in terms of M and N.Assume ping is
    // in
    // Θ(1) and returns an int.

    // int j = 0;
    // for(int i = N;i>0;i--){
    // for (; j <= M; j++) {
    // if (ping(i, j) > 64) {
    // break;
    // }
    // }
    // }
    // worst case：N+M, best case: N

    // Give the worst case and best case runtime where N = array.length. Assume
    // mrpoolsort(array) is in Θ(N log N).
    public static boolean mystery(int[] array) {
        array = mrpoolsort(array);
        int N = array.length;
        for (int i = 0; i < N; i += 1) {
            boolean x = false;
            for (int j = 0; j < N; j += 1) {
                if (i != j && array[i] == array[j])
                    x = true;
            }
            if (!x) {
                return false;
            }
        }
        return true;
    }
    // worst case: N^2, best case: N log N

    // Give the worst case and best case running time in Θ(·) notation in terms of M
    // and
    // N. Assume that comeOn() is in Θ(1) and returns a boolean.
    for(

    int i = 0;i<N;i+=1)
    {
        for (int j = 1; j <= M;) {
            if (comeOn()) {
                j += 1;
            } else {
                j *= 2;
            }
        }
    }
    // worst case: NM best case: N log M

    // Given an int x and a sorted array A of N distinct integers, design an
    // algorithm to
    // find if there exists indices i and j such that A[i] + A[j] == x.
    // Let’s start with the naive solution.
    public static boolean findSum(int[] A, int x) {
        for (int i = 0; i < A.length; i++){
        for (int j = 0; j < A.length; j++) {
            if (A[i] + A[j] == x) {
                return true;
                }
            }
        }
            return false;
    }

    // How can we improve this solution? Hint: Does order matter here?
    public static boolean findSumfaster(int[] A, int x){
        int left = 0;
        int right = A.length -1;
        while (left <= right) {
            if(A[left] + A[right] == x) {
                return true;
            }else if(A[left]+ A[right] < x){
                left++;
            }else{
                right--;
            }
            
        }

    }
    // What is the runtime of both the original and improved algorithm?
    // original worst: NN, best: 1, improved worst: N, best 1
}
