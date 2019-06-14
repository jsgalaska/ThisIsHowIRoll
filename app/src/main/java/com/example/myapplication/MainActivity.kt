package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Setup the spinner adn its adapter
        val diceTypes: IntArray = intArrayOf(4, 6, 8, 10, 12, 20)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter<String>(this, R.layout.spinner_item)

        adapter.setDropDownViewResource(R.layout.spinner_item)
        spinner!!.setAdapter(adapter)
        diceTypes.forEach { adapter.add(it.toString()) }

        //Set listener for when a user selects an item it the spinner dropdown
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // Reset die roll value on the screen when a new item is selected from the spinner dropdown
                resetDiceResult()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do Nothing
            }
        }

        // Automatically set the roll result to the max roll when the Roll button is long pressed
        val rollBtn = findViewById<Button>(R.id.btnRoll)
        rollBtn.setOnLongClickListener {view ->
            autoMaxRoll(view)
        }

    }

    // Set the die roll value to the max value of the selected die
    fun autoMaxRoll(view: View): Boolean {
        val rollValueText = findViewById<TextView>(R.id.rollValueDisplay)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val dieSelection = spinner.selectedItem.toString()

        rollValueText.setText(dieSelection)

        return true
    }

    // Get a random roll of the selected die and display the result
    fun roll(view: View)
    {
        val rollValueText = findViewById<TextView>(R.id.rollValueDisplay)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val dieSelection = spinner.selectedItem.toString()

        var result = Math.ceil((Math.random() * dieSelection.toInt())).toInt()

        rollValueText.setText(result.toString())
    }

    // Reset the die roll value on the screen
    fun resetDiceResult()
    {
        val rollValueText = findViewById<TextView>(R.id.rollValueDisplay)
        rollValueText.setText("")
    }

}
