package uz.pdp.uzmobile.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.pdp.uzmobile.ui.ImageFragment

class ImageViewPagerAdapter(fragment: FragmentManager, val imageList: ArrayList<String>):
    FragmentPagerAdapter(fragment,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(imageList[position])
    }

}