/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        EditText theirName = (EditText) findViewById(R.id.name_of_person);
        String nameOrdering = theirName.getText().toString();
        String subject = "JustJava order for " + nameOrdering;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private String createOrderSummary(int price) {
        EditText theirName = (EditText) findViewById(R.id.name_of_person);
        String nameOrdering = theirName.getText().toString();
        CheckBox isWhippedCream = (CheckBox) findViewById(R.id.check_whipped_cream);
        boolean hasWhippedCream = isWhippedCream.isChecked();
        CheckBox isChocolate = (CheckBox) findViewById(R.id.check_chocolate);
        boolean hasChocolate = isChocolate.isChecked();
        String orderSummary = "Name: " + nameOrdering;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary += "\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank you!";
        return orderSummary;
    }


    private int calculatePrice() {
        int basePrice = 5;
        CheckBox isWhippedCream = (CheckBox) findViewById(R.id.check_whipped_cream);
        boolean hasWhippedCream = isWhippedCream.isChecked();
        CheckBox isChocolate = (CheckBox) findViewById(R.id.check_chocolate);
        boolean hasChocolate = isChocolate.isChecked();
        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }
        int price = basePrice * quantity;
        return price;
    }

    public void increment(View view) {
        if (quantity < 100) {
            quantity += 1;
        } else {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);

    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
        } else {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }


}