public class LinkedListDeque<T> {
    public class Node{
        private T item;
        private Node pre;
        private Node next;
    
        public Node (T n, Node x, Node y){
            item = n;
            pre = x;
            next = y;
        }

        public Node (Node x, Node y){
            pre = x;
            next = y;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node(null,null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void addFirst(T item){
        Node Newlist = new Node(item, sentinel,sentinel.next);
        sentinel.next.pre = Newlist;
        sentinel.next = Newlist;
        size++;

    }

    public void addLast(T item){
        Node Newlist = new Node(item, sentinel.pre,sentinel);
        sentinel.pre.next = Newlist;
        sentinel.pre = Newlist;
        size++;
    }

    public int size(){
        return size;
    }


    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T x = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return x;
    }

    public T removeLast(){
        if (size ==0){
            return null;
        }
        T x = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        return x;
    }

    public T get(int index){
        if (index >= size){
            return null;
        }
        Node qwe = sentinel;
        for (int i = 0; i <= index; i++){
            qwe = qwe.next;
        }
        
        return qwe.item;
    }

    private T getRecursiveHelper(Node x, int index){
        if(index == 0){
            return x.item;
        }else{
            return getRecursiveHelper(x.next,index--);
        }
    }
    
    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public void printDeque(){
        Node x = sentinel.next;
        while (x != sentinel){
            System.out.print(x.item + " ");
            x = x.next;
        }
    }
}
