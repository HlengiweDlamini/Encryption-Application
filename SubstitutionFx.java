package lab07;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

public class SubstitutionFx extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Encryption and Decryption");

        Scene mainScene = createMainScene();

        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("Press ESC to exit");

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private Scene createMainScene() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("ENCRYPTION AND DECRYPTION");
        title.setStyle("-fx-font-size: 27px; -fx-font-weight: bold; -fx-text-fill: black;");

        // Adding a description to guide the user
        Label description = new Label("This application allows you to encrypt or decrypt strings. Select an operation below:");
        description.setStyle("-fx-font-size: 20px; -fx-text-fill: gray;");

        Button encryptButton = new Button("Encrypt");
        Button decryptButton = new Button("Decrypt");

        encryptButton.setStyle("-fx-background-color: #00875a; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 30px 50px; -fx-pref-width: 370px");
        decryptButton.setStyle("-fx-background-color: #00875a; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 30px 50px; -fx-pref-width: 370px");

        encryptButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        decryptButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));

        // Adding tooltips to explain each button's function
        encryptButton.setTooltip(new Tooltip("Encrypt a piece of text with a specified key."));
        decryptButton.setTooltip(new Tooltip("Decrypt an encrypted text with a specified key or frequency analysis."));

        encryptButton.setOnAction(e -> {
            Scene encryptScene = createEncryptScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(encryptScene);
            primaryStage.show();
        });

        decryptButton.setOnAction(e -> {
            Scene decryptScene = createDecryptScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(decryptScene);
            primaryStage.show();
        });

        layout.add(title, 0, 0, 2, 1);
        layout.add(description, 0, 2, 2, 1); // Description spans two columns
        layout.add(encryptButton, 0, 3);
        layout.add(decryptButton, 1, 3);

        Scene mainScene = new Scene(layout, 600, 400);
        mainScene.setFill(Color.web("#2d3436")); // Set the background color

        return mainScene;
    }

    private Scene createEncryptScene() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Encryption Page");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black;");

        TextField textField = new TextField();
        textField.setPromptText("Enter text to encrypt");
        textField.setStyle("-fx-background-color: #dfe6e9; -fx-prompt-text-fill: #b2bec3;");

        TextField keyField = new TextField();
        keyField.setPromptText("Enter key (integer)");
        keyField.setStyle("-fx-background-color: #dfe6e9; -fx-prompt-text-fill: #b2bec3;");

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: black;");

        Button encryptActionButton = new Button("Encrypt");
        encryptActionButton.setStyle("-fx-background-color: #00875a; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        encryptActionButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        encryptActionButton.setOnAction(e -> {
            try {
                String text = textField.getText();
                int key = Integer.parseInt(keyField.getText());
                String encryptedText = SubstitutionTech.encrypt(text, key);
                resultLabel.setText("Cypher text: " + encryptedText);
            } catch (NumberFormatException nfe) {
                resultLabel.setText("Invalid key. Please enter an integer.");
            }
        });

        Button backButton = new Button("Return");
        backButton.setStyle("-fx-background-color: #B0BEC5; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        backButton.setOnAction(e -> {
            Scene mainScene = createMainScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(mainScene);
            primaryStage.show();
        });

        layout.add(title, 0, 0, 2, 1);
        layout.add(new Label("Text/String:"), 0, 2);
        layout.add(textField, 1, 2);
        layout.add(new Label("Key:"), 0, 4);
        layout.add(keyField, 1, 4);
        layout.add(encryptActionButton, 0, 5, 2, 1);
        layout.add(resultLabel, 0, 7, 2, 1);
        layout.add(backButton, 0, 9, 2, 1);

        Scene encryptScene = new Scene(layout, 600, 400);
        encryptScene.setFill(Color.web("#2d3436")); // Set the background color

        return encryptScene;
    }

    private Scene createDecryptScene() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Decryption Page");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: black;");

        Button substitutionButton = new Button("Substitution Technique");
        Button frequencyAnalysisButton = new Button("Frequency Analysis");

        substitutionButton.setStyle("-fx-background-color: #00875a; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 30px 50px; -fx-pref-width: 370px");
        frequencyAnalysisButton.setStyle("-fx-background-color: #00875a; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 30px 50px; -fx-pref-width: 370px");

        substitutionButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        frequencyAnalysisButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));

        substitutionButton.setOnAction(e -> {
            Scene substitutionScene = createSubstitutionDecryptScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(substitutionScene);
            primaryStage.show();
        });

        frequencyAnalysisButton.setOnAction(e -> {
            Scene frequencyAnalysisScene = createFrequencyAnalysisScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(frequencyAnalysisScene);
            primaryStage.show();
        });

        Button backButton = new Button("Return");
        backButton.setStyle("-fx-background-color: #B0BEC5; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        backButton.setOnAction(e -> {
            Scene mainScene = createMainScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(mainScene);
            primaryStage.show();
        });

        layout.add(title, 0, 0, 2, 1);
        layout.add(substitutionButton, 0, 2);
        layout.add(frequencyAnalysisButton, 1, 2);
        layout.add(backButton, 0, 3, 2, 1);

        Scene decryptScene = new Scene(layout, 600, 400);
        decryptScene.setFill(Color.web("#2d3436"));

        return decryptScene;
    }

    private Scene createSubstitutionDecryptScene() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Substitution Decryption");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: black;");

        TextField encryptedTextField = new TextField();
        encryptedTextField.setPromptText("Enter encrypted text");
        encryptedTextField.setStyle("-fx-font-size: 20px; -fx-background-color: #dfe6e9; -fx-prompt-text-fill: #b2bec3;");

        TextField keyField = new TextField();
        keyField.setPromptText("Enter key (integer)");
        keyField.setStyle("-fx-font-size: 20px; -fx-background-color: #dfe6e9; -fx-prompt-text-fill: #b2bec3;");

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");

        Button decryptActionButton = new Button("Decrypt");
        decryptActionButton.setStyle("-fx-background-color: #00875a; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10px 20px;-fx-pref-width: 170px");
        decryptActionButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        decryptActionButton.setOnAction(event -> {
            try {
                String encryptedText = encryptedTextField.getText();
                int key = Integer.parseInt(keyField.getText());
                String decryptedText = SubstitutionTech.decrypt(encryptedText, key);
                resultLabel.setText("Decrypted text: " + decryptedText);
            } catch (Exception ex) {
                resultLabel.setText("Error during decryption: " + ex.getMessage());
            }
        });

        Button backButton = new Button("Return");
        backButton.setStyle("-fx-background-color: #B0BEC5; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        backButton.setOnAction(event -> {
            Scene decryptScene = createDecryptScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(decryptScene);
            primaryStage.show();
        });

        layout.add(title, 0, 0, 2, 1);
        layout.add(new Label("Cypher Text:"), 0, 2);
        layout.add(encryptedTextField, 1, 2);
        layout.add(new Label("Key:"), 0, 3);
        layout.add(keyField, 1, 3);
        layout.add(decryptActionButton, 0, 4, 2, 1);
        layout.add(resultLabel, 0, 5, 2, 1);
        layout.add(backButton, 0, 6, 2, 1);

        Scene substitutionDecryptScene = new Scene(layout, 600, 400);
        substitutionDecryptScene.setFill(Color.web("#2d3436"));

        return substitutionDecryptScene;
    }

    private Scene createFrequencyAnalysisScene() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(20);
        layout.setVgap(20);
        layout.setPadding(new Insets(20));

        Label title = new Label("Frequency Analysis");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black;");

        FileChooser fileChooser = new FileChooser();
        Label fileNameLabel = new Label("No file selected.");
        fileNameLabel.setStyle("-fx-text-fill: black;");

        Button browseButton = new Button("Browse File");
        browseButton.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        browseButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        browseButton.setOnAction(event -> {
            var selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                fileNameLabel.setText("Selected file: " + selectedFile.getName());
            } else {
                fileNameLabel.setText("No file selected.");
            }
        });

        Button analyzeButton = new Button("Perform Analysis");
        analyzeButton.setStyle("-fx-background-color: #00875a;  -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        Label resultLabel = new Label();
        analyzeButton.setOnAction(event -> {
            primaryStage.setFullScreen(true);
            try {
                String filePath = fileNameLabel.getText().split(":")[1].trim();
                Map<Character, Integer> frequencyMap = FrequencyAnalysis.charFrequency(filePath);

                StringBuilder result = new StringBuilder("Character frequencies:\n");
                frequencyMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEach(entry -> result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));

                resultLabel.setText(result.toString());
            } catch (IOException ex) {
                resultLabel.setText("Error analyzing file: " + ex.getMessage());
            }
        });


        Button backButton = new Button("Return");
        backButton.setStyle("-fx-background-color: #B0BEC5; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        backButton.setEffect(new DropShadow(5, 3, 3, Color.DARKGRAY));
        backButton.setOnAction(event -> {
            Scene decryptScene = createDecryptScene();
            primaryStage.setFullScreen(true);
            primaryStage.setScene(decryptScene);
            primaryStage.show();
        });

        layout.add(title, 0, 0, 2, 1);
        layout.add(browseButton, 0, 1);
        layout.add(fileNameLabel, 1, 1);
        layout.add(analyzeButton, 0, 2, 2, 1);
        layout.add(resultLabel, 0, 3, 2, 1);
        layout.add(backButton, 0, 4, 2, 1);

        Scene substitutionDecryptScene = new Scene(layout, 600, 400);
        substitutionDecryptScene.setFill(Color.web("#2d3436"));

        return substitutionDecryptScene;
}

    public static void main(String[] args) {
        launch(args);
    }
}
