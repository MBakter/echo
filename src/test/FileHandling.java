package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandling {
    private boolean debug = false;
    // Write read strings to terminal
    private void Debug(){
        if(debug == false)
            debug = true;
        else
            debug = false;
    }
    // Read the text from the Input Stream.
    private ArrayList<String> readFromInputStream(InputStream inputStream) throws IOException{
        ArrayList<String> lineList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank())
                    lineList.add(line.trim());
            }
        }
        return lineList;
    }


    // Main runner For testing 
    public static void main(String[] args) throws IOException {
        FileHandling fh = new FileHandling();
        Scanner scan =new Scanner(System.in);
        boolean exit = false;
        TestRunner tr = new TestRunner();
        while(!exit){
            String input = scan.nextLine();
            tr.addCommand((fh.interpreteLine(input.split(" "))));
            if(input.equals("exit"))
                exit = true;
        }
        scan.close();
        
        // File file = new File(currentDir, path);
/*         var list = fh.ReadTest("src/test/test_txt/test_input/test1.txt");
        fh.WriteTestResult(list.get(0).subject, "src/test/test_txt/test_output/test1_outtttt.txt");

        TestRunner tr2 = new TestRunner(list);
        tr2.evaluateAll(); */
    }

    // Writing out the resulting lines of String to the designated File.
    public void WriteTestResult(ArrayList<String> textLineListResult, String testPathToResultFile) throws IOException{
        String currentDir = System.getProperty("user.dir");
        File currDirPath = new File(currentDir);
        File filePath = new File(currDirPath, testPathToResultFile);
        FileWriter myWriter = null;

        try {
            myWriter = new FileWriter(filePath);
            for (int i = 0; i < textLineListResult.size(); i++) {
                myWriter.write(textLineListResult.get(i));
                if (i != textLineListResult.size() -1)  myWriter.write(System.lineSeparator());
            }
            myWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (myWriter != null){
                try{
                    myWriter.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            } 
        }
    }

    // Read file with a given path (as a string) and it splits into individual commands
    // returns the commands as alist with its agruments.
    public ArrayList<CommandData> ReadTest(String filePathString){
        String currentDir = System.getProperty("user.dir");
        File currDirPath = new File(currentDir);
        File filePath = new File(currDirPath, filePathString);

        FileInputStream fis = null;
        ArrayList<CommandData> list =  null;
        try {
            fis = new FileInputStream(filePath);
            ArrayList<String> lines = readFromInputStream(fis); 
            
            if(debug){
                for (String string : lines) {
                    System.out.println(string);
                }
            }

            fis.close();

            list = tokeniseLine(lines);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try{
                    fis.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    // Process the Line list for further interpretation.
    private ArrayList<CommandData> tokeniseLine(ArrayList<String> lines) throws IOException {
        ArrayList<CommandData> commandList = new ArrayList<CommandData>();

        // Each line is split to words to the line interpreter.
        for (String oneLineAtATime: lines){
            String[] split = oneLineAtATime.split(" ");
            commandList.add(interpreteLine(split));
        }
        return commandList;
    }

    // Categorise the command by the starting of the line, 
    // and appending the rest of the arguments to the subject.
    private CommandData interpreteLine(String[] split) throws IOException {
        CommandData command = new CommandData();
        command.subject = new ArrayList<String>();

        // Command type definition from first word
        switch (split[0]) {
            case "create": command.type = ECommand.CREATE; break;
            case "link": command.type = ECommand.LINK; break;
            case "state": command.type = ECommand.STATE; break;
            case "control": command.type = ECommand.CONTROL; break;
            case "interact": command.type = ECommand.INTERACT; break;
            case "stat": command.type = ECommand.STAT; break;
            case "save": command.type = ECommand.SAVE; break;
            case "load": command.type = ECommand.LOAD; break;
            //default: throw new IOException("Wrong string at start of line, wrong command naming \"" + split[0] + "\"");
            default: System.out.println("Unrecognized command");
        }

        // Appending the rest of the words as subject
        for (int i = 1; i < split.length; i++){
            command.subject.add(split[i]);
        }
        if(debug)
            System.out.println("valami "+ command.type);
        return command;
    };
}