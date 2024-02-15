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
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


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
            }
        });

    
        imagesPanel = new JPanel(new GridLayout(0, 4, 10, 10)); 
        JScrollPane imagesScrollPane = new JScrollPane(imagesPanel); 
        imagesScrollPane.setPreferredSize(new Dimension(800, 400)); 

   
        frame.setLayout(new GridLayout(2, 1));
        frame.add(imagesScrollPane);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(uploadButton);
        buttonsPanel.add(viewImagesButton);
        frame.add(buttonsPanel);
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
        Cliente cliente = new Cliente();
        cliente.show();
    }
}

