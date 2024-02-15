import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Cliente {




    private JFrame frame;
    private JButton uploadButton;
    private JButton viewImagesButton;
    private JPanel imagesPanel;

    public Cliente() {
        frame = new JFrame("Cliente");
        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        
        uploadButton = new JButton("Subir Imagen");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica 
 
            }
        });
        viewImagesButton = new JButton("Ver Imágenes");
        viewImagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica
                JPanel colorPanel = new JPanel();
                colorPanel.setBackground(getRandomColor());
                colorPanel.setPreferredSize(new Dimension(250, 250)); 
                colorPanel.setMaximumSize(new Dimension(250, 250));
                imagesPanel.add(colorPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

    
        imagesPanel = new JPanel();
        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.Y_AXIS));
        JScrollPane imagesScrollPane = new JScrollPane(imagesPanel); 
        imagesScrollPane.setPreferredSize(new Dimension(800, 550)); 

   
        frame.setLayout(new GridLayout(2, 1));
        frame.add(imagesScrollPane);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(uploadButton);
        buttonsPanel.add(viewImagesButton);
        frame.setLayout(new BorderLayout());
        frame.add(imagesScrollPane, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
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

    private Color getRandomColor() {
        return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.show();
    }
}

