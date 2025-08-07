package DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deque1 {
    static int capacity;
    static int front;
    static int rear;
    static String deque[];
    Scanner sc = new Scanner(System.in);

    public Deque1(int size) {
        deque = new String[size];
        capacity = size;
        front = rear = -1;
    }


    public boolean IsEmpty() {
        return capacity == 0;
    }

    public void InsertLast(String SongPath)
    {
        if(rear==capacity-1)
        {
            System.out.println("\n Deque Overflow");
        }
        else{
            if(front==-1)
            {
                front=0;
            }
            rear=rear+1;
            deque[rear]=SongPath;
        }
    }

    public String DeleteFirst()
    {
        if(front==-1)
        {
            System.out.println("\n Deque Underflow");
        }
        else{

            if(front==rear)
            {
                front=-1;
                rear=-1;
            }
            front=front+1;
        }
        return deque[front-1];
    }

    public void InsertFirst(String SongName) {
        if (front == (rear + 1) % capacity) {
            System.out.println("\n Deque Overflow");
        } else {
            if (front == -1 && rear == -1) {
                front = 0;
                rear = 0;
            } else if (front == 0) {
                front = capacity - 1;
            } else {
                front--;
            }
            deque[front] = SongName;
        }
    }

    public String DeleteLast() {
        if (front == -1) {
            System.out.println("\n Underflow");
        } else {
            if (front == rear) {
                front = rear = -1;
            } else if (rear == 0) {
                rear = capacity - 1;
            } else {
                rear--;
            }
        }
        return deque[rear+1];
    }

    public void DeleteAll() {
        if (front == -1) {
            System.out.println("\n Deque is already empty.");
        } else {
            front = rear = -1;
            System.out.println("\n Deque cleared successfully.");
        }
    }

    public void Shuffle() {
            ArrayList<String> arraylist = new ArrayList<>();
            // Converting array to arraylist
            for (String element : deque) {
                if (element != null) {
                    arraylist.add(element);
                }
            }

            Collections.shuffle(arraylist);

            // Converting arraylist back to array
            for (int i = 0; i < arraylist.size(); i++) {
                    deque[i] = arraylist.get(i);
            }
    }

}
