public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        Client client1 = new Client(server);
        client1.setLocation(server.getX() - 300, server.getY());
        Client client2 = new Client(server);
        client2.setLocation(server.getX() + 300, server.getY());
    }
}