package view;

import java.awt.MenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import controller.IController;
import model.items.Beer;
import model.items.EBeerState;
import model.items.IVMBeer;

public class VBeer implements IVItems{
    IVMBeer modelBeer;
    public VBeer(Beer b){
        modelBeer = b;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VBEER DRAWG");

        btn.setIcon(new ImageIcon("textures" + File.separator + "BeerRoom.png"));
        btn.addActionListener(e -> { c.getCommands().pickUpItem(modelBeer); });
    }

    @Override
    public void draw(IVStudent curPlayer, JLabel label, IController c) {
        System.out.println("VBEER DRAW");

        label.setIcon(new ImageIcon("textures" + File.separator + "Beer.png"));
        //btn.addActionListener(e -> { c.getCommands().useItem((IItem)modelBeer); });

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent){
                if(SwingUtilities.isLeftMouseButton(mouseEvent)) { 

                    JPopupMenu jp = new JPopupMenu("Choose action");
                    jp.add(new JMenuItem("Use item")).addActionListener(e -> {c.getCommands().useItem(modelBeer);});
                    jp.add(new JMenuItem("Drop item")).addActionListener(e -> {
                        c.getCommands().dropItem(modelBeer);
                    });
                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }

                if(SwingUtilities.isRightMouseButton(mouseEvent)) {
                    JPopupMenu jp = new JPopupMenu("Stats");
                    jp.add(new JLabel("Time: "+Integer.toString(modelBeer.getTime())));
                    jp.add(new JLabel("State: "+ EBeerState.convertToString(modelBeer.getState())));

                    jp.show(label, 100, 100);
                    jp.setLocation(mouseEvent.getXOnScreen(), mouseEvent.getYOnScreen());
                }
            }
        });


    }

    @Override
    public boolean isPairable() {
        return false;
    }
}
