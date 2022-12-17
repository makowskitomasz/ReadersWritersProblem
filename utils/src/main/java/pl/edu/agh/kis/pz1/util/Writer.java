package pl.edu.agh.kis.pz1.util;

import java.text.MessageFormat;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Writer {
    Library library;
    int id;

    private static final Logger logger = LogManager.getLogManager().getLogger(Reader.class.getName());

    public Writer(Library library, int id){
        this.library = library;
        this.id = id;
    }

//        @Override
    public void run(){
        try{
            while(true){
                logger.info(MessageFormat.format("{0} {1} is waiting.", Thread.currentThread(), id));
                library.queue.add(Thread.currentThread());
                library.startReading();

                logger.info(MessageFormat.format("{0} {1} starts writing. Number of writers: {2}, Number of readers: {3}",
                        Thread.currentThread(), id, library.getNumberOfWriters(), library.getNumberOfReaders()));

                Thread.sleep(1000);

                library.stopReading();
                logger.info(MessageFormat.format("{0} {1} stopped writing", Thread.currentThread(), id));
            }
        } catch(InterruptedException e){
            logger.info(e.toString());
            Thread.currentThread().interrupt();
        }
    }
}
