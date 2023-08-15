/*
 * 
Write what the main method will print out once it is executed. It might be helpful
to draw box and pointer diagrams to keep track of variables.
 public class Shock {
    public static int bang;
    public static Shock baby;
    public Shock() {
        this.bang = 100;
    }
    public Shock (int num) {
        this.bang = num;
        baby = starter();
        this.bang += num;
    }
    public static Shock starter() {
        Shock gear = new Shock();
        return gear;
    }
    public static void shrink(Shock statik) {
        statik.bang -= 1;
    }
    public static void main(String[] args) {
        Shock gear = new Shock(200);
        System.out.println(gear.bang); 300
        shrink(gear);
        shrink(starter());
        System.out.println(gear.bang); 99
    }
}

For each function call in the main method, write out the x and y values of both
foobar and baz after executing that line. (Spring ’15, MT1)
public class Foo {
    public int x, y;

    public Foo (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void switcheroo (Foo a, Foo b) {
        Foo temp = a;
        a = b;
        b = temp;
    }

    public static void fliperoo (Foo a, Foo b) {
        Foo temp = new Foo(a.x, a.y);
        a.x = b.x;
        a.y = b.y;
        b.x = temp.x;
        b.y = temp.y;
    }

    public static void swaperoo (Foo a, Foo b) {
        Foo temp = a;
        a.x = b.x;
        a.y = b.y;
        b.x = temp.x;
        b.y = temp.y;
    }

    public static void main (String[] args) {
        Foo foobar = new Foo(10, 20);
        Foo baz = new Foo(30, 40);
        switcheroo(foobar, baz); foobar.x: 10 foobar.y: 20 baz.x: 30 baz.y: 40
        fliperoo(foobar, baz); foobar.x: 30 foobar.y: 40 baz.x: 10 baz.y: 20
        swaperoo(foobar, baz); foobar.x: 10 foobar.y: 20 baz.x: 10 baz.y: 20
    }
}
 */