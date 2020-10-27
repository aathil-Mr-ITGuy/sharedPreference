package com.aathil.sharedpreference.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aathil.sharedpreference.R
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPrefernceActivity : AppCompatActivity() {

    //define shared preference file name
    private val sharedFile = "sharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)


        //define shared preference

        val sharedPreference: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()

        //save button
        btnSave.setOnClickListener(View.OnClickListener {
            //define editor


            //get the input values
            val id: Int= Integer.parseInt(userId.text.toString())
            val name = userName.text.toString()

            //put into shared preference

            editor.putInt("userId", id)
            editor.putString("userName", name)

            //apply the changes
            editor.apply()

            //commit the changes
            editor.commit()

        })

        //view button
        btnView.setOnClickListener(View.OnClickListener {
            //set default values

            val sharedInt = sharedPreference.getInt("userId", 0)
            val sharedName = sharedPreference.getString("userName", "defaultName")

            if(sharedInt != 0 && !sharedName.equals("defaultName")){
                nameIs.text = "Your Name: ${sharedName.toString()}"
                idIs.text = "Your ID: ${sharedInt.toString()}"
            }
        })

        //clear button
        btnClear.setOnClickListener(View.OnClickListener {
            //clear the memory
            editor.clear()
            //apply the changes
            editor.apply()
            nameIs.text = "Your Name: "
            idIs.text = "Your ID: "
        })
    }
}