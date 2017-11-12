package com.photocoffee.photocoffee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String customerName;
    boolean isToppingWhippedCream;
    boolean isToppingChocolate;
    boolean isToppingIceCream;
    int orderQuantity = 0;
    String message;

    EditText customerNameEditText;
    CheckBox toppingWhippedCreamCheckBox;
    CheckBox toppingChocolateCheckBox;
    CheckBox toppingIceCreamCheckBox;
    TextView quantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerNameEditText = (EditText)findViewById(R.id.customer_name);
        toppingWhippedCreamCheckBox = (CheckBox)findViewById(R.id.topping_whippedcream);
        toppingChocolateCheckBox = (CheckBox)findViewById(R.id.topping_chocolate);
        toppingIceCreamCheckBox = (CheckBox)findViewById(R.id.topping_icecream);
        quantityTextView = (TextView)findViewById(R.id.quantity_number);
    }

    public void quantityDecrease(View view) {
        orderQuantity -= 1;

        quantityTextView.setText("" + orderQuantity);
    }

    public void quantityIncrease(View view) {
        orderQuantity += 1;

        quantityTextView.setText("" + orderQuantity);
    }

    public void orderSend(View view) {
        customerName = customerNameEditText.getText().toString();
        isToppingWhippedCream = toppingWhippedCreamCheckBox.isChecked();
        isToppingChocolate = toppingChocolateCheckBox.isChecked();
        isToppingIceCream = toppingIceCreamCheckBox.isChecked();

        message = customerName;
        message += "\nTopping:";
        if(isToppingWhippedCream) {
            message += "\n- Whipped Cream";
        }
        if(isToppingChocolate) {
            message += "\n- Chocolate";
        }
        if(isToppingIceCream) {
            message += "\n- Ice Cream";
        }
        message += "\nQty: " + orderQuantity;


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,
                new String[] {"order@photocoffee.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,
                "Pesanan");
        intent.putExtra(Intent.EXTRA_TEXT,
                message);
        startActivity(intent);
    }
}
