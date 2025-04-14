import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MiPaint extends JFrame {
    JComboBox<String> comboColores;
    JButton btnLimpiar;
    DibujoPanel panelDibujo;
    Color colorSeleccionado = Color.BLACK;

    public MiPaint() {
        setTitle("Mi Paint");
        setSize(400, 400);
        setDefaultCloseOperation(3);

        String[] colores = {"Negro", "Rojo", "Azul", "Verde", "Naranja"};
        comboColores = new JComboBox<>(colores);

        comboColores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String colorSeleccionadoTexto = (String) comboColores.getSelectedItem();

                switch (colorSeleccionadoTexto) {
                    case "Rojo":
                        colorSeleccionado = Color.RED;
                        break;
                    case "Azul":
                        colorSeleccionado = Color.BLUE;
                        break;
                    case "Verde":
                        colorSeleccionado = Color.GREEN;
                        break;
                    case "Naranja":
                        colorSeleccionado = Color.ORANGE;
                        break;
                    default:
                        colorSeleccionado = Color.BLACK;
                        break;
                }
            }
        });

        panelDibujo = new DibujoPanel();

        JPanel panel01 = new JPanel();
        panel01.add(comboColores);

        add(panel01, BorderLayout.NORTH);
        add(panelDibujo, BorderLayout.CENTER);
    }

    class DibujoPanel extends JPanel {
        class PuntoColor {
            Point punto;
            Color color;

            public PuntoColor(Point punto, Color color) {
                this.punto = punto;
                this.color = color;
            }
        }

        ArrayList<PuntoColor> puntos = new ArrayList<>();

        public DibujoPanel() {
            setBackground(Color.WHITE);

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    puntos.add(new PuntoColor(e.getPoint(), colorSeleccionado));
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (PuntoColor pc : puntos) {
                g.setColor(pc.color);
                g.fillOval(pc.punto.x, pc.punto.y, 5, 5);
            }
        }

        public void limpiar() {
            puntos.clear();
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MiPaint().setVisible(true);
            }
        });
    }
}
