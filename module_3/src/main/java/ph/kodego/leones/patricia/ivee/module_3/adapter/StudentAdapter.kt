package ph.kodego.leones.patricia.ivee.module_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.kodego.leones.patricia.ivee.module_3.databinding.StudentItemBinding
import ph.kodego.leones.patricia.ivee.module_3.model.Student

class StudentAdapter(var students: ArrayList<Student>)
    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    //    add button activity_main.xml
    fun addStudent(student: Student) {
        students.add(0, student)
        notifyItemInserted(0)
    }

    fun removeStudent(position: Int) {
        students.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return students.size
    }

//    override fun dummy() {}

    override fun onCreateViewHolder(
//        Sets layout per line (row)
        parent: ViewGroup,
        viewtype: Int
    ): StudentAdapter.StudentViewHolder {
        val itemBinding = StudentItemBinding //ItemAccountBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: StudentAdapter.StudentViewHolder,
        position: Int
    ) {
//        what's inside each layout (student name)
//        studentViewHolder Has keyword recycler to save memory
        holder.bindStudent(students[position])
//        pass the data to be sent to viewHolder
    }

    inner class StudentViewHolder(private val itemBinding: StudentItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        var student = Student()

        //init will run first before bindstudent
        init {
            itemView.setOnClickListener(this)
        }

        fun bindStudent(student: Student) {
            this.student = student

            itemBinding.studentName.text =
                "${student.lastName}, ${student.firstName}" // or set text
            itemBinding.deleteRowButton.setOnClickListener {
                Snackbar.make(
                    itemBinding.root,
                    "Delete by Button",
                    Snackbar.LENGTH_SHORT
                ).show()

                removeStudent(adapterPosition)
            }
//                to set picture
            itemBinding.profilePicture.setImageResource(student.img)
//                to set picture from internet source
//                Bitmap = new Bit
//                itemBinding.profilePicture.resources
        }

        override fun onClick(p0: View?) {
            Snackbar.make(
                itemBinding.root,
                "${student.lastName},${student.firstName}",
                Snackbar.LENGTH_SHORT
            ).show()

            removeStudent(adapterPosition)
        }
    }
}