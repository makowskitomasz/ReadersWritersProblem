import org.junit.Test;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static junit.framework.TestCase.assertNotNull;

public class ReaderTest {

    /**
     * Test for the construction of Reader and the
     * reader object being called
     */
    @Test
    public void shouldCreateReaderObject(){
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);
        Reader reader = new Reader(library, "reader_");
        assertNotNull("Reader object called.", reader);
    }

    /**
     * Test for checking if in 5 seconds reader stops reading
     * @throws InterruptedException
     */
    @Test
    public void readerStopsReading() throws InterruptedException {
        Queue<Thread> queue = new LinkedList<>();
        Library library1 = new Library(queue);
        int numberOfReaders = 10;
        List<Thread> Readers = new ArrayList<>();

        for (int i = 0; i < numberOfReaders; i++) {
            Readers.add(new Thread(new Reader(library1, "reader_" + i)));
        }

        for(Thread thread : Readers){
            thread.start();
        }

        Thread.sleep(5000);

    }
}
