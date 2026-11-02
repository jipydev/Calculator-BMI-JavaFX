package com.mycompany.bmicalculator.model;

/**
 * Model untuk menyimpan data BMI. Berat dalam kg, tinggi dalam meter.
 */
public class BMIRecord {

    public static final String KATEGORI_KURUS = "Kurus / Kekurangan Berat";
    public static final String KATEGORI_NORMAL = "Normal (Ideal)";
    public static final String KATEGORI_LEBIH = "Kelebihan Berat Badan";
    public static final String KATEGORI_OBESITAS = "Obesitas";

    private double berat;
    private double tinggi;
    private double nilaiBMI;
    private String kategori;

    public BMIRecord(double berat, double tinggi) {
        setBerat(berat);
        setTinggi(tinggi);
        hitung();
    }

    /** Rumus: berat_kg / (tinggi_m * tinggi_m). */
    public double hitungBMI() {
        return berat / (tinggi * tinggi);
    }

    public static String tentukanKategori(double bmi) {
        if (bmi < 18.5) {
            return KATEGORI_KURUS;
        } else if (bmi < 25.0) {
            return KATEGORI_NORMAL;
        } else if (bmi < 30.0) {
            return KATEGORI_LEBIH;
        }
        return KATEGORI_OBESITAS;
    }

    /** Menghitung nilai BMI dan kategori dari berat dan tinggi saat ini. */
    public final void hitung() {
        this.nilaiBMI = hitungBMI();
        this.kategori = tentukanKategori(nilaiBMI);
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        if (!Double.isFinite(berat) || berat <= 0) {
            throw new IllegalArgumentException("Berat harus lebih dari 0.");
        }
        this.berat = berat;
    }

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        if (!Double.isFinite(tinggi) || tinggi <= 0) {
            throw new IllegalArgumentException("Tinggi harus lebih dari 0.");
        }
        this.tinggi = tinggi;
    }

    public double getNilaiBMI() {
        return nilaiBMI;
    }

    public void setNilaiBMI(double nilaiBMI) {
        this.nilaiBMI = nilaiBMI;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
