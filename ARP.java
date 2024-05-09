import java.io.*;
import java.util.regex.*;

public class ARP
 {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("arp -a");

            // Read the output of the command
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            // Store the output in a file
            File outputFile = new File("arp_output.txt");
            FileWriter writer = new FileWriter(outputFile);
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();

            int exitCode = process.waitFor();
            System.out.println("ARP command executed. Exit Code: " + exitCode);

            // Read user input for IP address to search
            while(true)
            {
                BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the IP address to search for: ");
            String ipToSearch = userInputReader.readLine();

            // Search for the IP address in the output file and extract the MAC address
            String macAddress = searchMacAddress(outputFile, ipToSearch);
            if (macAddress != null) {
                System.out.println("MAC address for IP " + ipToSearch + ": " + macAddress);
            } else {
                System.out.println("MAC address not found for IP " + ipToSearch);
            }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String searchMacAddress(File file, String ipAddress) throws IOException {
        Pattern pattern = Pattern.compile("\\b" + ipAddress + "\\b");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // Extract the MAC address from the line
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 3) {
                        char a=ipAddress.charAt(ipAddress.length()-3);
                        System.out.println(a);
                        if(a==':')
                            return parts[1];
                        return parts[3];
                    }
                }
            }
        }
        return null;
    }

    private static boolean isalpha(char charAt) {
        return false;
    }
}
