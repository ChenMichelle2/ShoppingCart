package com.example.shoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingcart.ui.theme.ShoppingCartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                ShoppingCartScreen()
            }
        }
    }
}
data class CartItem(
    val name: String,
    val price: Double,
    val quantity: Int
)
@Composable
fun ShoppingCartScreen(){
    val cartItems = listOf(
        CartItem(name = "Apple", price = 5.0, quantity = 3),
        CartItem(name = "Eggs", price = 10.0, quantity = 5),
        CartItem(name = "Bananas", price = 6.0, quantity = 2),
    )
    val totalCost = cartItems.sumOf { it.price * it.quantity }
    var showSnackbar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Shopping cart item list
        Text(
            text = "Shopping Cart",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
        )

        //display item
        cartItems.forEach{item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.name, fontSize = 16.sp)
                Text(text = "Price: $${item.price}", fontSize = 16.sp)
                Text(text = "Qty: ${item.quantity}", fontSize = 16.sp)
            }
    }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
    //Summary section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //total cost
            Text(
            text = "Total: $${String.format("%.2f", totalCost)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
            )
            //checkout button
            Button(
                onClick = {showSnackbar = true},
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Checkout")
            }
        }
        //Snackbar
        if (showSnackbar) {
            Snackbar(
                modifier = Modifier.padding(top = 16.dp),

            ){
                Text(
                    text = "Order Placed"
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingCartTheme {
        ShoppingCartScreen()
    }}
}