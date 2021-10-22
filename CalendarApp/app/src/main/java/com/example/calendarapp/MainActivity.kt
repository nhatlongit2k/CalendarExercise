package com.example.calendarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.calendarapp.adapter.ViewPager2Adapter
import com.example.calendarapp.adapter.ViewPagerAdapter
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

//    lateinit var viewPager: ViewPager2
lateinit var viewPager: ViewPager
    lateinit var preSelectedDate: LocalDate
    lateinit var nextSelectedDate: LocalDate
    var selectedDate: LocalDate = LocalDate.now()
    var fragments: ArrayList<FragmentCalendar> = arrayListOf(
        FragmentCalendar.newInstance(selectedDate.minusMonths(1)),
        FragmentCalendar.newInstance(selectedDate),
        FragmentCalendar.newInstance(selectedDate.plusMonths(1))
    )
    lateinit var adapter: ViewPagerAdapter

    var focusPage = 1
    var PAGE_CENTER = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager = findViewById(R.id.view_pager2)

        //selectedDate = LocalDate.now()
        preSelectedDate = selectedDate.minusMonths(1)
        nextSelectedDate = selectedDate.plusMonths(1)

//        fragments = arrayListOf(
//            FragmentCalendar(selectedDate.minusMonths(1)),
//            FragmentCalendar(selectedDate),
//            FragmentCalendar(selectedDate.plusMonths(1))
//        )
        adapter = ViewPagerAdapter(fragments, supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(1)


//        var myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
////                if(position == 0){
////                    Log.d("TAG", "position: $position")
////                    test()
//////                    preSelectedDate = selectedDate.minusMonths(1)
//////                    fragments.add(0, FragmentCalendar(preSelectedDate))
//////                    adapter.notifyDataSetChanged()
//////                    viewPager.setCurrentItem(1, false)
////                }
////                if(position == fragments.size-1){
////                    nextSelectedDate = nextSelectedDate.plusMonths(1)
////                    fragments.add(FragmentCalendar(nextSelectedDate))
////                    adapter.notifyItemInserted(fragments.size-1)
////                }
//                focusPage = position
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                if (state == ViewPager2.SCROLL_STATE_IDLE) {
//                    Log.d("TAG", "focusPage: $focusPage")
//                    if (focusPage < PAGE_CENTER) {
//                        selectedDate = selectedDate.minusMonths(1)
//                        Log.d("TAG", "selectedDate gui di: ${selectedDate.month}")
//                    } else if (focusPage > PAGE_CENTER) {
//                         selectedDate = selectedDate.plusMonths(1)
//                        Log.d("TAG", "selectedDate gui di: ${selectedDate.month}")
//                    }
//                    adapter.setCalendar(selectedDate);
//                    adapter.notifyDataSetChanged()
//                    viewPager.setCurrentItem(1, true);
//                }
//            }
//        }
//        viewPager.registerOnPageChangeCallback(myPageChangeCallback)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                focusPage = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (focusPage < 1) {
                        selectedDate = selectedDate.minusMonths(1)
                    } else if (focusPage > 1) {
                        selectedDate = selectedDate.plusMonths(1)
                    }
                    adapter.setCalendar(selectedDate)
                    viewPager.setCurrentItem(1,false)
                }
            }
        })
    }

}