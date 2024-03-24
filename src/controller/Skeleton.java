package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
        // Test Category List
        Map<Integer, Map<Integer, ITestcase>> tests = new HashMap<Integer, Map<Integer, ITestcase>>();

        // 1 Pickup Tests
        Map<Integer, ITestcase> ItemPickTests = new HashMap<Integer, ITestcase>();
        tests.put(1, ItemPickTests); // Testlist put to categorylist
        ItemPickTests.put(1, new test1()); 
        ItemPickTests.put(2, new test2()); 

        // 2 Drop Tests
        Map<Integer, ITestcase> ItemDropTests = new HashMap<Integer, ITestcase>();
        tests.put(2, ItemDropTests); // Testlist put to categorylist

        // 3 Use Tests
        Map<Integer, ITestcase> ItemUseTests = new HashMap<Integer, ITestcase>();
        tests.put(3, ItemUseTests); // Testlist put to categorylist

        // 4 RoomIneraction Tests
        Map<Integer, ITestcase> RoomInteractionTests = new HashMap<Integer, ITestcase>();
        tests.put(4, RoomInteractionTests); // Testlist put to categorylist

        // 5 Room functionality Tests
        Map<Integer, ITestcase> RoomFunctionalityTests = new HashMap<Integer, ITestcase>();
        tests.put(5, RoomFunctionalityTests); // Testlist put to categorylist
        fillRF.fill(RoomFunctionalityTests);

        // 6 Item functionality Tests
        Map<Integer, ITestcase> ItemFunctionalityTests = new HashMap<Integer, ITestcase>()
        {{
            put(1, new Beer_save());
            put(2, new TVSZ_save());
            put(3, new Sponge_save());
            put(4, new Mask_save());
            put(5, new Cheese_timer());
            put(6, new Cheese_timer_end());
            put(7, new Beer_timer());
            put(8, new Sponge_timer());
            put(9, new Mask_pause());
        }}; 
        tests.put(6, ItemFunctionalityTests); // Testlist put to categorylist


        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int number = 0;
        int categoryMenu = 0;
        //System.out.println("Press ANY BUTTON to start...");

        while (true) {
            try {
                String line;// = br.readLine();

                if  (categoryMenu == 0) {
                    clearScreen();
                    printMenu();
                    System.out.print("Enter a number: ");

                    line = br.readLine();
                    if(line == null)
                        break;
                    number = Integer.parseInt(line);

                    if(number == 9)
                        return;

                    if (tests.containsKey(number)){

                        categoryMenu = number;
                        System.out.format("Switching to %d%n", categoryMenu);
                    } else {
                        System.out.print("wrong number");
                    };

                }
                if (categoryMenu != 0) {

                    clearScreen();
                    System.out.println("Press 0 to return to category menu:");
                    for (Map.Entry<Integer, ITestcase> e : tests.get(categoryMenu).entrySet()){
                        System.out.format("Option %d: %s %n", e.getKey(), e.getValue().testTitle());
                    }
                    System.out.print("Enter a number: ");

                    line = br.readLine();
                    if(line == null)
                        break;
                    number = Integer.parseInt(line);

                    if (number == 0) {
                        categoryMenu = 0;
                    }

                    switch (categoryMenu) {
                        case 1: //Item pickup
                            testList(ItemPickTests, number);
                            break;
                        case 6:
                            testList(ItemFunctionalityTests, number);
                        case 5:
                            testList(RoomFunctionalityTests, number);
                            break;
                        default:
                            break;
                    }
                    if (categoryMenu != 0) {
                        System.out.println("Press RETURN to continue...");
                        line = br.readLine();
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Not a number!");
            } catch (IOException e) {
                System.out.println("Could not read input");
                e.printStackTrace();
            }
        }
    }

    public static void testList(Map<Integer, ITestcase> m, Integer option){

        if (m.containsKey(option)){
            m.get(option).runTest();
        } else {
            System.out.println("There is no option like this");
        }

    }

    public static void main(String[] args) {
        Menu();
    }
}
