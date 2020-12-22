package uz.pdp.uzmobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rv_ussd.view.*
import uz.pdp.uzmobile.R
import uz.pdp.uzmobile.models.TrafficModel

class TariffRvAdapter(var trafficList: ArrayList<TrafficModel>,var listener: onItemClickListener):RecyclerView.Adapter<TariffRvAdapter.Vh>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_ussd,parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(trafficList[position])
        holder.itemView.setOnClickListener {
            listener.onClick(trafficList[position])
        }
    }

    override fun getItemCount(): Int = trafficList.size

    inner class Vh(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(trafficModel: TrafficModel){
            itemView.text_title.text = trafficModel.tname
            itemView.text_desc.text = trafficModel.tdesc
        }
    }

    interface onItemClickListener{
        fun onClick(trafficModel: TrafficModel)
    }
}