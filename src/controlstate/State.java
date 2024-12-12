package controlstate;

import java.awt.event.MouseEvent;

import java.awt.event.KeyEvent;

/**
 * Interface that represents state of control for type of rasterized object.
 * Interface offers events that can be used to react on user actions.
 */
public interface State {
    void mousePressed(MouseEvent e) throws Exception;

    void mouseReleased(MouseEvent e) throws Exception;

    void mouseDragged(MouseEvent e) throws Exception;

    void keyPressed(KeyEvent e) throws Exception;

    void keyReleased(KeyEvent e);

    void moveCamera();
}
