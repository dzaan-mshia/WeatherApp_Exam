package ge.edu.btu.weatherapp_exam.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ge.edu.btu.weatherapp_exam.MainActivity

class ViewPagerAdapter(activity: MainActivity, fragments: List<Fragment>) : FragmentStateAdapter(activity){

    var fragments = fragments

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}