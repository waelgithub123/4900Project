package com.example.travel_inten;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//import org.json.simple.JSONObject;
//import org.w3c.dom.events.MouseEvent;
//
//import java.io.IOException;
//
//public class TravelItineraryController {
//
//
//    private Label budgetLabel;
//
//    @FXML
//    private Stage budgetStage;
//
//    private final travel_advisory advisory = new travel_advisory();
//
//
//    @FXML
//    private TextField budgetTextField;
//
//
//    @FXML
//    private void openBudgetWindow() {
//        if (budgetStage == null) {
//            budgetStage = new Stage();
//
//            // Create a VBox to hold the input field and label
//            VBox vbox = new VBox();
//            vbox.setAlignment(Pos.CENTER);
//
//            // Create a label
//            Label label = new Label("Enter Budget Amount:");
//
//            // Create a TextField for input
//            budgetTextField = new TextField();
//
//            // Create a button to update the budget panel
//            Button updateButton = new Button("Update Budget");
//            updateButton.setOnAction(this::handleUpdateBudget);
//
//            // Add the label, TextField, and button to the VBox
//            vbox.getChildren().addAll(label, budgetTextField, updateButton);
//
//            Scene budgetScene = new Scene(vbox, 300, 200);
//            budgetStage.setTitle("Budget Panel Window");
//            budgetStage.setScene(budgetScene);
//            budgetStage.show();
//        }
//    }
//
//    private void handleUpdateBudget(ActionEvent event) {
//        if (budgetStage != null) {
//            String inputText = budgetTextField.getText();
//            try {
//                double budgetAmount = Double.parseDouble(inputText);
//                updateBudgetPanel(budgetAmount);
//                budgetStage.close();
//            } catch (NumberFormatException e) {
//                // Handle invalid input (e.g., display an error message)
//            }
//        }
//    }
//
//    private void updateBudgetPanel(double budgetAmount) {
//        budgetLabel.setText("Budget: $" + String.format("%.2f", budgetAmount));
//    }
//
//
//    private void handleAdvisoryPanelClick(MouseEvent event) {
//        Rectangle clickedPanel;
//        clickedPanel = (Rectangle) event.getTarget();
//
//        String countryCode = determineCountryCode(clickedPanel.getId());
//
//        if (countryCode != null) {
//            try {
//                JSONObject advisoryInfo = advisory.getTravelAdvisoryFromPanel(countryCode);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private String determineCountryCode(String panelId) {
//        switch (panelId) {
//            case "US":
//                return "US"; // United States
//            case "UK":
//                return "UK"; // United Kingdom
//            case "FR":
//                return "FR"; // France
//            case "DE":
//                return "DE"; // Germany
//            case "JP":
//                return "JP"; // Japan
//            case "CA":
//                return "CA"; // Canada
//            case "AU":
//                return "AU"; // Australia
//            case "IT":
//                return "IT"; // Italy
//            case "ES":
//                return "ES"; // Spain
//            case "BR":
//                return "BR"; // Brazil
//            case "IN":
//                return "IN"; // India
//            default:
//                return null;
//        }
//    }
//}

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TravelItineraryController {

    @FXML
    private Label totalExpensesLabel;

    @FXML
    private TextField airplaneTicketField;

    @FXML
    private TextField hotelField;

    @FXML
    private TextField foodField;

    @FXML
    private TextField miscExpensesField;

    // Method to calculate and display total expenses
    @FXML
    private void calculateTotalExpenses() {
        double airplaneTicket = parseExpense(airplaneTicketField.getText());
        double hotel = parseExpense(hotelField.getText());
        double food = parseExpense(foodField.getText());
        double miscExpenses = parseExpense(miscExpensesField.getText());

        double totalExpenses = airplaneTicket + hotel + food + miscExpenses;

        // Display individual expenses
        displayExpense("Airplane Ticket", airplaneTicket);
        displayExpense("Hotel", hotel);
        displayExpense("Food", food);
        displayExpense("Misc Expenses", miscExpenses);

        // Display total expenses
        totalExpensesLabel.setText("Total Expenses: $" + String.format("%.2f", totalExpenses));
    }

    // Helper method to parse expense input as a double
    private double parseExpense(String expense) {
        try {
            return Double.parseDouble(expense);
        } catch (NumberFormatException e) {
            return 0.0; // Return 0.0 if parsing fails
        }
    }

    // Helper method to display individual expense
    private void displayExpense(String expenseName, double amount) {
        System.out.println(expenseName + ": $" + String.format("%.2f", amount));
    }

    // Method to handle the user input for updating the budget details
    @FXML
    private void updateBudgetDetails() {
        double airplaneTicket = parseExpense(airplaneTicketField.getText());
        double hotel = parseExpense(hotelField.getText());
        double food = parseExpense(foodField.getText());
        double miscExpenses = parseExpense(miscExpensesField.getText());

        displayExpense("Airplane Ticket", airplaneTicket);
        displayExpense("Hotel", hotel);
        displayExpense("Food", food);
        displayExpense("Misc Expenses", miscExpenses);

        double totalExpenses = airplaneTicket + hotel + food + miscExpenses;
        totalExpensesLabel.setText("Total Expenses: $" + String.format("%.2f", totalExpenses));
    }
    // You can add more methods as needed to handle user input and update the UI
}