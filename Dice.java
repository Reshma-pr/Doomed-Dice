package Securin;

import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.Map;

public class Dice {
    public static void main(String[] args) {
        System.out.println("Question-1");
        int total_outcomes=total_combinations();
        System.out.println("Question-2");

        HashMap<Integer,Integer> dist_map= distribution_Map();
        System.out.println("Question-3");
        probablity(dist_map,total_outcomes);

    }
    public static int total_combinations(){
        int[] a = {1,2,3,4,5,6};
        int[] b = {1,2,3,4,5,6};
        System.out.println("Total Outcomes of Die A : " +a.length);
        System.out.println("Total Outcomes of Die B : " +b.length);
        int total_outcomes=a.length*b.length;
        System.out.println("Total Combinations Formed : Outcomes of A * Outcomes of B = "+total_outcomes);
        return total_outcomes;
    }
    public static HashMap<Integer,Integer> distribution_Map(){
        HashMap<Integer,Integer> distribution = new HashMap<>();
       int[] a= {1,2,3,4,5,6};
       int[] b = {1,2,3,4,5,6};
       int len = a.length;
       int[][] matrix = new int[6][6];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = a[i]+b[j];
            }
        }
        System.out.println("Matrix With Sum of Each Combination:");
        System.out.print("{ " );
        for (int i = 0; i < len; i++) {
            System.out.print('[');
            for (int j = 0; j < len; j++) {
                int val = matrix[i][j];
                System.out.print(val+",");
                if(distribution.containsKey(val)){
                    distribution.put(val,distribution.get(val)+1);
                }
                else {
                    distribution.put(val,1);
                }
            }
            System.out.print("\b]");
            System.out.println();
        }
        System.out.println("}");
        System.out.println("Distribution of Possible Combinations");
        distribution.forEach((key,value)-> System.out.println(key+": "+value));
        return distribution;
    }
    public static void probablity(HashMap<Integer,Integer> map,int total_outcomes){
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            double probability = (double) value / total_outcomes;
            System.out.println("P(" + key + ") = " + value + "/" + total_outcomes + " = " + String.format("%.3f",probability));
        }
    }
}
