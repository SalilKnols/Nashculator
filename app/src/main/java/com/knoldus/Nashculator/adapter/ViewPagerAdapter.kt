package com.knoldus.Nashculator.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
/**
 * The ViewPagerAdapter class is a custom adapter for managing the fragments in a ViewPager.
 * It extends the FragmentPagerAdapter class and provides methods to handle
 * ...fragment management and retrieval.
 *
 * Constructs a ViewPagerAdapter object.
 *
 * @param supportFragmentManager The fragment manager instance.
*/
class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()


    /**
     * Returns the number of fragments in the adapter.
     *
     * @return The number of fragments.
     */
    override fun getCount(): Int {
        return fragmentList.size
    }

    /**
     * Returns the fragment at the specified position.
     *
     * @param position The position of the fragment.
     * @return The fragment at the specified position.
     */
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
    /**
     * Returns the title of the fragment at the specified position.
     *
     * @param position The position of the fragment.
     * @return The title of the fragment at the specified position.
     */
    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }
    /**
     * Adds a fragment to the adapter.
     *
     * @param fragment The fragment to be added.
     * @param title    The title of the fragment.
     */
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}