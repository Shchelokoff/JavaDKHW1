import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    public static final String LOGFILE = "C:\\IdeaP\\Flood\\src\\logfile.txt";
    List<Client> clientList;
    JButton btnStart, btnStop;
    public Server(){
        clientList = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        add(createButtons(), BorderLayout.SOUTH);
        setVisible(true);
    }
    public void connectUser(Client client){
        clientList.add(client);
    }
    private void showChat(String text){
        for (Client client: clientList){
            client.appendHistoryChat(text);
        }
    }
    private void saveLog(String text){
        try (FileWriter writer = new FileWriter(LOGFILE, true)){
            writer.write(text);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public String readLog() {
        StringBuilder log = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(LOGFILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Лог файл не найден.");
        }
        return log.toString();
    }
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        panel.add(btnStart);
        panel.add(btnStop);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        return panel;
    }
    public void message(String text){
        readLog();
        showChat(text);
        saveLog(text);
    }
}
