package ph.kodego.leones.patricia.ivee.module_2.adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.kodego.leones.patricia.ivee.module_2.R
import ph.kodego.leones.patricia.ivee.module_2.dao.DatabaseHandler
import ph.kodego.leones.patricia.ivee.module_2.databinding.DialogueUpdateStudentBinding
import ph.kodego.leones.patricia.ivee.module_2.databinding.StudentItemBinding
import ph.kodego.leones.patricia.ivee.module_2.model.Student

class StudentAdapter(var students: ArrayList<Student>, var activity: Activity)
    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

//    add button activity_main.xml
    fun addStudent(student:Student){
        students.add(0,student)
        notifyItemInserted(0)
    }

    fun removeStudent(position: Int){
        students.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateStudents(newStudents:ArrayList<Student>){
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()

    }

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
//init will run first before bindstudent
            init {
                itemView.setOnClickListener(this)
            }

            fun bindStudent(student:Student){
                this.student = student

                itemBinding.studentName.text ="${student.lastName}, ${student.firstName}" // or set text
                itemBinding.deleteRowButton.setOnClickListener{
                    Snackbar.make(itemBinding.root,
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
                Snackbar.make(itemBinding.root,
                    "${student.lastName},${student.firstName}",
                    Snackbar.LENGTH_SHORT
                ).show()

//                removeStudent(adapterPosition)
//                showAlertDialogue()
                showCustomDialogue()
//                showCustomDialogue2()
            }

            private fun showCustomDialogue(){
//                Doesn't Work Code from Announcement
//                return activity?.let {
//                    val builder = AlertDialog.Builder(it)
//                    val inflater = activity.layoutInflater;
//
//                    builder.setView(inflater.inflate(R.layout.dialogue_update_student,null))
//                        .setPositiveButton("Update",
//                        DialogInterface.OnClickListener { dialog, id ->
//                            // sign in the user ...
//                        })
//                        .setNegativeButton("Cancel",
//                            DialogInterface.OnClickListener { dialog, id ->
////                            getDialog().cancel()
//                            })
//                    builder.create()
//                }?: throw IllegalStateException("Activity cannot be null")


//                Code ni sir During lecture pero di working
//                var customDialogueBinding:DialogueUpdateStudentBinding
//                val inflater = activity.layoutInflater
//                val builder = AlertDialog.Builder(activity)
//
//                customDialogueBinding  = DialogueUpdateStudentBinding.inflate(inflater)
//
//                val customDialogView = inflater.inflate(R.layout.dialogue_update_student,null)
//
//                builder.setView(customDialogueBinding.root)
//                builder.create()
//                builder.show()

//                Code after comparing James Dave's code and First code ni sir with AI
                val dialogueBinding:DialogueUpdateStudentBinding =
                    DialogueUpdateStudentBinding.inflate(LayoutInflater.from(activity))
                val alertDialog = AlertDialog.Builder(activity).setView(dialogueBinding.root).create()
                    alertDialog.show()

            }

            private fun showCustomDialogue2(){
                var customDialogueBinding:DialogueUpdateStudentBinding
                val inflater = activity.layoutInflater
                val builder = AlertDialog.Builder(activity)

                customDialogueBinding  = DialogueUpdateStudentBinding.inflate(inflater)

                val customDialogView = inflater.inflate(R.layout.dialogue_update_student,null)

                builder.setView(customDialogueBinding.root)
                builder.create()
                builder.show()


            }

//        Dialogue box programmatically
//            private fun showAlertDialogue(){
////                Where will the dialogue show (current activity in loaded)
//                val alertDialog = AlertDialog.Builder(activity)
//                alertDialog.apply{
////
////                    Can put an icon
//                    setIcon(R.drawable.photo_placeholder)
////                    You can put title and message
//                    setTitle("Student Background")
//                    setMessage("Student Record")
////                    You can put up to three buttons
//                    setPositiveButton("Positive"){ _, _ ->
//                        toast("clicked positive Button")
//
//                    }
//                    setNegativeButton("Negative"){ _, _ ->
//                        toast("clicked negative Button")
//                    }
//                    setNeutralButton("Neutral") { _, _ ->
//                        toast("clicked Neutral Button")
//                    }
//                } .create().show()
//
//            }


            private fun toast(text:String) = Toast.makeText(activity.applicationContext,text, Toast.LENGTH_SHORT).show()
    }
// the purpose of ViewHolder is to bind ui components,
    // behavior of each rows and data inside each rows organize the data where they will be placed
}
