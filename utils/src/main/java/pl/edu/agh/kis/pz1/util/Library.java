package pl.edu.agh.kis.pz1.util;

import java.util.Objects;
import java.util.Queue;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Library {
    Queue<Thread> queue;
    private int numberOfWriters = 0, numberOfReaders = 0;
    public static Logger logger = LogManager.getLogManager().getLogger(Library.class.getName());

    public Library(Queue<Thread> queue){
        this.queue = queue;
    }

    public int getNumberOfWriters(){
        return numberOfWriters;
    }

    public int getNumberOfReaders(){
        return numberOfReaders;
    }

    public void setNumberOfWriters(int numberOfWriters){
        this.numberOfWriters = numberOfWriters;
    }

    public void setNumberOfReaders(int numberOfReaders){
        this.numberOfReaders = numberOfReaders;
    }

    public synchronized void startWriting(){
        try{
            while(numberOfWriters > 0 || numberOfReaders > 0 || !Objects.equals(queue.peek(),
                    Thread.currentThread())){
                wait();
            }
            queue.remove();
            numberOfWriters += 1;
        } catch(InterruptedException e){
            logger.info(e.toString());
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void stopWriting(){
        numberOfWriters -= 1;
        notifyAll();
    }

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
            logger.info(e.toString());
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void stopReading(){
        numberOfReaders =- 1;
        notifyAll();
    }
}
