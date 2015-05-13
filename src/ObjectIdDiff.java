import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.jms.TextMessage;
import javax.jms.TopicPublisher;


public class ObjectIdDiff {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
      String fileName = "C:\\Users\\hwan4805\\Desktop\\OSA notes\\0124 silent failure investigate\\id0128.txt";
      String fileName2 = "C:\\Users\\hwan4805\\Desktop\\OSA notes\\0124 silent failure investigate\\existingComponentRsiID0128.txt";
      java.util.Set<String> set = new java.util.TreeSet<String>();

      /* Get file contents of message file we are going to send. */

      BufferedReader msgFileRdr = new BufferedReader(new FileReader(fileName));
      String line = null;
      while ((line = msgFileRdr.readLine()) != null) {
          set.add(line);
      }
      
      BufferedReader msgFileRdr2 = new BufferedReader(new FileReader(fileName2));
      String line2 = null;
      while ((line2 = msgFileRdr2.readLine()) != null) {
          set.remove(line2);
      }
      
      for(String s: set) {
          System.out.println(s);
      }
      

    }

}
