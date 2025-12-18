import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicArrayTests {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
    compareToString(a1.append(s),"abcdefs");
    compareToString(s.append(a1),"sabcdef");
    compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    // ~*~*~*~*~ Add Extract Tests Below ~*~*~*~*~

   /**
    * Tests that extracts a standard range returns correct elements in order
    */
   @Test
   public void testExtractStandard() {
    compareToString(a1.extract(2, 4), "cd"); // "c" at index 2, "d" at 3
    compareToString(a1.extract(0, 3), "abc");
    compareToString(a2.extract(1, 3), "xy");
}

   /**
    * Tests that extract from start to end returns the entire array unchanged
    */
   @Test
   public void testExtractEntire() {
    compareToString(a1.extract(0, a1.size()), "abcdef");
    compareToString(a2.extract(0, a2.size()), "wxyz");
}

   /**
    * Tests that extracts zero-length range (same start/end) returns empty array
    */
   @Test
   public void testExtractZero() {
    DynamicArray<Character> zero = a1.extract(2, 2);
    assertEquals(0, zero.size());
}

   /**
    * Tests that extracts from an empty array returns another empty array
    */
   @Test
   public void testExtractEmpty() {
    DynamicArray<Character> result = empty.extract(0, 0);
    assertEquals(0, result.size());
}

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    // @Test(expected = IndexOutOfBoundsException.class)
    // public void testExtractBounds() {
    //     DynamicArray<Character> extract = a1.extract(-1, 5);
        // More bounds that you can check:
        // low index is negative => throws ArrayIndexOutOfBoundsException   
        // high index is greater than array length => throws ArrayIndexOutOfBoundsException
        // low index is greater than array length => throws ArrayIndexOutOfBoundsException
        // high index is negative => throws ArrayIndexOutOfBoundsException
        // high index is less than low
        
    /**
     * Tests that extract throws exception when low index is negative.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractLowNegative() {
        a1.extract(-1, 3);
    }

    /**
     * Tests that extract throws exception when high index exceeds array size.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractHighTooBig() {
        a1.extract(2, 10);
    }

    /**
     * Tests that extract throws exception when low index exceeds array size.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractLowTooBig() {
        a1.extract(10, 12);
    }

    /**
     * Tests that extract throws exception when high index is negative.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractHighNegative() {
        a1.extract(2, -1);
    }

    /**
     * Tests that extract throws exception when high index is less than low index.
     */

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractHighLessThanLow() {
        a1.extract(4, 2);
    }
    }

    // ~*~*~*~*~ Write More Tests Below ~*~*~*~*~

    // write tests for the other methods here

    //Tests for get method
    /**
     * Tests that get returns correct elements at valid indices.
     */
    @Test
    public void testGetValid() {
        assertEquals('a', a1.get(0).charValue());
        assertEquals('f', a1.get(5).charValue());
        assertEquals('w', a2.get(0).charValue());
        assertEquals('z', a2.get(3).charValue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegative() {
        a1.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTooBig() {
        a1.get(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmpty() {
        empty.get(0);
    }


    //Tests for add method

    @Test
    public void testAddMiddle() {
        a1.add(3, 'X');
        compareToString(a1, "abcXdef");
        assertEquals(7, a1.size());
    }

    @Test
    public void testAddStart() {
        a1.add(0, 'X');
        compareToString(a1, "Xabcdef");
        assertEquals(7, a1.size());
    }

    @Test
    public void testAddEnd() {
        a1.add(6, 'X'); // same as append
        compareToString(a1, "abcdefX");
        assertEquals(7, a1.size());
    }

    @Test
    public void testAddAppend() {
        a1.add('X'); // uses overloaded add(T)
        compareToString(a1, "abcdefX");
        assertEquals(7, a1.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddNegative() {
        a1.add(-1, 'X');
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddTooBig() {
        a1.add(10, 'X');
    }




}




