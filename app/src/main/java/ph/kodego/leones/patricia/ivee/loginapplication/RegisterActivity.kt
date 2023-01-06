package ph.kodego.leones.patricia.ivee.loginapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.SeekBar
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import ph.kodego.leones.patricia.ivee.loginapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profilePicture.setBackgroundResource(R.drawable.sample)
//        setOnClickListener do something on click
//        R.drawable.sample = Resource.drawable.idName
//        setBackgroundResource to change background for images

        binding.fullNameText.text
//      to get the value of fullnametext
        var value = binding.fullNameText.text

//        to change text
        binding.fullNameText.setText("FULL NAME 2")
//        binding.fullNameText.text = "fullname" == it's supposed to work according to instructor
//        binding.fullNameText.setHint("Full name")
        binding.fullNameText.hint = "Full Name"
//        you can use hint instead of set hint


//        get value of spinner selected item
        var spinnerValue = binding.usertype.selectedItem.toString()

//
        binding.usertype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                var data = binding.usertype.selectedItem
                Snackbar.make(binding.root,"$data", Snackbar.LENGTH_SHORT).show()

//                Snackbar.make(binding.root,"${binding.usertype[position]", Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Snackbar.make(binding.root,"No change in the choice", Snackbar.LENGTH_SHORT).show()
            }

        }

        ArrayAdapter.createFromResource(
            this,
            R.array.usertype2,
            android.R.layout.simple_spinner_item
        ).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.userSubType.adapter = adapter
        }

        var locations = arrayOf("Marikina", "Makati", "Mandaluyong", "Taguig", "Rizal" )
//        programmatically
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            locations
        ).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.location.adapter = adapter
        }
//        Array Adapter is another way to set value of UI components (material


        binding.seek.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    if (seekBar !=null) {
                        if (progress > 50 && progress < seekBar.max) {
                            Snackbar.make(binding.root, "Seek : ${progress}", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Snackbar.make(binding.root, "Seek : start", Snackbar.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Snackbar.make(binding.root, "Seek : stop", Snackbar.LENGTH_SHORT).show()
            }
        })


//        binding.rating.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener {}
        binding.btnRegister.setOnClickListener {
            var ratingValue = binding.rating.rating
            var seekValue = binding.seek.progress
//            Snackbar.make(binding.root, "Rating : ${ratingValue}", Snackbar.LENGTH_SHORT).show()
            Snackbar.make(binding.root, "Seek : ${seekValue}", Snackbar.LENGTH_SHORT).show()
        }
    }
}