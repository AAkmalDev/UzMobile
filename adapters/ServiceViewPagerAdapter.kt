package uz.pdp.uzmobile.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.pdp.uzmobile.ui.PackageFragment

class ServiceViewPagerAdapter(var fragment: FragmentManager,var serviseList: ArrayList<String>,var fragmentList: ArrayList<String>) :FragmentPagerAdapter(fragment, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return serviseList.size
    }

    override fun getItem(position: Int): Fragment {
        return PackageFragment.newInstance(fragmentList[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return serviseList[position]
    }
}