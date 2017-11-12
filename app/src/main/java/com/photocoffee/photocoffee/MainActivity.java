package com.photocoffee.photocoffee; // Nama paket disesuaikan dengan project masing-masing. Jangan dicopas!

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * Variabel Global
     */
    String customerName; // Nama pelanggan
    boolean isToppingWhippedCream; // Topping Whipped Cream
    boolean isToppingChocolate; // Topping Chocolate
    boolean isToppingIceCream; // Topping Ice Cream

    int orderQuantity = 0; // Jumlah pesanan
    int harga = 0;
    int totalHarga = 0;
    String message; // Pesan yang akan dikirim di Email

    EditText customerNameEditText; // View nama pelanggan
    CheckBox toppingWhippedCreamCheckBox; // View chekbox Whipped Cream
    CheckBox toppingChocolateCheckBox; // View checkbox Chocolate
    CheckBox toppingIceCreamCheckBox; // View checkbox Ice Cream
    TextView quantityTextView; // View jumlah pesanan
    TextView totalHargaTextView;

    /**
     * Fungsi ini akan dipanggil saat aplikasi dibuka
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Buka tampilan dari file activity_main.xml

        /**
         * Mencari View untuk digunakan dalam program kita
         */
        customerNameEditText = findViewById(R.id.customer_name);
        toppingWhippedCreamCheckBox = findViewById(R.id.topping_whippedcream);
        toppingChocolateCheckBox = findViewById(R.id.topping_chocolate);
        toppingIceCreamCheckBox = findViewById(R.id.topping_icecream);
        quantityTextView = findViewById(R.id.quantity_number);
        totalHargaTextView = findViewById(R.id.total_harga);
    }

    public void hitungTotalHarga() {
        harga = 20000;
        if(toppingWhippedCreamCheckBox.isChecked()){
            harga += 10000;
        }
        if(toppingChocolateCheckBox.isChecked()){
            harga += 5000;
        }
        if(toppingIceCreamCheckBox.isChecked()){
            harga += 15000;
        }

        totalHarga = orderQuantity * harga;
    }

    /**
     * Ketika tombol minus "-" ditekan, kurangi jumlah pesanan
     */
    public void quantityDecrease(View view) {
        orderQuantity -= 1; // Operator -= berfungsi untuk mengurangi angka pada variabel

        /**
         * - setText berfungsi untuk menyetel teks di View
         * - "" + berfungsi untuk menconvert int menjadi String
         */
        quantityTextView.setText("" + orderQuantity);

        hitungTotalHarga(); // Hitung totalHarga saat tombol "-" ditekan
    }

    /**
     * Ketika tombol plus "+" ditekan, tambah jumlah pesanan
     */
    public void quantityIncrease(View view) {
        orderQuantity += 1; // Operator += berfungsi untuk menambah angka pada variabel

        /**
         * - setText berfungsi untuk menyetel teks di View
         * - "" + berfungsi untuk menconvert int menjadi String
         */
        quantityTextView.setText("" + orderQuantity);

        hitungTotalHarga(); // Hitung totalHarga saat tombol "+" ditekan
    }

    public void orderSend(View view) {
        customerName = customerNameEditText.getText().toString(); // .toString berfungsi merubah Objek EditText menjadi String

        isToppingWhippedCream = toppingWhippedCreamCheckBox.isChecked(); // Set variable berdasarkan status checkbox di View
        isToppingChocolate = toppingChocolateCheckBox.isChecked(); // Set variable berdasarkan status checkbox di View
        isToppingIceCream = toppingIceCreamCheckBox.isChecked(); // Set variable berdasarkan status checkbox di View

        /**
         * Rancang isi email yang akan dikirim di sini
         */
        message = "Photo Coffee pesanan untuk " + customerName;
        message += "\n\nNama: " + customerName;

        if(isToppingWhippedCream || isToppingChocolate || isToppingIceCream) { // Cek apakah menggunakan topping
            message += "\nTopping:";
            if(isToppingWhippedCream) { // Cek apakah menggunakan Whipped Cream
                message += "\n- Whipped Cream";
            }
            if(isToppingChocolate) { // Cek apakah menggunakan Chocolate
                message += "\n- Chocolate";
            }
            if(isToppingIceCream) { // Cek apakah menggunakan Ice Cream
                message += "\n- Ice Cream";
            }
        } else { // Tidak menggunakan topping
            message += "Topping: Tidak ada";
        }

        message += "\nQty: " + orderQuantity;
        message += "\nTotal: Rp" + totalHarga;
        message += "\n\nTerima Kasih!";


        /**
         * Kirim pesanan ke aplikasi EMail menggunakan Intent
         */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Protokol untuk mengirim Email
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"order@photocoffee.com"}); // Alamat Email tujuan
        intent.putExtra(Intent.EXTRA_SUBJECT,"Pesanan"); // Subjek Email
        intent.putExtra(Intent.EXTRA_TEXT, message); // Isi email yang berisi detil pesanan

        startActivity(intent); // Kirim detil pesanan ke aplikasi Email
    }
}
