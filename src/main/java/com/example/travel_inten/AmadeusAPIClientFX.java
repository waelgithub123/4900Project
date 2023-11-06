package com.example.travel_inten;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//public class AmadeusAPIClientFX extends Application {
//
//    private String accessToken;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Amadeus API Client");
//
//        // Create UI components
//        Button getTokenButton = new Button("Get Access Token");
//        Button searchButton = new Button("Search");
//        TextField apiKeyField = new TextField();
//        Label resultLabel = new Label();
//
//        getTokenButton.setOnAction(event -> {
//            // Handle access token retrieval here
//            String apiKey = apiKeyField.getText();
//
//            try {
//                HttpClient httpClient = HttpClients.createDefault();
//
//                String tokenEndpoint = "https://test.api.amadeus.com/v1/security/oauth2/token";
//                HttpGet tokenRequest = new HttpGet(tokenEndpoint);
//                tokenRequest.setHeader("Authorization", "Basic " + apiKey); // Use Basic Auth header
//
//                HttpResponse tokenResponse = httpClient.execute(tokenRequest);
//
//                if (tokenResponse.getStatusLine().getStatusCode() == 200) {
//                    HttpEntity entity = tokenResponse.getEntity();
//                    String tokenResponseString = EntityUtils.toString(entity);
//                    // Parse and store the access token securely
//                    accessToken = "your_access_token"; // Replace with the obtained access token
//                    resultLabel.setText("Access token obtained.");
//                } else {
//                    resultLabel.setText("Access token request failed with status code: " + tokenResponse.getStatusLine().getStatusCode());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                resultLabel.setText("An error occurred while obtaining the access token.");
//            }
//        });
//
//        searchButton.setOnAction(event -> {
//            // Handle the API request using the obtained access token
//            if (accessToken != null) {
//                try {
//                    HttpClient httpClient = HttpClients.createDefault();
//
//                    String searchEndpoint = "https://test.api.amadeus.com/v2/reference-data/urls/checkin-links?airline=IB";
//                    HttpGet searchRequest = new HttpGet(searchEndpoint);
//                    searchRequest.setHeader("Authorization", "Bearer " + accessToken);
//
//                    HttpResponse searchResponse = httpClient.execute(searchRequest);
//
//                    if (searchResponse.getStatusLine().getStatusCode() == 200) {
//                        HttpEntity entity = searchResponse.getEntity();
//                        String searchResponseString = EntityUtils.toString(entity);
//                        resultLabel.setText("Search result: " + searchResponseString);
//                    } else {
//                        resultLabel.setText("Search request failed with status code: " + searchResponse.getStatusLine().getStatusCode());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    resultLabel.setText("An error occurred while making the search request.");
//                }
//            } else {
//                resultLabel.setText("Access token is not available. Obtain the access token first.");
//            }
//        });
//
//        // Create the layout
//        VBox vbox = new VBox(10);
//        vbox.getChildren().addAll(
//                new Label("API Key:"),
//                apiKeyField,
//                getTokenButton,
//                searchButton,
//                resultLabel
//        );
//
//        // Create the scene and set it on the stage
//        Scene scene = new Scene(vbox, 600, 400);
//        primaryStage.setScene(scene);
//
//        // Show the stage
//        primaryStage.show();
//    }
//}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class AmadeusAPIClientFX extends Application {
    private final String API_KEY = "bf9cbf866a16e7ca7b6ad3166660a3f9";
    private final String API_BASE_URL = "https://api.aviationstack.com/v1/flights";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flight Information App");

        Label departureLabel = new Label("Departure Location:");
        Label returnLabel = new Label("Return Location:");
        Label departureTimeLabel = new Label("Departure Time (YYYY-MM-DD):");
        Label returnTimeLabel = new Label("Return Time (YYYY-MM-DD):");

        TextField departureLocationField = new TextField();
        TextField returnLocationField = new TextField();
        TextField departureTimeField = new TextField();
        TextField returnTimeField = new TextField();

        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();

        searchButton.setOnAction(e -> {
            String departureLocation = departureLocationField.getText();
            String returnLocation = returnLocationField.getText();
            String departureTime = departureTimeField.getText();
            String returnTime = returnTimeField.getText();

            if (!departureLocation.isEmpty() && !returnLocation.isEmpty() && !departureTime.isEmpty() && !returnTime.isEmpty()) {
                String apiUrl = String.format(
                        "%s?access_key=%s&dep_iata=%s&arr_iata=%s&flight_date=%s&arr_scheduled_time_arr=%s",
                        API_BASE_URL, API_KEY, departureLocation, returnLocation, departureTime, returnTime);

                try {
                    String response = sendGetRequest(apiUrl);
                    resultArea.setText(response);
                } catch (IOException ex) {
                    resultArea.setText("Error: " + ex.getMessage());
                }
            } else {
                resultArea.setText("Please fill in all fields.");
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(departureLabel, 0, 0);
        grid.add(returnLabel, 0, 1);
        grid.add(departureTimeLabel, 0, 2);
        grid.add(returnTimeLabel, 0, 3);
        grid.add(departureLocationField, 1, 0);
        grid.add(returnLocationField, 1, 1);
        grid.add(departureTimeField, 1, 2);
        grid.add(returnTimeField, 1, 3);
        grid.add(searchButton, 0, 4, 2, 1);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(grid, resultArea);

        Scene scene = new Scene(vbox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String sendGetRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } finally {
            connection.disconnect();
        }
    }
}
