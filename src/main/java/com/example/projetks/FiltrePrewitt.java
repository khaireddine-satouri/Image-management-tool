package com.example.projetks;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class FiltrePrewitt extends Filtres{
   public FiltrePrewitt(ImageView imageView){
       Image image = imageView.getImage();
       PixelReader pixelReader = image.getPixelReader();
       WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

       PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = 0; i < image.getWidth()-2; i++) {
            for (int j = 0; j < image.getHeight()-2; j++) {
                double xr1 = (pixelReader.getColor(i, j+2).getRed() + pixelReader.getColor(i+1, j+2).getRed() + pixelReader.getColor(i+2, j+2).getRed());
                double xg1 = (pixelReader.getColor(i, j+2).getGreen() + pixelReader.getColor(i+1, j+2).getGreen() + pixelReader.getColor(i+2, j+2).getGreen());
                double xb1 = (pixelReader.getColor(i, j+2).getBlue() + pixelReader.getColor(i+1, j+2).getBlue() + pixelReader.getColor(i+2, j+2).getBlue());

                double xr2 = (pixelReader.getColor(i, j).getRed() + pixelReader.getColor(i+1, j).getRed() + pixelReader.getColor(i+2, j).getRed());
                double xg2 = (pixelReader.getColor(i, j).getGreen() + pixelReader.getColor(i+1, j).getGreen() + pixelReader.getColor(i+2, j).getGreen());
                double xb2 = (pixelReader.getColor(i, j).getBlue() + pixelReader.getColor(i+1, j).getBlue() + pixelReader.getColor(i+2, j).getBlue());

                double xr = xr1-xr2;
                double xg = xg1-xg2;
                double xb = xb1-xb2;


                double yr1 = (pixelReader.getColor(i+2, j).getRed() + pixelReader.getColor(i+2, j+1).getRed() + pixelReader.getColor(i+2, j+2).getRed());
                double yg1 = (pixelReader.getColor(i+2, j).getGreen() + pixelReader.getColor(i+2, j+1).getGreen() + pixelReader.getColor(i+2, j+2).getGreen());
                double yb1 = (pixelReader.getColor(i+2, j).getBlue() + pixelReader.getColor(i+2, j+1).getBlue() + pixelReader.getColor(i+2, j+2).getBlue());

                double yr2 = (pixelReader.getColor(i, j).getRed() + pixelReader.getColor(i, j+1).getRed() + pixelReader.getColor(i, j+2).getRed());
                double yg2 = (pixelReader.getColor(i, j).getGreen() + pixelReader.getColor(i, j+1).getGreen() + pixelReader.getColor(i, j+2).getGreen());
                double yb2 = (pixelReader.getColor(i, j).getBlue() + pixelReader.getColor(i, j+1).getBlue() + pixelReader.getColor(i, j+2).getBlue());

                double yr = yr1-yr2;
                double yg = yg1-yg2;
                double yb = yb1-yb2;

                double r=Math.abs(xr)+Math.abs(yr);
                double g=Math.abs(xg)+Math.abs(yg);
                double b=Math.abs(xb)+Math.abs(yb);

                if (r>1) r=1;
                if (g>1) g=1;
                if (b>1) b=1;
                Color color = new Color(r, g, b, 1.0);
                pixelWriter.setColor(i, j, color);
            }
        }
       imageView.setImage(writableImage);
   }
}
