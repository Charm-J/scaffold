package com.dj.scaffold.util;

import java.util.Random;

public class RandomUtil {

    public static Integer getRandomRange( Integer MAX, Integer MIN){
        Random random = new Random();
        return random.nextInt(MAX - MIN + 1) + MIN;

    }


    public static void main(String[] args) {
        Integer MAX = 5;
        Integer MIN = 1;
        Random random = new Random();
        int randNumber = random.nextInt(MAX - MIN + 1) + MIN;
        System.out.println(randNumber);
    }
}
