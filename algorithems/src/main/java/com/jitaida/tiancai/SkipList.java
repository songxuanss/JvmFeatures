package com.jitaida.tiancai;

import java.util.LinkedList;
import java.util.Random;

public class SkipList {

    public class SkipListEntry {
        public short level;
        public String key;
        public Integer value;
        public SkipListEntry left;
        public SkipListEntry right;
        public SkipListEntry up;
        public SkipListEntry down;

        public static final String negInfi = "-oo";
        public static final String posInfi = "+oo";

        public SkipListEntry(short level, String key, int value,
                             SkipListEntry right, SkipListEntry down,
                             SkipListEntry left, SkipListEntry up){
            this.level = level;
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.up = up;
            this.down = down;
        }

        public SkipListEntry(String key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Why need skiplist as we already have the binary search?
     * Binary search depends on the array data structure where the random access read can get O(1) complexity
     * When data structured in Linked List, binary search would not applicable any more, thus we introduce skiplist.
     */
    public static final short MAX_LEVEL = 4;
    public short head_level = 0;
    public SkipListEntry head;
    public SkipListEntry tail;
    private Random r;

    public SkipList(){
        SkipListEntry p1, p2;
        p1 = new SkipListEntry(SkipListEntry.negInfi, null);
        p2 = new SkipListEntry(SkipListEntry.posInfi, null);
        p1.right = p2;
        p2.left = p1;

        this.head = p1;
        this.tail = p2;

        this.r = new Random();
    }

    public SkipListEntry findByKey(String key){
        /**
         * If the key exsit in the skipList, return the entry with the same key
         * If the key doesn't in the skipList, return the entry with the most nearest(smaller) key
         */
        SkipListEntry pointer = this.head;

        while (true) {
            while (pointer.right.key.equals(SkipListEntry.posInfi) && key.compareTo(pointer.right.key) >= 0) {
                pointer = pointer.right;
            }

            if (pointer.down != null) {
                pointer = pointer.down;
            } else {
                break;
            }
        }

        if (key.compareTo(pointer.key) == 0)
            return pointer;

        return pointer;
    }

    public Integer get(String key) {
        SkipListEntry pointer = this.findByKey(key);

        if (key.compareTo(pointer.key) != 0){
            return null;
        }

        return pointer.value;
    }

    public void put(String key, Integer value){
        /**
         * If the key already exist in the skipList, update the value
         * If the key not exist, insert into the skipList
         */
        int currentLevel = 0;

        SkipListEntry pointer = this.findByKey(key);
        if (pointer.key.equals(key)){
            // same key, update value
            pointer.value = value;
        }

        // insert entry into lowest level
        SkipListEntry nexPointer = pointer.right;
        SkipListEntry entryPointer = new SkipListEntry(key, value);
        pointer.right = entryPointer;
        nexPointer.left = entryPointer;
        entryPointer.left = pointer;
        entryPointer.right = nexPointer;

        while (r.nextDouble() < 0.5){
            if (currentLevel >= SkipList.MAX_LEVEL){
                //  already reach the MAXIMUM level
                break;
            }

            if (currentLevel >= this.head_level) {
                this.addNewLevel();
            }

            while(pointer.up == null){
                pointer = pointer.left;
            }

            pointer = pointer.up;

            SkipListEntry p  = new SkipListEntry(key, null);
            p.down = entryPointer;
            entryPointer.up = p;
            pointer.right.left = p;
            p.right = pointer.right;
            p.left = pointer;
            pointer.right = p;

            // for the next iteration
            entryPointer = p;
            currentLevel ++;
        }
    }

    private void addNewLevel(){
        /**
         * For easy implementation, we have to make sure the that head and tail pointer always be the
         * PosInfi and NegInfi entry
         *
         * NegInfi ---------------------------------------------------------------- PosInfi
         * NegInfi -------------------X------------------------X------------------- PosInfi
         * ( head )                                                                 ( tail )
         */

        SkipListEntry p1 = new SkipListEntry(SkipListEntry.negInfi, null);
        SkipListEntry p2 = new SkipListEntry(SkipListEntry.posInfi, null);

        p1.right = p2;
        p2.left = p1;
        p1.down = head;
        p2.down = tail;

        head.up = p1;
        tail.up = p2;

        head = p1;
        tail = p2;

        this.head_level += 1;
    }

    public Integer remove(String key) {

        SkipListEntry p, q;

        p = this.findByKey(key);

        if(!p.key.equals(key)) {
            return null;
        }

        Integer oldValue = p.value;
        while(p != null) {
            q = p.up;
            p.left.right = p.right;
            p.right.left = p.left;
            p = q;
        }

        return oldValue;
    }


    public static void main(String[] args ){
        SkipList sl = new SkipList();
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] c = s.toCharArray();
        Random random = new Random();

        int i = 0;
        while(i < 60){
            sl.put(Character.toString(c[random.nextInt(c.length)]), random.nextInt());
            i++;
        }

        System.out.println(sl.get("c"));
    }
}
