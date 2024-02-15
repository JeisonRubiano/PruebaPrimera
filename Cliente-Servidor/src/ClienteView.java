import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteView {

    private JFrame frame;
    private JButton uploadButton;
    private JButton viewImagesButton;
    private JPanel imagesPanel;

    public ClienteView(ActionListener listener) {
        initComponents(listener);

    }
    private void initComponents(ActionListener listener){

        frame = new JFrame("Cliente");
        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        
        uploadButton = new JButton("Subir Imagen");
        uploadButton.addActionListener(listener);
        uploadButton.setActionCommand("Subir");

        viewImagesButton = new JButton("Ver Imágenes");
        viewImagesButton.addActionListener(listener);
        viewImagesButton.setActionCommand("Mostrar");
    
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


        public File chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
         }


         public void mostrarImagenes() {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                     
                        Socket socket = new Socket("localhost", 12345);
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        
                        
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        outputStream.writeObject("DESCARGAR_IMAGENES");
        
                        
                        while (true) {
                            byte[] imageData = (byte[]) inputStream.readObject();
                            if (imageData == null) {
                               
                                break;
                            }
        
                            ImageIcon originalIcon = new ImageIcon(imageData);
                            Image originalImage = originalIcon.getImage();
                            Image resizedImage = originalImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                            ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
                           
                            JLabel label = new JLabel(resizedIcon);
                            imagesPanel.add(label);
        
                          
                            imagesPanel.revalidate();
                            imagesPanel.repaint();
                        }
        
                       
                        inputStream.close();
                        outputStream.close();
                        socket.close();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            t.start();
        }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }


}

