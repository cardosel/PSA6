/* DictionaryADT.java */

package dictionary;

/**
 *  An interface for (unordered) dictionaries.
 *
 *  
 **/

public interface DictionaryADT{



  public int size(); 


  public boolean isEmpty();


  public DictionaryEntry insert(Object key, Object value);


  public DictionaryEntry find(Object key);


  public DictionaryEntry remove(Object key);


  public void emptyAll();

}
