import org.junit.Test;
import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Reader;
import pl.edu.agh.kis.pz1.util.Writer;

import java.util.LinkedList;
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
}
