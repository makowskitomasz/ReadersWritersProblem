package pl.edu.agh.kis.pz1.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Objects;
import java.util.Queue;

/**
 * @author tomaszmakowski
 * Class defines Library
 */
public class Library {
    Queue<Thread> queue;
    private int numberOfWriters = 0;
    private int numberOfReaders = 0;
    private static final Logger logger = LogManager.getLogger(Library.class);
    /**
     * Constructor of Library Class
     * @param queue queue of all threads in the program
     */
    public Library(Queue<Thread> queue){
        this.queue = queue;
    }
    /**
     *
     * @return getter of number of Writers in the Library
     */
    public int getNumberOfWriters(){
        return numberOfWriters;
    }
    /**
     * @return getter of number of Readers in the Library
     */
    public int getNumberOfReaders(){
        return numberOfReaders;
    }
    /**
     *
     * @param numberOfWriters the number of Writers in Library chose by the user
     */
    public void setNumberOfWriters(int numberOfWriters){
        this.numberOfWriters = numberOfWriters;
    }
    /**
     *
     * @param numberOfReaders the number of Readers in Library chose by the user
     */
    public void setNumberOfReaders(int numberOfReaders){
        this.numberOfReaders = numberOfReaders;
    }
    /**
     * Allows Writer to enter the Library if it meets the conditions
     */
    public synchronized void startWriting(){
        try{
            while(numberOfWriters > 0 || numberOfReaders > 0 || !Objects.equals(queue.peek(),
                    Thread.currentThread())){
                wait();
            }
            queue.remove();
            numberOfWriters += 1;
        } catch(InterruptedException e){
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Decreases the number of Writers in Library (numberOfWriters = 0) and informs the other
     */
    public synchronized void stopWriting(){
        numberOfWriters -= 1;
        notifyAll();
    }
    /**
     * Allows Reader to enter the library if it meets the conditions
     */
    public synchronized void startReading(){
        try{
            while(numberOfWriters > 0 || numberOfReaders >= 5 || !Objects.equals(queue.peek(),
                    Thread.currentThread())){
                wait();
            }
            queue.remove();
            numberOfReaders += 1;
            notifyAll();
        } catch (InterruptedException e){
            logger.trace(e);
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Decreases the number of Readers in Library and informs the other
     */
    public synchronized void stopReading(){
        numberOfReaders -= 1;
        notifyAll();
    }
}
