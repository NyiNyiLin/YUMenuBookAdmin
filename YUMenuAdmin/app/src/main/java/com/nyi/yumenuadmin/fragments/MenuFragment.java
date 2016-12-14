package com.nyi.yumenuadmin.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.yumenuadmin.R;
import com.nyi.yumenuadmin.adapters.MenuFragmentPagerAdapter;
import com.nyi.yumenuadmin.data.VOs.ShopVO;
import com.nyi.yumenuadmin.data.models.ShopModel;
import com.nyi.yumenuadmin.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuFragment extends Fragment {
    @BindView(R.id.pager_item)
    ViewPager pagerItem;

    @BindView(R.id.tab_type)
    TabLayout tabType;

    private ShopVO shopVO;
    private List<String> shopTypeList = new ArrayList<>();
    private String shopid;
    private MenuFragmentPagerAdapter menuFragmentPagerAdapter;


    public MenuFragment() {
    }

    public static Fragment newInstance(){
        Fragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        shopVO = ShopModel.getobjInstance().getShopVO();
        shopTypeList = shopVO.getType();
        shopid = shopVO.getShopID();

        Log.d(Constants.TAG, "MenuFragment shopItem List size " + shopTypeList.size());

        menuFragmentPagerAdapter = new MenuFragmentPagerAdapter(getFragmentManager());

        for(String s: shopTypeList){
            if(!s.isEmpty()) menuFragmentPagerAdapter.addTab(MenuItemFragment.newInstance(shopid, s), s);
        }
        pagerItem.setAdapter(menuFragmentPagerAdapter);
        tabType.setupWithViewPager(pagerItem);

        Log.d(Constants.TAG, menuFragmentPagerAdapter.getCount() + "");

        //to create all of the fragment
        pagerItem.setOffscreenPageLimit(menuFragmentPagerAdapter.getCount());

        pagerItem.setClipToPadding(false);
        pagerItem.setPageMargin(12);

        return view;
    }

    private ShopVO getDummyShopVO(){
        List<String> type = new ArrayList<>();
        type.add("Breakfast");
        type.add("Lunch");
        type.add("Drink");
        ShopVO shopVO = new ShopVO("ShopId", "Name", "Img Link", Constants.TAUNGNOO_CANTEEN, type);
        return shopVO;
    }
}
