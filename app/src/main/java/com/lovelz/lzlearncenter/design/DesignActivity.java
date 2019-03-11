package com.lovelz.lzlearncenter.design;

import com.lovelz.lzlearncenter.BaseActivity;
import com.lovelz.lzlearncenter.R;
import com.lovelz.lzlearncenter.design.observer.SubscriptionSubject;
import com.lovelz.lzlearncenter.design.observer.WeixinUser;

/**
 * @author lovelz
 * @date on 2019/3/11.
 */
public class DesignActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_design;
    }

    @Override
    public void initEvent() {
        super.initEvent();

        initObserver();

    }

    /**
     * 观察者模式
     */
    private void initObserver() {
        SubscriptionSubject subscriptionSubject = new SubscriptionSubject();

        WeixinUser user1 = new WeixinUser("张三");
        WeixinUser user2 = new WeixinUser("李四");
        WeixinUser user3 = new WeixinUser("王五");

        subscriptionSubject.attach(user1);
        subscriptionSubject.attach(user2);
        subscriptionSubject.attach(user3);

        subscriptionSubject.notify("啦啦啦");
    }

}
