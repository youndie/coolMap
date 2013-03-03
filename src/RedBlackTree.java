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

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */

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

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        if (m_size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     *         key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
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

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
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

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * <p/>
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     * <p/>
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
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

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>,
     *         if the implementation supports <tt>null</tt> values.)
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     */
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

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this map
     */
    @Override
    public void clear() {
        root = null;
        m_size = 0;
    }

    /**
     * Returns a {@link java.util.Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns a {@link java.util.Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Returns a {@link java.util.Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
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


