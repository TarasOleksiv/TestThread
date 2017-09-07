/**
 * Created by Taras on 07.09.2017.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mutagen on 09.09.13.
 */
public class CountThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread("a" + i) {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        for (String s : getRunningThreads()) {
            System.out.println(s);
        }
    }

    static List<String> getRunningThreads() {
        List<String> threads = new ArrayList<>();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parent;
        while ((parent = threadGroup.getParent()) != null) {
            if (threadGroup != null) {
                threadGroup = parent;
                Thread[] threadList = new Thread[threadGroup.activeCount()];
                threadGroup.enumerate(threadList);
                for (Thread thread : threadList)
                    threads.add(new StringBuilder().append(thread.getThreadGroup().getName())
                            .append("::").append(thread.getName()).append("::PRIORITY:-")
                            .append(thread.getPriority()).toString());
            }
        }
        return threads;
    }
}