import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Client extends JFrame implements ActionListener, KeyListener, Thread.UncaughtExceptionHandler {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea();

    @Override
    protected void frameInit() {
        super.frameInit();
    }
    public FileWriter writer = new FileWriter("log.txt");
    private final JTextField field = new JTextField(20);
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("80");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("ivan_igorevich");
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("Disconnect");
    private final JTextField tfMessage = new JTextField("hereeeeeeeeeeee");
    private final JButton btnSend = new JButton("Send");
    private final JList<String> userList = new JList<>();


    public Client() throws IOException {

        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");
        log.setEditable(false);this.addKeyListener(this);
        JScrollPane spLog = new JScrollPane(log);
        JScrollPane spUsers = new JScrollPane(userList);
        String[] users = {"user1", "user2",
                "user3", "user4", "user5", "user6",
                "user7", "user8", "user9",
                "user10_with_a_exceptionally_long_nickname", };
        userList.setListData(users);
        spUsers.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);
        panelBottom.add(field);
        btnSend.addActionListener(this);
        tfMessage.addKeyListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        panelBottom.addKeyListener(this);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelTop, BorderLayout.NORTH);
        add(spLog, BorderLayout.CENTER);
        add(spUsers, BorderLayout.EAST);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Client();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        }
        else if (src == btnSend) {
            String str = tfMessage.getText() + "\n";
            log.append(str);
            try {
                FileWriter writer = new FileWriter("log.txt");
                writer.write("");
                writer.append(str);
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            tfMessage.setText("");
        }
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg = "Exception in thread " + t.getName() +
                " " + e.getClass().getCanonicalName() +
                ": " + e.getMessage() +
                "\n\t" + e.getStackTrace()[0];
        JOptionPane.showMessageDialog(null, msg,
                "Exception", JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            String str = tfMessage.getText() + "\n";
            log.append(str);
            try {
                FileWriter writer = new FileWriter("log.txt");
                writer.write("");
                writer.append(str);
                writer.close();
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            tfMessage.setText("");
        }

//        System.out.println("Realise key char " + e.getKeyChar());
//        System.out.println("Realise key char " + e.getKeyCode());
    }
}