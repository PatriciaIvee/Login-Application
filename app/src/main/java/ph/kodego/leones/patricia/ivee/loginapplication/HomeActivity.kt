package ph.kodego.leones.patricia.ivee.loginapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ph.kodego.leones.patricia.ivee.loginapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private final var LOGINFO = "HOMEACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        Log.d(LOGINFO,"USERNAME: ${bundle!!.getString("username")}")
        Log.d(LOGINFO,"PASSWORD: ${bundle!!.getString("password")}")
//        bundle should be not null to avoid missing data
//        NEVER USE PRINTLN use Log.d to restrict who can see things happening in the app
//        Log.d means debug
//        Log.i means anyone can see it
//        Log cat is used for printing output

        val extra = intent.getStringExtra("something")
        Log.d(LOGINFO, "SOMETHING : $extra")
    }
}