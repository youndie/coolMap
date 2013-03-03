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

            keys[i] = random.nextInt(25);
            values[i] = random.nextInt();
        }
    }

    private void checkMap(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        assertEquals("Size", a.size(), b.size());
        for (Integer key : b.keySet()) {
            assertEquals("Value for key " + key.toString(), b.get(key), a.get(key));

        }


    }

//    @Test
//    public void putTest() {
//
//        for (int i = 0; i < keys.length; i++) {
//            testMap.put(keys[i], values[i]);
//            controlMap.put(keys[i], values[i]);
//            checkMap(testMap, controlMap);
//        }
//    }

//    @Test
//    public void delTest1()
//    {
//        putNode(95);
//        putNode(2);
//        putNode(27);
//        putNode(60);
//        putNode(62);
//        delNode(95);
//        delNode(2);
//        checkMap(testMap,controlMap);
//    }

    @Test
    public void doublePut()
    {
        putNode(1);putNode(1);
        chkMaps();
    }


    @Test
    public void redParentRedUnclePUTTest()
    {
        putNode(5);
        putNode(1);
        putNode(6);
        putNode(7);
        chkMaps();

        controlMap.clear();
        testMap.clear();

        putNode(5);
        putNode(1);
        putNode(6);
        putNode(0);
        chkMaps();
    }

    @Test
    public void redParentBlackUnclePUTTest()
    {
        putNode(15);
        putNode(17);
        putNode(10);
        putNode(13);
        putNode(11);
        chkMaps();

        controlMap.clear();
        testMap.clear();

        putNode(15);
        putNode(10);
        putNode(18);
        putNode(16);
        putNode(17);
        chkMaps();
    }

    @Test
    public void leftRotateTest()
    {
        putNode(1);
        putNode(2);
        putNode(3);
        putNode(4);
        putNode(5);
        chkMaps();

        testMap.clear();
        controlMap.clear();
        putNode(7);
        putNode(5);
        putNode(6);
        chkMaps();
    }

    @Test
    public void rightRotateTest()
    {
        putNode(5);
        putNode(4);
        putNode(3);
        putNode(2);
        putNode(1);
        chkMaps();

        testMap.clear();
        controlMap.clear();
        putNode(5);
        putNode(7);
        putNode(6);
        chkMaps();
    }

    public void chkMaps()
    {
        checkMap(testMap, controlMap);
    }

//    @Test
//    public void delTest() {
//        for (int i = 0; i < keys.length; i++) {
//            testMap.put(keys[i], values[i]);
//            controlMap.put(keys[i], values[i]);
//        }
//
//        for (int i = 0; i < keys.length; i++) {
//            testMap.remove(keys[i]);
//            controlMap.remove(keys[i]);
//            checkMap(testMap, controlMap);
//        }
////
//        assertTrue(testMap.isEmpty());
//
//    }

    @Test
    public void containsTest()
    {
        putNode(5);
        testMap.put(0,0);
        testMap.put(3,1);
        testMap.put(4,2);
        testMap.put(1,16);
        assertEquals(testMap.containsKey(5),true);
        assertEquals(testMap.containsKey(0),true);
        assertEquals(testMap.containsKey(1125),false);
        assertEquals(testMap.containsValue(1),true);
        assertEquals(testMap.containsValue(0),true);
        assertEquals(testMap.containsValue(16),true);
        assertEquals(testMap.containsValue(26),false);
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

    @Test
    public void delTest_case1()   //новый корень
    {
        delNode(15);

        putNode(1);
        putNode(2);
        delNode(1);

        chkMaps();
    }


    @Test
    public void delTest_case2() //красный узел, чёрные потомки
    {
        putNode(1);
        putNode(2);
        putNode(5);
        putNode(6);
        putNode(3);
        delNode(5);
        chkMaps();
    }


    @Test
    public void delTest_case3() //чёрный узел, красный брат
    {
        putNode(1);
        putNode(2);
        putNode(5);
        putNode(6);
        putNode(3);
        delNode(1);
        chkMaps();
    }


    @Test
    public void delTest_case4()  //чёрный узел, красный брат (симметр.)
    {
        putNode(5);
        putNode(7);
        putNode(3);
        putNode(4);
        putNode(2);
        delNode(7);
        chkMaps();
    }


    @Test
    public void delTest_case5()
    {

        String NODES = "17 5 15 7 13";
        for (int i=0;i<NODES.split(" ").length;i++)
        {
            putNode(Integer.parseInt(NODES.split(" ")[i]));
        }
        delNode(17);  //чёрный предок чёрный брат чёрные сыновья
        delNode(5);   //чёрный предок чёрный брат чёрные сыновья (сим)
        putNode(6);

        delNode(13);  //чёрные сыновья чёрный брат красный предок


//        for (int i = 0;i<5;i++)
//        {
//            putNode(keys[i]);
//            System.out.println(keys[i]);
//        }
//
//        for (int i = 0;i<5;i++)
//        {
//            delNode(keys[i]);
//        }

    }

//    public void delTest_case6()
//    {
//        String NODES = "17 5 15 7 13";
//        for (int i=0;i<NODES.split(" ").length;i++)
//        {
//            putNode(Integer.parseInt(NODES.split(" ")[i]));
//        }
//         delNode(17);
//        delNode(15);
//    }


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


