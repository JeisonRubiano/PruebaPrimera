import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class Servidor {

   public static void main(String[] args) {
    final int PUERTO = 12345; 
    final String CARPETA_IMAGENES = "Data/";

    try (ServerSocket servidor = new ServerSocket(PUERTO)) {
        System.out.println("Servidor iniciado. Esperando conexiones...");

        while (true) {
            Socket cliente = servidor.accept(); 
            System.out.println("Cliente conectado desde: " + cliente.getInetAddress().getHostAddress());

         
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());

          
            enviarImagenes(salida, CARPETA_IMAGENES);

          
            cliente.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static void enviarImagenes(ObjectOutputStream salida, String carpeta) throws IOException {
    File directorio = new File(carpeta);
    File[] archivos = directorio.listFiles();

   
    for (File archivo : archivos) {
        if (archivo.isFile()) {
            BufferedImage imagen = ImageIO.read(archivo);

   
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(imagen, "jpg", byteArrayOutputStream);
            byte[] imageData = byteArrayOutputStream.toByteArray();

       
            System.out.println("Envio de imagenes correcto");
            salida.writeObject(imageData);
            salida.flush();
        }
    }


    salida.writeObject(null);
}
}

