package com.github.harbby.gadtry.java8Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created By lcl on 2019/7/24
 */
public class MapReduce {
    public static void main(String[] args) {
        // 为每个订单加上12%的税
// 老方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

// 新方法：
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax2.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);

    }
}
