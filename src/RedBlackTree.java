import java.util.Collection;
import java.util.Map;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: Youndie
 * Date: 16.02.13
 * Time: 20:35
 */
public class RedBlackTree<K extends Comparable<K>, V extends Comparable<V>> implements Map<K, V> {



    private Node<K, V> root = null;

    enum Color {red, black}

    private int m_size;

    static private Node NIL;

    public RedBlackTree() {
        NIL = new Node();
        root = NIL;
    }


    static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left = NIL;
        private Node<K, V> right = NIL;
        private Node<K, V> parent;
//        NIL.left = NIL;
//        NIL.right = NIL;

        private Color color = Color.black;


        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node() {
        }
    }

    public int size() {
        return m_size;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean isEmpty() {
        if (m_size == 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean containsKey(Object key) {
        if (key == null) throw new NullPointerException();
        K k = (K) key;
        Node<K, V> current = root;

        while (current != null) {
            int compareInt = k.compareTo(current.key);
            if (compareInt == 0) {
                return true;
            }
            if (compareInt < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }


    @Override
    public boolean containsValue(Object value) {
        if (value == null) throw new NullPointerException();
        V v = (V) value;
        Node<K, V> current = root;

        while (current != null) {
            int compareInt = v.compareTo(current.value);
            if (compareInt == 0) {
                return true;
            }
            if (compareInt < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }


    @Override
    public V get(Object key) {
        if (key == null) throw new NullPointerException();
        K k = (K) key;
        Node<K, V> current = root;

        while (current != NIL) {
            int compareInt = k.compareTo(current.key);
            if (compareInt == 0) {
                return current.value;
            }
            if (compareInt < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }


        return null;
    }


    @Override
    public V put(K key, V value) {
        if (key == null) throw new NullPointerException();

        Node<K, V> current = root;
        Node<K, V> myNode = NIL;
        V returnValue = null;
        while (current != NIL) {
            int compareInt = key.compareTo(current.key);
            if (compareInt == 0) {
                returnValue = current.value;
                current.value = value;
                return returnValue;
            } else {
                myNode = current;
                if (compareInt < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
        Node<K, V> newNode = new Node<K, V>(key, value);


        newNode.color = Color.red;

        if (myNode == NIL) {
            root = newNode;
        } else {
            newNode.parent = myNode;
            if (key.compareTo(myNode.key) < 0) {
                myNode.left = newNode;
            } else {
                myNode.right = newNode;
            }
        }
        m_size++;

        insertFix(newNode);

        return null;
    }


    @Override
    public V remove(Object K) {
        if (K == null) throw new NullPointerException();
        Node<K, V> current = root;
        Node<K, V> myNode = NIL;
        Node<K,V> node;
        K k = (K) K;
        while (current != NIL) {
            int cmp = k.compareTo(current.key);
            if (cmp == 0) {
                break;
            } else {
                if (cmp < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
        if (current == NIL) {
            return null;
        }
        m_size--;
        V returnValue = current.value;

        if (current==null || current==NIL) return null;
        if (current.left == NIL ||current.right == NIL) {
            myNode = current;
        } else {
            myNode = current.right;
            while (myNode.left != NIL) myNode = myNode.left;
        }
        if (myNode.left != NIL)
            node = myNode.left;
        else
            node = myNode.right;
        node.parent = myNode.parent;
        if (myNode.parent!=null)
        {
            if (myNode==myNode.parent.left)
            {
                myNode.parent.left = node;
            }
            else myNode.parent.right = node;
        }
        else root = node;
        if (myNode!=current)
        {
            current.key = myNode.key;
            current.value = myNode.value;
        }
        if (myNode.color==Color.black)
        {
            removeFix(node);
        }
        return returnValue;
    }


    public void putAll(Map<? extends K, ? extends V> m) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void clear() {
        root = null;
        m_size = 0;
    }


    @Override
    public Set<K> keySet() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public Collection<V> values() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void rotateRight(Node<K, V> x) {
        Node<K, V> y = x.left;
        x.left = y.right;
        if (y.right != NIL) y.right.parent = x;
        if (y != NIL) y.parent = x.parent;
        if (x.parent != null) {
            if (x == x.parent.right)
                x.parent.right = y;
            else
                x.parent.left = y;
        } else {
            root = y;
        }

        y.right = x;
        if (x != NIL) x.parent = y;
    }

    private void rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        if (y != NIL) y.parent = x.parent;
        if (x.parent != null) {
            if (x == x.parent.left)
                x.parent.left = y;
            else
                x.parent.right = y;
        } else {
            root = y;
        }

        y.left = x;
        if (x != NIL) x.parent = y;
    }

    private void insertFix(Node<K, V> x) {
        x.color = Color.red;
        while (x != null && x != root && x.parent.color == Color.red) {
            if (x.parent == x.parent.parent.left) {
                //мы слева
                Node<K, V> y = x.parent.parent.right;
                if (y.color == Color.red) {
                    //дядя справа красный
                    y.color = Color.black;
                    x.parent.color = Color.black;
                    x.parent.parent.color = Color.red;
                    x = x.parent.parent;
                } else {
                    //дядя справа чёрный
                    if (x.parent.right == x) {
                        x = x.parent;
                        rotateLeft(x);
                    }
                    x.parent.color = Color.black;
                    x.parent.parent.color = Color.red;
                    rotateRight(x.parent.parent);
                }

            } else {
                Node<K, V> y = x.parent.parent.left;
                if (y.color == Color.red) {
                    y.color = Color.black;
                    x.parent.color = Color.black;
                    x.parent.parent.color = Color.red;
                    x = x.parent.parent;
                } else {
                    if (x.parent.left == x) {
                        x = x.parent;
                        rotateRight(x);
                    }
                    x.parent.color = Color.black;
                    x.parent.parent.color = Color.red;
                    rotateLeft(x.parent.parent);


                }


            }


        }
        root.color = Color.black;
    }




    private void removeFix(Node<K, V> x) {
        while (x != root && x.color == Color.black) {
            if (x == x.parent.left) {
                Node<K, V> myNode = x.parent.right;
                if (myNode.color == Color.red) {
                    myNode.color = Color.black;
                    x.parent.color = Color.red;
                    rotateLeft(x.parent);
                    myNode = x.parent.right;
                }
                if (myNode.left.color == Color.black && myNode.right.color == Color.black) {
                    myNode.color = Color.red;
                    x = x.parent;
                } else {
                    if (myNode.right.color == Color.black) {
                        myNode.left.color = Color.black;
                        myNode.color = Color.red;
                        rotateRight(myNode);
                        myNode = x.parent.right;
                    }
                    myNode.color = x.parent.color;
                    x.parent.color = Color.black;
                    myNode.right.color = Color.black;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                Node myNode = x.parent.left;
                if (myNode.color == Color.red) {
                    myNode.color = Color.black;
                    x.parent.color = Color.red;
                    rotateRight(x.parent);
                    myNode = x.parent.left;
                }
                if (myNode.right.color == Color.black && myNode.left.color == Color.black) {
                    myNode.color = Color.red;
                    x = x.parent;
                } else {
                    if (myNode.left.color == Color.black) {
                        myNode.right.color = Color.black;
                        myNode.color = Color.red;
                        rotateLeft(myNode);
                        myNode = x.parent.left;
                    }
                    myNode.color = x.parent.color;
                    x.parent.color = Color.black;
                    myNode.left.color = Color.black;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.black;

    }

}


