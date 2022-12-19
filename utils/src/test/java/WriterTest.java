import org.junit.Test;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Writer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static junit.framework.TestCase.assertNotNull;



public class WriterTest {
    Queue<Thread> queue = new LinkedList<>();
    Library library = new Library(queue);
    /**
     * Test for the construction of Writer and the
     * writer object being called
     */
    @Test
    public void shouldCreateWriterObject(){
        Writer writer = new Writer(library, "writer_");
        assertNotNull("Writer object called.", writer);
    }

    /**
     * Test for checking if in a 5 seconds writer will stop writing
     * @throws InterruptedException
     */
    @Test
    public void writerStopsWriting() throws InterruptedException {
        Queue<Thread> queue = new LinkedList<>();
        Library library1 = new Library(queue);
        int numberOfWriters = 1;
        List<Thread> Writers = new ArrayList<>();
        for (int i = 0; i < numberOfWriters; i++) {
            Writers.add(new Thread(new Writer(library1, "writer_" + i)));
        }
        for(Thread thread : Writers){
            thread.start();
        }
        Thread.sleep(5000);
    }
}
