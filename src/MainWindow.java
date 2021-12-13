import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MainWindow extends JFrame{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            try {
                MainWindow window = new MainWindow("Uciekający Przycisk");
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        });
    }

    public MainWindow(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize( 1020, 480);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,700,460);
        setContentPane(panel);
        panel.setLayout(null);

        Random random = new Random();

        JButton btnRun = new JButton("Złap tu →");
        btnRun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Point p = e.getLocationOnScreen();
                SwingUtilities.convertPointFromScreen(p, panel);
                if (p.x < (btnRun.getX() + 90)) //trzeba najechać z prawej strony, aby przycisk nie zmienił pozycji
                    btnRun.setLocation(random.nextInt(600), random.nextInt(300));
            }
        });
        btnRun.setBounds(300, 180, 100, 50);
        panel.add(btnRun);

        JButton btnReset = new JButton("RESET");
        btnReset.addActionListener(e -> btnRun.setLocation(300, 180));
        btnReset.setBounds(300, 380, 100, 50);
        panel.add(btnReset);

        JPanel kanwa = new JPanel();
        kanwa.setBounds(700,0,320,460);
        kanwa.setBackground(Color.gray);
        kanwa.setFocusable(true);
        panel.add(kanwa);

        JPanel kwadrat = new JPanel();
        kwadrat.setLayout(null);
        kanwa.add(kwadrat);

        kanwa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, kanwa);
                if(e.getKeyChar() == KeyEvent.VK_1){
                    kwadrat.setBounds(p.x, p.y, 50, 50);
                    kwadrat.setBackground(Color.cyan);
                }
                if(e.getKeyChar() == KeyEvent.VK_2){
                    kwadrat.setBounds(p.x, p.y, 100, 50);
                    kwadrat.setBackground(Color.magenta);
                }
            }
        });
    }

}