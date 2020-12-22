package uz.pdp.uzmobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.android.synthetic.main.item_tab.view.*
import uz.pdp.uzmobile.MainActivity
import uz.pdp.uzmobile.R
import uz.pdp.uzmobile.adapters.ServiceViewPagerAdapter

class GalleryFragment : Fragment() {

    private lateinit var root: View
    private lateinit var serviceList: ArrayList<String>
    private lateinit var fragmentList: ArrayList<String>
    private lateinit var serviceViewPagerAdapter: ServiceViewPagerAdapter
    private lateinit var tabLayoutPage: TabLayout
    private lateinit var viewpagerGallery: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_gallery, container, false)

        val key = arguments?.getInt("key") as Int
        tabLayoutPage = root.tab_layout_page
        viewpagerGallery = root.viewpager_gallery

        if (key == 1) {
            (activity as MainActivity ).supportActionBar?.title = getString(R.string.xizmatlar)
            serviseData()
        } else if (key == 2) {
            (activity as MainActivity ).supportActionBar?.title = getString(R.string.minut)
            minutData()
        }else if(key == 3){
            (activity as MainActivity ).supportActionBar?.title = getString(R.string.internet)
            internetData()
        }else if (key == 4){
            (activity as MainActivity ).supportActionBar?.title = getString(R.string.sms)
            smsData()
        }

        tabLayoutPage.setupWithViewPager(viewpagerGallery)

        serviceViewPagerAdapter = ServiceViewPagerAdapter(childFragmentManager, serviceList,fragmentList)
        viewpagerGallery.adapter = serviceViewPagerAdapter

        val tabCount = tabLayoutPage.tabCount
        for (i in 0 until root.tab_layout_page.tabCount){
            val tabview = LayoutInflater.from(root.context).inflate(R.layout.item_tab,null,false)
            tabview.tab_text.text = serviceList[i]
            if (i == 0){
                tabview.tab_view.background = resources.getDrawable(R.drawable.tab_selec_back)
                tabview.tab_text.setTextColor(resources.getColor(R.color.blue))
            }else{
                tabview.tab_view.background = resources.getDrawable(R.drawable.tab_back)
                tabview.tab_text.setTextColor(resources.getColor(R.color.white))
            }
            root.tab_layout_page.getTabAt(i)?.customView = tabview
        }

        tabLayoutPage.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tab_view?.background = resources.getDrawable(R.drawable.tab_selec_back)
                customView?.tab_text?.setTextColor(resources.getColor(R.color.blue))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tab_view?.background = resources.getDrawable(R.drawable.tab_back)
                customView?.tab_text?.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        return root
    }

    fun serviseData(){
        serviceList = ArrayList()
        serviceList.add(getString(R.string.xizmatlar_gallery))
        serviceList.add(getString(R.string.xizmatlar_smslar))
        serviceList.add(getString(R.string.rouming))

        fragmentList = ArrayList()
        fragmentList.add("xizmatlar")
        fragmentList.add("pulliksmslar")
        fragmentList.add("rouming")

    }
    fun minutData(){
        serviceList = ArrayList()
        serviceList.add(getString(R.string.daqiqa_toplam))
        serviceList.add(getString(R.string.foydali_almashuv))
        serviceList.add(getString(R.string.constructor_abonet))

        fragmentList = ArrayList()
        fragmentList.add("daqiqa")
        fragmentList.add("foydalialmashtiruv")
        fragmentList.add("constructor")
    }
    fun internetData(){
        serviceList = ArrayList()
        serviceList.add(getString(R.string.oylik_paket))
        serviceList.add(getString(R.string.kunlik_paket))
        serviceList.add(getString(R.string.tungi_intenet))
        serviceList.add(getString(R.string.internet_non))

        fragmentList = ArrayList()
        fragmentList.add("oylikmb")
        fragmentList.add("kunlikmb")
        fragmentList.add("tungimb")
        fragmentList.add("nonstopmb")
    }
    fun smsData(){
        serviceList = ArrayList()
        serviceList.add(getString(R.string.kunlik_sms))
        serviceList.add(getString(R.string.oylik_sms))
        serviceList.add(getString(R.string.xalq_sms))

        fragmentList = ArrayList()
        fragmentList.add("kunliksms")
        fragmentList.add("oyliksms")
        fragmentList.add("xalqarosms")
    }

}