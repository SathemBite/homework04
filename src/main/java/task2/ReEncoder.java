package task2;

import java.io.*;

/**
 * Created by anton on 23.10.17.
 */
public class ReEncoder {
    public static void main(String[] args) {
        char[] content = new char[1000000];
        int countOfSymb = 0;
        try(InputStreamReader in = new InputStreamReader(
                                     new FileInputStream("src/main/resources/task2/in_utf8"),
                                     "UTF-8")){
            countOfSymb = in.read(content);
        }catch (FileNotFoundException e){
            FileNotFndExcHandler(e);
        }catch (IOException e){
            IOExcHandler(e);
        }
        try(OutputStreamWriter outS =
                            new OutputStreamWriter(
                                    new FileOutputStream("src/main/resources/task2/out_utf16"),
                                    "UTF-16")){
            outS.write(content, 0, countOfSymb);
        }catch (FileNotFoundException e){
            FileNotFndExcHandler(e);
        }catch (IOException e){
            IOExcHandler(e);
        }
    }


    public static void IOExcHandler(IOException ex){
        System.out.println("Input\\Output error!");
        throw new RuntimeException(ex);
    }


    public static void FileNotFndExcHandler(IOException ex){
        System.out.println("The specified file not found!");
        throw new RuntimeException(ex);
    }
}
