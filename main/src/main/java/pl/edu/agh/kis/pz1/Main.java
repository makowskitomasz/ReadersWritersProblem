package pl.edu.agh.kis.pz1;

import org.apache.logging.log4j.LogManager;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Writer;

import java.util.*;

/**
 * @author tomaszmakowski
 * @version 1.0.0
 */
public class Main {
    /**
     * Main method of program, user chooses the number of readers and writers and threads are started
     * @param args none
     */
    public static void main(String[] args){
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(Main.class);
        List<Thread> readers = new ArrayList<>();
        List<Thread> writer = new ArrayList<>();
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);

        int numberOfReaders;
        int numberOfWriters;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        logger.info("Enter number of Readers: ");
        numberOfReaders = Integer.parseInt(scanner.nextLine());
        logger.info("Enter number of Writers: ");
        numberOfWriters = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < Math.max(numberOfReaders, numberOfWriters); i++) {
            if(i < numberOfReaders) readers.add(new Thread(new Reader(library, "Reader_" + i), "Reader_" + i));
            if(i < numberOfWriters) writer.add(new Thread(new Writer(library, "Writer_" + i), "Writer_" + i));
        }


        for(Thread thread : writer){
            thread.start();
        }

        for(Thread thread : readers){
            thread.start();
        }

    }
}
