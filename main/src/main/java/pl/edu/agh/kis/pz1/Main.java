package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.Library;
import pl.edu.agh.kis.pz1.util.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @author tomaszmakowski
 */
public class Main {
    public static void main(String[] args){
        List<Thread> Readers = new ArrayList<>();
        List<Thread> Writers = new ArrayList<>();
        Queue<Thread> queue = new LinkedList<>();
        Library library = new Library(queue);

        int numberOfWriters = 3;
        int numberOfReaders = 10;

        for (int i = 0; i < max(numberOfReaders, numberOfWriters); i++) {
            if(i < numberOfReaders) Readers.add(new Thread(new Reader(library, i)));
            if(i < numberOfWriters) Writers.add(new Thread(new Writer(library, 100 + i)));
        }

        for(Thread thread : Writers){
            thread.start();
        }

        for(Thread thread : Readers){
            thread.start();
        }
    }
}
