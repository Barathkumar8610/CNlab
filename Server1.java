import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Server1 {
    public static void main(String args[])
    {
        Process p;
        try {
            p = Runtime.getRuntime().exec("traceroute localhost");
            BufferedReader input=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str=input.readLine();
            System.out.println(str);
            // String str1[]=str.split(" ");
            // Map<String,String> map=new HashMap<String,String>();
            // map.put(str1[1],str1[3]);
            // System.out.println(map.get(str1[1]));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

}
}
