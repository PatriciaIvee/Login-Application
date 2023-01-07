package ph.kodego.leones.patricia.ivee.module_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.leones.patricia.ivee.module_2.adapter.StudentAdapter
import ph.kodego.leones.patricia.ivee.module_2.databinding.ActivityMainBinding
import ph.kodego.leones.patricia.ivee.module_2.model.Student

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private var students: ArrayList<Student> = ArrayList()


//    init {
////        init()
////        you can call init here
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        studentAdapter = StudentAdapter(students)
        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        binding.list.adapter = studentAdapter
//        The purpose of Adapter is to be able to put data inside the recycler view (items)

//        you can also call init here
//        for organizational purposes you can add init on line 14 depending on the situation
//        in this case it is used to set up data so it was decided to put init inside line 13
    }

    fun init(){
        students.add(Student("Dave", "Navor"))
        students.add(Student("Victor", "Yu"))
        students.add(Student("JP", "Soriano"))
        students.add(Student("CJ", "Tronco"))
        students.add(Student("Rene", "Palma"))
        students.add(Student("Joni", "James"))
        students.add(Student("Janreign","Aragon"))
        students.add(Student("John Rey", "Balais"))
        students.add(Student("James Nico", "Rara"))
        students.add(Student("Pat Ivee", "Leones"))
        students.add(Student("Matthew", "Mottos"))
    }
}