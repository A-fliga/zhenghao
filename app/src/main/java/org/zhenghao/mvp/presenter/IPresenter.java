package org.zhenghao.mvp.presenter;


import org.zhenghao.mvp.view.IDelegate;

/**
 * Created by www on 2017/5/5.
 */
public interface IPresenter<T extends IDelegate> {

    Class<T> getDelegateClass();
}