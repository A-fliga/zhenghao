package org.zhenghao.mvp.presenter.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.zhenghao.R;
import org.zhenghao.mvp.model.bean.CertificateBean;
import org.zhenghao.mvp.view.CertificateDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by www on 2018/1/11.
 * 办证指南页面
 */

public class CertificateActivity extends ActivityPresenter<CertificateDelegate> {
    //不动产，抵押等级url  rensheju

    //公安局的
    private String[] itemName1 = new String[]{"身份证", "临时身份证", "婴儿入户登记", "户口变更", "居住证",
            "港澳通行证", "暂住证", "驾驶证", "行驶证"};
    private String[] itemUrl1 = new String[]{"sfz", "lssfz", "xsyerh", "hkbg", "jzz", "gatxz", "zzz", "jsz", "xsz"};
    private int[] itemId1 = new int[]{R.mipmap.shenfenzheng, R.mipmap.linshishenfen, R.mipmap.yingerruhu,
            R.mipmap.hukou, R.mipmap.juzhuzheng, R.mipmap.gangaotongxing, R.mipmap.zanzhuzheng,
            R.mipmap.jiashizheng, R.mipmap.xingshizheng};

    //民政局的
    private String[] itemName2 = new String[]{"结婚证", "残疾证", "低保证", "老年证", "烈士证"};
    private String[] itemUrl2 = new String[]{"jhz", "cjz", "dbz", "lnz", "lsz"};
    private int[] itemId2 = new int[]{R.mipmap.jiehunzheng, R.mipmap.canjizheng, R.mipmap.dibaozheng,
            R.mipmap.laonianzheng, R.mipmap.lieshizheng};


    //国土局的
    private String[] itemName3 = new String[]{"不动产证书", "抵押登记"};
    private String[] itemUrl3 = new String[]{"fcz", "fcdyzl"};
    private int[] itemId3 = new int[]{R.mipmap.budongchan, R.mipmap.diyadengji};

    //人社局的
    private String[] itemName4 = new String[]{"农医保险", "城医保险"};
    private String[] itemUrl4 = new String[]{"yb", "yb"};
    private int[] itemId4 = new int[]{R.mipmap.nongyibaoxian, R.mipmap.chengyibaoxian};

    //卫生委
    private String[] itemName5 = new String[]{"健康证", "生育证", "出生证", "接种证"};
    private String[] itemUrl5 = new String[]{"jkz", "syz", "csz", "yfjzz"};
    private int[] itemId5 = new int[]{R.mipmap.jiankangzheng, R.mipmap.shengyuzheng, R.mipmap.chushengzheng, R.mipmap.jiezhongzheng};


    //市场综合管理
    private String[] itemName6 = new String[]{"营业执照", "卫生许可证"};
    private String[] itemUrl6 = new String[]{"yyzz", "wsxkz"};
    private int[] itemId6 = new int[]{R.mipmap.yingyezhizhao, R.mipmap.weishengxuke};

    @Override
    public Class<CertificateDelegate> getDelegateClass() {
        return CertificateDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initList();
    }

    private void initList() {
        List<CertificateBean> list = new ArrayList<>();
        list.add(getFirstBean());
        list.add(getSecondBean());
        list.add(getThirdBean());
        list.add(getFourthBean());
        list.add(getFifthBean());
        list.add(getSixthBean());
        viewDelegate.initRecycler(list);
    }

    @NonNull
    private CertificateBean getFirstBean() {
        CertificateBean bean = new CertificateBean();
        bean.setTitle("公安局");
        List<CertificateBean.CertificateItemBean> beanList = new ArrayList<>();
        for (int i = 0; i < itemName1.length; i++) {
            CertificateBean.CertificateItemBean itemBean = new CertificateBean.CertificateItemBean();
            itemBean.setName(itemName1[i]);
            itemBean.setImgId(itemId1[i]);
            itemBean.setUrl(itemUrl1[i]);
            beanList.add(itemBean);
        }
        bean.setBeanList(beanList);
        return bean;
    }

    @NonNull
    private CertificateBean getSecondBean() {
        CertificateBean bean = new CertificateBean();
        bean.setTitle("民政局");
        List<CertificateBean.CertificateItemBean> beanList = new ArrayList<>();
        for (int i = 0; i < itemName2.length; i++) {
            CertificateBean.CertificateItemBean itemBean = new CertificateBean.CertificateItemBean();
            itemBean.setName(itemName2[i]);
            itemBean.setImgId(itemId2[i]);
            itemBean.setUrl(itemUrl2[i]);
            beanList.add(itemBean);
        }
        bean.setBeanList(beanList);
        return bean;
    }

    @NonNull
    private CertificateBean getThirdBean() {
        CertificateBean bean = new CertificateBean();
        bean.setTitle("国土局");
        List<CertificateBean.CertificateItemBean> beanList = new ArrayList<>();
        for (int i = 0; i < itemName3.length; i++) {
            CertificateBean.CertificateItemBean itemBean = new CertificateBean.CertificateItemBean();
            itemBean.setName(itemName3[i]);
            itemBean.setImgId(itemId3[i]);
            itemBean.setUrl(itemUrl3[i]);
            beanList.add(itemBean);
        }
        bean.setBeanList(beanList);
        return bean;
    }

    @NonNull
    private CertificateBean getFourthBean() {
        CertificateBean bean = new CertificateBean();
        bean.setTitle("人社局");
        List<CertificateBean.CertificateItemBean> beanList = new ArrayList<>();
        for (int i = 0; i < itemName4.length; i++) {
            CertificateBean.CertificateItemBean itemBean = new CertificateBean.CertificateItemBean();
            itemBean.setName(itemName4[i]);
            itemBean.setImgId(itemId4[i]);
            itemBean.setUrl(itemUrl4[i]);
            beanList.add(itemBean);
        }
        bean.setBeanList(beanList);
        return bean;
    }

    @NonNull
    private CertificateBean getFifthBean() {
        CertificateBean bean = new CertificateBean();
        bean.setTitle("卫生委");
        List<CertificateBean.CertificateItemBean> beanList = new ArrayList<>();
        for (int i = 0; i < itemName5.length; i++) {
            CertificateBean.CertificateItemBean itemBean = new CertificateBean.CertificateItemBean();
            itemBean.setName(itemName5[i]);
            itemBean.setImgId(itemId5[i]);
            itemBean.setUrl(itemUrl5[i]);
            beanList.add(itemBean);
        }
        bean.setBeanList(beanList);
        return bean;
    }

    @NonNull
    private CertificateBean getSixthBean() {
        CertificateBean bean = new CertificateBean();
        bean.setTitle("市场综合管理");
        List<CertificateBean.CertificateItemBean> beanList = new ArrayList<>();
        for (int i = 0; i < itemName6.length; i++) {
            CertificateBean.CertificateItemBean itemBean = new CertificateBean.CertificateItemBean();
            itemBean.setName(itemName6[i]);
            itemBean.setImgId(itemId6[i]);
            itemBean.setUrl(itemUrl6[i]);
            beanList.add(itemBean);
        }
        bean.setBeanList(beanList);
        return bean;
    }
}
