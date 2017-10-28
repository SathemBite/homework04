package task1;

import java.io.*;

/**
 * Created by anton on 21.10.17.
 */
public class JavaKeyWordsInFile {
    public static void main(String[] args) {
        byte[] javaCode = {};
        byte[] javaKW = {};
        try(FileInputStream fileJavaCode = new FileInputStream("src/main/resources/task1/javacode");
            FileInputStream fileKeyWords = new FileInputStream("src/main/resources/task1/javaKeyWords")){

            javaCode = new byte[fileJavaCode.available()];
            fileJavaCode.read(javaCode);
            javaKW =new byte[fileKeyWords.available()];
            fileKeyWords.read(javaKW);

        }catch (FileNotFoundException e){
            FileNotFndExcHandler(e);
        }catch (IOException e){
            IOExcHandler(e);
        }

        String jCode = new String(javaCode);
        String[] jKW = new String(javaKW).split("\n");
        int[] keyWordsSeq = countOfOccurens(jCode, jKW);

        try(FileOutputStream oSt = new FileOutputStream("src/main/resources/task1/output/result")){
            for (int i = 0; i < jKW.length; i++) {
                oSt.write(jKW[i].getBytes());
                oSt.write(" - ".getBytes());
                oSt.write(Integer.toString(keyWordsSeq[i]).getBytes());
                oSt.write('\n');
            }
        }catch (FileNotFoundException e){
            FileNotFndExcHandler(e);
        }catch (IOException e){
            IOExcHandler(e);
        }
    }
    
    public static int[] countOfOccurens(String text, String[] seekWords){
        int[] seekWordsSequence = new int[seekWords.length];

        for (int i = 0; i < seekWords.length; i++){
            int lastIndex = 0;

            while(lastIndex != -1){
                lastIndex = text.indexOf(seekWords[i],lastIndex);
                if(lastIndex != -1){
                    seekWordsSequence[i]++;
                    lastIndex += seekWords[i].length();
                }
            }
        }

        return seekWordsSequence;
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
