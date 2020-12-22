package uz.pdp.uzmobile.ui

import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import iammert.com.expandablelib.ExpandCollapseListener
import iammert.com.expandablelib.ExpandableLayout
import iammert.com.expandablelib.Section
import kotlinx.android.synthetic.main.fragment_slideshow.view.*
import uz.pdp.uzmobile.MainActivity
import uz.pdp.uzmobile.R
import uz.pdp.uzmobile.models.UssdModel

class SlideshowFragment : Fragment() {
    private lateinit var root: View
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var database: DatabaseReference
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        database = firebaseDatabase.getReference("ussd/")

        (activity as MainActivity).supportActionBar?.title = getString(R.string.ussd)
        val expanble = root.expanble

        expanble.setRenderer(object : ExpandableLayout.Renderer<UssdModel,String>{
            override fun renderParent(
                view: View?,
                model: UssdModel?,
                isExpanded: Boolean,
                parentPosition: Int
            ) {
                view?.findViewById<TextView>(R.id.ussd_item_text)?.text = model?.desc
                view?.findViewById<TextView>(R.id.ussd_item_img)?.text = model?.code
            }

            override fun renderChild(
                view: View?,
                model: String?,
                parentPosition: Int,
                childPosition: Int
            ) {
                view?.findViewById<Button>(R.id.ussd_child_item_connect_btn)?.text = "Ulanish"
                view?.findViewById<Button>(R.id.ussd_child_item_connect_btn)?.setOnClickListener {
                    startActivity(Intent(ACTION_DIAL, Uri.parse("tel: " + Uri.encode(model))))
                }
            }
        })

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val value = child.getValue(UssdModel::class.java) as UssdModel
                    val section = Section<UssdModel, String>()
                    section.parent = value
                    section.children.add(value.code)
                    expanble.addSection(section)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Bootcamp_1", error.message)
            }
        })
        expanble.setExpandListener(object :ExpandCollapseListener.ExpandListener<UssdModel>{
            override fun onExpanded(parentIndex: Int, parent: UssdModel?, view: View?) {
                view?.findViewById<ImageView>(R.id.ussd_item_arrow_id)?.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            }
        })
        expanble.setCollapseListener(object :ExpandCollapseListener.CollapseListener<UssdModel>{
            override fun onCollapsed(parentIndex: Int, parent: UssdModel?, view: View?) {
                view?.findViewById<ImageView>(R.id.ussd_item_arrow_id)?.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            }
        })
        return root
    }
}