package com.jitaida.tiancai.leetcode.midium;

public class CompareVersion {
    /**
     * 比较两个版本号 version1?和 version2。
     * 如果?version1?>?version2?返回?1，如果?version1?<?version2 返回 -1， 除此之外返回 0。
     * <p>
     * 你可以假设版本字符串非空，并且只包含数字和?. 字符。
     * <p>
     * ?. 字符不代表小数点，而是用于分隔数字序列。
     * <p>
     * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
     * <p>
     * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/compare-version-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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