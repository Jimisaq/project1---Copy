import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Client started..");
        try(Socket soc = new Socket("localhost",2212);){
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
            runClient(out,in);

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    
    }

    public static void runClient(PrintWriter out, BufferedReader in) throws IOException {
        String request;
        String response;
        Scanner scanner = new Scanner(System.in);
        showMenu();
        do{
            System.out.print("IES_MCS>>");
            request = scanner.nextLine();
            out.println(request);
            if(request.equalsIgnoreCase("done")){
                break;
            }
            while((response = in.readLine())!=null){
                System.out.println(response);
            }

        }while(!request.equalsIgnoreCase("done"));
        scanner.close();

        runClient(out,in);
    }

    public static void showMenu(){
        String instructionSet1= """
                                            **WELCOME TO IES MATH CHALLENGE SYSTEM**
                Available commands:
                _______________________________________________________________________________________________________________________
                >register <username> <firstname> <lastname> <email> <password> <DateOfBirth> <school_reg_no> <imageFile.png> to register
                >login <p>(pupil)/<sr>(school representative) <username> <password> to log into the system
                >done to exit the system
                -----------------------------------------------------------------------------------------------------------------------
                """;
        System.out.println(instructionSet1);
    }
}
