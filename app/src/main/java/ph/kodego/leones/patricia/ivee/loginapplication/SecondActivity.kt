package ph.kodego.leones.patricia.ivee.loginapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var title: TextView
//    this is what is done before
//    You declare the thing parameter (ex.TextView) id
//     android:id="@+id/title" to set something
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = findViewById(R.id.title)
//    this is java style/ old school
        title.setText("New Second Activity")
//    this is the new way (kotlin style)
//    title.text = "New Second Activity"
//    both are fine to use
//    Not so efficient if you have more textView etc.
    }
}