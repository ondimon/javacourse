public class WaitNotify {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';
    private final int COUNT = 5;

    public static void main(String[] args) {
        WaitNotify printClass = new WaitNotify();
        Thread printA = new Thread(() -> printClass.print('A', 'B'));
        Thread printB = new Thread(() -> printClass.print('B', 'C'));
        Thread printC = new Thread(() -> printClass.print('C', 'A'));
        printA.start();
        printB.start();
        printC.start();
    }

    public void print(char printChar, char nextChar) {
        synchronized (monitor) {
            try {
                for ( int i = 0; i < COUNT; i++ ) {
                    while (currentLetter != printChar) {
                        monitor.wait();
                    }
                    System.out.print(printChar);
                    currentLetter = nextChar;
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
