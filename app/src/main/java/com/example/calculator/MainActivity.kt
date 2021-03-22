package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Calculator with basic arithmetic operation capacity.
 *
 * @author Bridget Black
 * 2021-03-21
 * Last Updated: 2021-03-21
 */
class MainActivity : AppCompatActivity() {
    /*
    By default for Kotlin, properties and variables can not be null - to guard against null-pointer
    exceptions. They also can not be uninitialized. The declarations + initializations:
    private var result: EditText = null
    private var result: EditText
    are not allowable in Kotlin. The problem can be circumvented with:
    private var result: EditText? = null //no null-pointer exception protection from Kotlin
    or with making the property/variable abstract or with lateinit (only usable with read and
    write properties AKA var).
     */
//    private lateinit var result: EditText
//    private lateinit var newNumber: EditText
//    private lateinit var displayOperations: TextView
    /*
    By lazy = defining a function that will be called to assign the value to the property. The
    function is called the first time the property is accessed, then the value is cached to prevent
    future function calls.
    Use DisplayOperation after onCreate has been called.
    Can be used with val for immutable properties.
     */
//    private val result by lazy {findViewById<EditText>(R.id.editTextNumberSigned_result)}
//    private val newNumber by lazy {findViewById<EditText>(R.id.editTextNumberSigned_new_input)}
//    private val displayOperation by lazy {findViewById<TextView>(R.id.textView_operation)}
    /*
    Lazy function is thread safe, but the Activity MainActivity can only be accessed via one thread
    so we can remove the "extra baggage" of thread safe code from the Lazy function.
     */
    private val result by lazy(LazyThreadSafetyMode.NONE) { findViewById<EditText>(R.id.editTextNumberSigned_result) }
    private val newNumber by lazy(LazyThreadSafetyMode.NONE) { findViewById<EditText>(R.id.editTextNumberSigned_new_input) }
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.textView_operation) }

    /*
    Variables to hold Operands and type of calculations.
    Declaration of operand1 shows that it can be null via the '?' after the Data Type.
    We want operand1 to be null capable b/c there isn't an operand when the app first starts up.
     */
    private var operand1: Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="

    /**
     * Create saved state. TODO update description with added functionality
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Definitions for the data input buttons.
        val button0: Button = findViewById(R.id.button_0) // Specify the type of Widget
        the "found view" will be saved into.

        val button0 = findViewById<Button>(R.id.button_0) // Specify the Generic Type
        of view that will be found.
         */
        val button0: Button = findViewById(R.id.button_0)
        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)
        val buttonDivide: Button = findViewById(R.id.button_divide)
        val buttonMultiply: Button = findViewById(R.id.button_multiply)
        val buttonMinus: Button = findViewById(R.id.button_minus)
        val buttonPlus: Button = findViewById(R.id.button_plus)
        val buttonEquals: Button = findViewById(R.id.button_equals)
        val buttonDecimal: Button = findViewById(R.id.button_decimal)

        /*
        Apply the same listener to multiple buttons.
        Listener needs a name for this.

        Variable listener holds reference to new Instance of OnClickListener, that instance's
        method OnClick reads the 'caption' of the clicked Button (who's reference was passed to
        the method) via it's text property and appends the value to the value held by
        newNumber's EditText view.
        Since not all Widgets/Views have a text property, we cast the value to a View that does
        have a text property, we choose to cast it as a Button because we are attaching this
        listener to Buttons.
         */
        val listener = View.OnClickListener { v ->
            val btn = v as Button
            newNumber.append(btn.text)
        }
        // Could set up a loop to go through these but only 11 is easy enough.
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDecimal.setOnClickListener(listener)

        /*
        Create another new instance of OnClickListener and assign it's reference to the variable
        'opListener'.
        Cast the view as a Button then read the text and assign it to the String 'op'.
        Reads the number from the newNumber EditText Widget, if there IS text inside the Widget
        to be read then we call performOperation.
        Whether performOperation is called or not, we then update our pendingOperation and display
        it for the user to see as they input their next value.
        Assign this listener to the five operations Buttons.
         */
        val operationListener = View.OnClickListener { v ->
            val operation = (v as Button).text.toString()
            val value = newNumber.text.toString()
            if (value.isNotEmpty()) {
                performOperation(value, operation)
            }
            pendingOperation = operation
            displayOperation.text = pendingOperation
        }
        // Could set up a loop to go through these but only 5 is easy enough.
        buttonEquals.setOnClickListener(operationListener)
        buttonDivide.setOnClickListener(operationListener)
        buttonMultiply.setOnClickListener(operationListener)
        buttonPlus.setOnClickListener(operationListener)
        buttonMinus.setOnClickListener(operationListener)
    }

    private fun performOperation(value: String, operation: String) {
        displayOperation.text = operation
    }
}