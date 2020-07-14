package com.jitaida.tiancai.algorithms;
import java.util.HashMap;
import java.util.Map;

public class LRUCacheWithExpire<K,V>{
    private Map<K,Entry> cache;
    private long maxSize;
    private long expireMillisec;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry head;
    Entry tail;

    public class Entry{
        long serverTs;
        K key;
        V value;
        Entry left;
        Entry right;

        public Entry(long serverTs, K key, V value){
            this.serverTs = serverTs;
            this.value = value;
            this.key = key;
        }
    }

    public LRUCacheWithExpire(long maxSize, long expireMillisec){
        this.maxSize = maxSize;
        this.expireMillisec = expireMillisec;

        // make sure that we wont double the size of the hashmap when reaching the load factor point
        int capacity = (int) Math.ceil(maxSize/DEFAULT_LOAD_FACTOR + 1);
        this.cache = new HashMap<>(capacity);
    }

    public void insert(K k, V v){
        long currentTime = System.currentTimeMillis();
        Entry newItem = new Entry(currentTime, k, v);

        if (this.cache.size() >= this.maxSize){
            cache.remove(this.tail.key);
            this.removeLast();
        }

        cache.put(k,newItem);
        addEntry(newItem);
    }

    public V get(K k){
        Entry entry = this.cache.get(k);
        if (entry == null){
            return null;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - entry.serverTs > expireMillisec){
            this.cache.remove(entry.key);
            this.removeEntry(entry);
            return null;
        }

        entry.serverTs = currentTime;
        return entry.value;
    }

    public void removeLast(){
        if (this.tail == null){
            return;
        }

        this.tail = this.tail.left;
        this.tail.right = null;

        if (this.tail == null){
            this.head = null;
        }
    }

    private void addEntry(Entry newItem){
        if (tail == null){
            tail = newItem;
        }

        if (head == null){
            head = newItem;
        } else {
            Entry tmp = this.head;
            this.head = newItem;
            tmp.left = newItem;
            head.right = tmp;
        }
    }

    private synchronized void removeEntry(Entry entry){
        // check if entry is head
        if (entry.left != null)
            entry.left.right = entry.right;
        else if (entry == head){
            this.head = entry.right;
            entry.right.left = null;
        }

        // check if entry is tail
        if (entry.right != null)
            entry.right.left = entry.left;
        else if (entry == tail){
            removeLast();
        }
    }

    public void printAll(){
        System.out.println("\n All entries in cache");
        for(Map.Entry<K,Entry> entry : this.cache.entrySet()){
            System.out.print(entry.getKey().toString() + " " + entry.getValue().value.toString() + ";");
        }

        System.out.println("\n All items in list");

        Entry entry = this.head;
        while(true){
            System.out.print("\n" + entry.key.toString() + " " + entry.value.toString() + ";" );
            if (entry.right != null){
                entry = entry.right;
            } else{
                break;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LRUCacheWithExpire lru = new LRUCacheWithExpire<>(5, 1000);
        lru.insert("a","v");
        lru.insert("a2","v");
        lru.insert("a3","v");
        lru.insert("a4","v");
        lru.insert("a5","v");
        lru.insert("a6","v");
        lru.insert("a7","v");
        lru.insert("a8","v");
        // list all the values in cache
        lru.printAll();
        // wait for the cache expire, and then get it
        Thread.sleep(1000);
        lru.get("a8");
        lru.printAll();
    }
}