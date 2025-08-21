package Cipher;

import java.util.LinkedList;
import java.util.List;
import Table.Table;
public class Cipher
{
    /**It is the starting value for the key*/
    private int start;
    /**It represents all the tables the cipher could use*/
    private int[][] tables;
    /**
     * @author Aaron Waller
     * @param key is the numerical key for encryption/decryption
     * */
    public Cipher(int key){
        start = key;
        tables = new int[start][963];
        for(int i = 0; i< tables.length; i++){
            tables[i] = Table.random_list(32,993, i);
        }
    }

    /**
     * @param input is a String representing the input that needs to be encrypted
     * @return String representing the encrypted String
     */
    public String encrypt(String input){
        String output = new String();
        for(int x = 0; x<input.length(); x++)
        {
            char ch = input.charAt(x);
            //System.out.print(ch);
            int original = ch;
            if(original>=32&&original<=993) {
                original -= 32;
                int[] values = tables[x%start];
                int mod = values[original];
                //System.out.println("'" + (char) mod + "'");
                output = output + (char) mod;
            }
            else{
                output = output +ch;
            }
        }

        return output;
    }
    /**
     * @param input is a String representing the input that needs to be decrypted
     * @return String representing the decrypted String
     */
    public String decrypt(String input){
        String output = new String();
        for(int x = 0; x<input.length(); x++)
        {
            char ch = input.charAt(x);
            int original = ch;
            if(original>=32&&original<=993) {
                int[] values = tables[x%start];
                int mod = 0;
                for (int y = 0; y < values.length; y++) {
                    if (original == values[y]) {
                        mod = y + 32;
                        break;
                    }

                }
                //System.out.println ("'"+(char)mod+"'");
                output = output + (char) mod;
            }
            else{
                output = output +ch;
            }
        }
        return output;
    }

}




