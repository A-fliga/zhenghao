package org.baoshengVillage.mvp.presenter;


import org.baoshengVillage.mvp.view.IDelegate;

/**
 * Created by www on 2017/5/5.
 */
public interface IPresenter<T extends IDelegate> {

    Class<T> getDelegateClass();
}