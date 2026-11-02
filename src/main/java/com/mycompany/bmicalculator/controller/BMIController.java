package com.mycompany.bmicalculator.controller;

import com.mycompany.bmicalculator.model.BMIRecord;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller untuk form-bmi.fxml.
 */
public class BMIController {

    @FXML private TextField tfBerat;
    @FXML private TextField tfTinggi;
    @FXML private Button btnHitung;
    @FXML private Button btnReset;
    @FXML private Label lblNilaiBMI;
    @FXML private Label lblKategori;

    @FXML
    private void handleHitung() {
        try {
            double berat = parseAngka(tfBerat.getText());
            double tinggiM = parseAngka(tfTinggi.getText()) / 100.0;   // cm -> meter

            BMIRecord record = new BMIRecord(berat, tinggiM);

            lblNilaiBMI.setText(String.format(Locale.US, "%.2f", record.getNilaiBMI()));
            lblKategori.setText(record.getKategori());
            lblKategori.setStyle("-fx-text-fill: " + warnaKategori(record.getKategori()) + ";");
        } catch (IllegalArgumentException e) {
            // NumberFormatException turunan IllegalArgumentException:
            // kolom kosong, teks, atau angka <= 0 masuk ke sini.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Tidak Valid");
            alert.setHeaderText(null);
            alert.setContentText("Masukkan angka yang valid!");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleReset() {
        tfBerat.clear();
        tfTinggi.clear();
        lblNilaiBMI.setText("-");
        lblKategori.setText("-");
        lblKategori.setStyle("-fx-text-fill: #14532d;");
        tfBerat.requestFocus();
    }

    /** Mengubah teks menjadi angka positif. Koma desimal (65,5) juga diterima. */
    private double parseAngka(String teks) {
        if (teks == null || teks.trim().isEmpty()) {
            throw new NumberFormatException("Kolom kosong");
        }
        double nilai = Double.parseDouble(teks.trim().replace(',', '.'));
        if (!Double.isFinite(nilai) || nilai <= 0) {
            throw new NumberFormatException("Angka harus lebih dari 0");
        }
        return nilai;
    }

    private String warnaKategori(String kategori) {
        return switch (kategori) {
            case BMIRecord.KATEGORI_KURUS -> "#b45309";
            case BMIRecord.KATEGORI_NORMAL -> "#15803d";
            case BMIRecord.KATEGORI_LEBIH -> "#c2410c";
            default -> "#b91c1c";
        };
    }
}
