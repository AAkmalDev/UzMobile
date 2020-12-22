package uz.pdp.uzmobile.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tariff.view.*
import uz.pdp.uzmobile.MainActivity
import uz.pdp.uzmobile.R
import uz.pdp.uzmobile.adapters.TariffRvAdapter
import uz.pdp.uzmobile.models.TrafficModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class TariffFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var root: View
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var tariffList: ArrayList<TrafficModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_tariff, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("tarif/")
        (activity as MainActivity).supportActionBar?.title = getString(R.string.tarif)
        tariffList = ArrayList()
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val value = child.getValue(TrafficModel::class.java)
                    tariffList.add(value!!)

                }
                root.rv.adapter = TariffRvAdapter(tariffList,object :TariffRvAdapter.onItemClickListener{
                    override fun onClick(trafficModel: TrafficModel) {
                        val bundle = Bundle()
                        bundle.putSerializable("tariff",trafficModel)
                        Navigation.findNavController(root).navigate(R.id.mainFragment,bundle)
                    }
                })
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("Bootcamp",error.message)
            }
        })

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TariffFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}