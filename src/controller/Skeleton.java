package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Skeleton {
    
    private static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    private static void printMenu() {
        System.out.println("Logarlec game tester -- Echo team");
        System.out.println("To test the model, please enter a number from the list");
        System.out.println("_____________________\n"
                            + "1. Item pickup\n"
                            + "2. Item drop\n"
                            + "3. Item use\n"
                            + "4. Room interactions\n"
                            + "5. Room functionality\n"
                            + "6. Item functionality\n"
                            + "9. Exit program\n"
                            + "---------------------");
    }

    public static void Menu() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int number = 0;
        System.out.println("Press ANY BUTTON to start...");

        while (true) {
            try {
                String line = br.readLine();
        
                clearScreen();
                printMenu();
                System.out.print("Enter a number: ");
        
                line = br.readLine();
                if(line == null)
                    break;
                number = Integer.parseInt(line);
                
                switch (number) {
                    case 1:
                        System.out.println("Switching to 1");
                        break;
                    case 2:
                        System.out.println("Switching to 2");
                        break;
                    case 3:
                        System.out.println("Switching to 3");
                        break;
                    case 4:
                        System.out.println("Switching to 4");
                        break;
                    case 5:
                        System.out.println("Switching to 5");
                        break;
                    case 6:
                        System.out.println("Switching to 6");
                        break;
                    case 9:
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Incorrect input!");
                        break;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Not a number!");
            } catch (IOException e) {
                System.out.println("Could not read input");
                e.printStackTrace();
            } 
        }
    }

    public static void main(String[] args) {
        Menu();
    }
}
