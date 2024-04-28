package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import controller.Timer;
import model.*;
import model.player.*;
import model.items.*;

/**
 * TestRunner
 */
public class TestRunner {
    private enum Type {
        PLAYER,
        ITEM,
        ROOM
    }

    private Timer testTimer = new Timer();
    private ArrayList<CommandData> commands;
    private HashMap<String, IItem> items = new HashMap<>();
    private HashMap<IItem, String> itemsReverse = new HashMap<>();
    private HashMap<String, Player> players = new HashMap<>();
    private HashMap<Player, String> playersReverse = new HashMap<>();
    private HashMap<String, Room> rooms = new HashMap<>();
    private HashMap<Room, String> roomsReverse = new HashMap<>();

    public TestRunner(ArrayList<CommandData> l) {
        commands = l;
    }

    public TestRunner() {
        commands = new ArrayList<>();
    }
    public void addCommand(CommandData c) {
        if (!c.subject.isEmpty()) {
            commands.add(c);
            evaluate();
        }
    }
    public void evaluate() {
        for (CommandData cd : commands) {
            interpretCmd(cd);
        }
    }

    private void interpretCmd(CommandData cd) {
        switch (cd.type) {
            case CREATE:
                cmdCreate(cd.subject);
                break;
            case LINK:
                cmdLink(cd.subject);
                break;
            case STATE:
                cmdState(cd.subject);
                break;
            case INTERACT:
                cmdInteract(cd.subject);
                break;
            case STAT:
                cmdStat(cd.subject);
                break;
            case CONTROL:
                cmdControl(cd.subject);
                break;
            case SAVE:
                cmdSave(cd.subject);
                break;
            case LOAD:
                cmdLoad(cd.subject);
                break;
            default:
                break;
        }
    }

    private void cmdCreate(ArrayList<String> args) {
        if (args.size() < 2) {
            return;
        }
        String name = args.get(1);
        switch (args.get(0)) {
            case "room":
                Room r = new Room(name);
                rooms.put(args.get(1), r);
                roomsReverse.put(r, args.get(1));
                System.out.printf("room %s created%n", args.get(1));
                break;
            case "student":
                Student st = new Student(name);
                st.setState(EPlayerState.ALIVE);
                players.put(args.get(1), st);
                playersReverse.put(st, args.get(1));
                System.out.printf("student %s created%n", args.get(1));
                break;
            case "teacher":
                Teacher t = new Teacher(name);
                t.setState(EPlayerState.ALIVE);
                players.put(args.get(1), t);
                playersReverse.put(t, args.get(1));
                System.out.printf("teacher %s created%n", args.get(1));
                break;
            case "cleaner":
                Cleaner cl = new Cleaner(name);
                cl.setState(EPlayerState.ALIVE);
                players.put(args.get(1), cl);
                playersReverse.put(cl, args.get(1));
                System.out.printf("cleaner %s created%n", args.get(1));
                break;
            case "cheese":
                Cheese ch = new Cheese(name, testTimer);
                items.put(args.get(1), ch);
                itemsReverse.put(ch, args.get(1));
                System.out.printf("cheese %s created%n", args.get(1));
                break;
            case "transistor":
                Transistor tr = new Transistor(name);
                items.put(args.get(1), tr);
                itemsReverse.put(tr, args.get(1));
                System.out.printf("transistor %s created%n", args.get(1));
                break;
            case "logar":
                Logarlec lo = new Logarlec(name);
                items.put(args.get(1), lo);
                itemsReverse.put(lo, args.get(1));
                System.out.printf("logar %s created%n", args.get(1));
                break;
            case "tvsz":
                TVSZ tvsz = new TVSZ(name);
                items.put(args.get(1), tvsz);
                itemsReverse.put(tvsz, args.get(1));
                System.out.printf("tvsz %s created%n", args.get(1));
                break;
            // TODO : Not yet implemented in model
            /*
             * case "purifier":
             * items.put(args.get(1), new Purifier());
             * System.out.printf("logarlec %s created%n", args.get(1));
             * break;
             */
            case "beer":
                Beer br = new Beer(name, testTimer);
                items.put(args.get(1), br);
                itemsReverse.put(br, args.get(1));
                System.out.printf("beer %s created%n", args.get(1));
                break;
            case "sponge":
                Sponge sp = new Sponge(name, testTimer);
                items.put(args.get(1), sp);
                itemsReverse.put(sp, args.get(1));
                System.out.printf("sponge %s created%n", args.get(1));
                break;
            case "mask":
                Mask ma = new Mask(name, testTimer);
                items.put(args.get(1), ma);
                itemsReverse.put(ma, args.get(1));
                System.out.printf("mask %s created%n", args.get(1));
                break;
            default:
                break;
        }
    }

