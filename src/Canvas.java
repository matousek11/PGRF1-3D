import controlstate.*;

import enums.StateEnum;
import rasterdata.Raster;
import rasterdata.RasterAdapter;
import rasterops.DDALiner;
import rasterops.Liner;

import java.awt.*;
import java.awt.event.*;
import java.io.Serial;

import javax.swing.*;

/**
 * Main class for painting on canvas
 */
public class Canvas {
    private final String applicationName = "UHK FIM PGRF 1 : Lukáš Matoušek";
    private final JFrame frame;
    private final JPanel panel;
    private final Raster raster;
    private final Liner liner;
    private final HouseState houseState;
    private final HouseStateWithCubic houseStateWithCubic;
    private final RocketsState rocketsState;
    private BaseState stateObject;
    private StateEnum state = StateEnum.HOUSE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Canvas(1500, 1000).start());
    }

    public Canvas(int width, int height) {
        raster = new RasterAdapter(width, height);
        liner = new DDALiner();
        frame = new JFrame();

        panel = new JPanel() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                raster.present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        setUpJFrameElement();
        registerListeners();

        houseState = new HouseState(raster, panel, liner);
        houseStateWithCubic = new HouseStateWithCubic(raster, panel, liner);
        rocketsState = new RocketsState(raster, panel, liner);
        stateObject = houseState;

        Timer moveTimer = new Timer(17, e -> {
            try {
                stateObject.moveCamera();
                stateObject.animateObjects();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        moveTimer.start();
    }

    public void start() {
        panel.repaint();
    }

    private void switchState() {
        state = state.nextState();
        stateObject = switch (state) {
            case HOUSE -> houseState;
            case HOUSE_WITH_CUBIC -> houseStateWithCubic;
            case ROCKETS -> rocketsState;
        };
    }

    private void setUpJFrameElement() {
        frame.setLayout(new BorderLayout());
        frame.setTitle(applicationName);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void registerListeners() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_X:
                            switchState();
                            break;
                    }
                    stateObject.keyPressed(e);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                stateObject.keyReleased(e);
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    stateObject.mousePressed(e);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    stateObject.mouseReleased(e);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    stateObject.mouseDragged(e);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
