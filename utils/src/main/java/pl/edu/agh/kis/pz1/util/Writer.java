package pl.edu.agh.kis.pz1.util;
import java.text.MessageFormat;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Class that defines Reader
 * @author tomaszmakowski
 */
public class Writer implements Runnable{
    Library library;
    String id;
    Random random = new Random();
    private static final Logger logger = LogManager.getLogger(Writer.class);
    /**
     * Constructor of Writer Class
     *
     * @param library object of class Library
     * @param id unique id of each person in the Library
     */
    public Writer(Library library, String id){
        this.library = library;
        this.id = id;
    }
    /**
     * Method that overrides run method.
     * It defines the behaviour of the writer who entered the library
     * And gives the information to output
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run(){
        try{
            while(true){
                    logger.info(MessageFormat.format("{0} is waiting.", id));
                    library.queue.add(Thread.currentThread());
                    library.startWriting();
                    logger.info(MessageFormat.format("{0} starts writing. Number of writers: {1}, Number of readers: {2}",
                            id, library.getNumberOfWriters(), library.getNumberOfReaders()));
                    Thread.sleep(random.nextInt(2000) + 1000);
                    library.stopWriting();
                    logger.info(MessageFormat.format("{0} stopped writing", id));

            }
        } catch(InterruptedException e){
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }
}
