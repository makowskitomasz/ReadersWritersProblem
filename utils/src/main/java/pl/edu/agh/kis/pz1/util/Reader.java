package pl.edu.agh.kis.pz1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.MessageFormat;
import java.util.Random;


/**
 * Class that defines Reader
 * @author tomaszmakowski
 */
public class Reader implements Runnable {
    Library library;
    String id;
    Random random = new Random();
    private static final Logger logger = LogManager.getLogger(Reader.class);
    /**
     * Constructor of Reader Class
     *
     * @param library object of class Library
     * @param id unique id of each person in the Library
     */
    public Reader(Library library, String id){
        this.library = library;
        this.id = id;
    }
    /**
     * Method that overrides run method.
     * It defines the behaviour of the reader who entered the library
     * And gives the information to output
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run(){
        try{
            while(true){
                if(logger.isInfoEnabled()) {
                    logger.info(MessageFormat.format("{0} is waiting.", id));
                    library.queue.add(Thread.currentThread());
                    library.startReading();
                    logger.info(MessageFormat.format("{0} starts reading. Number of writers: {1}, Number of readers: {2}.",
                            id, library.getNumberOfWriters(), library.getNumberOfReaders()));
                    Thread.sleep(random.nextInt(2000) + 1000);
                    library.stopReading();
                    logger.info(MessageFormat.format("{0} stopped reading", id));
                }

            }
        } catch(InterruptedException e){
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }
}
