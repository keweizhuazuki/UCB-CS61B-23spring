public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int size;
    private int length;
    private int front;
    private int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int addOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;

    }

    private void incrLength() {
        T[] newArray = (T[]) new Object[length * 2];
        int pos1 = front;
        int pos2 = length;
        while (pos1 != last) {
            newArray[pos2] = array[pos1];
            pos1 = addOne(pos1, length);
            pos2 = addOne(pos2, length * 2);
        }
        front = length;
        last = pos2;
        array = newArray;
        length *= 2;
    }

    private void decrLength() {
        T[] newArray = (T[]) new Object[length / 2];
        int pos1 = front;
        int pos2 = length / 4;
        while (pos1 != last) {
            newArray[pos2] = array[pos1];
            pos1 = addOne(pos1, length);
            pos2 = addOne(pos2, length / 2);
        }
        front = length / 4;
        last = pos2;
        array = newArray;
        length /= 2;
    }

    @Override
    public void addFirst(T item) {
        if (size == length - 1) {
            incrLength();
        }
        front = minusOne(front);
        array[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == length - 1) {
            incrLength();
        }
        array[last] = item;
        last = addOne(last, length);
        size++;
    }

    @Override
    public T removeFirst() {
        if (size >= 16 && length / size >= 4) {
            decrLength();
        }
        if (size == 0) {
            return null;
        }
        T temp = array[front];
        front = addOne(front, length);
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (size >= 16 && length / size >= 4) {
            decrLength();
        }
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        size--;
        return array[last];
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int temp = front;
        for (int i = 0; i < index; i++) {
            temp = addOne(temp, length);
        }
        return array[temp];
    }

    @Override
    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = addOne(ptr, length);
        }
    }
}