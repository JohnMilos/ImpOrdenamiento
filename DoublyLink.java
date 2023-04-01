public class DoublyLink<T> {
    public T dData; 
    public DoublyLink<T> next; 
    public DoublyLink<T> previous; 

    public DoublyLink(T d) { 
        dData = d; 
    }

    public void displayLink() { 
        System.out.print(dData + ","); 
    }

} 