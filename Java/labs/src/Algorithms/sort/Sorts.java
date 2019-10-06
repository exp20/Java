package Algorithms.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Sorts {
    public static void main (String... args){
        selectionSotr(new ArrayList<Integer>(Arrays.asList(5,6,3,4,1)));
        System.out.println( my_quick_sort(new LinkedList<Integer>(Arrays.asList(1,3,9,19,29,2,6,7))));
    }
    public static void selectionSotr(ArrayList arr){ // O(n^2)
        ArrayList<Integer> sorted_array = new ArrayList<>(arr.size());
        int index  = 0;
        //находит оставшиеся минимальные элементы
       while (arr.size()!=0) {
            index =0;
            int smallest = (int) arr.get(0);
            //осущ поиск минимального элемента во всем массиве
            for (int i = 0; i < arr.size(); i++) {
                if ((int) arr.get(i) < smallest) {
                    smallest = (int) arr.get(i);
                    index = i;
                }
            }
            sorted_array.add(smallest);
            arr.remove(index);
        }
    for(int e : sorted_array){
        System.out.println(e);
    }
    }

    // худший случай O(n^2) лучший = среднему O(n log n) log n - высота стека вызовов (деение происхлдит на 2 массива)
    public static LinkedList<Integer> my_quick_sort(LinkedList<Integer> array){
        if (array.size()<2){
            return array;
        }
        else{
            int pivot = ThreadLocalRandom.current().nextInt(0, array.size());// выбор случайного опорного элемента
            LinkedList<Integer> less = new LinkedList();
            LinkedList<Integer> greate = new LinkedList();
            for (int i = 0; i < array.size(); i++){
                if(i != pivot) { //чтоб не добавлял сам опорный элемент в массивы less и greate
                    if (array.get(i) <= array.get(pivot)) {
                        less.add(array.get(i));
                    } else {
                        greate.add(array.get(i));
                    }
                }
            }


            System.out.println("less " +less+" pivot " + array.get(pivot)+" greate "+ greate);

            LinkedList<Integer> res = new LinkedList<>();

            if (less.size()!=0) {
                res.addAll(my_quick_sort(less));
            }
            res.add(array.get(pivot));
            if (greate.size()!=0){
                res.addAll(my_quick_sort(greate));
            }
            return res;
        }

    }

}
