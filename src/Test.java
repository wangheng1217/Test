import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) throws ParseException {
//        String a = "07/15/2013 00:00:00";
//        System.out.println(a.replaceAll("/", "").substring(0, 8));
//        
//        String b = "20130712";
//        System.out.println(b.substring(4, 8) + b.substring(0, 4));
//        
//        String aa = "aa";
//        up(aa);
//        System.out.println(aa);

//        String tsp = "TSP0AH1N1-01";
//        if (tsp != null)
//            tsp = tsp.replaceAll("-", "");
//
//        System.out.println(tsp);

//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//        df.parse(null);
        
//        String a = "A";
//        String b = "B";
//        test(a, b);
//        System.out.println(a+b);
        
//        int i = '9'-'0';
//        System.out.println(i);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println((-3)/2);
//        
//        Long a = new Long(1);
//        Long b = new Long(1);
//        System.out.println(a==b);
//        
//        System.out.println("//".split("/").length);
//        
//        int[] l = {1};
//        int[] m = l;
//        l[0] = 2;
//        System.out.println(m[0]);
 
//        IRIType type = null;
//        switch (type) {
//        case A:
//            return;
//        case B:
//            return;
//        case C:
//            return;
//        default:
//            return;
//        }
        
//        System.out.println(Long.valueOf("1"));
//        System.out.println((int)'a');
//        System.out.println((int)'A');
//        for(int i = 0; i < 256; i++) {
//            System.out.println(i + " = " + (char)i);
//        }
//        
//        System.out.println("ABC " + 'd');
//        
//        String a = "AAA";
//        System.out.println(a.substring(a.length(), a.length()).equals(""));
        
//        Pattern messageIdPattern = Pattern.compile("<MESSAGE_ID>(\\w+)</MESSAGE_ID>");
//        String content = "<MESSAGE_ID>  sfaf </MESSAGE_ID>";
//        Matcher m = messageIdPattern.matcher(content);
//        System.out.println(m.find());
//        
//        String osaVendorIntervalSQL =
//                "select interval.days " +
//                "from osa_twtc.ref_install_interval interval," +
//                "  osa_twtc.ref_interface interface " +
//                "where interval.ccna = ?" +
//                "  and interval.icsc = ?" +
//                "  and interface.interface_rate = ?" +
//                "  and interval.interface_id = interface.id";
//        System.out.println(osaVendorIntervalSQL);

//    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    	System.out.println(simpleDateFormat.parse("2014-02-28"));
//    	
//    	BigInteger i = new BigInteger("9138178814013941966");
//    	System.out.println(i);
//    	
//    	String s1 = "AAABBBCCC";
//    	String s2 = s1.replaceFirst("BBB", "OOO");
//    	System.out.println(s1);
//    	System.out.println(s2);
        
        /*
    	String ip = null;
    	List<String> ipListInUse = new ArrayList<String>();
    	ip = "198.18.233.152/30";
    	ipListInUse.add(ip);
    	ip = "198.18.233.152/31";
    	ipListInUse.add(ip);
    	ipListInUse.add(ip);
//    	ipListInUse.add("198.18.233.152/30");
//    	ipListInUse.add("198.18.233.152/31");
//    	ipListInUse.add("198.18.233.152/32");
    	
    	String ipListStr = "";
        for (int i = 0; i < ipListInUse.size(); i++) {
            if (i > 0) {
                ipListStr = ipListStr + " | ";
            }
            ipListStr = ipListStr + ipListInUse.get(i);
        }
        
        System.out.println(ipListStr);
        
        C c = new C();
        c.print("SSS");
        c.print(c);
        
        
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(i1 == i2);
        Integer i3 = 1;
        Integer i4 = 1;
        System.out.println(i3 == i4);
        
        char c1 = 'A';
        char c2 = 'a';
        System.out.println(c1 < c2);
        
        String[] dp1 = new String[1];
        List<String>[] dp2 = new List[1];
        
        Long l1 = Long.valueOf(0);
        Long l2 = null;
        long l3 = 0;
        Long l4 = new Long(0);
        // Long.valueOf was cached
        Long l5 = Long.valueOf(0);
        System.out.println(l3 == l1);
        System.out.println(l1 == l3);
//        System.out.println(l3 == l2);
//        System.out.println(l2 == l3);
        System.out.println(l4 == l1);
        System.out.println(l5 == l1);
        */
        
        
        Executor executor = java.util.concurrent.Executors.newFixedThreadPool(10);
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> ite = list.iterator();
        while (ite.hasNext()) {
            ite.next();
//            list.remove(s);
//            ite.remove();
//            System.out.println(list.size());
        }
        
        try{
            String s = null;
            s.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage() == null);
        }
        
        System.out.println(8 >> 1);
        System.out.println(-8 >> 1);
        System.out.println(-8 >>> 1);
        
    }
    
    static void test(String a, String b){
        a = b;
    }
    
    static void up(String a){
        System.out.println(a.toUpperCase());
        System.out.println(a);
    }

}

enum IRIType {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    J,
    K,
    L,
    M,
    N,
    P,
    R;

    public String value() {
        return name();
    }

    public static IRIType fromValue(String v) {
        return valueOf(v);
    }

}

class A {
    private A(){}
    
      A(int i){}
    
    public static A createA() {
        java.util.Comparator<String> c = new java.util.Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                return 0;
            }
           
        };
        
        return new A();
    }
}

class B extends A{

    B() {
        super(1);
        // TODO Auto-generated constructor stub
    }

    A a = A.createA();
}

class C {
    void print(Object o) {
        System.out.println("Object: " + o);
    }
    
    void print(String s) {
        System.out.println("String: " + s);  
    }
}

