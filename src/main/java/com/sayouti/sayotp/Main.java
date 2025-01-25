/****************************************************
 @Created_by :  ABDELAZIZ ES SAYOUTI aka @Sayouti1
 @mail:         aes-sayo@student.1337.ma
 @Updated_at:   25-01-2025 by : aes-sayo
 ****************************************************/

package com.sayouti.sayotp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SayOTP.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 594, 739);
        stage.setTitle("SayOTP");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}