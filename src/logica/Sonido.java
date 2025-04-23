package logica;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sonido {

    // Volumen entre -80.0f (mínimo) y 6.0f (máximo). 0.0f es volumen normal.
    private static float volumenDecibeles = -10.0f;

    public static void reproducirSonido(String rutaDentroDeSrc) {
        try {
            InputStream audioSrc = Sonido.class.getClassLoader().getResourceAsStream(rutaDentroDeSrc);
            if (audioSrc == null) {
                System.err.println("No se encontró el archivo: " + rutaDentroDeSrc);
                return;
            }

            // Convertimos a un InputStream con marca compatible
            InputStream bufferedIn = new java.io.BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Intentar aplicar control de volumen
            try {
                FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                control.setValue(volumenDecibeles);
            } catch (IllegalArgumentException e) {
                System.err.println("Control de volumen no soportado.");
            }

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void setVolumenDecibeles(float nuevoVolumen) {
        volumenDecibeles = nuevoVolumen;
    }
}
