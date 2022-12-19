package pl.edu.agh.kis.pz1;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import org.junit.Test;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Writer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;


public class MainTest {

    Random random = new Random();
    /**
     * Test for the construction of Main and the 
     * main method being called
     */
    @Test
    public void shouldCreateMainObject(){
        Main main = new Main();
        assertNotNull("Main method called.", main);
    }

    /**
     * Test to check if as many as number of readers + writers threads have been started
     */
    @Test
    public void howManyThreads(){
        int numberOfThreads = Thread.activeCount();
        int numberOfReaders = random.nextInt(10);
        int numberOfWriters = random.nextInt(10);
        List<Thread> Readers = new ArrayList<>();
        List<Thread> Writers = new ArrayList<>();
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);
        for (int i = 0; i < Math.max(numberOfReaders, numberOfWriters); i++) {
            if(i < numberOfReaders) Readers.add(new Thread(new Reader(library, "Reader_" + i), "Reader_" + i));
            if(i < numberOfWriters) Writers.add(new Thread(new Writer(library, "Writer_" + i), "Writer_" + i));
        }
        for(Thread thread : Writers){
            thread.start();
        }
        for(Thread thread : Readers){
            thread.start();
        }
        assertEquals(numberOfReaders + numberOfWriters + numberOfThreads, Thread.activeCount() );
    }

    /**
     * Test that checks created arrays
     */
    @Test
    public void arraysCreatedCorrectly() {
        List<Thread> Readers = new ArrayList<>();
        List<Thread> Writers = new ArrayList<>();
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);
        int numberOfReaders = random.nextInt(10), numberOfWriters = random.nextInt(10);
        for (int i = 0; i < Math.max(numberOfReaders, numberOfWriters); i++) {
            if(i < numberOfReaders) Readers.add(new Thread(new Reader(library, "Reader_" + i), "Reader_" + i));
            if(i < numberOfWriters) Writers.add(new Thread(new Writer(library, "Writer_" + i), "Writer_" + i));
        }
        assertEquals(Readers.size(), numberOfReaders);
        assertEquals(Writers.size(), numberOfWriters);
    }
}


