package com.example.projetks;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


/**
 * Sites web consultés :
 * ------------------------------
 * https://docs.oracle.com/javase/8/javafx/get-started-tutorial/form.htm
 * https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
 * https://o7planning.org/11127/javafx-image
 * https://o7planning.org/11091/javafx-button
 * Source de JavaFX FileChooser, open and save image file
 * http://java-buddy.blogspot.com
 * https://youtu.be/iDqHseUmATU
 * Source de Scene
 * http://tutorials.jenkov.com/javafx/gridpane.html
 * Source des filtres
 * https://codes-sources.commentcamarche.net/source/view/50908/1207507#browser
 * https://stackoverflow.com/questions/5132015/how-to-convert-image-to-sepia-in-java
 * Cryptage
 * http://www.javased.com/index.php?api=java.security.SecureRandom
 * http://www.java2s.com/Tutorial/Java/0490__Security/PasswordStorageExample.htm
 * https://www.geeksforgeeks.org/encrypt-and-decrypt-image-using-java
 * https://stackoverflow.com/questions/44360812/how-can-i-convert-a-md5-hash-to-an-integral-type-in-java
 * ------------------------------
 */

public class ImgApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        // ---------------------- Page 1 ---------------------- //
        final FileChooser fileChooser = new FileChooser();

        // ---------------------- Image de page 1 ---------------------- //
        Image image = new Image("page1.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);

        // ---------------------- Bouton de page 1 ---------------------- //
        final Button openButton = new Button("Choisir une image");
        openButton.setPrefWidth(180);
        openButton.setPrefHeight(28);
        openButton.setAlignment(Pos.CENTER);
        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        setExtFilters(fileChooser);
                        File file = fileChooser.showOpenDialog(primaryStage);
                        if (file != null) {
                            openNewImageWindow(file);
                        }
                    }
                });

        // ---------------------- Organisation de l'affichage de Page 1 ---------------------- //
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));
        vbox.getChildren().addAll(imageView,openButton);

        GridPane gridPane = new GridPane();
        gridPane.add(imageView, 0, 0, 3, 3);
        gridPane.add(vbox, 1, 3, 3, 3);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setTitle("Outils de gestion d’images");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ---------------------- Ouverture d'une image ---------------------- //
    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images (*.*)", "*.*"),
                new FileChooser.ExtensionFilter("PNG (*.PNG)", "*.PNG"),
                new FileChooser.ExtensionFilter("JPG (*.JPG)","*.JPG*"),
                new FileChooser.ExtensionFilter("JPEG (*.JPEG)","*.JPEG*"),
                new FileChooser.ExtensionFilter("GIF (*.GIF)","*.GIF*")
        );
    }

    // ---------------------- Page 2 ---------------------- //
    private void openNewImageWindow(File file){
        Stage secondStage = new Stage();
        Label name = new Label(file.getAbsolutePath());
        name.setAlignment(Pos.CENTER);

        // ---------------------- Image de page 2 ---------------------- //
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView();
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);

        // ---------------------- Effectuer la rotation à droite de l'image ---------------------- //
        final Button rotation1 = new Button("Rotation à droite");
        rotation1.setPrefWidth(180);
        rotation1.setPrefHeight(28);
        rotation1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                double value = imageView.getRotate();
                imageView.setRotate(value+30);
                imageView.setImage(image);
            }
        });

        // ---------------------- Effectuer la rotation à gauche de l'image ---------------------- //
        final Button rotation2 = new Button("Rotation à gauche");
        rotation2.setPrefWidth(180);
        rotation2.setPrefHeight(28);
        rotation2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                double value = imageView.getRotate();
                imageView.setRotate(value-30);
                imageView.setImage(image);
            }
        });

        // ---------------------- Symetrie de l'image ---------------------- //
        final Button symetrie = new Button("Symetrie de l'image");
        symetrie.setPrefWidth(180);
        symetrie.setPrefHeight(28);
        symetrie.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                if ("Symetrie de l'image".equals(symetrie.getText())) {
                    imageView.setRotate(180);
                    symetrie.setText("Annuler");
                } else {
                    imageView.setRotate(0);
                    symetrie.setText("Symetrie de l'image");
                }
                imageView.setImage(image);
            }
        });

        // ---------------------- Filtre BRG ---------------------- //
        final Button brg = new Button("Filtre BRG");
        brg.setPrefWidth(180);
        brg.setPrefHeight(28);
        brg.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                if ("Filtre BRG".equals(brg.getText())) {
                    new FiltreBRG(imageView);
                    brg.setText("Annuler");
                } else {
                    imageView.setImage(image);
                    brg.setText("Filtre BRG");
                }
            }
        });

        // ---------------------- Filtre Noir et Blanc ---------------------- //
        final Button neb = new Button("Filtre noir et blanc");
        neb.setPrefWidth(180);
        neb.setPrefHeight(28);
        neb.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                if ("Filtre noir et blanc".equals(neb.getText())) {
                    new FiltreNeB(imageView);
                    neb.setText("Annuler");
                } else {
                    imageView.setImage(image);
                    neb.setText("Filtre noir et blanc");
                }
            }
        });

        // ---------------------- Filtre Sepia ---------------------- //
        final Button sepia = new Button("Filtre Sepia");
        sepia.setPrefWidth(180);
        sepia.setPrefHeight(28);
        sepia.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                if ("Filtre Sepia".equals(sepia.getText())) {
                    new FiltreSepia(imageView);
                    sepia.setText("Annuler");
                } else {
                    imageView.setImage(image);
                    sepia.setText("Filtre Sepia");

                }
            }
        });

        // ---------------------- Filtre Prewitt ---------------------- //
        final Button prewitt = new Button("Filtre Prewitt");
        prewitt.setPrefWidth(180);
        prewitt.setPrefHeight(28);
        prewitt.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                if ("Filtre Prewitt".equals(prewitt.getText())) {
                    new FiltrePrewitt(imageView);
                    prewitt.setText("Annuler");
                } else {
                    imageView.setImage(image);
                    prewitt.setText("Filtre Prewitt");
                }
            }
        });

        // ---------------------- Enregistrement de l'image ---------------------- //
        final Button enrg = new Button("Enregistrer");
        enrg.setPrefWidth(180);
        enrg.setPrefHeight(28);
        enrg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Image");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PNG (*.PNG)", "*.PNG"),
                        new FileChooser.ExtensionFilter("JPG (*.JPG)","*.JPG*"),
                        new FileChooser.ExtensionFilter("JPEG (*.JPEG)","*.JPEG*"),
                        new FileChooser.ExtensionFilter("GIF (*.GIF)","*.GIF*"));
                File file = fileChooser.showSaveDialog(secondStage);
                if (file != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
                                null), "png", file);
                    } catch (IOException ex) {
                        Logger.getLogger(
                                ImgApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        // ---------------------- Crypt et Decrypt ---------------------- //
        final TextField cryp = new TextField();
        cryp.setPromptText("Enter your password.");
        final Button crypt = new Button("Crypt");
        crypt.setPrefWidth(180);
        crypt.setPrefHeight(28);
        crypt.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                if ("Crypt".equals(crypt.getText())) {
                    try {
                        String pass= new Crypt().createPassword(cryp.getText());
                        new Crypt(imageView,pass);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        final TextField decryp = new TextField();
        decryp.setPromptText("Enter your password.");
        final Button decrypt = new Button("Decrypt");
        decrypt.setPrefWidth(180);
        decrypt.setPrefHeight(28);
        decrypt.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                    if ("Decrypt".equals(decrypt.getText())) {
                        try {
                            String pass= new Decrypt().createPassword(decryp.getText());
                            if ((cryp.getText()).equals(decryp.getText())) imageView.setImage(image);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

        // ---------------------- Organisation de l'affichage de Page 2 ---------------------- //
        GridPane gridPane = new GridPane();

        gridPane.add(name, 1, 0, 4, 1);
        gridPane.add(imageView, 1, 1, 4, 1);

        gridPane.add(rotation1, 0, 4, 1, 1);
        gridPane.add(rotation2, 1, 4, 1, 1);
        gridPane.add(symetrie, 2, 4, 1, 1);
        gridPane.add(brg, 3, 4, 1, 1);

        gridPane.add(neb, 0, 5, 1, 1);
        gridPane.add(sepia, 1, 5, 1, 1);
        gridPane.add(prewitt, 2, 5, 1, 1);
        gridPane.add(enrg, 3, 5, 1, 1);

        gridPane.add(cryp,1, 6, 1, 1);
        gridPane.add(crypt, 2, 6, 1, 1);

        gridPane.add(decryp, 1, 7, 1, 1);
        gridPane.add(decrypt, 2, 7, 1, 1);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Scene scene = new Scene(gridPane, 700, 700);
        secondStage.setTitle(file.getName());
        secondStage.setScene(scene);
        secondStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


