package com.jitaida.tiancai.leetcode.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ŷ�������㲻С�İ�һ����ƪ�����еĿո񡢱�㶼ɾ���ˣ����Ҵ�дҲŪ����Сд��
 * �����"I reset the computer. It still didn��t boot!"�Ѿ������"iresetthecomputeritstilldidntboot"��
 * �ڴ�������źʹ�Сд֮ǰ������Ȱ����ϳɴ����Ȼ�ˣ�����һ�����Ĵʵ�dictionary����������Щ��û�ڴʵ��
 * ����������sentence��ʾ�����һ���㷨�������¶Ͽ���Ҫ��δʶ����ַ����٣�����δʶ����ַ�����
 *
 * ע�⣺�������ԭ�������Ķ���ֻ�践��δʶ����ַ���
 *
 * ʾ����
 *
 * ���룺
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * ����� 7
 * ���ͣ� �Ͼ��Ϊ"jess looked just like tim her brother"����7��δʶ���ַ���
 * ��ʾ��
 *
 * 0 <= len(sentence) <= 1000
 * dictionary�����ַ��������� 150000��
 * �������Ϊdictionary��sentence��ֻ����Сд��ĸ��
 */

// ʹ���ֵ���TIRE
public class Respace {
    Tire head;

    public class Tire {
        Map<Character, Tire> children;
        boolean isTrue;
        Character value;

        public Tire(Character value){
            this.value = value;
            this.children = new HashMap();
        }
    }

    public Respace() {
    }

    public void insertTire(String s, Tire head){
        if (this.head == null) {
            this.head = new Tire(null);
        }

        int len = s.length();
        int idx = 0;
        Tire curTire = this.head;
        while (idx < len){


            idx++;
        }
    }

    public boolean isMatch(String s){
        return false;
    }

    public void respace(String[] dictionary, String sentence){

    }

    public void buildCache(String[] dictionary, int prefixLen){

    }
}
