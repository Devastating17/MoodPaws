package com.example.moodpaws

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var selectedMood: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mood_entry_layout) // Link to your XML file

        val emojiViews = mapOf(
            "Happy" to findViewById<TextView>(R.id.emojiHappy),
            "Neutral" to findViewById<TextView>(R.id.emojiNeutral),
            "Sad" to findViewById<TextView>(R.id.emojiSad),
            "Angry" to findViewById<TextView>(R.id.emojiAngry),
            "Excited" to findViewById<TextView>(R.id.emojiExcited)
        )

        val noteInput = findViewById<EditText>(R.id.noteInput)
        val saveButton = findViewById<Button>(R.id.saveMoodButton)

        // Emoji click listeners
        emojiViews.forEach { (mood, textView) ->
            textView.setOnClickListener {
                selectedMood = mood
                Toast.makeText(this, "$mood selected!", Toast.LENGTH_SHORT).show()
                // Optional: visually highlight selected emoji
                emojiViews.values.forEach { it.setBackgroundResource(0) }
                textView.setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
            }
        }

        // Save button click
        saveButton.setOnClickListener {
            val note = noteInput.text.toString()
            if (selectedMood == null) {
                Toast.makeText(this, "Please select a mood", Toast.LENGTH_SHORT).show()
            } else {
                // You can now save to database or file
                Toast.makeText(this, "Mood saved: $selectedMood\nNote: $note", Toast.LENGTH_LONG).show()
                // Reset UI (optional)
                selectedMood = null
                noteInput.text.clear()
                emojiViews.values.forEach { it.setBackgroundResource(0) }
            }
        }
    }
}
