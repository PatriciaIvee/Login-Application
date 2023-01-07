package ph.kodego.leones.patricia.ivee.module_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.kodego.leones.patricia.ivee.module_2.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val intent = Intent()
            intent.putExtra("username", binding.registerUsernameText.text.toString())
            setResult(1,intent)
//            the purpose of intent here is to contain data
//            result can either be binary (1 means success and 0 means failed) and there is supposed to be success(text) result
            finish()
        }
    }
}