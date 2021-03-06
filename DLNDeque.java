/*
DequeOfCards: Terry, Fabiola Radosav, Maxillian Korsun
APCS2 pd3
lab02: Deque Implementation
*/
import java.util.*;
import java.io.*;
public class DLNDeque<T> implements Deque<T> {

  private DLLNode<T> _front, _end;
  private int _size;

  public DLNDeque() {
    _front = _end = null;
    _size = 0;
  }

  public void addFirst(T e) {
    if (_size == 0) {// IF nothing is in the queue, add the first thing to the front, which is also the end
      _front = new DLLNode<T>(e, null, null);
      _end = _front;
    }
    else {
      _front.setBefore(new DLLNode<T>(e, null, _front));//otherwise, add something infront of the front and make it the front
      _front = _front.getBefore();
    }
    _size++;
  }

  public T removeFirst() {
    if (_size == 0)
      throw new NoSuchElementException();//cant remove anything
    T ret = _front.getCargo();
    _front = _front.getNext();//otherwise set _front to the next thing in the queue
    _size--;
    return ret;
  }

  // returns true if the given argument is in the deque
  public boolean contains(T e) {
    DLLNode<T> current = _front;
    for (int i = 0; i < _size; i++) {//Go through the queue until you do or dont find e
      if (current.getCargo().equals(e))
        return true;
      current = current.getNext();
    }
    return false;
  }

  // adds value at end of deque represented by queue
  public void addLast(T e) {
    if (_size == 0) {
      _end = new DLLNode<T>(e, null, null);// Same thing as in addFront()
      _front = _end;
    }
    else {
      _end.setNext(new DLLNode<T>(e, _end, null));// add something after the last thing and make that the new end
      _end = _end.getNext();
    }
    _size++;
  }

  // removes value at end of deque
  public T removeLast() {
    if (_size == 0)
      throw new NoSuchElementException();//If nothing to remove, you cant remove
    T ret = _end.getCargo();
    _end = _end.getBefore();//set end to the thing before end
    _size--;
    return ret;
  }

  public T peekFirst() {
    return _front.getCargo();
  }

  public T peekLast() {
    return _end.getCargo();
  }

  public int size(){
    return _size;
  }

  public String toString() {
    String ret = "";
    DLLNode current = _front;
    for (int i = 0; i < _size; i++) {
      ret += current.getCargo() + ", ";
      current = current.getNext();
    }
    return ret;
  }

  public static void main(String[] args) {
    DLNDeque<String> JohnProctor = new DLNDeque<String>();
    System.out.println("\nNow testing add methods: ");
    JohnProctor.addFirst("Abigail");
    JohnProctor.addLast("Elizabeth");
    JohnProctor.addFirst("Mary Warren");
    System.out.println(JohnProctor);

    System.out.println("\nNow testing peek methods: ");
    System.out.println(JohnProctor.peekFirst()); //return Mary Warren
    System.out.println(JohnProctor.peekLast()); //return Elizabeth

    System.out.println("\nNow testing remove methods: ");
    JohnProctor.removeLast(); //remove Elizabeth
    JohnProctor.removeFirst(); //remove Mary Warren
    System.out.println(JohnProctor);

    System.out.println("\nNow testing contain method: ");
    System.out.println(JohnProctor.contains("Abigail"));

    System.out.println("\nNow testing remove errors: ");
    JohnProctor.removeFirst();
    JohnProctor.removeLast(); //should return error
  } //end of main
} //end of class
