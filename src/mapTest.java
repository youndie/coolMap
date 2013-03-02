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
    private Map<Integer, Integer> testMap = new RedBlackTree<Integer, Integer>();
    private Map<Integer, Integer> controlMap = new TreeMap<Integer, Integer>();
    Integer[] values;
    Integer[] keys;

    @Before
    public void generateData() {
        Random random = new Random();
        int size = 100;
        values = new Integer[size];
        keys = new Integer[size];
        for (int i = 0; i < size; i++) {

            keys[i] = random.nextInt();
            values[i] = random.nextInt();
        }
    }

    private void checkMap(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        assertEquals("Size", a.size(), b.size());
        for (Integer key : b.keySet()) {
            assertEquals("Value for key " + key.toString(), b.get(key),a.get(key));
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
    public void delTest() {

       putNode(1);
        putNode(2);
        putNode(3);
        putNode(4);
        putNode(5);
        putNode(6);
        putNode(7);

        delNode(3);

            checkMap(testMap, controlMap);

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


