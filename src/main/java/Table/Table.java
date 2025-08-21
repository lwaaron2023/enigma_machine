package Table;

import java.lang.Math;
import java.util.*;
import java.lang.reflect.Array;
import java.util.Random;

public class Table {
    public static void main(String[] args)
    {
        
       System.out.println(Arrays.toString(random_list(1,26,1))); 
    }

    /**
     *create a list including all integers from min to max inclusive in a random order
     * @param max the highest value in the list
     * @param min the lowest value in the list
     *@param seed the seed for randomization
     * */
    public static int[] random_list(int min, int max, int seed) {
        Random rand = new Random(seed);
        int range = max-min;
        int range_initial = max-min;
        int new_index;
        int index;

        int baseArray[] = new int[range+2];
        int randomArray[] = new int[range+2];

        boolean no_zeros = true;
        int char_to_compare;
        int char_to_add;  
        int x_1 = 0;

        for (int x=0;x<=range;x++)
        {
            baseArray[(int)x]=(int)x+min;
        }
        do
        {
            if (range+1 >0){
            new_index = (int)((rand.nextInt((range+1))));
            }
            else
            {
                new_index = 0;
            }
            range--;
            char_to_add = Array.getInt(baseArray,new_index);
            randomArray[(int)x_1]=(int)char_to_add;
            //System.out.println(Arrays.toString(randomArray));
            x_1 = x_1 +1;
            //System.out.println(x_1);
            for (int x = new_index; x<range_initial+1; x++)
            {
                char_to_add = Array.getInt(baseArray,(int)x+1);
                baseArray[(int)x]=(int)char_to_add;
            }
            baseArray[(int)range_initial+1]=(int)-1;
            //System.out.println(Arrays.toString(baseArray));
            for (int x = 0; x<range_initial+1; x++)
            {   
                char_to_compare = Array.getInt(baseArray,x);
                if((int)char_to_compare==-1)
                {
                    no_zeros = false;
                }
                else if((int)char_to_compare!=-1)
                {
                    no_zeros = true;
                    break;
                }
            }
            
        }while(no_zeros);
        //System.out.println(Arrays.toString(randomArray));
        int[] randomTrueArray = Arrays.copyOf(randomArray, range_initial+1);
        return randomTrueArray;

    }
}
