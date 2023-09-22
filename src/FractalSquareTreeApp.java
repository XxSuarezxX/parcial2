import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractalSquareTreeApp extends JFrame {
    private int iterations;

    public FractalSquareTreeApp() {
        super("Árbol de Cuadrados");

        // Pide al usuario el número de iteraciones
        try {
            String input = JOptionPane.showInputDialog("Ingrese el número de iteraciones:");
            iterations = Integer.parseInt(input);

            if (iterations <= 0) {
                JOptionPane.showMessageDialog(null, "El número de iteraciones debe ser mayor que 0. Usando 5 iteraciones por defecto.");
                iterations = 5;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Usando 5 iteraciones por defecto.");
            iterations = 5;
        }

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawSquareTree(g, iterations, getWidth() / 2, getHeight(), getHeight() / 4, Math.PI / 2);
    }

    private void drawSquareTree(Graphics g, int n, int x, int y, int size, double angle) {
        if (n == 0) {
            return;
        }

        int x1 = x - (int) (Math.cos(angle) * size);
        int y1 = y - (int) (Math.sin(angle) * size);

        // Dibuja un cuadrado en lugar de una línea
        g.drawRect(x1, y1 - size, size, size);

        int newSize = (int) (size / Math.sqrt(2));

        drawSquareTree(g, n - 1, x1, y1, newSize, angle - Math.PI / 4);
        drawSquareTree(g, n - 1, x1, y1, newSize, angle + Math.PI / 4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FractalSquareTreeApp app = new FractalSquareTreeApp();
            app.setVisible(true);
        });
    }
}
