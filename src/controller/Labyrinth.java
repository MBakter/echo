package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.ERoomEffects;
import model.Room;
import model.items.*;

public class Labyrinth {

    List<Room> roomList;

    private Room findRoom(String roomName) {
        for (Room room : roomList) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    private void initMerge(Room r1, Room r2) {
        if(!r1.merge(r2))
            return;
        for (Room room : roomList) {
            if(room.equals(r1) || room.equals(r2))
                continue;
            for (int i = 0; i < room.getNeighbours().size(); i++) 
                if(room.getNeighbours().get(i).equals(r2))
                    room.getNeighbours().set(i, r1);
        }
        roomList.remove(r2);
    }

    public Labyrinth() {
        roomList = new ArrayList<Room>();
    }

    public void randomMove() {
        if (roomList == null || roomList.isEmpty()) {
            throw new RuntimeException("Üres lista");
        }

        Random random = new Random();
        int i = random.nextInt(roomList.size() - 1);
        Room room = roomList.get(i);

        double x = random.nextDouble();
        if (x <= 0.5) {
            room.split();
        } else {
            int i2 = random.nextInt(roomList.size() - 1);
            while (i2 == i) {
                System.out.println("ASDASD");
                i2 = random.nextInt(roomList.size() - 1);
                System.out.println(i2);
                if(i2 ==0)
                break;
            }
            initMerge(room, roomList.get(i2));
        }
    }
    
    public void placeItems(Timer t){
        ArrayList<IItem> items = new ArrayList<>();

        Cheese c1 = new Cheese("c1",t);
        Cheese c2 = new Cheese("c2",t);
        Transistor t1 = new Transistor("t1");
        Transistor t2 = new Transistor("t2");
        Transistor t3 = new Transistor("t3");
        Logarlec l1 = new Logarlec("l1");
        Logarlec l2 = new Logarlec("l1", true);
        TVSZ tvsz1 = new TVSZ("tvsz1");
        TVSZ tvsz2 = new TVSZ("tvsz2");
        TVSZ tvsz3 = new TVSZ("tvsz3", true);
        Purifier p1 = new Purifier("p1");
        Purifier p2 = new Purifier("p2");
        Beer b1 = new Beer(t);
        Beer b2 = new Beer(t);
        Sponge s1 = new Sponge(t);
        Mask m1 = new Mask("m1", t);
        Mask m2 = new Mask("m2", t);
        Mask m3 = new Mask("m3", t, true);

        items.add(c1);
        items.add(c2);
        items.add(t1);
        items.add(t2);
        items.add(t3);
        items.add(l1);
        items.add(l2);
        items.add(tvsz1);
        items.add(tvsz2);
        items.add(tvsz3);
        items.add(p1);
        items.add(p2);
        items.add(b1);
        items.add(b2);
        items.add(s1);
        items.add(m1);
        items.add(m2);
        items.add(m3);

        Random r = new Random();

        for (IItem iItem : items) {
            int randomNum = r.nextInt(roomList.size()-2);
            roomList.get(randomNum).addItem(iItem);
        }
    }

    public boolean generateFromFile(String filename) {
        
        File file = new File(filename);
        if(!file.exists())
            return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("create room")) 
                {   
                    if (parts.length < 3) 
                        throw new IOException("Hiba a fájl beolvasása során: Szoba neve nem található");
                    

                    String roomName = parts[2];
                    Room room = new Room(roomName);
                    roomList.add(room);

                    if(parts.length == 4){
                        if(parts[3].equals("p")){
                            room.addEffect(ERoomEffects.POISONED);
                        }
                        if(parts[3].equals("c")){
                            room.addEffect(ERoomEffects.CURSED);
                        }
                    }

                } else if (line.startsWith("link")) 
                {
                    String room1Name = parts[1];
                    String room2Name = parts[2];

                    if (room1Name == null || room2Name == null) 
                        throw new IOException("Hiba a fájl beolvasása során: Szobák neve nem található");
                    
                    Room room1 = findRoom(room1Name);
                    Room room2 = findRoom(room2Name);

                    if (room1 != null && room2 != null) 
                        room1.addNeighbour(room2);
                    else 
                        throw new IOException("Hiba a fájl beolvasása során: Szoba nem létezik");
                    
                } else {
                    throw new IOException("Hiba a fájl beolvasása során: Parancs nem értelmezett");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void clear() {
        roomList.clear();
    }
    
}
