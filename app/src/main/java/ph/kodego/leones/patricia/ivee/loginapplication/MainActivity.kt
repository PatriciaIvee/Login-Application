package ph.kodego.leones.patricia.ivee.loginapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.kodego.leones.patricia.ivee.loginapplication.databinding.ActivityLoginBinding
import ph.kodego.leones.patricia.ivee.loginapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    latest version to display things in android studio
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}