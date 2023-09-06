package disc9;

public class disc9 {
    /*
     * A class is immutable if nothing about its instances can change after they are
     * constructed.
     * Which of the following classes are immutable?
     */

    public class Pebble {
        public int weight;

        public Pebble() {
            weight = 1;
        }
    }

    /* Mutable */

    public class Rock {
        public final int weight;

        public Rock(int w) {
            weight = w;
        }
    }

    /* Immutable */

    public class Rocks {
        public final Rock[] rocks;

        public Rocks(Rock[] rox) {
            rocks = rox;
        }
    }

    /* Mutable */

    public class SecretRocks {
        private Rock[] rocks;

        public SecretRocks(Rock[] rox) {
            rocks = rox;
        }
    }

    /* Mutable */

    public class PunkRock {

        private final Rock[] band;

        public PunkRock(Rock yRoad) {
            band={yRoad};
        }

        public Rock[] myBand() {
            return band;
        }
    }

    /* Mutable */

    public class MommaRock {
        public static final Pebble baby = new Pebble();
    }
}

/* Mutable */

/* Below is a flawed implementation of the stack ADT. */
public class BadIntegerStack {
    public class Node(){
        public Integer value;
        public Node prev;

        public Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }

    public Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Integer num) {
        top = new Node(num, top);
    }

    public Integer pop() {
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }

    public Integer peek() {
        return top.value;
    }

}

    /*
     * Exploit the flaw by filling in the main method below so that it prints
     * “Success”
     * by causing BadIntegerStack to produce a NullPointerException.
     */

    public static void main(String[] args) {
        try {
            BadIntegerStack stack = new BadIntegerStack();
            stack.pop();
        } catch (NullPointerException e) {
            // TODO: handle exception
            System.out.println("Success");
        }
    }

    /*
     * Exploit another flaw in the stack by completing the main method below so that
     * the stack appears infinitely tall.
     */

 public static void main(String[] args) {
    BadIntegerStack stack = new BadIntegerStack();
    stack.push(1);
    stack.top.prev = stack.top;
    while (!isEmpty()){
        stack.pop();
    }
    System.out.println("It will never print");

 }

/*
 * How can we change the BadIntegerStack class so that it won’t throw
 * NullPointerException
 * s or allow ne’er-do-wells to produce endless stacks?
 */

/*
 * Private the top so they won't have the access to top even they are in the
 * same Package.
 */