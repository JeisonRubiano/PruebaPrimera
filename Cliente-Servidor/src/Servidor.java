import javax.swing.*;
import java.awt.*;

public class Servidor {



    private JFrame frame;
    private JList<String> connectedClientsList;
    private DefaultListModel<String> connectedClientsModel;
    private JPanel imagesPanel;

    public Servidor() {
        frame = new JFrame("Servidor");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        JPanel connectedClientsPanel = new JPanel(new BorderLayout());
        connectedClientsPanel.setBorder(BorderFactory.createTitledBorder("Clientes Conectados"));
        connectedClientsPanel.setPreferredSize(new Dimension(100, 600)); 


        connectedClientsModel = new DefaultListModel<>();
        connectedClientsList = new JList<>(connectedClientsModel);
        JScrollPane connectedClientsScrollPane = new JScrollPane(connectedClientsList);

        connectedClientsPanel.add(connectedClientsScrollPane, BorderLayout.CENTER);


        imagesPanel = new JPanel(new GridLayout(0, 1));
        imagesPanel.setBorder(BorderFactory.createTitledBorder("Imágenes de Clientes"));
        imagesPanel.setPreferredSize(new Dimension(700,600));

  
        frame.setLayout(new GridLayout(1, 2));
        frame.add(connectedClientsPanel);
        frame.add(imagesPanel);
    }

    public void addConnectedClient(String clientName) {
        connectedClientsModel.addElement(clientName);
    }

    public void removeConnectedClient(String clientName) {
        connectedClientsModel.removeElement(clientName);
    }

    public void addImage(ImageIcon imageIcon) {
        JLabel imageLabel = new JLabel(imageIcon);
        imagesPanel.add(imageLabel);
        frame.revalidate();
        frame.repaint();
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        Servidor server = new Servidor();
        server.show();
    }
}


