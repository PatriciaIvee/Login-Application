package ph.kodego.leones.patricia.ivee.module_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.kodego.leones.patricia.ivee.module_2.databinding.StudentItemBinding
import ph.kodego.leones.patricia.ivee.module_2.model.Student

class StudentAdapter(var students: ArrayList<Student>)
    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun getItemCount(): Int {
        return students.size
    }

//    override fun dummy() {}

    override fun onCreateViewHolder(
//        Sets layout per line (row)
        parent: ViewGroup,
        viewtype: Int
    ):StudentAdapter.StudentViewHolder {
         val itemBinding = StudentItemBinding //ItemAccountBinding
             .inflate(
                 LayoutInflater.from(parent.context),
                 parent,false)
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder :StudentAdapter.StudentViewHolder,
    position:Int){
//        what's inside each layout (student name)
//        studentViewHolder Has keyword recycler to save memory
        holder.bindStudent(students[position])
//        pass the data to be sent to viewHolder
    }

    inner class StudentViewHolder(private val itemBinding: StudentItemBinding)
        :RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener{

            var student = Student()

            fun bindStudent(student:Student){
                this.student = student

                itemBinding.studentName.text ="${student.lastname}, ${student.firstname}" // or set text
            }

            override fun onClick(p0: View?) {
            TODO("Not yet implemented")
            }

    }
// the purpose of ViewHolder is to bind ui components,
    // behavior of each rows and data inside each rows organize the data where they will be placed
}
