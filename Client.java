import java .util.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;
public class Client {
    public static void main(String args[])
    {
        try{
            Socket s=new Socket("localhost",8000);
            System.out.println("Server started");
            BufferedReader input=new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader input1=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output=new PrintWriter(s.getOutputStream(),true);
            System.out.println("Enter a word : ");
            String str=input1.readLine();
            int n=str.length();
            for(int i=0;i<n;i=i+3)
            {
                long st=System.currentTimeMillis();
                if(i+3<n)
                {
                    output.println(str.substring(i,i+3));
                }
                else
                {
                    output.println(str.substring(i,n));
                }
                String ack=input.readLine();
                long et=System.currentTimeMillis();
                double time=(et-st)/1000.0;
                System.out.println(time);
                if(time>6)
                {
                    throw new TimeoutException("TimeOutError");
                }
                if(ack.equals("Received"))
                {
                    continue;
                }
                else
                {
                    break;
                }
            }
            // FileWriter fw=new FileWriter("./output.txt");
            // int i=0;
            // String str1="";
            // while(i<4)
            // {
            //     str1+=input.readLine();
            //     i++;
            // }
            // fw.write(str1);
            // fw.close();
            }
            catch(TimeoutException e)
            {
                System.out.println("Timeout");
            }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());        }
    }
}
