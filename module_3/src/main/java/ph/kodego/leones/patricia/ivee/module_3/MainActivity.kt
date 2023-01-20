package ph.kodego.leones.patricia.ivee.module_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.leones.patricia.ivee.module_3.adapter.StudentAdapter
import ph.kodego.leones.patricia.ivee.module_3.dao.StudentDAO
import ph.kodego.leones.patricia.ivee.module_3.dao.StudentDAOSQLImpl
import ph.kodego.leones.patricia.ivee.module_3.databinding.ActivityMainBinding
import ph.kodego.leones.patricia.ivee.module_3.model.Student
import ph.kodego.leones.patricia.ivee.module_3.model.SwipeCallBack

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private var students: ArrayList<Student> = ArrayList()
    private lateinit var itemTouchHelper: ItemTouchHelper

    private lateinit var dao:StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        init()
        dao = StudentDAOSQLImpl(applicationContext)
        students = dao.getStudents()


        studentAdapter = StudentAdapter(students)
        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        binding.list.adapter = studentAdapter

         binding.addStudentButton.setOnClickListener {
            studentAdapter.addStudent(Student(
                binding.studentFirstName.text.toString(),
                binding.studentLastName.text.toString(),
                R.drawable.photo_placeholder))
        }

        var swipeCallBack = SwipeCallBack(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallBack.studentAdapter = studentAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallBack)
        itemTouchHelper.attachToRecyclerView(binding.list)

    }

    fun init(){
        students.add(Student("Dave", "Navor",R.drawable.photo_placeholder))
        students.add(Student("Victor", "Yu", R.drawable.photo_placeholder))
        students.add(Student("JP", "Soriano",R.drawable.photo_placeholder))
        students.add(Student("CJ", "Tronco",R.drawable.photo_placeholder))
        students.add(Student("Rene", "Palma",R.drawable.photo_placeholder))
        students.add(Student("Joni", "James",R.drawable.photo_placeholder))
        students.add(Student("Janreign","Aragon",R.drawable.photo_placeholder))
        students.add(Student("John Rey", "Balais",R.drawable.photo_placeholder))
        students.add(Student("James Nico", "Rara",R.drawable.photo_placeholder))
        students.add(Student("Pat Ivee", "Leones",R.drawable.photo_placeholder))
        students.add(Student("Matthew", "Mottos",R.drawable.photo_placeholder))
    }
}