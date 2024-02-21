import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    private Server server;
    private String nickname;
    private boolean flag;
    JTextArea historyChat;
    JTextField IPAddress, Port, Login, Message, Password;
    JButton buttonLogin, buttonSend;
    JPanel upPanel;
    JPanel downPanel;
    public Client(Server server) {
        this.server = server;
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX(), server.getY());
        createPanel();
        setVisible(true);
    }
    private void lookHistoryChat(){
        server.readLog();
    }
    void appendHistoryChat(String text){
        historyChat.append(text + "\n");
    }
    private void connectToServer() {
        server.connectUser(this);
        appendHistoryChat("Вы успешно подключились!");
        upPanel.setVisible(false);
        flag = true;
        nickname = Login.getText();
        lookHistoryChat();
    }
    public void chatHistory(){
        if (flag){
            String chat = Message.getText();
            if (!chat.equals("")){
                server.message(nickname + ": " + chat);
            }
        } else {
            lookHistoryChat();
        }
    }
    private void createPanel() {
        add(createUpPanel(), BorderLayout.NORTH);
        add(createMiddlePanel());
        add(createDown(), BorderLayout.SOUTH);
    }
    private Component createUpPanel () {
        upPanel = new JPanel(new GridLayout(2, 3));
        IPAddress = new JTextField();
        Port = new JTextField();
        Login = new JTextField("Naruto");
        Password = new JTextField();
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });
        upPanel.add(IPAddress);
        upPanel.add(Port);
        upPanel.add(new JPanel());
        upPanel.add(Login);
        upPanel.add(Password);
        upPanel.add(buttonLogin);
        return upPanel;
    }
    private Component createMiddlePanel(){
        return historyChat = new JTextArea();
    }
    private Component createDown() {
        downPanel = new JPanel(new BorderLayout());
        Message = new JTextField();
        buttonSend = new JButton("Send");
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatHistory();
            }
        });
        downPanel.add(Message);
        downPanel.add(buttonSend, BorderLayout.EAST);
        return downPanel;
    }
}