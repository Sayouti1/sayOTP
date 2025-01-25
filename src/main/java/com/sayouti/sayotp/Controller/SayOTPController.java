/****************************************************
 @Created_by :  ABDELAZIZ ES SAYOUTI aka @Sayouti1
 @mail:         aes-sayo@student.1337.ma
 @Updated_at:   25-01-2025 by : aes-sayo
 ****************************************************/

package com.sayouti.sayotp.Controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class SayOTPController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField account_txt;

    @FXML
    private GridPane accounts_pane;

    @FXML
    private Button add_button;

    @FXML
    private Button refresh_button;

    @FXML
    private TextField key_txt;

    @FXML
    private Label alert_label;

    @FXML
    private ProgressBar progress;

    private void raiseError(TextField txt)
    {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));

        txt.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
        alertError("Cannot set empty fields !");
        pause.setOnFinished(e -> {
            txt.setStyle("-fx-border-color: transparent");
        });
        pause.play();
    }
    private void alertError(String msg)
    {
        PauseTransition pause = new PauseTransition(Duration.seconds(4));

        alert_label.setStyle("-fx-text-fill: red");
        alert_label.setText(msg);
        pause.setOnFinished(e -> {alert_label.setText("");});
        pause.play();
    }

    private void alertSuccess(String msg)
    {
        PauseTransition pause = new PauseTransition(Duration.seconds(4));

        alert_label.setStyle("-fx-text-fill: green");
        alert_label.setText(msg);
        pause.setOnFinished(e -> {alert_label.setText("");});
        pause.play();
    }

    private boolean validateTxtFields()
    {
        if (account_txt.getText().trim().isEmpty() || key_txt.getText().trim().isEmpty())
        {
            if (account_txt.getText().isEmpty())
                raiseError(account_txt);
            if (key_txt.getText().isEmpty())
                raiseError(key_txt);
            return false;
        }
        return (true);
    }
    @FXML
    void addAccount(ActionEvent event) throws Exception
    {
        account_txt.setText(account_txt.getText().trim());
        key_txt.setText(key_txt.getText().trim());
        if (!validateTxtFields())
            return;
        if (Data.getListKeys().contains(account_txt.getText().toLowerCase()))
        {
            alertSuccess("Account already exists !");
            account_txt.setText("");
            key_txt.setText("");
            return;
        }
        Data.addData(account_txt.getText().toLowerCase(), key_txt.getText());
        alertSuccess("Account added !");
        account_txt.setText("");
        key_txt.setText("");
        showList();
    }

    @FXML
    void refreshList(ActionEvent actionEvent)
    {
        accounts_pane.getChildren().clear();
        try
        {
            showList();
        }catch (Exception e)
        {
            System.out.println("Error in refreshList" + e.getMessage());
            alertError(e.getMessage());
        }
    }
    private Node generateAccountNode(String key, String val)
    {
        VBox node = new VBox();

        node.setMinSize(250, 100);
        node.setAlignment(Pos.CENTER);
        node.setSpacing(10);
        node.setBackground(Background.fill(Paint.valueOf("#7263f2")));

        Label name = new Label(key);
        name.setStyle("-fx-font-weight: bold;-fx-font-family: FreeMono 'JetBrains Mono ExtraBold'; -fx-font-size: 18");
        name.setTextFill(Paint.valueOf("#ffffff"));
        name.setAlignment(Pos.CENTER);

        HBox name_box = new HBox();
        name_box.setSpacing(15);

        TextField otp = new TextField(String.valueOf(val));
        otp.setEditable(false);
        otp.setMinWidth(215);

        Button copy = new Button("copy");
        copy.setOnAction(e -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(otp.getText());
            Clipboard.getSystemClipboard().setContent(content);
            copy.setText("copied");
        });
        name_box.getChildren().addAll(otp,copy);

        node.getChildren().addAll(name, name_box );
        return node;
    }

    public void showList() throws Exception
    {
        int i = 0, j = 0;

        HashMap<String, String> list = (HashMap<String, String>)Data.getList();
        accounts_pane.getChildren().clear();
        for (String key : list.keySet())
        {
            accounts_pane.add(generateAccountNode(key.toUpperCase(), list.get(key)), j, i);
            j++;
            if (j == 2)
            {
                j = 0;
                i++;
            }
        }
    }

    private void startTimer() {
        Timeline timeline = null;
        progress.setProgress(1.0);
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    double currentProgress = progress.getProgress();
                    double newProgress = currentProgress - (1.0 / 10);
                    progress.setProgress(newProgress);

                    if (newProgress <= 0.0) {
                        try
                        {
                            showList();
                        } catch (Exception e)
                        {
                            System.out.println("Error in startTimer" + e.getMessage());
                            alertError(e.getMessage());
                        }
                        progress.setProgress(1.0);
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    void initialize() {


        assert account_txt != null : "fx:id=\"account_txt\" was not injected: check your FXML file 'SayOTP.fxml'.";
        assert accounts_pane != null : "fx:id=\"accounts_pane\" was not injected: check your FXML file 'SayOTP.fxml'.";
        assert add_button != null : "fx:id=\"add_button\" was not injected: check your FXML file 'SayOTP.fxml'.";
        assert refresh_button != null : "fx:id=\"refresh_button\" was not injected: check your FXML file 'SayOTP.fxml'.";
        assert key_txt != null : "fx:id=\"key_txt\" was not injected: check your FXML file 'SayOTP.fxml'.";
        assert alert_label != null : "fx:id=\"alert_label\" was not injected: check your FXML file 'SayOTP.fxml'.";
        assert progress != null : "fx:id=\"progress\" was not injected: check your FXML file 'SayOTP.fxml'.";

        try
        {
            Data.loadData();
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                try
                {
                    showList();
                } catch (Exception ex)
                {
                    System.out.printf("Error in startTimer" + ex.getMessage());
                    alertError(ex.getMessage());
                }
            });
            pause.play();
        } catch (Exception e)
        {
            System.out.println("ERROR " + e.getMessage());
            alertError(e.getMessage());
        }
        startTimer();
    }
}
