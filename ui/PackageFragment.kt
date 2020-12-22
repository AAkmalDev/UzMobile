package uz.pdp.uzmobile.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_package.view.*
import kotlinx.android.synthetic.main.item_pac_dialog.view.*
import uz.pdp.uzmobile.R
import uz.pdp.uzmobile.adapters.PackageAdapter
import uz.pdp.uzmobile.models.PackageModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class PackageFragment : Fragment() {
    private var param1: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }
    private lateinit var root: View
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var packageList: ArrayList<PackageModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_package, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference(param1!!)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                packageList = ArrayList()
                for (child in snapshot.children) {
                    val value = child.getValue(PackageModel::class.java) as PackageModel
                    packageList.add(value)
                }
                root.recycler_pac.adapter = PackageAdapter(packageList,object :PackageAdapter.onPackageClikItemListener{
                    override fun onClikListener(packageModel: PackageModel) {
                        var dialog = AlertDialog.Builder(root.context)
                        var alertView = LayoutInflater.from(root.context).inflate(R.layout.item_pac_dialog,null,false)
                        dialog.setView(alertView)
                        alertView.dialog_text_name.text = packageModel.sname
                        alertView.dialog_text_desc.text = packageModel.sdesc
                        alertView.btn_activ.setOnClickListener {
                            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + Uri.encode(packageModel.scode))))
                        }
                        dialog.setPositiveButton("Orqaga",object :DialogInterface.OnClickListener{
                            override fun onClick(p0: DialogInterface?, p1: Int) {

                            }
                        })
                        dialog.show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Bootcamp_2",error.message)
            }
        })


        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            PackageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}