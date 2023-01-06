package ph.kodego.leones.patricia.ivee.loginapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ph.kodego.leones.patricia.ivee.loginapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_login) == this is the previous way to do it
        setContentView(binding.root)

//        binding.title.setText("Main Page")
//        binding.title.text = "New Main Page"

        binding.btnSubmit.setOnClickListener {

            username = binding.usernameText.text.toString()
            password = binding.passwordText.text.toString()


            Snackbar.make(binding.root,"$username - $password", Snackbar.LENGTH_SHORT).show()

//            Snackbar.make(binding.root,"SUBMIT", Snackbar.LENGTH_SHORT).show()
//            Notif(black)binding.root where the snackbar will show Text is what is the message and Length is how long will it show
//            Snackbar has more functionalities. You can also add other UI components in a Snackbar such as buttons or links to new page


//            Toast.makeText(applicationContext, "Submit", Toast.LENGTH_SHORT).show()
//           applicationContext which app will it show
        //            length short = 3sec duration length long = 5sec duration
        }
    }
}