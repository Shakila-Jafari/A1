//ADT for dynamicArray

public interface DynamicArrayADT<T>{

    /**
     * Sets the element at the specified index and returns the previous value.
     */
    public T set(int index, T value); 


    //gets the elements at the specified index
    public T get(int index);

    /**
     * return the number of elemnets in the array
     */
    public int size();

    //Inserts element at specified index, shifting elements right
    public void add(int index, T value);

    /**
     * Removes and returns element at index, shifting left
     */
    public T remove(int index);

    /**
     * Returns new array with elements of this array followed by other array.
     * Do not modify either array
     */
    
    public DynamicArrayADT<T> append(DynamicArrayADT<T> other);

    //Inserts all elements of 'other' into this array at 'index', returns new array
    public DynamicArrayADT<T> insert(int index, DynamicArrayADT<T> other);

    //Returns new array containing elements from 'index' to end
    public DynamicArrayADT<T> splitSuffix(int index);

    //Returns new array containing elements from start to 'index'
    public DynamicArrayADT<T> splitPrefix(int index);

    //Returns new array with elements removed from [fromIndex, toIndex)
    public DynamicArrayADT<T> delete(int fromIndex, int toIndex);

    //Returns new array containing elements from [fromIndex, toIndex)
    public DynamicArrayADT<T> extract(int fromIndex,int toIndex);


    }