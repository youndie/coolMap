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
        int size = 7;
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

    @Test
    public void doublePut()
    {
        pushMaster(new int[]{1,1});
    }


    @Test
    public void redParentRedUnclePUTTest()
    {
        pushMaster(new int[]{5,1,6,7});
        clearMaster();
        pushMaster(new int[]{5,1,6,0});
    }

    private void clearMaster()
    {
        controlMap.clear();
        testMap.clear();
    }


    @Test
    public void redParentBlackUnclePUTTest()
    {
        pushMaster(new int[]{15,17,10,13,11});
        clearMaster();
        pushMaster(new int[]{15,10,18,16,17});
    }

    @Test
    public void leftRotateTest()
    {
        pushMaster(new int[]{1,2,3,4,5});
        clearMaster();
        pushMaster(new int[]{7,5,6});
    }

    @Test
    public void rightRotateTest()
    {
        pushMaster(new int[]{5,4,3,2,1});
        clearMaster();
        pushMaster(new int[]{5,7,6});
    }

    public void chkMaps()
    {
        checkMap(testMap, controlMap);
    }

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
    public void delTest_case0() //удаляемого элемента нет
    {
        delNode(666);
    }


    @Test
    public void delTest_case1()   //новый корень
    {
        pushDelMaster(new int[]{1,2});
    }


    @Test
    public void delTest_case2() //Мы справа чёрный узел, красные потомки, чёрный брат, чёрный отец
    {
        pushDelMaster(new int[]{1,2,5,6,3},new int[]{5});
    }

    @Test
    public void delTest_case3()  //чёрный узел, красный брат
    {
        pushDelMaster(new int[]{5,7,3,4,2},new int[]{7});
    }

    @Test
    public void delTest_case4()  //Мы слева, правый cын красный, отец чёрный, узел чёрный
    {
        pushDelMaster(new int[]{1, 3, 7, 6, 22, 17});
    }

    @Test
    public void delTest_case5()  //Мы справа, правый cын красный, отец чёрный, узел чёрный
    {
        pushDelMaster(new int[]{16, 15, 6, 1, 11, 8, 23});
    }

    @Test
    public void delTest_case6() //Мы слева, красный узел, чёрный брат
    {
        pushDelMaster(new int[]{15, 8, 14, 5, 12, 21, 7});
    }

    private void pushDelMaster(int[]nodes)
    {
        pushDelMaster(nodes,nodes);
    }

    private void pushMaster(int[]nodes)
    {
        for (int i = 0; i<nodes.length;i++)
        {
            putNode(nodes[i]);
            chkMaps();
        }
    }

    private void pushDelMaster(int[]nodes,int[]nodesTodelete)
    {
        pushMaster(nodes);
        for (int i = 0; i<nodesTodelete.length;i++)
        {
            delNode(nodesTodelete[i]);
            chkMaps();
        }

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


