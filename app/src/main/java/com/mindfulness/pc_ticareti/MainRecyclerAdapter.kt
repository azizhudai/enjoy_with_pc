package com.mindfulness.pc_ticareti

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

//(//private val userIdArray: ArrayList<String>,
//    //private val projectIdArray: ArrayList<String>,
//    private val projectName: ArrayList<Project>,
//    private val mCtx: Context
//)
class MainRecyclerAdapter
constructor(context: Context, objects: MutableList<Project>, val clickListener: (Project) -> Unit) :
    RecyclerView.Adapter<MainRecyclerAdapter.PostHolder>() {

    /*(
       // val context: Context,
        var problems:
        MutableList<Project> ):
        //var projectList: MutableList<Project?>,
     RecyclerView.Adapter<MainRecyclerAdapter.PostHolder>(){*/
    //MainRecyclerAdapter.PostHolder {
    private var context = context
    private var mObjects: MutableList<Project> = objects// = MutableList<Project>()
    var clickStatus: MutableLiveData<HashMap<String, Any>> = MutableLiveData()

    // var onItemClick: ((Project) -> Unit)? = null
    //var contacts: List<Project> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_project_row, parent, false)
        return PostHolder(view)
    }

    /*override fun getItemCount(): Int {
        return objects.size
    }
*/
    /*fun removeItem(position: Int) {
        projectName.removeAt(position)
        notifyDataSetChanged()
    }*/

    /*override fun onBindViewHolder(holder: ProjectRecyclerAdapter.PostHolder, position: Int) {

        // holder.rvProjectName?.text = projectName.get(position).projectName

        holder.buttonViewOption!!.setOnClickListener(View.OnClickListener {
            //creating a popup menu
            //creating a popup menu
            val popup = PopupMenu(mCtx, holder.buttonViewOption)
            //inflating menu from xml resource
            //inflating menu from xml resource
            popup.inflate(R.menu.rv_options_menu)
            //adding click listener
            //adding click listener
            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    when (item.getItemId()) {
                        R.id.menu1 -> {
                            editClickStatus.value = hashMapOf(
                                "pid" to projectName.get(position).projectId,
                                "statu" to true
                            )
                        }
                        R.id.menu2 -> {
                            deleteClickStatus.value = hashMapOf(
                                "pid" to projectName.get(position).projectId,
                                "position" to position,
                                "pname" to projectName.get(position).projectName,
                                "statu" to true
                            )
                        }

                    }
                    return false
                }
            })
            //displaying the popup
            //displaying the popup
            popup.show()

        })
    }*/

    class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
        //View Holder class
        var rvProjectName: TextView? = null
        var rv_project_name_text: TextView? = null
        var card_viewProjectList: CardView? = null

        init {
            rvProjectName = view.findViewById(R.id.rv_project_name_text)
            //rv_project_name_text = view.findViewById(R.id.rv_project_name_text)
            card_viewProjectList = view.findViewById(R.id.card_viewProjectList)

        }

    }


    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        var item: Project = mObjects[position]
        //val project= problems[position]
        holder.rvProjectName?.text = item.projectName
        //projectList = project


        holder.rvProjectName!!.setOnClickListener {

            holder?.rvProjectName?.setOnClickListener { clickListener(item) }
            Log.e("TAGGG", "asda")
            //onItemClick?.invoke(item[position])
            clickStatus.value = hashMapOf(
                "pid" to item.projectId,
                "position" to position,
                "pname" to item.projectName,
                "statu" to true
            )
        }
    }

    override fun getItemCount(): Int {
        return mObjects.size
    }
}

