package com.example.projetks;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class FiltreBRG extends Filtres{
    public FiltreBRG(ImageView imageView) {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                double r = pixelReader.getColor(i, j).getRed();
                double g = pixelReader.getColor(i, j).getGreen();
                double b = pixelReader.getColor(i, j).getBlue();
                Color color = new Color(b, r, g, 1.0);
                pixelWriter.setColor(i, j, color);
            }
        }
        imageView.setImage(writableImage);
    }
}
