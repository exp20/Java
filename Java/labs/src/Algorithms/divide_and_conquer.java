package Algorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
//метод разделяй и влавствуй
public class divide_and_conquer {
    public static void main(String ... arg){
        System.out.println(sum(new ArrayList(Arrays.asList(1,2,3,4))));
    }
    public static int sum(ArrayList arr){
        if (arr.size()==0){
            return 0;
        }
        else{
            return (int)arr.remove(arr.size()-1) + sum(arr);
        }

    }

}
