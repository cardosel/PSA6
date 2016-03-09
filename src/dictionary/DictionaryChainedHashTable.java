/* DictionaryChainedHashTable.java */

package dictionary;
import list.*;


/**
 *
 *  Uses a dictionary as a hash table. All keys have a hashcode method to determine
 *  which place in the hash table an entry is stored in. Each hashcode returns an int
 *  between the integer's min value and max value. DictionaryChainedHashTable class 
 *  uses a compression function which links the hashcode to a place in the hash table's range.
 *  
 *  
 **/

public class DictionaryChainedHashTable implements DictionaryADT{

  
  protected DoublyLinkedList[] myTable;
  protected int size;

  /** 
   *  Construct a new empty hash table intended to hold approximate size of
   *  entries.  
   **/

  public DictionaryChainedHashTable(int sizeApproximated) {

    while (!prime(sizeApproximated)) {
      sizeApproximated++;
    }
    myTable=new DoublyLinkedList[sizeApproximated];
    size=0;
  }

  /** 
   *  Construct a new empty hash table with a fixed size.
   **/

  public DictionaryChainedHashTable() {
    myTable=new DoublyLinkedList[101];
    size=0;
  }

  private boolean prime(int n) {
    if (n%2==0 && n!=2) {
      return false;
    }
    for (int i=3; i*i<=n;i+=2) {
      if (n%i==0) {
        return false;
      }
    }
    return true;
  }

  
  
  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   **/

  int compressionFunction(int code) {
	    int a=3;
	    int b=5;
	    int p=131;  
	int comp_val = ((a * code + b) % p) % myTable.length;

    if (comp_val < 0) {
      comp_val = comp_val + myTable.length;
    }
    return comp_val;
  }

  /** 
   *  Returns number of entries in dictionary.  Entries with same key and value 
   *  still count as a unique entry
   *  
   **/

  public int size() {
    
    return size;
  }

  /** 
   *  Is dictionary empty.
   *
   *  @return true if dictionary has no entries, false otherwise.
   **/

  public boolean isEmpty() {
    if (size==0) {
      return true;
    } else {
      return false;
    }
  }
  
  /** 
   *  Search for entry with specific key.  If such an entry is found,
   *  return it; otherwise return null. If there are entries with 
   *  the same key, choose one random.
   **/

  public DictionaryEntry find(Object key) {

    try{
      int code = key.hashCode();
      int cd = compressionFunction(code);
      DoublyLinkedList list = myTable[cd];
      ListNode node = list.front();
      while(node.isValidNode()) {
        if (((DictionaryEntry) node.item()).key().equals(key)) {
          return ((DictionaryEntry) node.item());
        } else {
          node = node.next();
        }  
      }
      return null;
    }

    catch (InvalidNodeException e) {
      System.out.println(e);
    }
    return null;
  }
  /**
   *  Create new Entry object referring to key and value, insert
   *  the entry into the dictionary.  Return a reference to the new
   *  entry. 
   *
   **/

  public DictionaryEntry insert(Object key, Object value) {

    DictionaryEntry entry=new DictionaryEntry();
    entry.key=key;
    entry.value=value;

    int code=key.hashCode();
    int comp_val=compressionFunction(code);

    if (myTable[comp_val] == null) {
      myTable[comp_val] = new DoublyLinkedList();
      myTable[comp_val].insertFront(entry);
    } else{
      myTable[comp_val].insertFront(entry);
    }
    size++;
    return entry;
  }


  /**
   *  Remove all entries from dictionary.
   */
  public void emptyAll() {
    myTable=new DoublyLinkedList[myTable.length];
    
    size=0;
  }

  public String toString() {
    String string="[  ";
    try{
      for (int i=0; i<myTable.length; i++) {
        int count=0;
        if (myTable[i]!=null) {
          ListNode node=myTable[i].front();
          while(node.isValidNode()) {
            node=node.next();
            count++;
          }
          string+=count+"  ";
        }
      }
    } catch (InvalidNodeException e) {
      System.out.println(e);
    }
    return string+"]";
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null. If there are entries with 
   *  the same key, choose one random.
   *  b*/

  public DictionaryEntry remove(Object key) {

    try{
      int code = key.hashCode();
      int cd = compressionFunction(code);
      DoublyLinkedList list = myTable[cd];
      ListNode node = list.front();      
      while(node.isValidNode()) {
        if (key.equals(((DictionaryEntry) node.item()).key())) {
          DictionaryEntry foundKey = (DictionaryEntry) node.item();
          node.remove();
          size--;
          return foundKey;
        } else {
          node = node.next();
        } 
      }
        return null;
    } catch (InvalidNodeException e) {
      System.out.println(e);
    }
    return null;
  }


}
