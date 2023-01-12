package ph.kodego.leones.patricia.ivee.fragments.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ph.kodego.leones.patricia.ivee.fragments.FragmentAdapter
import ph.kodego.leones.patricia.ivee.fragments.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ViewPager is the 3rd Way to use Fragments
//        Androidx Navigation is the New Way to use Fragments
//        ViewPager 2 + Android X Navigation is the current Standard of using Fragments

        var fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(AFragment())
        fragmentAdapter.addFragment(BFragment())
        fragmentAdapter.addFragment(CFragment())

        //        fragments also have life cycle
//        Every new Android X component is lifecycle aware

        with(binding.viewPager2){
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            swipe animation
            setPageTransformer(ZoomOutPageTransformer())
//            There's another Page transformer to be searched
            adapter = fragmentAdapter
//            with this you can now swipe from one fragment to another like a page in a book
        }

//        with "with"(line 26) you don't have to add additional code to set the orientation and adapter
//it's called a convenience function


//      Tab Layout above the activity Screen Similar to many apps like Shopee, Facebook
        TabLayoutMediator(binding.tabLayout,binding.viewPager2){
            tab, position ->
            tab.text = "Chapter ${(position + 1)}"
        }.attach()

//        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        binding.viewPager2.adapter = fragmentAdapter

    }

    override fun onBackPressed() {
        if(binding.viewPager2.currentItem == 0){
            super.onBackPressed()
        }else{
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }

//    onBackPressed also tells fragments to go back to previous fragment once you click the back button on your phone
}