//ADT for dynamicArray
public interface DynamicArrayADT<T> {

    /**
     * Replaces the element at the given index with a new one
     * and gives back the old value that used to be there
     * Throws an exception if the index doesn't make sense
     */
    public T set(int index, T value);

    /**
     * takes the element at the specified index
     * If the index is out of bounds, it'll throw an exception
     */
    public T get(int index);

    /**
     * Tells us how many actual elements are stored right now
     */
    public int size();

    /**
     * Puts a new element at the given index
     * Throws an exception if the index is like negative or way too big
     */
    public void add(int index, T value);

    /**
     * R emoves and returns the element at the given index 
     * everything after it shifts left to fill the gap
     * Throws an exception fr non existence index
     */
    public T remove(int index);

    /**
     * Makes a new array that has all stuffs
     */
    public DynamicArrayADT<T> append(DynamicArrayADT<T> other);

    /**
     * Creates a new array where all other elements are inserted into this array
     * starting at the given index
     */
    public DynamicArrayADT<T> insert(int index, DynamicArrayADT<T> other);

    /**
     * Returns a new array containing everything from index to end
     */
    public DynamicArrayADT<T> splitSuffix(int index);

    /**
     * Returns a new array with everything but index
     */
    public DynamicArrayADT<T> splitPrefix(int index);

    /**
     * Returns a new array with a chunk removed
     */
    public DynamicArrayADT<T> delete(int fromIndex, int toIndex);

    /**
     * Returns a new array containing just the slice from fromIndex inclusive to toIndex exclusive
     */
    public DynamicArrayADT<T> extract(int fromIndex, int toIndex);

    /**
     * Returns a copy of a portion of the array
     * range: [fromIndex, toIndex)
     */
    public DynamicArrayADT<T> sublist(int fromIndex, int toIndex);

    /**
     * the smallest valid index which is 0
     */
    public int lowIndex();

    /**
     * the biggest valid index
     */
    public int highIndex();

    /**
     * Checks if a given index is actually usable for get/set.
     * Returns true only if it's in the [0, size) range
     */
    public boolean indexInRange(int index);
}