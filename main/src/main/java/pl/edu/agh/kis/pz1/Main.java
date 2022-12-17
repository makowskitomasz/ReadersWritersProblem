package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Writer;

import java.sql.SQLOutput;
import java.util.*;


/**
 * @author tomaszmakowski
 * @version 1.0.0
 */
public class Main {
    /**
     *
     * @param args none
     */
    public static void main(String[] args){
        List<Thread> Readers = new ArrayList<>();
        List<Thread> Writers = new ArrayList<>();
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);

        int numberOfReaders, numberOfWriters;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter number of Readers: ");
        numberOfReaders = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter number of Writers: ");
        numberOfWriters = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < Math.max(numberOfReaders, numberOfWriters); i++) {
            if(i < numberOfReaders) Readers.add(new Thread(new Reader(library, "Reader_" + i)));
            if(i < numberOfWriters) Writers.add(new Thread(new Writer(library, "Writer_" + i)));
        }


        for(Thread thread : Writers){
            thread.start();
        }

        for(Thread thread : Readers){
            thread.start();
        }

    }
}
