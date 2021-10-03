package com.ashu.explorer.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.ashu.explorer.R
import com.ashu.explorer.adapter.BlackLiveMatterAdapter
import com.ashu.explorer.adapter.HelpFriendsAdapter
import com.ashu.explorer.data.BlackLiveMatterData
import com.ashu.explorer.data.HelpFriendsData
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var scrollStarted = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // dummy data for viewpager
        val dummyData = arrayListOf(BlackLiveMatterData(R.drawable.photo,"Black Lives Matter"),
                BlackLiveMatterData(R.drawable.photo,"Black Lives Matter"),
                    BlackLiveMatterData(R.drawable.photo,"Black Lives Do Matter"),
                        BlackLiveMatterData(R.drawable.photo,"Black Lives Do Matter A Lot"),
                            BlackLiveMatterData(R.drawable.photo,"All Lives Matter"),
                                BlackLiveMatterData(R.drawable.photo,"We Stand by Everyone"))

        val adapter = BlackLiveMatterAdapter(dummyData)

        val viewPager = root.findViewById<ViewPager2>(R.id.vp_black_lives)

        viewPager.adapter = adapter

        val handler = Handler()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val runnable = Runnable { viewPager.currentItem = (position + 1) % adapter.itemCount }
                handler.postDelayed(runnable, 1500)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                /**
                 * The user swiped forward or back and we need to
                 * invalidate the previous handler.
                 */
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    handler.removeMessages(0)

                }
                scrollStarted = !scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
        })

        TabLayoutMediator(root.findViewById(R.id.tab_layout), root.findViewById(R.id.vp_black_lives)) {tab, position ->
        }.attach()

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_help_friends)

        val helpFriendsData = arrayListOf(HelpFriendsData(R.drawable.img_help_friend,
                "Kaya", "Sr. Business Manager", "London", "Remotely", 4200, 100),
                HelpFriendsData(R.drawable.img_help_friend, "Maya",
                        "Flutter Developer", "London", "Remotely", null, null),
                HelpFriendsData(R.drawable.img_help_friend, "Gabriella",
                        "Android Developer", "London", "Remotely", 1000, 85),
                HelpFriendsData(R.drawable.img_help_friend, "Rosona",
                        "Staff Engineer", "London", "Remotely", null, null),
                HelpFriendsData(R.drawable.img_help_friend, "Condoliza",
                        "VP of Engineering", "London", "Remotely", null, null),
                HelpFriendsData(R.drawable.img_help_friend, "Jasmine",
                        "Senior IOS Developer", "London", "Remotely", null, null))

        val helpFriendsAdapter = HelpFriendsAdapter(helpFriendsData)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        recyclerView.adapter = helpFriendsAdapter

        return root
    }
}