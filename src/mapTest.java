import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: Youndie
 * Date: 21.02.13
 * Time: 00:15
 */

public class mapTest extends Assert {
    private RedBlackTree<Integer, Integer> testMap = new RedBlackTree<Integer, Integer>();
    private Map<Integer, Integer> controlMap = new TreeMap<Integer, Integer>();
    Integer[] values;
    Integer[] keys;

    @Before
    public void generateData() {
        Random random = new Random();
        int size = 1000;
        values = new Integer[size];
        keys = new Integer[size];
        for (int i = 0; i < size; i++) {

            keys[i] = random.nextInt(100);
            values[i] = random.nextInt();
        }
    }

    private void checkMap(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        assertEquals("Size", a.size(), b.size());
        for (Integer key : b.keySet()) {
            assertEquals("Value for key " + key.toString(), b.get(key), a.get(key));

        }


    }

    @Test
    public void putTest() {

        for (int i = 0; i < keys.length; i++) {
            testMap.put(keys[i], values[i]);
            controlMap.put(keys[i], values[i]);
            checkMap(testMap, controlMap);
        }
    }

    @Test
    public void delTest1()
    {
        putNode(95);
        putNode(2);
        putNode(27);
        putNode(60);
        putNode(62);
        delNode(95);
        delNode(2);
        checkMap(testMap,controlMap);
    }

    @Test
    public void delTest() {
        for (int i = 0; i < keys.length; i++) {
            testMap.put(keys[i], values[i]);
            controlMap.put(keys[i], values[i]);
        }

        for (int i = 0; i < keys.length; i++) {
            testMap.remove(keys[i]);
            controlMap.remove(keys[i]);
            checkMap(testMap, controlMap);
        }
//
        assertTrue(testMap.isEmpty());

    }

    @Test
    public void containsTest()
    {
        putNode(5);
        testMap.put(0,0);
        assertEquals(testMap.containsKey(5),true);
        assertEquals(testMap.containsKey(0),true);
        assertEquals(testMap.containsKey(1),false);
        assertEquals(testMap.containsValue(1),true);
        assertEquals(testMap.containsValue(0),true);
        assertEquals(testMap.containsValue(2),false);
    }

    @Test
    public void cleanTest()
    {
        testMap.put(5,1);
        assertFalse(testMap.isEmpty());
        testMap.clear();
        assertTrue(testMap.isEmpty());
    }

    @Test
    public void dummiesTest()
    {
        assertNull(testMap.keySet());
        assertNull(testMap.entrySet());
        testMap.putAll(testMap);
        assertNull(testMap.values());
    }

    public void delNode(int key)
    {
        testMap.remove(key);
        controlMap.remove(key);
    }

    public void putNode(int key)
    {
        testMap.put(key,1);
        controlMap.put(key,1);
    }
}


