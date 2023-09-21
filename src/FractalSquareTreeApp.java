import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractalSquareTreeApp extends JPanel {

    private int recursionLevel = 0;
    private JComboBox<Integer> levelChoice;

    public FractalSquareTreeApp() {
        levelChoice = new JComboBox<>();
        for (int i = 0; i <= 10; i++) {
            levelChoice.addItem(i);
        }
        levelChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recursionLevel = (int) levelChoice.getSelectedItem();
                repaint();
            }
        });
        setLayout(new BorderLayout());
        add(levelChoice, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int startX = width / 2;
        int startY = height;

        drawFractalSquareTree(g, startX, startY, 90, 50, recursionLevel);
    }

    private void drawFractalSquareTree(Graphics g, int x, int y, double angle, int size, int level) {
        if (level == 0) {
            return;
        } else {
            int endX = x + (int) (Math.cos(Math.toRadians(angle)) * size);
            int endY = y - (int) (Math.sin(Math.toRadians(angle)) * size);

            int halfSize = size / 2;
            int topLeftX = x - halfSize;
            int topLeftY = y - size;
            int bottomRightX = x + halfSize;
            int bottomRightY = y;

            g.setColor(Color.BLACK);
            g.fillRect(topLeftX, topLeftY, size, size);

            drawFractalSquareTree(g, endX, endY, angle - 45, halfSize, level - 1);
            drawFractalSquareTree(g, endX, endY, angle + 45, halfSize, level - 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Árbol Fractal ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.add(new FractalSquareTreeApp());
            frame.setVisible(true);
        });
    }
}
