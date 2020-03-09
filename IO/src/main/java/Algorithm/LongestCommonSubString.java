package Algorithm;

public class LongestCommonSubString {

    public static String getLongestCommonSubString(String a, String b){

        if (a ==null || b == null){
            return null;
        }

        if (a.isEmpty() || b.isEmpty()){
            return null;
        }

        String longerOne;
        String shorterOne;

        if (a.length() > b.length()){
            longerOne = a;
            shorterOne = b;
        } else{
            longerOne = b;
            shorterOne = a;
        }

        String current ="";
        String best = "";
        int index_b = 0;

        for (int i = 0; i<shorterOne.length() ; i++){
            current = current + a.charAt(i);

            if (b.contains(current)){
                continue;
            }

            if (current.length() > best.length()){
                best = current;
                current = "";
            }
        }

        return current;

    }

    public static void main(String[] args){

        String a = "sadfasdfasfdasfasfasdfsakjsldjfs   sdfassfdsdfsssssssssssssssssssssssssssssssssssssssssssssss";
        String b = "sasdkfhoiajxknbqmzdpkalnqmer wsdpkcvnsdfsssjsdflsssssslssssss";

        System.out.println(LongestCommonSubString.getLongestCommonSubString(a, b));
    }
}
