package ph.kodego.leones.patricia.ivee.module_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.leones.patricia.ivee.module_2.adapter.StudentAdapter
import ph.kodego.leones.patricia.ivee.module_2.databinding.ActivityMainBinding
import ph.kodego.leones.patricia.ivee.module_2.model.Student
import ph.kodego.leones.patricia.ivee.module_2.model.SwipeCallBack

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private var students: ArrayList<Student> = ArrayList()
    private lateinit var itemTouchHelper: ItemTouchHelper


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
//        You can also use Grid Layout
//        binding.list.layoutManager = GridLayoutManager(applicationContext, 2)
//        span count means like columns or  how many item in a row.
        binding.list.adapter = studentAdapter
//        The purpose of Adapter is to be able to put data inside the recycler view (items)

//        you can also call init here
//        for organizational purposes you can add init on line 14 depending on the situation
//        in this case it is used to set up data so it was decided to put init inside line 13

//        add student button in activity_main.xml
        binding.addStudentButton.setOnClickListener {
            studentAdapter.addStudent(Student(
                binding.studentFirstName.text.toString(),
                binding.studentLastName.text.toString(),
                R.drawable.photo_placeholder))
        }

//        Swipe the recyclerview (individual row)
        var swipeCallBack =SwipeCallBack(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
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