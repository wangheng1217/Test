package wangheng.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConcurrentTest {

    public static void main(String[] args) {
        new ConcurrentTest().test2();
        System.exit(0);
    }

    List<String> list;
    UtilClass util = new UtilClass();

    public void test() {
        list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()) {
            String s = ite.next();
            list.remove(s);
            System.out.println(list.size());
        }
    }

    public void test2() {
        list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Executor executor = Executors.newFixedThreadPool(10);
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()) {
            String s = ite.next();
            executor.execute(new MyThread(s));
            System.out.println(list.size());
        }
    }

    class MyThread extends Thread {
        String s;

        MyThread(String s) {
            this.s = s;
        }

        public void run() {
//            list.remove(s);
            util.doSomething();
        }
    }
    
    class UtilClass {
        int i = 0;
        public void doSomething() {
            for (int j = 0; j < 40000; j++) {
                if (j%10000 == 0) System.out.println(j);
            }
        }
    }
}
