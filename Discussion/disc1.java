/**
 * 
 * Question 1
 * 
public class Pokemon {
 public String name;
 public int level;

 public Pokemon(String name, int level) {
 this.name = name;
 this.level = level;
 }

 public static void main(String[] args) {
 Pokemon p = new Pokemon("Pikachu", 17);
 int level = 100;
 change(p, level);
 System.out.println("Name: " + p.name + ", Level: " + p.level);
 }

 public static void change(Pokemon poke, int level) {
 poke.level = level;
 level = 50;
 poke = new Pokemon("Gengar", 1);
 }
 }

What would Java display?
Name: Pikachu Level: 100

On line 19, we set level equal to 50. What level do we mean? An instance
variable of the Pokemon class? The local variable containing the parameter to
the change method? The local variable in the main method? Something else?

The local variable containing the parameter to the change method. 


 * Question 2
 * 
public class Cat {
 public String name;
 public static String noise;

 public Cat(String name, String noise) {
 this.name = name;
 this.noise = noise;
 }

 public void play() {
 System.out.println(noise + " I'm " + name + " the cat!");
 }

 public static void anger() {
 noise = noise.toUpperCase();
 }
 public static void calm() {
 noise = noise.toLowerCase();
 }
 }

 Write what will happen after each call of play() in the following method.
 public static void main(String[] args) {
 Cat a = new Cat("Cream", "Meow!");
 Cat b = new Cat("Tubbs", "Nyan!");
 a.play(); Nyan! I'm Cream the cat!
 b.play(); Nyan! I'm Tubbs the cat!
 Cat.anger(); 
 a.calm();
 a.play(); nyan! I'm Cream the cat
 b.play(); nyan! I'm Tubbs the cat!
 }

Implement square and squareMutative which are static methods that both take in
an IntList L and return an IntList with its integer values all squared. square does
this non-mutatively with recursion by creating new IntLists while squareMutative
uses a iterative approach to change the instance variables of the input IntList L.

public static IntList square(IntList L) {
    if (L == null){
        return L;
    }else{
        return new Intlist(L.first * L.first, square(L.rest));
    }
}

public static IntList squareMutative(IntList L) {
    IntList M = L;
    while (M != null){
        M.first *= M.first;
        M = M.rest;
    }
    return L;
}

Extra: Now, implement square iteratively, and squareMutative recursively

public static Intlist square(IntList L){
    if (L == null){
        return L;
    }

    Intlist b = L.rest;
    Intlist SIntlist = new Intlist (L.first*L.first, null);
    Intlist c = SIntlist;

    while (b != null){
        c.rest = new Intlist (b.first*b.first, null);
        b = b.rest;
        c = c.rest;
    }
    return Sintlist;
}

public static Intlist squareMutative(IntList L) {
    if (L == null){
        return L;
    }else{
        L.first *= L.first;
        squareMutative(L.rest);
    }
    return L;
}
 */
