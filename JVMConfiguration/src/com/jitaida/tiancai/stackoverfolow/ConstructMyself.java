package com.jitaida.tiancai.stackoverfolow;

public class ConstructMyself {
    ConstructMyself(String a, String b) {
        new ConstructMyself(a, b);
    }

    public static void main(String[] args) {
        /*
         This will directly trigger StackOverflowError

         */
        ConstructMyself a = new ConstructMyself("sfdadfadfsafsafdsafsafasfasfsdfsafsafasf", "sfdlasdjfksfljsalkfjslfjsaldfjlsakjfslkjflsajflksjflksajflkjsaflkjdsalfjs");
    }
}
