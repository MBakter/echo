package test;

import java.util.ArrayList;
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
        Player,
        Item,
        Room
    }

    private ArrayList<CommandData> commands;

    private HashMap<String, IItem> items = new HashMap<>();
    private HashMap<String, Player> players = new HashMap<>();
    private HashMap<String, Room> rooms = new HashMap<>();

    public TestRunner(ArrayList<CommandData> l) {
        commands = l;
    }

    public void Evaluate() {
        for (CommandData cd : commands) {
            InterpretCmd(cd);
        }
    }

    private void InterpretCmd(CommandData cd) {
        switch (cd.type) {
            case CREATE:
                CmdCreate(cd.subject);
                break;
            case LINK:
                CmdLink(cd.subject);
                break;
            case STATE:
                CmdState(cd.subject);
                break;
            case INTERACT:
                CmdInteract(cd.subject);
                break;
            case STAT:
                CmdStat(cd.subject);
                break;
            case CONTROL:
                CmdControl(cd.subject);
                break;
            default:
                break;
        }
    }

    private void CmdCreate(ArrayList<String> args) {
        if (args.size() < 2) {
            return;
        }

        switch (args.get(0)) {
            case "room":
                rooms.put(args.get(1), new Room());
                System.out.printf("room %s created\n", args.get(1));
                break;
            case "student":
                players.put(args.get(1), new Student());
                System.out.printf("student %s created\n", args.get(1));
                break;
            case "teacher":
                players.put(args.get(1), new Teacher());
                System.out.printf("teacher %s created\n", args.get(1));
                break;
            case "cleaner":
                players.put(args.get(1), new Cleaner());
                System.out.printf("cleaner %s created\n", args.get(1));
                break;
            case "cheese":
                items.put(args.get(1), new Cheese(new Timer()));
                System.out.printf("cheese %s created\n", args.get(1));
                break;
            case "transistor":
                items.put(args.get(1), new Transistor());
                System.out.printf("transistor %s created\n", args.get(1));
                break;
            case "logar":
                items.put(args.get(1), new Logarlec());
                System.out.printf("logar %s created\n", args.get(1));
                break;
            case "tvsz":
                items.put(args.get(1), new TVSZ());
                System.out.printf("tvsz %s created\n", args.get(1));
                break;
            // TODO : Not yet implemented in model
            /*
             * case "purifier":
             * items.put(args.get(1), new Purifier());
             * System.out.printf("logarlec %s created\n", args.get(1));
             * break;
             */
            case "beer":
                items.put(args.get(1), new Beer(new Timer()));
                System.out.printf("beer %s created\n", args.get(1));
                break;
            case "sponge":
                items.put(args.get(1), new Sponge(new Timer()));
                System.out.printf("sponge %s created\n", args.get(1));
                break;
            case "mask":
                items.put(args.get(1), new Mask(new Timer()));
                System.out.printf("mask %s created\n", args.get(1));
                break;
            default:
                break;
        }
    }

    private Type getType(String name) {
        for (String savedName : rooms.keySet()) {
            if (savedName.equals(name)) {
                return Type.Room;
            }

        }
        for (String savedName : players.keySet()) {
            if (savedName.equals(name)) {
                return Type.Player;
            }

        }
        for (String savedName : items.keySet()) {
            if (savedName.equals(name)) {
                return Type.Item;
            }

        }
        return null;
    }

    private void CmdLink(ArrayList<String> args) {
        Type arg1 = getType(args.get(0));
        if(arg1 == Type.Room){
            if(getType(args.get(1)) == Type.Room){
                rooms.get(args.get(0)).addNeighbour(rooms.get(args.get(1)));
                System.out.printf("%s connected to %s\n",args.get(0),args.get(1));
            }
        }
        if(arg1 == Type.Player){
            if(getType(args.get(1)) == Type.Room){
                players.get(args.get(0)).move(rooms.get(args.get(1)));
                System.out.printf("%s moved to %s\n",args.get(0),args.get(1));
            }
        }
        if(arg1 == Type.Item){
            
        }

    }

    private void CmdState(ArrayList<String> args) {

    }

    private void CmdInteract(ArrayList<String> args) {

    }

    private void CmdStat(ArrayList<String> args) {
        System.out.println("Stat:");
        for (var item : players.keySet()) {
            players.get(item).PrintStat();
        }
        for (var item : items.keySet()) {
            items.get(item).PrintStat();
        }
        for (var item : rooms.keySet()) {
            rooms.get(item).PrintStat();
        }
    }

    private void CmdControl(ArrayList<String> args) {

    }
}