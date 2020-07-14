package com.jitaida.tiancai.leetcode.hard;

/**
 * ����һ���ַ���?(s) ��һ���ַ�ģʽ?(p) ��ʵ��һ��֧��?'?'?��?'*'?��ͨ���ƥ�䡣
 *
 * '?' ����ƥ���κε����ַ���
 * '*' ����ƥ�������ַ������������ַ�������
 * �����ַ�����ȫƥ�����ƥ��ɹ���
 *
 * ˵��:
 *
 * s?����Ϊ�գ���ֻ������?a-z?��Сд��ĸ��
 * p?����Ϊ�գ���ֻ������?a-z?��Сд��ĸ���Լ��ַ�???��?*��
 * ʾ��?1:
 *
 * ����:
 * s = "aa"
 * p = "a"
 * ���: false
 * ����: "a" �޷�ƥ�� "aa" �����ַ�����
 * ʾ��?2:
 *
 * ����:
 * s = "aa"
 * p = "*"
 * ���: true
 * ����:?'*' ����ƥ�������ַ�����
 * ʾ��?3:
 *
 * ����:
 * s = "cb"
 * p = "?a"
 * ���: false
 * ����:?'?' ����ƥ�� 'c', ���ڶ��� 'a' �޷�ƥ�� 'b'��
 * ʾ��?4:
 *
 * ����:
 * s = "adceb"
 * p = "*a*b"
 * ���: true
 * ����:?��һ�� '*' ����ƥ����ַ���, �ڶ��� '*' ����ƥ���ַ��� "dce".
 * ʾ��?5:
 *
 * ����:
 * s = "acdcb"
 * p = "a*c?b"
 * ���: false
 *
 */
public class CheckIsMatch {
    public boolean isMatch(String s, String p){
        int pLen = p.length();
        int sLen = s.length();
        boolean[][] dp = new boolean[pLen+1][sLen+1];

        dp[0][0] = true;

        for (int i = 1; i<=pLen; i++){
            if (p.charAt(i-1) != '*'){
                break;
            }

            dp[i][0] = true;
        }

        for (int i = 1; i <= pLen; i++){
            for (int j = 1; j <= sLen; j++){
                if (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(i-1) == '*'){
                    dp[i][j] = dp[i-1][j] | dp[i][j-1];
                }
            }
        }

//        for (int i = 0; i < dp.length; i ++){
//            for(int j = 0; j< dp[0].length; j++)
//            {
//                System.out.print(dp[i][j] + " ");
//            }
//
//            System.out.print("\n");
//        }

        return dp[pLen][sLen];
    }

    public static void main(String[] args){
        String s = "abceb";
        String p = "*a*b";

        CheckIsMatch cim = new CheckIsMatch();
        System.out.println(cim.isMatch(s, p));

    }
}
