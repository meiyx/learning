package com.study.meiyx.learning.collection;

import java.util.*;

public class HashMap1<K, V> extends java.util.HashMap<K, V> {

//    /**
//     * 重要参数
//     */

//
//    /**
//     * 初始化容量大小，默认16
//     */
//    static final int DEFAULT_INITIAL_CAPACITY = 1 >> 4;
//
//    /**
//     * 最大容量2的30次方
//     */
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//
//    /**
//     * 负载因子：0.75f,超过绒里的比例，则扩容
//     */
//
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    /**
//     * 链表size 超过该阈值时，用书来代替链表，默认值是8
//     */
//
//    static final int TREEIFY_THRESTHOLD = 8;
//
//    /**
//     * 当执行resize操作是，当桶中bin的数量少于该值是使用链表来代替树
//     */
//
//    static final int UNTREEIFY_THRESTHOLD = 6;
//
//    /**
//     * hash 容量小于MIN_TREEIFY_CAPACITY,都是通过resize 来扩容，不会启动树结构装化，哪怕是链表size>8
//     */
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    /**
//     * Note 数组
//     */
//
//    private Node<K, V>[] table;
//
//    transient Set<Entry<K, V>> entrySet;
//
//    private float loadFactor;
//
//    private int threshold;
//
//    /**
//     * 修改次数
//     */
//    transient int modCount;
//
//    /**
//     * 长度
//     */
//    transient int size;
//
//
//    /**
//     * Note 链表
//     *
//     * @param <K>
//     * @param <V>
//     */
//    static class Node<K, V> implements Map.Entry<K, V> {
//        final int hash;
//        final K key;
//        V value;
//        Node<K, V> next;
//
//        Node(int hash, K key, V value, Node<K, V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        /**
//         * Returns the key corresponding to this entry.
//         *
//         * @return the key corresponding to this entry
//         * @throws IllegalStateException implementations may, but are not
//         *                               required to, throw this exception if the entry has been
//         *                               removed from the backing map.
//         */
//        @Override
//        public K getKey() {
//            return key;
//        }
//
//        /**
//         * Returns the value corresponding to this entry.  If the mapping
//         * has been removed from the backing map (by the iterator's
//         * <tt>remove</tt> operation), the results of this call are undefined.
//         *
//         * @return the value corresponding to this entry
//         * @throws IllegalStateException implementations may, but are not
//         *                               required to, throw this exception if the entry has been
//         *                               removed from the backing map.
//         */
//        @Override
//        public V getValue() {
//            return value;
//        }
//
//        /**
//         * Replaces the value corresponding to this entry with the specified
//         * value (optional operation).  (Writes through to the map.)  The
//         * behavior of this call is undefined if the mapping has already been
//         * removed from the map (by the iterator's <tt>remove</tt> operation).
//         *
//         * @param newValue new value to be stored in this entry
//         * @return old value corresponding to the entry
//         * @throws UnsupportedOperationException if the <tt>put</tt> operation
//         *                                       is not supported by the backing map
//         * @throws ClassCastException            if the class of the specified value
//         *                                       prevents it from being stored in the backing map
//         * @throws NullPointerException          if the backing map does not permit
//         *                                       null values, and the specified value is null
//         * @throws IllegalArgumentException      if some property of this value
//         *                                       prevents it from being stored in the backing map
//         * @throws IllegalStateException         implementations may, but are not
//         *                                       required to, throw this exception if the entry has been
//         *                                       removed from the backing map.
//         */
//        @Override
//        public V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//
//        @Override
//        public boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//    /**
//     * hash算法
//     *
//     * @param key
//     * @return
//     */
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//
//    public HashMap(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0) {
//            throw new IllegalArgumentException("Illegal initial capacity:" + initialCapacity);
//        }
//        if (initialCapacity > MAXIMUM_CAPACITY) {
//            initialCapacity = MAXIMUM_CAPACITY;
//        }
//        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
//            throw new IllegalArgumentException("Illegal load factor: " +
//                    loadFactor);
//        }
//
//        this.loadFactor = (int) loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//
//    /**
//     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
//     * (16) and the default load factor (0.75).
//     */
//    public HashMap() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
//    }
//
//    /**
//     * Returns a power of two size for the given target capacity.
//     */
//    static final int tableSizeFor(int cap) {
//        int n = cap - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    /**
//     * Implements Map.put and related methods.
//     *
//     * @param hash         hash for key
//     * @param key          the key
//     * @param value        the value to put
//     * @param onlyIfAbsent if true, don't change existing value
//     * @param evict        if false, the table is in creation mode.
//     * @return previous value, or null if none
//     */
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, i;
//        // table是否为空或者length等于0, 如果是则调用resize方法进行初始化
//        if ((tab = table) == null || (n = table.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        // 通过hash值计算索引位置, 如果table表该索引位置节点为空则新增一个
//        else if ((p = tab[i = (tab.length - 1) & hash]) == null) {
//            tab[i] = newNode();
//        }
//        //table表索引位置不空
//        else {
//            Node<K, V> e=null;
//            K k = p.key;
//            //table索引位置的key 和 Put的key值是否相同
//            if (p.hash == hash && (k == key) || (key != null && key.equals(k))) {
//                //如果相同，则p节点即为要查找的目标节点，赋值给e
//                e = p;
//            }
//            //判断p节点是否为TreeNode,如果是则调用红黑树的putTreeVal方法查找目标节点
//            else if (p instanceof TreeNode) {
//                ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
//            } else {
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                    }
//                    if (binCount >= this.TREEIFY_THRESTHOLD - 1) {
//                        //转成红黑树
//                        treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash && (k = e.key) == key || (key != null && key.equals(k))) {
//                        break;
//                    }
//                    p = e;
//                }
//            }
//            if (e != null) {
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null) {
//                    e.value = value;
//                }
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//   public V get(Object key){
//     Node<K,V> e;
//     return (e=getNode(hash(key),key))==null?null:e.value;
//   }
//
//    final Node<K,V> getNode(int hash, Object key) {
//        Node<K,V>[] tab;
//        Node<K,V> first, e;
//        int n; K k;
//        //如果table为null直接返回，不为null如下
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (first = tab[(n - 1) & hash]) != null) { //对于非null的table,找到索引位置的节点tab[(n - 1) & hash]
//            //如果找到节点就是我们所找key对应的节点则返回
//            if (first.hash == hash && // always check first node
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            //如果之前put时发生冲突，判断找到的节点frist是否是红黑树是调用getTreeNode返回，
//            if ((e = first.next) != null) {
//                if (first instanceof TreeNode)
//                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
//                //不是则为链表，按照顺序查找
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
//
//
//    final Node<K, V>[] resize() {
//        Node<K, V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = this.threshold;
//        //扩展后的容量和阀值默认都为0
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            //如果老的HashMap容量已经到达最大阈值了，没法扩容了，直接将阈值设置为最大并返回，没办法只能让它冲突去了
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                newThr = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            // 否则扩容原来的2倍
//            if ((newCap = oldCap >> 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
//                newThr = oldThr << 1;
//            }
//
//        }
//        //oldCap为0，就说明是初始化的情况
//        //如果已经有阈值，则初始化的时候HashMap的容量就是阈值的大小
//        //即通过HashMap(int initialCapacity, float loadFactor)或者 HashMap(int initialCapacity)
//        else if (oldThr > 0) {
//            newCap = oldThr;
//        } else {
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int) DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY;
//        }
//        //这里需要判断下上面的第二种只设置了容量的情况，需要再设置下新的阈值
//        if (newThr == 0) {
//            float ft = (float) newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
//                    (int) ft : Integer.MAX_VALUE);
//        }
//        //这里如果是初始化的情况，newThr=16*0.75=12，所以当size>12时就会触发扩容
//        threshold = newThr;
//        //生成新的数组
//        Node<K, V>[] newTab = new Node[newCap];
//        //如果oldTab！=null给新数组重新赋值
//        if (oldTab != null) {
//            for (int j = 0; j < oldCap; j++) {
//                Node<K, V> e = oldTab[j];
//                //如果老节点不等于null，释放老节点数据
//                if (e != null) {
//                    oldTab[j] = null;
//                    //新数组对应位置赋值
//                    //如果只有一个节点的话重新映射到新的数组
//                    if (e.next == null) {
//                        newTab[e.hash & (newCap - 1)] = e;
//                    }
//                    //如果不是一个节点那就是原来有hash冲突，可能是链表也可能是红黑树
//                    else if (e instanceof TreeNode) {
//                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
//
//                    }
//                    //不是红黑树，那就是链表了
//                    //下面也是Java8中的一个优化点 TODO fix me 暂且还没搞太明白
//                    else {
//                        HashMap.Node<K, V> loHead = null, loTail = null;
//                        HashMap.Node<K, V> hiHead = null, hiTail = null;
//                        HashMap.Node<K, V> next;
//                        do {
//                            next = e.next;
//                            //没扩容 原索引
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null) {
//                                    loHead = e;
//                                } else {
//                                    loTail.next = e;
//                                }
//                                loTail = e;
//                            } else {
//                                if (hiTail == null) {
//                                    hiHead = e;
//                                } else {
//                                    hiTail.next = e;
//                                }
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//    static final class TreeNode<K, V> extends HashMap.Node<K, V> {
//        TreeNode<K, V> parent;
//        TreeNode<K, V> left;
//        TreeNode<K, V> right;
//        TreeNode<K, V> prev;
//
//        TreeNode(int hash, K key, V val, HashMap.Node<K, V> next) {
//            super(hash, key, val, next);
//        }
//
//        /**
//         * Tree version of putVal.
//         */
//        final TreeNode<K, V> putTreeVal(java.util.HashMap<K, V> map, java.util.HashMap.Node<K, V>[] tab,
//                                        int h, K k, V v) {
//            return null;
//        }
//    }




}
