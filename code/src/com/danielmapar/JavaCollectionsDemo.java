package com.danielmapar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;


public class JavaCollectionsDemo {

  public enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
  }

  // All sets only care unique values (no duplicates)
  // https://www.devmedia.com.br/diferencas-entre-treeset-hashset-e-linkedhashset-em-java/29077
  public static void setsPlayground() {

    // HashSet is not sorted at all
    // Not in ascendant order and not based on the order that the keys were inserted.
    // However, complexity wise we are looking for an O(1) insertion and removal.
    // HashSet is the fastest of all Sets

    Set<String> demoHashSet = new HashSet<>();
    demoHashSet.add("Daniel");
    demoHashSet.add("Daniel");
    demoHashSet.add("Pedro");
    demoHashSet.add("Aldo");
    demoHashSet.add("Bruna");
    demoHashSet.remove("Pedro");

    System.out.println(demoHashSet);

    // TreeSets are always sorted in ascendant order
    // TreeSets are implemented using a Red-black tree -> add, remove, and search takes O(log(N)) time.

    Set<String> demoTreeSet = new TreeSet<>();
    demoTreeSet.add("Daniel");
    demoTreeSet.add("Daniel");
    demoTreeSet.add("Pedro");
    demoTreeSet.add("Aldo");
    demoTreeSet.add("Bruna");

    System.out.println(demoTreeSet);

    LinkedHashSet<String> demoLinkedSet = new LinkedHashSet<>();
    demoLinkedSet.add("Daniel");
    demoLinkedSet.add("Aldo");
    demoLinkedSet.add("Bruna");
    demoLinkedSet.add("Carla");
    demoLinkedSet.add("Jeff");

    // LinkedHashSet keep the ordered the elements were inserted
    // It is the middle ground between a HashSet and a TreeSet
    // LinkedHashSet also uses a HashTable, so most basic operations are O(1)
    Iterator<String> iterator = demoLinkedSet.iterator();
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }

    //System.out.println(demoLinkedSet);
  }

  public static void queuesAndStackPlayground() {

    /*
    * Queue Interface: It is an Interface that is a FirstIn – FirstOut Data Structure where the
    * elements are added from the back.
    * Deque Interface: It is a Doubly Ended Queue in which you can insert the elements from both
    * sides. It is an interface that implements the Queue.
    * */

    // ArrayDeque provides a way to apply resizable-array in addition to the implementation of the
    // Deque interface. It is also known as Array Double Ended Queue or Array Deck. This is a special
    // kind of array that grows and allows users to add or remove an element from both sides of the queue.

    Deque<String> demoArrayDeque = new ArrayDeque<>();
    demoArrayDeque.add("1");
    demoArrayDeque.add("2");
    demoArrayDeque.add("3");
    demoArrayDeque.add("4");
    demoArrayDeque.add("5");

    /*Iterator<String> iterator = demoArrayDeque.iterator();
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }*/

    /* while (demoArrayDeque.size() > 0) {
      String val = demoArrayDeque.pollFirst();
      System.out.println(val);
    }*/

    while (demoArrayDeque.size() > 0) {
      String first = demoArrayDeque.pollFirst();
      String last = demoArrayDeque.pollLast();
      System.out.println(first + last);
    }

    System.out.println(demoArrayDeque);

    // LinkedList Queue and Dequeue
    // We can also implement a Queue using a linked list. It does not contain all the benefits
    // from ArrayDeque (double-ended queue) but it is good enough for a great number of use cases.

    Queue<Integer> demoLinkedListQueue = new LinkedList<>();
    // Deque<Integer> demoLinkedListDeque = new LinkedList<>();

    demoLinkedListQueue.add(1);
    demoLinkedListQueue.add(2);
    demoLinkedListQueue.add(3);
    demoLinkedListQueue.add(4);
    demoLinkedListQueue.add(5);
    demoLinkedListQueue.add(6);

    System.out.println(demoLinkedListQueue);
    while (demoLinkedListQueue.size() > 0) {
      Integer val = demoLinkedListQueue.poll();
    }
    System.out.println(demoLinkedListQueue);

    /*
    * A PriorityQueue is used when the objects are supposed to be processed based on the priority.
    * It is known that a Queue follows the First-In-First-Out algorithm, but sometimes the elements
    * of the queue are needed to be processed according to the priority, that’s when the
    * PriorityQueue comes into play. The PriorityQueue is based on the priority heap.
    * The elements of the priority queue are ordered according to the natural ordering, or by a
    * Comparator provided at queue construction time, depending on which constructor is used.
    *
    * */

    // It provides O(log(n)) time for add and poll methods.

    PriorityQueue<Integer> demoPriorityQueue = new PriorityQueue<>();
    demoPriorityQueue.add(1);
    demoPriorityQueue.add(0);
    demoPriorityQueue.add(3);
    demoPriorityQueue.add(5);
    demoPriorityQueue.add(4);

    System.out.println(demoPriorityQueue);

    demoPriorityQueue.add(-1);

    System.out.println(demoPriorityQueue);

    demoPriorityQueue.poll();

    System.out.println(demoPriorityQueue);

    /*
    * A Stack can be implemented using the Dequeue interface of the Stack interface
    * We covered Dequeue previously, let's see the Stack class now (based on the Vector class)
    * */

    Stack<Integer> demoStack = new Stack<>();
    demoStack.add(1);
    demoStack.add(2);
    demoStack.add(3);
    demoStack.add(4);
    demoStack.add(5);

    demoStack.pop();

    System.out.println(demoStack);
  }

  public static void arrayAndListPlayground() {

    // ArrayList implements a list but using an Array on the background
    // As we add elements we may need to resize the array, that least to a O(n) copy
    // Besides that operations are pretty cheap O(1) insert
    List<String> demoArrayList = new ArrayList<>();
    demoArrayList.add("Daniel");
    demoArrayList.add("Pedro");
    demoArrayList.add("Joao");
    demoArrayList.add("Bruna");

    System.out.println(demoArrayList);

    // Similar to ArrayList, but it uses actual List nodes
    // The advantage of a LinkedList over ArrayList is that it has an infinite capacity (no need to move
    // elements to a new array when the capacity is over). The disadvantage is cache-locality
    // ArrayList uses an array, that guarantees a contiguous chunk of memory to be allocated
    List<String> demoLinkedList = new LinkedList<>();
    demoLinkedList.add("Daniel");
    demoLinkedList.add("Pedro");
    demoLinkedList.add("Joao");
    demoLinkedList.add("Bruna");

    System.out.println(demoLinkedList);

    /*
    *  Vector is synchronized, which means only one thread at a time can access the code, while
    *  ArrayList is not synchronized, which means multiple threads can work on ArrayList at the
    * same time.
    * A Vector defaults to doubling the size of its array, while the ArrayList increases its array size by 50 percent.
    * */

    List<String> demoVector = new Vector<>();
    demoVector.add("Daniel");
    demoVector.add("Pedro");
    demoVector.add("Joao");
    demoVector.add("Bruna");

    System.out.println(demoVector);

  }

  public static void mapsPlayground() throws InterruptedException {
    /*
    * A TreeMap is a HashMap that is ordered by keys in ascendant order. We could also supply
    * our own Comparable object.
    * Remember that a TreeMap is implemented using a Red-black tree (a balanced binary tree)
    * Most operations will be log n
    * */
    Map<Integer, String> demoTreeMap = new TreeMap<Integer, String>();
    demoTreeMap.put(2, "Daniel");
    demoTreeMap.put(1, "Pedro");
    demoTreeMap.put(4, "Aldo");
    demoTreeMap.put(3, "Arthur");
    demoTreeMap.put(6, "John");

    System.out.println(demoTreeMap);

    /*
     * A HashMap implements a HashTable. Most operations are O(1)
     * HashMaps are unordered by nature. They do not follow an order of insertion or any sorting order
     * */
    Map<Integer, String> demoHashMap = new HashMap<>();
    demoHashMap.put(200, "Daniel");
    demoHashMap.put(1, "Pedro");
    demoHashMap.put(4, "Aldo");
    demoHashMap.put(3, "Arthur");
    demoHashMap.put(6, "John");

    /*for (Integer key : demoHashMap.keySet()) {
      String val = demoHashMap.get(key);
      System.out.println(val);
    }*/

    System.out.println(demoHashMap);

    /*
     * A LinkedHashMap implements a HashTable and Linked List. Most operations are O(1)
     * LinkedHashMaps are ordered by nature. They follow the order of input aka add.
     * */

    Map<Integer, String> demoLinkedHashMap = new LinkedHashMap<>();
    demoLinkedHashMap.put(2, "Daniel");
    demoLinkedHashMap.put(1, "Pedro");
    demoLinkedHashMap.put(4, "Aldo");
    demoLinkedHashMap.put(3, "Arthur");
    demoLinkedHashMap.put(6, "John");

    System.out.println(demoLinkedHashMap);

    /*
    * Simply put, the WeakHashMap is a hashtable-based implementation of the Map interface,
    *  with keys that are of a WeakReference type.
     * An entry in a WeakHashMap will automatically be removed when its key is no longer in ordinary
     * use, meaning that there is no single Reference that point to that key. When the
     * garbage collection (GC) process discards a key, its entry is effectively removed from the
     * map, so this class behaves somewhat differently from other Map implementations.
    * */

    Object prime = new Object();
    // The variable prime has a strong reference to an Integer object with value 1. Any object which
    // has a strong reference pointing to it is not eligible for GC.

    /*
    * Simply put, an object that has a SoftReference pointing to it won't be garbage collected
    * until the JVM absolutely needs memory.
     * */
    // SoftReference<Integer> soft = new SoftReference<Integer>(prime);

    /*
    * The objects that are referenced only by weak references are garbage collected eagerly; the GC
    *  won't wait until it needs memory in that case.
     */
    // WeakReference<Integer> soft2 = new WeakReference<Integer>(prime);

    WeakHashMap<Object, String> demoWeakMap = new WeakHashMap<>();
    demoWeakMap.put(prime, "test");
    System.out.println(demoWeakMap);
    prime = null;
    System.gc();
    //Main m = new Main();
    //System.out.println("Waiting 10 seconds...");
    //synchronized (m) {
    //  m.wait(10000);
    //}
    System.out.println(demoWeakMap);

    /*
    * The IdentityHashMap implements Map interface using Hashtable, using reference-equality in
    * place of object-equality when comparing keys (and values). This class is not a general-purpose
    *  Map implementation. While this class implements the Map interface, it intentionally violates
    *  Map’s general contract, which mandates the use of the equals() method when comparing objects.
    * This class is used when the user requires the objects to be compared via reference. It belongs
    * to java.util package.
     * */
    Map<String, String> demoIdentityHashMap = new IdentityHashMap<>();
    demoIdentityHashMap.put(new String("Test"), "TEST");
    demoIdentityHashMap.put("Test", "ihmkey");
    demoIdentityHashMap.put("Test", "ihmkey1");


    System.out.println(demoIdentityHashMap);

    /*
    * EnumMap is a Map implementation that exclusively takes Enum as its keys.
    * In this tutorial, we'll discuss its properties, common use cases and when we should use it.
    * */

    EnumMap<DayOfWeek, String> demoEnumMap = new EnumMap<>(DayOfWeek.class);
    demoEnumMap.put(DayOfWeek.MONDAY, "Soccer");
    demoEnumMap.put(DayOfWeek.TUESDAY, "Basketball");

    System.out.println(demoEnumMap);

  }

  public static void main(String[] args) throws InterruptedException {
    // Sets
    // setsPlayground();

    // Queues and Stacks
    // queuesAndStackPlayground();

    // Array and Lists
    // arrayAndListPlayground();

    // Maps
    mapsPlayground();



  }
}
