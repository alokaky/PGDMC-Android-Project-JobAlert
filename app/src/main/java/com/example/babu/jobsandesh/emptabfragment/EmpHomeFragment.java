package com.example.babu.jobsandesh.emptabfragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babu.jobsandesh.R;

public class EmpHomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static int int_items = 2;
        /*private int[] tabIcons = {
            R.drawable.tab_pickup_white_48,
            R.drawable.tab_payment_white_50,
            R.drawable.tab_tracking_white_48,
            R.drawable.tab_rateinfo_white_48,
    };*/


    public EmpHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emp_home, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.emp_tabs);
        viewPager = (ViewPager) view.findViewById(R.id.emp_viewpager);

        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                //setupTabIcons();
            }
        });
        return view;
    }

    /* private void setupTabIcons() {
         tabLayout.getTabAt(0).setIcon(tabIcons[0]);
         tabLayout.getTabAt(1).setIcon(tabIcons[1]);
         tabLayout.getTabAt(2).setIcon(tabIcons[2]);
         tabLayout.getTabAt(3).setIcon(tabIcons[3]);
     }
 */
    class ViewPagerAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PostedJobFragment();
                case 1:
                    return new AddJobFragment();

            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "POSTED LIST";
                case 1:
                    return "ADD JOB";
            }
            return null;
        }
    }
}