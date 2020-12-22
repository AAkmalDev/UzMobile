package uz.pdp.uzmobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_service.view.*
import uz.pdp.uzmobile.R
import uz.pdp.uzmobile.models.PackageModel

class PackageAdapter(var packageList: ArrayList<PackageModel>,var listener: onPackageClikItemListener) :
    RecyclerView.Adapter<PackageAdapter.Vh>() {

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(packageModel: PackageModel) {
            itemView.servise_text_name.text = packageModel.sname
            itemView.servise_img_text.text = packageModel.sname
            itemView.servise_text_desc.text = packageModel.sdesc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(packageList[position])
        holder.itemView.setOnClickListener {
            listener.onClikListener(packageList[position])
        }
    }

    override fun getItemCount(): Int = packageList.size

    interface onPackageClikItemListener{
        fun onClikListener(packageModel: PackageModel)
    }

}