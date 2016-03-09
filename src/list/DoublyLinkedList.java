

package list;


public class DoublyLinkedList extends List {


  protected DoublyLinkedListNode head;

  protected DoublyLinkedListNode newNode(Object item, DoublyLinkedList list,
    DoublyLinkedListNode prev, DoublyLinkedListNode next) {
    return new DoublyLinkedListNode(item, list, prev, next);
  }

  public DoublyLinkedList() {
    this.head = newNode(null,null,null,null);

    head.next=head;
    head.prev=head;
    size=0;
  }


  public void insertFront(Object item) {
    DoublyLinkedListNode h1=newNode(item, this, head, head.next);
    head.next=h1;
    head.next.next.prev=h1;
    size++;
  }

  public void insertBack(Object item) {
    DoublyLinkedListNode back1=newNode(item, this, head.prev,head);
    head.prev.next=back1;
    head.prev=back1;
    size++;
  }


  public ListNode front() {
    return head.next;
  }


  public ListNode back() {
    return head.prev;
  }


  public String toString() {
    String result = "[  ";
    DoublyLinkedListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  private static void testInvalidNode(ListNode p) {
    System.out.println("p.isValidNode() should be false: " + p.isValidNode());
    try {
      p.item();
      System.out.println("p.item() should throw an exception, but didn't.");
    } catch (InvalidNodeException lbe) {
      System.out.println("p.item() should throw an exception, and did.");
    }
    try {
      p.setItem(new Integer(0));
      System.out.println("p.setItem() should throw an exception, but didn't.");
    } catch (InvalidNodeException lbe) {
      System.out.println("p.setItem() should throw an exception, and did.");
    }
    try {
      p.next();
      System.out.println("p.next() should throw an exception, but didn't.");
    } catch (InvalidNodeException lbe) {
      System.out.println("p.next() should throw an exception, and did.");
    }
    try {
      p.prev();
      System.out.println("p.prev() should throw an exception, but didn't.");
    } catch (InvalidNodeException lbe) {
      System.out.println("p.prev() should throw an exception, and did.");
    }
    try {
      p.insertBefore(new Integer(1));
      System.out.println("p.insertBefore() should throw an exception, but " +
       "didn't.");
    } catch (InvalidNodeException lbe) {
      System.out.println("p.insertBefore() should throw an exception, and did."
       );
    }
    try {
      p.insertAfter(new Integer(1));
      System.out.println("p.insertAfter() should throw an exception, but " +
       "didn't.");
    } catch (InvalidNodeException lbe) {
      System.out.println("p.insertAfter() should throw an exception, and did."
       );
    }
    try {
      p.remove();
      System.out.println("p.remove() should throw an exception, but didn't.");
    } catch (InvalidNodeException lbe) {
      System.out.println("p.remove() should throw an exception, and did.");
    }
  }

  private static void testEmpty() {
    List l = new DoublyLinkedList();
    System.out.println("An empty list should be [  ]: " + l);
    System.out.println("l.isEmpty() should be true: " + l.isEmpty());
    System.out.println("l.length() should be 0: " + l.length());
    System.out.println("Finding front node p of l.");
    ListNode p = l.front();
    testInvalidNode(p);
    System.out.println("Finding back node p of l.");
    p = l.back();
    testInvalidNode(p);
    l.insertFront(new Integer(10));
    System.out.println("l after insertFront(10) should be [  10  ]: " + l);
  }

  public static void main(String[] argv) {
    testEmpty();
    List l = new DoublyLinkedList();
    l.insertFront(new Integer(3));
    l.insertFront(new Integer(2));
    l.insertFront(new Integer(1));
    System.out.println("l is a list of 3 elements: " + l);
    try {
      ListNode n;
      int i = 1;
      for (n = l.front(); n.isValidNode(); n = n.next()) {
       System.out.println("n.item() should be " + i + ": " + n.item());
       n.setItem(new Integer(((Integer) n.item()).intValue() * 2));
       System.out.println("n.item() should be " + 2 * i + ": " + n.item());
       i++;
     }
     System.out.println("After doubling all elements of l: " + l);
     testInvalidNode(n);

     i = 6;
     for (n = l.back(); n.isValidNode(); n = n.prev()) {
       System.out.println("n.item() should be " + i + ": " + n.item());
       n.setItem(new Integer(((Integer) n.item()).intValue() * 2));
       System.out.println("n.item() should be " + 2 * i + ": " + n.item());
       i = i - 2;
     }
     System.out.println("After doubling all elements of l again: " + l);
     testInvalidNode(n);

     n = l.front().next();
     System.out.println("Removing middle element (8) of l: " + n.item());
     n.remove();
     System.out.println("l is now: " + l);
     testInvalidNode(n);    
     n = l.back();
     System.out.println("Removing end element (12) of l: " + n.item());
     n.remove();
     System.out.println("l is now: " + l);
     testInvalidNode(n);    

     n = l.front();
     System.out.println("Removing first element (4) of l: " + n.item());
     n.remove();
     System.out.println("l is now: " + l);
     testInvalidNode(n);    
   } catch (InvalidNodeException lbe) {
    System.err.println ("Caught InvalidNodeException that should not happen."
      );
    System.err.println ("Aborting the testing code.");
  }
}
}

