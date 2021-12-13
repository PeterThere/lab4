import javax.swing.*;
import java.awt.*;
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
    public MainWindow() throws HeadlessException {
        this("undefined");
    }

    public MainWindow(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize( 720, 480);
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
                    if(p.x < (btnRun.getX()+90)) //trzeba najechać z prawej strony, aby przycisk nie zmienił pozycji
                    btnRun.setLocation(random.nextInt(600), random.nextInt(300));
                }
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    super.mousePressed(e);
//                    MainWindow etap2 = new MainWindow("Kanwa");
//                    etap2.setVisible(true);
//                }
            });
            btnRun.setBounds(300, 180, 100, 50);
            panel.add(btnRun);

            JButton btnReset = new JButton("RESET");
            btnReset.addActionListener(e -> btnRun.setLocation(300, 180));
            btnReset.setBounds(300, 380, 100, 50);
            panel.add(btnReset);
    }

}