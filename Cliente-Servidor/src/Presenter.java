

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;




public class Presenter implements ActionListener {
	
	private ClienteView clientview;
	private Socket socket;

	public Presenter() throws IOException{
		clientview = new ClienteView(this);
		socket = new Socket("localhost", 12345);
		
	}

	public void mostrarcliente(){
		clientview.show();
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
        switch (action) {
            case "Subir": {    
				File selectedImage = clientview.chooseImage(); 
                if (selectedImage != null) {
                    enviarImagenAlServidor(selectedImage); 
                }
                break;
            }
			case "Mostrar": {
				clientview.mostrarImagenes();
				System.out.println("Hola mostre");
                break;
            }
	}
}

private void enviarImagenAlServidor(File imagen) {
        try {
            
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            
            byte[] imageData = new byte[(int) imagen.length()];
            try (FileInputStream fileInputStream = new FileInputStream(imagen)) {
				fileInputStream.read(imageData);
			}
            
            outputStream.writeObject("SUBIR_IMAGEN");
            outputStream.writeObject(imagen.getName()); 
            outputStream.writeObject(imageData);

            
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		
	public static void main(String[] args) {
		try {
			Presenter presenter = new Presenter();
			presenter.mostrarcliente();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