    private Type getType(String name) {
        for (String savedName : rooms.keySet()) {
            if (savedName.equals(name)) {
                return Type.ROOM;
            }

        }
        for (String savedName : players.keySet()) {
            if (savedName.equals(name)) {
                return Type.PLAYER;
            }

        }
        for (String savedName : items.keySet()) {
            if (savedName.equals(name)) {
                return Type.ITEM;
            }

        }
        return null;
    }

    private void cmdLink(ArrayList<String> args) {
        Type arg1 = getType(args.get(0));
        if (arg1 == Type.ROOM) {
            if (getType(args.get(1)) == Type.ROOM) {
                rooms.get(args.get(0)).addNeighbour(rooms.get(args.get(1)));
                System.out.printf("%s connected to %s%n", args.get(0), args.get(1));
            }
        }
        if (arg1 == Type.PLAYER) {
            if (getType(args.get(1)) == Type.ROOM) {
                players.get(args.get(0)).move(rooms.get(args.get(1)));
                System.out.printf("%s moved to %s%n", args.get(0), args.get(1));
            }
        }
        if (arg1 == Type.ITEM) {

        }

    }

    private void cmdState(ArrayList<String> args) {

    }

    private void cmdInteract(ArrayList<String> args) {

    }

    private void cmdStat(ArrayList<String> args) {
        System.out.println("stat:");
        for (var item : players.entrySet()) {
            /*
             * System.out.printf("%s room %s%n", item.getKey(),
             * roomsReverse.get(item.getValue().getRoom()));
             * System.out.printf("%s EPlayerState %s%n", item.getKey(),
             * item.getValue().getState());
             * System.out.printf("%s itemList", item.getKey());
             * for (var playersItem : item.getValue().getItems()) {
             * System.out.printf(" %s", itemsReverse.get(playersItem));
             * }
             * System.out.printf("%n");
             */
            item.getValue().PrintStat(item.getKey());
        }
        for (var item : items.entrySet()) {
            item.getValue().PrintStat(item.getKey());
        }
        ArrayList<String> tmpRoomNames = new ArrayList<>(rooms.keySet());
        Collections.sort(tmpRoomNames);
        for (var item : tmpRoomNames) {
            rooms.get(item).PrintStat("need to remove");
            // item.getValue().PrintStat(item.getKey());
            /*
             * String name = item.getKey();
             * Room r = item.getValue();
             * System.out.printf("%s effects",name);
             * for (var effect : r.getEffects()) {
             * System.out.printf(" %s", effect);
             * }
             * System.out.printf("%n");
             * 
             * System.out.printf("%s itemList",name);
             * for (var roomItem : r.getItems()) {
             * System.out.printf(" %s", itemsReverse.get(roomItem));
             * }
             * System.out.printf("%n");
             * 
             * System.out.printf("%s studentList",name);
             * for (var student : r.getStudents()) {
             * System.out.printf(" %s", playersReverse.get(student));
             * }
             * System.out.printf("%n");
             * 
             * System.out.printf("%s teacherList",name);
             * for (var teacher : r.getTeachers()) {
             * System.out.printf(" %s", playersReverse.get(teacher));
             * }
             * System.out.printf("%n");
             * 
             * // TODO klíner
             * System.out.printf("%s cleanerList",name);
             * for (var teacher : r.getTeachers()) {
             * System.out.printf(" %s", playersReverse.get(teacher));
             * }
             * System.out.printf("%n");
             * 
             * System.out.printf("%s neighbouringRooms",name);
             * for (var neighbour : r.getNeighbours()) {
             * System.out.printf(" %s", roomsReverse.get(neighbour));
             * }
             * System.out.printf("%n");
             * 
             * System.out.printf("%s sticky %s%n",name,false);
             * System.out.printf("%s cleaned %s%n",name,false);
             * // TODO kieg
             * int numOfVisitors = r.getStudents().size() + r.getTeachers().size();
             * System.out.printf("%s numOfVisitors %d%n",name, numOfVisitors);
             */
        }
    }

    private void cmdControl(ArrayList<String> args) {

    }

    private void cmdSave(ArrayList<String> args) {

    }

    private void cmdLoad(ArrayList<String> args) {

    }
}