package com.compani.ilai.bioproducts.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.compani.ilai.bioproducts.ui.productType.cheese.CheeseFragment
import com.compani.ilai.bioproducts.ui.productType.diverse.DiverseFragment
import com.compani.ilai.bioproducts.ui.productType.fruit.FruitFragment
import com.compani.ilai.bioproducts.ui.productType.meat.MeatFragment
import com.compani.ilai.bioproducts.ui.productType.vegetables.VegetablesFragment

class TabPagerAdapter(
    fm : FragmentManager,
    private var tabCount: Int
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
             0 -> VegetablesFragment()
             1 -> MeatFragment()
             2 -> FruitFragment()
             3 -> CheeseFragment()
             else -> DiverseFragment()
         }
    }
}