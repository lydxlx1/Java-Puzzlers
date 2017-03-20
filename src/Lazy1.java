/**
 * Puzzle 85: Lazy Initialization
 * <p>
 * This does fix the deadlock problem, but it is a very bad idea.
 * The main thread waits for the background thread to finish its work,
 * but other threads don't have to.
 * They can use the class Lazy as soon as the main thread has finished initializing it,
 * allowing them to observe initialized when its value is still false.
 */
public class Lazy1 {
    private static boolean initialized = false;


    private static Thread t = new Thread(new Runnable() {
        public void run() {
            initialized = true;
        }
    });

    static {
        t.start();
    }

    public static void main(String[] args) {
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
        System.out.println(initialized);
    }
}
