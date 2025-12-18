//Implementation of DynamicArrayADT

public class DynamicArray<T> implements DynamicArrayADT<T> {

    private T[] data;   // internal storage
    private int size;   // number of elements stored

    //Constructors

    /**
     * Creates empty DynamicArray with initial capacity.
     */
    public DynamicArray(int initialCapacity) {
        data = allocate(initialCapacity);
        size = 0;
    }

    //Cope constructor
    public DynamicArray(DynamicArray<T> other) {
        this.data = allocate(other.data.length);
        this.size = other.size;
        for (int i = 0; i < size; i++) {
            data[i] = other.data[i];
        }
    }

    /**
     * Allocates generic array 
     */
    @SuppressWarnings("unchecked")
    private T[] allocate(int len) {
        return (T[]) new Object[len];
    }

    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T old = data[index];
        data[index] = element;
        return old;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // Resize if needed
        if (size == data.length) {
            resize();
        }

        // Shift elements right
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

    public void add(T element) {
        add(size, element);  // delegate to indexed add
    }


    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T removed = data[index];

        // Shift elements left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null; // help GC
        size--;

        return removed;
    }


    public DynamicArrayADT<T> append(DynamicArrayADT<T> other) {
        DynamicArray<T> result = new DynamicArray<>(this.size + other.size());
        // Copy this
        for (int i = 0; i < this.size; i++) {
            result.add(this.data[i]);
        }
        // Copy other
        for (int i = 0; i < other.size(); i++) {
            result.add(other.get(i));
        }
        return result;
    }


    public DynamicArrayADT<T> insert(int index, DynamicArrayADT<T> other) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        DynamicArray<T> result = new DynamicArray<>(this.size + other.size());
        // Copy elements before index
        for (int i = 0; i < index; i++) {
            result.add(this.data[i]);
        }
        // Copy other array
        for (int i = 0; i < other.size(); i++) {
            result.add(other.get(i));
        }
        // Copy remaining elements
        for (int i = index; i < this.size; i++) {
            result.add(this.data[i]);
        }
        return result;
    }

    public DynamicArrayADT<T> splitSuffix(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        DynamicArray<T> result = new DynamicArray<>(size - index);
        for (int i = index; i < size; i++) {
            result.add(data[i]);
        }
        return result;
    }


    public DynamicArray<T> splitPrefix(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        DynamicArray<T> result = new DynamicArray<>(index);
        for (int i = 0; i < index; i++) {
            result.add(data[i]);
        }
        return result;
    }

    public DynamicArrayADT<T> delete(int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex);

        DynamicArray<T> result = new DynamicArray<>(size - (toIndex - fromIndex));
        // Copy before range
        for (int i = 0; i < fromIndex; i++) {
            result.add(data[i]);
        }
        // Copy after range
        for (int i = toIndex; i < size; i++) {
            result.add(data[i]);
        }
        return result;
    }

    public DynamicArrayADT<T> extract(int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex);

        DynamicArray<T> result = new DynamicArray<>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            result.add(data[i]);
        }
        return result;
    }
    
    /**
     * Returns a copy of the current DynamicArray in the range [fromIndex, toIndex).
     */
    public DynamicArrayADT<T> sublist(int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex);
        return extract(fromIndex, toIndex);
    }
    
    /**
     * Returns the lowest valid index
     */
    public int lowIndex() {
        return 0;
    }
    
    /**
     * Returns the highest valid index
     */
    public int highIndex() {
        return size - 1;
    }
    
    /**
     * Returns a boolean indicating if an index is valid
     */
    public boolean indexInRange(int index) {
        return index >= 0 && index < size;
    }

    /**
     * checks range for extract/delete methods
     */
    private void checkRange(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(
                "fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
        }
    }

    /**
     * Doubles the capacity of the internal array
     */
    private void resize() {
        T[] newData = allocate(data.length * 2);
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}