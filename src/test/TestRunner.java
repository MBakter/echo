package test;

import java.io.IOException;
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

    public Timer testTimer = new Timer();
    public ArrayList<CommandData> commands;
    public HashMap<String, IItem> items = new HashMap<>();
    public HashMap<IItem, String> itemsReverse = new HashMap<>();
    public HashMap<String, Player> players = new HashMap<>();
    public HashMap<Player, String> playersReverse = new HashMap<>();
    public HashMap<String, Room> rooms = new HashMap<>();
    public HashMap<Room, String> roomsReverse = new HashMap<>();

    public TestRunner(ArrayList<CommandData> l) {
        commands = l;
    }

    public TestRunner() {
        commands = new ArrayList<>();
    }

    public void addCommand(CommandData c) {
        if (c.type != null) {
            commands.add(c);
            evaluateLast();
        }
    }

    public void evaluateAll() {
        for (CommandData cd : commands) {
            interpretCmd(cd);
        }
    }

    public void evaluateLast() {
        interpretCmd(commands.get(commands.size() - 1));
    }

    private void interpretCmd(CommandData cd) {
        if (cd.type == null)
            return;
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
                Student st = new Student(name, testTimer);
                st.setState(EPlayerState.ALIVE);
                players.put(args.get(1), st);
                playersReverse.put(st, args.get(1));
                System.out.printf("student %s created%n", args.get(1));
                break;
            case "teacher":
                Teacher t = new Teacher(name, testTimer);
                t.setState(EPlayerState.ALIVE);
                players.put(args.get(1), t);
                playersReverse.put(t, args.get(1));
                System.out.printf("teacher %s created%n", args.get(1));
                break;
            case "cleaner":
                Cleaner cl = new Cleaner(name, testTimer);
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

            case "purifier":
                Purifier pf = new Purifier(name);
                items.put(args.get(1), pf);
                System.out.printf("logarlec %s created%n", args.get(1));
                break;

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
        String source = args.get(0);
        String target = args.get(1);
        boolean force = false;
        if (args.get(0).equals("-f")) {
            source = args.get(1);
            target = args.get(2);
            force = true;
        }

        if (getType(source) == Type.ROOM) {
            if (getType(target) == Type.ROOM) {
                rooms.get(source).addNeighbour(rooms.get(target));
                System.out.printf("%s connected to %s%n", source, target);
            }
            if (getType(target) == Type.PLAYER) {
                players.get(target).move(rooms.get(source));
                System.out.printf("%s moved to %s%n", target, source);
            }
        }
        if (getType(source) == Type.PLAYER) {
            if (getType(target) == Type.ROOM) {
                if (force) {
                    players.get(source).forceMove((rooms.get(target)));
                    System.out.printf("%s moved to %s%n", source, target);
                } else {
                    players.get(source).move(rooms.get(target));
                    System.out.printf("%s moved to %s%n", source, target);
                }

            }
            if (getType(target) == Type.ITEM) {
                players.get(source).pickUp(items.get(target));
                System.out.printf("%s added to %s%n", target, source);
            }
        }
        if (getType(source) == Type.ITEM) {
            if (getType(target) == Type.ROOM) {
                rooms.get(target).addItem(items.get(source));
                System.out.printf("%s added to %s%n", source, target);
            }
            if (getType(target) == Type.PLAYER) {
                players.get(target).pickUp(items.get(source));
                System.out.printf("%s added to %s%n", source, target);
            }
        }

    }

    private void cmdState(ArrayList<String> args) {
        if (args.size() < 2) {
            for (var item : players.entrySet()) {
                if (item.getKey().equals(args.get(0)))
                    item.getValue().statesOptions();
            }
            for (var item : items.entrySet()) {
                if (item.getKey().equals(args.get(0)))
                    item.getValue().statesOptions();
            }
            for (var item : rooms.entrySet()) {
                if (item.getKey().equals(args.get(0)))
                    item.getValue().statesOptions();
            }
        } else {
            for (var item : players.entrySet()) {
                if (item.getKey().equals(args.get(0))) {
                    item.getValue().setState(args.get(1));
                }
            }
            for (var item : items.entrySet()) {
                if (item.getKey().equals(args.get(0)))
                    item.getValue().setState(args);
            }
            for (var item : rooms.entrySet()) {
                if (item.getKey().equals(args.get(0)))
                    item.getValue().addEffect((args.get(1)));
            }
        }

    }

    private void cmdInteract(ArrayList<String> args) {
        if (args.get(0).equals("pickup")) {
            players.get(args.get(1)).pickUp((items.get(args.get(2))));
            System.out.printf("interact pickup %s %s ok%n", args.get(1), args.get(2));
        }
        if (args.get(0).equals("drop")) {
            players.get(args.get(1)).dropItem(((items.get(args.get(2)))));
            System.out.printf("interact activate %s %s ok%n", args.get(1), args.get(2));
        }
        if (args.get(0).equals("activate")) {
            ((Transistor) items.get(args.get(2))).ActivateTransistor();
            System.out.printf("interact activate %s %s %s ok%n", args.get(0), args.get(1), args.get(2));
        }
        if (args.get(0).equals("pair")) {
            ((Transistor) items.get(args.get(2))).PairTransistor((Transistor) items.get(args.get(3)));
            System.out.printf("interact pair %s %s %s ok%n", args.get(0), args.get(1), args.get(2));
        }
    }

    private void cmdStat(ArrayList<String> args) {
        System.out.println("stat:");
        for (var item : players.entrySet()) {
            item.getValue().printStat(item.getKey());
        }
        for (var item : items.entrySet()) {
            item.getValue().printStat(item.getKey());
        }
        ArrayList<String> tmpRoomNames = new ArrayList<>(rooms.keySet());
        Collections.sort(tmpRoomNames);
        for (var item : tmpRoomNames) {
            rooms.get(item).printStat("need to remove");
        }
    }

    private void cmdControl(ArrayList<String> args) {
        if (args.size() == 2) {

            // The timer
            if (args.get(0) == "timer") {
                // On off or time integer
                if (args.size() == 2) {
                    if (args.get(1) == "on") {
                    } else if (args.get(1) == "of") {
                    } else {

                        int number = Integer.parseInt(args.get(1));

                        // Run time for a turn each time, for number times
                        for (int i = 0; i < number; i++) {
                            testTimer.iterateTime();
                        }
                    }
                }
            }
            if (args.get(0) == "random") {

            }
        } else {
            throw new IllegalArgumentException("Control needs two aruments to work as intended!");
        }
    }

    private void cmdSave(ArrayList<String> args) {

    }

    private void cmdLoad(ArrayList<String> args) {
        System.out.println("FASDZADFASFAS");
        FileHandling fh = new FileHandling();
        var list = fh.ReadTest("src/test/test_txt/test_input/test1.txt");
        try {
            fh.WriteTestResult(list.get(0).subject, "src/test/test_txt/test_output/test1_outtttt.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        commands = list;
        evaluateAll();
    }
}