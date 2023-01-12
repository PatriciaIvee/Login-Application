package ph.kodego.leones.patricia.ivee.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import ph.kodego.leones.patricia.ivee.fragments.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragmentTransaction:FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_holder,FirstFragment())
        fragmentTransaction.commit()

        var fragmentTransaction2 :FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction2.replace(R.id.fragment_holder2,SecondFragment())
        fragmentTransaction2.commit()
    }
//    fragmentTransaction is the old school way of using Fragments in Android Studio
}