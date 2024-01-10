package com.example.jsondwa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dane z Linku");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        try {

            String url = "http://zasob.itmargen.com/4TD/";
            String jsonData = fetchDataFromUrl(url);

            JSONObject jsonObject = new JSONObject(jsonData);
            StringBuilder result = new StringBuilder();

            JSONObject infoSekcja = jsonObject.getJSONObject("info");
            result.append("INFO:\n")
                    .append("Przedmiot: ").append(infoSekcja.getString("przedmiot")).append("\n")
                    .append("Prowadzący: ").append(infoSekcja.getString("prowadzacy")).append("\n")
                    .append("Szkoła: ").append(infoSekcja.getString("szkola")).append("\n")
                    .append("Miasto: ").append(infoSekcja.getString("miasto")).append("\n")
                    .append("Data i czas: ").append(infoSekcja.getString("dataczas")).append("\n\n");

            JSONObject grupa2Sekcja = jsonObject.getJSONObject("Grupa2");
            String mojeImieINazwisko = grupa2Sekcja.getString("6");
            result.append("Grupa2:\n");
                result.append("6: ").append(mojeImieINazwisko);

            textArea.setText(result.toString());

        } catch (IOException e) {
            textArea.setText("Błąd podczas pobierania danych z linku.");
            e.printStackTrace();
        }

        primaryStage.setScene(new Scene(textArea, 600, 400));
        primaryStage.show();
    }

    private String fetchDataFromUrl(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }
}