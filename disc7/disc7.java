/* 
2.1
(a) Given a news article, find the frequency of each word used in the article.
    Map
(b) Given an unsorted array of integers, return the array sorted from least to
greatest.
    Priority queue
(c) Implement the forward and back buttons for a web browser.
    Stack
 */

/* 
2.2
BiDividerMap
put(k, V); // put a key, value pair
getByKey(K); // get the value corresponding to a key
getByValue(V); // get the key corresponding to a value
numLessThan(K); // return number of keys in map less than K

(a) Describe how you could implement this ADT by using existing Java ADTs as
building blocks. Come up with an idea that is correct first before trying to
make it more efficient.
    Using Map(k, V) and Map(V, k)
    when put (k, V) is called, it must adds k and V both Map
    when numLessThank is called, it must sort it first;

(b) Next, Suppose we would like to invent a new ADT called MedianFinder which
is a collection of integers and supports finding the median of the collection.
MedianFinder
add(x); // adds x to the collection of numbers
median(); // returns the median from a collection of numbers

Describe how you could implement this ADT by using existing Java ADTs as
building blocks. What’s the most efficient implementation you can come up
with? 

Using List
*/

/* 
Define a Queue class that implements the push and poll methods of a queue ADT
using only a Stack class which implements the stack ADT. 
A stack is a last-in, first-out ADT: elements are always added or removed from one
end of the data structure. A queue is a first-in, first-out ADT. Both data types
support three basic operations: push(e) which adds an element, peek() which
returns the next element, and poll() which returns and removes the next element.
*/

import java.util.Stack;

public class Queue<E> {
    private Stack<E> stack = new Stack<E>();

    public void push(E element) {
        stack.push(element);
    }

    public E poll() {
        Stack<E> temp = new Stack<E>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        E toPop = temp.pop();
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return toPop;
    }
}
