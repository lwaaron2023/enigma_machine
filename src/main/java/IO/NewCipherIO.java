package IO;

import Cipher.Cipher;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.LinkedList;
import java.util.List;

public class NewCipherIO {
    /**The cipher used to encrypt/decrypt*/
    private Cipher cipher;


    /**
     * Standard constructor
     * @param key the cipher key to be used to encrypt/decrypt
     */
    public NewCipherIO(int key){
        this.cipher = new Cipher(key);
    }


    /**
     *Encrypts a file of user selection, by:
     *
     * i) Reading in the file
     * ii) Encrypting the contents
     * iii) Creating a new file in the same directory with the encrypted contents of the aforementioned file
     *
     *@param toRead is the file whose text is to be encrypted
     *@param outputLocation is the name of the directory in which the encrypted file will be stored
     */
    public void encryptFile(File toRead, File outputLocation) throws IOException, FileNotFoundException
    {

        LinkedList<String> encrypted = new LinkedList<>(this.readIn(toRead, true));

        NewCipherIO.printOut(NewCipherIO.createOutPutFile(toRead, outputLocation, true), encrypted);

        //System.out.println("Encrypted- Done");
        System.gc();

    }
    /**
     *Decrypts a file of user selection, by:
     *
     * i) Reading in the file
     * ii) Decrypting the contents
     * iii) Creating a new file in the same directory with the decrypted contents of the aforementioned file
     *
     * @param toRead is the file whose text is to be decrypted
     *@param outputLocation is the name of the directory in which the decrypted file will be stored
     */
    public void decryptFile(File toRead, File outputLocation) throws IOException, FileNotFoundException
    {

        LinkedList<String> encrypted = new LinkedList<>(this.readIn(toRead, false));
        //PB.setProgress(.5);
        NewCipherIO.printOut(NewCipherIO.createOutPutFile(toRead, outputLocation, false), encrypted);
        //System.out.println("Decrypted- Done");
        //PB.setProgress(1);
        System.gc();
    }
    /**
     *Converts the text of a textfile into a list of Strings that is either encrypted or decrypted
     *
     * @param toRead is the file that is being read
     * @param encrypt if true the reader will encrypt each line, false means it will decrypt instead
     * @return List<String> is a list of string representing each line of text
     */
    private List<String> readIn(File toRead, boolean encrypt) throws IOException, FileNotFoundException{
        FileReader in = new FileReader(toRead);
        BufferedReader input = new BufferedReader(in);
        LinkedList<String> output = new LinkedList<>();
        String addition = input.readLine();

        while (addition!=null){
            //System.out.println(addition);
            if(encrypt) {
                output.add(cipher.encrypt(addition));
            }
            else{
                output.add(cipher.decrypt(addition));
            }
            addition = input.readLine();
        }

        input.close();
        in.close();
        //System.out.println("Done- Reading In");
        return output;

    }
    /**
     *Converts the text represented by a list of String into text in a file
     *
     * @param file is the path to the file in question
     * @param toPrint is a list of string representing each line of text
     *
     * @return boolean, returns true if operation was succesfull
     */
    private static boolean printOut(File file, List<String> toPrint){
      try{

        PrintStream out = new PrintStream(file);

        for(String s: toPrint){
            out.println(s);
        }

        out.close();

        return true;

      }catch(IOException e){
          System.err.println(e.getMessage());
          throw new RuntimeException();
      }
    }

    /**
     * Creates the output file at the directory outputLocation
     *
     * @param hasBeenRead is the file whose text was encrypted/decrypted
     * @param outputLocation is the location that the encrypted/decrypted file will be created
     * @param encrypt if true the creator will use the encrypt naming scheme, false means it will decrypt naming scheme
     * @return File the file that has been created
     */
    private static File createOutPutFile(File hasBeenRead, File outputLocation, boolean encrypt){
        try{
            if(encrypt) {
                File f = new File(outputLocation.getPath(), "e_"+hasBeenRead.getName().hashCode()+".txt");
                f.createNewFile();
                //System.out.println(f.getPath());

                return f;

            }
            else{
                File f = new File(outputLocation.getPath(), "d_"+hasBeenRead.getName().hashCode()+".txt");
                f.createNewFile();
                //System.out.println(f.getPath());

                return f;
            }
        } catch(FileAlreadyExistsException e){
            if(encrypt) {

                return new File("e_"+hasBeenRead.getName().hashCode()+".txt");
            }
            else{

                return new File("d_"+hasBeenRead.getName().hashCode()+".txt");

            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

}
