package disc6;

public class Untitled-1
{

    public static void main(String[] args) {
        Dog d = new Corgi();
        Corgi c = new Corgi();
        
        d.play(d); Compile-Error Runtime-Error A B C D E    Compile-Error
        d.play(c); Compile-Error Runtime-Error A B C D E    Compile-Error
        c.play(d); Compile-Error Runtime-Error A B C D E    D
        c.play(c); Compile-Error Runtime-Error A B C D E    E
        
        c.bark(d); Compile-Error Runtime-Error A B C D E    C
        c.bark(c); Compile-Error Runtime-Error A B C D E    B
        d.bark(d); Compile-Error Runtime-Error A B C D E    A
        d.bark(c); Compile-Error Runtime-Error A B C D E    C
        }

    /*
     * Suppose Cat and Dog are two subclasses of the Animal class and the Tree class
     * is
     * unrelated to the Animal hierarchy. All four classes have default
     * constructors. For
     * each line below, determine whether it causes a compilation error, runtime
     * error, or
     * runs successfully. Consider each line independently of all other lines.
     * (extended
     * from Summer ’17, MT1)
     */
    public static void main(String[] args) {
        Cat c = new Animal(); //compile error
        Animal a = new Cat(); //fine
        Dog d = new Cat();  //compile error
        Tree t = new Animal(); //compile error
        
        Animal a = (Cat) new Cat(); //fine
        Animal a = (Animal) new Cat();  //fine
        Dog d = (Dog) new Animal(); //run time error
        Cat c = (Cat) new Dog();    //run time error 
        Animal a = (Animal) new Tree(); //compile error
        }

import java.util.NoSuchElementException;

public class SLListVista extends SLList {
    @Override
    public int idnexOf(int x) {
        if (x == 1) {
            throw new NoSuchElementException();
        }
        return index;
    }

}

public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, new LastIntNode());
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }

    public class LastIntNode extends IntNode {
        public LastIntNode() {
            super(0, null);
        }

        @Override
        public int max() {
            return 0;
        }
    }

    public int max() {
        return sentinel.next.max();
    }
}}
