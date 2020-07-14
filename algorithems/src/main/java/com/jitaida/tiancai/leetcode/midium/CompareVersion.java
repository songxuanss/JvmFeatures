package com.jitaida.tiancai.leetcode.midium;

public class CompareVersion {
    /**
     * �Ƚ������汾�� version1?�� version2��
     * ���?version1?>?version2?����?1�����?version1?<?version2 ���� -1�� ����֮�ⷵ�� 0��
     * <p>
     * ����Լ���汾�ַ����ǿգ�����ֻ�������ֺ�?. �ַ���
     * <p>
     * ?. �ַ�������С���㣬�������ڷָ��������С�
     * <p>
     * ���磬2.5 ���ǡ������롱��Ҳ���ǡ���һ�뵽���������ǵڶ����еĵ����С�汾��
     * <p>
     * ����Լ���汾�ŵ�ÿһ����Ĭ���޶����Ϊ 0�����磬�汾�� 3.4 �ĵ�һ������汾���͵ڶ�����С�汾���޶��ŷֱ�Ϊ 3 �� 4����������͵��ļ��޶��ž�Ϊ 0��
     * <p>
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/compare-version-numbers
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return 0;
        }

        if (version1.length() == 0 || version2.length() == 0) {
            return 0;
        }

        if (version1.startsWith(".") || version2.startsWith(".")){
            return 0;
        }

        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");

        int ver1, ver2;

        for (int i = 0; i < Math.max(versionArray1.length, versionArray2.length); i++) {
            try{
                ver1 = versionArray1.length > i ? Integer.parseInt(versionArray1[i]) : 0;
                ver2 = versionArray2.length > i ? Integer.parseInt(versionArray2[i]) : 0;
            }catch(Exception e){
                /*
                For the case that the item in array cannot be parsed as integer version like:
                "3.as.3"
                "3..3"
                 */
                return 0;
            }

            if (ver1 > ver2) return 1;
            if (ver1 < ver2) return -1;
        }

        return 0;
    }

    public static void main(String[] args) {
        CompareVersion cv = new CompareVersion();
        System.out.println(cv.compareVersion("4.1.2","3.1"));
    }
}