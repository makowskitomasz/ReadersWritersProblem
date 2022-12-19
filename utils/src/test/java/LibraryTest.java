import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Writer;

import java.util.*;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class LibraryTest {
    Queue<Thread> queue = new LinkedList<>();
    Library library = new Library(queue);
    Random random = new Random();
    @Test
    public void getterWriters(){
        assertEquals(0, library.getNumberOfWriters());
    }
    @Test
    public void getterReaders(){
        assertEquals(0, library.getNumberOfReaders());
    }
    /**
     * Test for the construction of Library and the
     * library object being called
     */
    @Test
    public void shouldCreateLibraryObject(){
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);
        assertNotNull("Library object called.", library);
    }
    /**
     * Test for the method stopReading() it checks if the number of readers decreases if the reader stops reading
     */
    @Test
    public void readerStopsReading(){
        library.setNumberOfReaders(5);
        library.stopReading();
        assertEquals(4, library.getNumberOfReaders());
    }
    /**
     * Test for the method stopWriting() it checks if the number of writers decreases if the reader stops writing
     */
    @Test
    public void writerStopsWriting(){
        library.setNumberOfWriters(1);
        library.stopWriting();
        assertEquals(0, library.getNumberOfWriters());
    }
    /**
     * Tests if none of threads that have run is interrupted
     * @throws InterruptedException
     */
    @Test
    public void allThreadsAlive() throws InterruptedException {
        int numberOfReaders = random.nextInt(5) + 5;
        int numberOfWriters = random.nextInt(2) + 1;
        List<Thread> Readers = new ArrayList<>();
        List<Thread> Writers = new ArrayList<>();
        for (int i = 0; i < numberOfReaders; i++)
            Readers.add(new Thread(new Reader(library, "reader_" + i)));
        for (int i = 0; i < numberOfWriters; i++)
            Writers.add(new Thread(new Writer(library, "writer_" + i)));
        for(Thread thread : Writers)
            thread.start();
        for(Thread thread : Readers)
            thread.start();
        for(Thread thread : Writers)
            assertTrue(thread.isAlive());
        for(Thread thread : Readers)
            assertTrue(thread.isAlive());
    }
    /**
     * Tests if number of Readers is always less or equal 5
     * @throws InterruptedException
     */
    @Test
    public void libraryNotOverflowByReaders() throws InterruptedException {
        int numberOfReaders = 6;
        List<Thread> Readers = new ArrayList<>();
        for (int i = 0; i < numberOfReaders; i++) {
            Readers.add(new Thread(new Reader(library, "reader_" + i)));
        }
        for(Thread thread : Readers){
            thread.start();
        }
        Thread.sleep(1000);
        assertEquals(5, library.getNumberOfReaders());
        assertNotNull(queue);
        for(Thread thread : Readers){
            thread.interrupt();
        }
    }
    /**
     * Tests if number of Writers is always equal zero or one
     * @throws InterruptedException
     */
    @Test
    public void libraryNotOverflowByWriters() throws InterruptedException {
        int numberOfWriters = 4;
        List<Thread> Writers = new ArrayList<>();
        for (int i = 0; i < numberOfWriters; i++)
            Writers.add(new Thread(new Writer(library, "writer_" + i)));
        for (Thread thread : Writers){
            thread.start();
        }

        Thread.sleep(1000);
        assertEquals(1, library.getNumberOfWriters());
        assertNotNull(queue);

        for(Thread thread : Writers){
            thread.interrupt();
        }
    }
    /**
     * Tests if the writer cannot write when readers are reading
     * @throws InterruptedException
     */
    @Test
    public void xorReadersWritersInLibrary() throws InterruptedException {
        int numberOfReaders = random.nextInt(5) + 5;
        int numberOfWriters = random.nextInt(2) + 1;
        List<Thread> Readers = new ArrayList<>();
        List<Thread> Writers = new ArrayList<>();
        for (int i = 0; i < numberOfReaders; i++)
            Readers.add(new Thread(new Reader(library, "reader_" + i)));
        for (int i = 0; i < numberOfWriters; i++)
            Writers.add(new Thread(new Writer(library, "writer_" + i)));
        for(Thread thread : Writers)
            thread.start();
        for(Thread thread : Readers)
            thread.start();
        if(library.getNumberOfReaders() > 0)
            assertEquals(0, library.getNumberOfWriters());
        else
            assertEquals(0, library.getNumberOfReaders());
    }
    /**
     * Tests if random number of Readers can be in the queue
     */
    @Test
    public void correctSetNumberOfReaders(){
        int n = random.nextInt(99) + 1;
        library.setNumberOfReaders(n);
        assertEquals(library.getNumberOfReaders(), n);
    }
    /**
     * Tests if random number of Writers can be in the queue
     */
    @Test
    public void correctSetNumberOfWriters(){
        int n = random.nextInt(99) + 1;
        library.setNumberOfWriters(n);
        assertEquals(library.getNumberOfWriters(), n);
    }
}



