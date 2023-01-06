package ph.kodego.leones.patricia.ivee.loginapplication

import android.content.Intent
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


//            Snackbar.make(binding.root,"$username - $password", Snackbar.LENGTH_SHORT).show()

//            Snackbar.make(binding.root,"SUBMIT", Snackbar.LENGTH_SHORT).show()
//            Notif(black)binding.root where the snackbar will show Text is what is the message and Length is how long will it show
//            Snackbar has more functionalities. You can also add other UI components in a Snackbar such as buttons or links to new page


//            Toast.makeText(applicationContext, "Submit", Toast.LENGTH_SHORT).show()
//           applicationContext which app will it show
        //            length short = 3sec duration length long = 5sec duration
            var goToHome = Intent(this, HomeActivity::class.java)

//          Intent is intended to move from one activity to another.(in this case it will go to HomeActivity)

            val bundle = Bundle()
            bundle.putString("username",username)
            bundle.putString("password", password)
            goToHome.putExtras(bundle)
//            putExtras(Bundle) makes a new copy to be passed to next activity
//            You pass a group of Bundle putExtras
//            downside of putExtras is it might not be able to pass some of the data

            goToHome.putExtra("something", "Extra")
//            you can specify data type of this to byte integer, arraylist etc. but if you wish not to
//            you should know what data type to put when accessing it to another activity.
//            here you're passing the data as is
//          You're passing single data on putExtra(bundle)
//            Bundles are data stored to be passed between page or activity
//            there are other ways to pass data but this is the one of the most common one

            startActivity(goToHome)
//            This should be below the code above
            finish()
//          finish() is added so that the user can't go back to previous page(for log in)
        }
    }
}