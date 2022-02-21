package com.example.projetks;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class FiltreSepia extends Filtres {
    public FiltreSepia(ImageView imageView) {
        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        double sepiaDepth = 20/255;
        double sepiaIntensity = 0.4;

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                double r = pixelReader.getColor(i, j).getRed();
                double g = pixelReader.getColor(i, j).getGreen();
                double b = pixelReader.getColor(i, j).getBlue();
                double c = (r + g + b) / 3;
                r = c;
                g = c;
                b = c;

                r = r + (sepiaDepth * 2);
                g = g + sepiaDepth;

                if (r>1) r=1;
                if (g>1) g=1;
                if (b>1) b=1;
                b-= sepiaIntensity;
                if (b<0) b=0;
                if (b>1) b=1;

                Color color = new Color(r, g, b, 1.0);
                pixelWriter.setColor(i, j, color);
            }
        }
        imageView.setImage(writableImage);
    }
}

