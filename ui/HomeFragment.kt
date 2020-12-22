package uz.pdp.uzmobile.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.view.*
import uz.pdp.uzmobile.R
import uz.pdp.uzmobile.adapters.ImageViewPagerAdapter
import uz.pdp.uzmobile.ui.MainFragment

class HomeFragment : Fragment() {

    private lateinit var imageList:ArrayList<String>
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter
    private lateinit var root: View
    private lateinit var viewPager: ViewPager
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    private var page = 0
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = root.view_pager
        val tabLayout = root.tab_layout
        LoadData()
        imageViewPagerAdapter = ImageViewPagerAdapter(childFragmentManager,imageList)
        viewPager.adapter = imageViewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        handler =  Handler()
        runnable = object :Runnable{
            override fun run() {
                if (imageViewPagerAdapter.count == page){
                    page = 0
                }else{
                    page++
                }
                viewPager.currentItem = page
                handler.postDelayed(this,3000)
            }
        }
        runnable.run()
        root.ussd_request.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.nav_slideshow)
        }

        root.tariff.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.tariffFragment)
        }
        root.service.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key",1)
            Navigation.findNavController(root).navigate(R.id.nav_gallery,bundle)
        }
        root.minutes.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key",2)
            Navigation.findNavController(root).navigate(R.id.nav_gallery,bundle)
        }
        root.internet.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key",3)
            Navigation.findNavController(root).navigate(R.id.nav_gallery,bundle)
        }
        root.sms.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("key",4)
            Navigation.findNavController(root).navigate(R.id.nav_gallery,bundle)
        }

        return root
    }

    private fun LoadData() {
        imageList = ArrayList()
        imageList.add("https://uztelecom.uz/uploads/2020/10/15/AvB7ayEhto.jpg")
        imageList.add("https://uztelecom.uz/uploads/2020/10/15/YDBe74JGOd.jpg")
        imageList.add("https://uztelecom.uz/uploads/2020/8/5/uzJd31cGRC.jpg")
        imageList.add("https://uztelecom.uz/uploads/2020/6/30/ZTD5m2llQU.jpg")
        imageList.add("https://uztelecom.uz/uploads/2020/2/12/RHpo7j55Ok.jpg")
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,3000)
    }

}
