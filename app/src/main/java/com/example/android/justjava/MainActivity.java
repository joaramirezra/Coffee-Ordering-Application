package com.example.android.justjava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends Activity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    private void displayMessage(String Message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(Message);
    }
    public void submitOrder(View view) {
        int price = quantity*5;
        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();
        if(value.isEmpty()){
            Toast.makeText(this,"Please Enter Your Name ",Toast.LENGTH_SHORT).show();
            return ;
        }
        display( quantity);
        displayPrice(quantity*5);
        CheckBox haschecked = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean addwhipped = haschecked.isChecked();
        if(addwhipped){
            price+=1*quantity;
        }
        CheckBox haschecked1 = (CheckBox) findViewById(R.id.chocolate_cream_checked);
        boolean addchocolate = haschecked1.isChecked();
        if(addchocolate){
            price+=2*quantity;
        }
        String message = "Name : " + value;
        message+= "\nQuantity : " + quantity;
        message+= "\nAdd Whipped Cream ? :" + addwhipped;
        message+= "\nAdd Chocolate Cream ? : "+addchocolate;
        message+= "\nPrice : " + price;
        message+= "\nThank You!";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"just java order of "+ value);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        displayMessage(message);
    }

    public void increment(View view) {
        if(quantity==100){
            Toast.makeText(this,"You can not have more than 100 coffee at a time ",Toast.LENGTH_SHORT).show();
            return ;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity==1){
            Toast.makeText(this,"You can not have less than 1 coffee ",Toast.LENGTH_SHORT).show();
            return ;
        }
        quantity = quantity - 1;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}