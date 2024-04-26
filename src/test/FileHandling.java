package test;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileHandling {

    public ArrayList<CommandData> ReadTest(String filePathString){
        Path p = FileSystems.getDefault().getPath("logs", "access.log");
        //File f = new File();
        ArrayList<CommandData> list=  new ArrayList<CommandData>();
        list.add(new CommandData());

        return list;
    };
}