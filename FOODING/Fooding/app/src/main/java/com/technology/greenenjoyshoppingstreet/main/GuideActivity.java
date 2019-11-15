package com.technology.greenenjoyshoppingstreet.main;

import com.technology.greenenjoyshoppingstreet.BaseActivity;

/**
 * Created by Bern on 2017/11/9 0009.
 */

public class GuideActivity extends BaseActivity {

//
//    private ViewPager mPager;
//    private List<View> viewList = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);
//
//        mPager = (ViewPager) findViewById(R.id.guide_viewpager);
//        initViews();
//        mPager.setAdapter(new ViewPagerAdapter(viewList));
//
//    }
//
//
//    private void initViews() {
//        int[] images = new int[]{R.drawable.ic_guide_one, R.drawable.ic_guide_two, R.drawable
//                .ic_guide_three};
//        for (int i = 0; i < images.length; i++) {
//            viewList.add(initView(images[i]));
//        }
//
//    }
//
//
//    private View initView(int res) {
//        View view = LayoutInflater.from(getApplicationContext()).inflate(
//                R.layout.item_guide, null);
//        ImageView imageView = (ImageView) view.findViewById(R.id.iguide_img);
//        imageView.setImageResource(res);
//        RelativeLayout rootView = (RelativeLayout) view.findViewById(R.id.rootRL);
//        return rootView;
//    }
//
//    private void openHome() {
//        PreferencesUtil.setSharedBooleanData(UserInfoManger.HIDE_GUIDE, true);
//
//        Intent guideIntent = new Intent(GuideActivity.this, MainActivity.class);
//        startActivity(guideIntent);
//        GuideActivity.this.finish();
//    }
//
//    /**
//     * The type View pager adapter.
//     */
//    class ViewPagerAdapter extends PagerAdapter {
//
//        private List<View> data;
//
//        /**
//         * Instantiates a new View pager adapter.
//         *
//         * @param data the data
//         */
//        public ViewPagerAdapter(List<View> data) {
//            super();
//            this.data = data;
//        }
//
//        @Override
//        public int getCount() {
//            // TODO Auto-generated method stub
//            return data.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            // TODO Auto-generated method stub
//            return arg0 == arg1;
//        }
//
//        @Override
//        public Object instantiateItem(final ViewGroup container, final int position) {
//            // TODO Auto-generated method stub
//            container.addView(data.get(position));
//            if (position == data.size() - 1) {
//
//                data.get(position).setOnClickListener(new View.OnClickListener() {
//
//                    //还原不记录这个点击，只记录里面的就可以了
//
//                    @Override
//                    public void onClick(View v) {
//                        openHome();
//                    }
//
//
//                });
//
//            }
//            return data.get(position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//
//            container.removeView(data.get(position));
//
//        }
//
//    }


}
