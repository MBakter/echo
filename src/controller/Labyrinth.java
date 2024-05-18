package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Room;

public class Labyrinth {

    public List<Room> roomList;

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
                i2 = random.nextInt(roomList.size() - 1);
            }
            initMerge(room, roomList.get(i2));
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
    
}
