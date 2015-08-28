/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.util;

/**
 * Math functions.
 *
 * @author dmitriya
 */
public class MathHelper {

    private MathHelper() {
    }

    /**
     * Find the minimum in an array of numbers.
     * @param nums
     * @return
     */
    public static double min(double... nums) {
        double min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > min) {
                min = nums[i];
            }
        }
        return min;
    }
}
