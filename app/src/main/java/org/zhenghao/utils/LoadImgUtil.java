package org.zhenghao.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by www on 2018/1/10.
 */

public class LoadImgUtil {
    public static void loadRoundImage(Activity context, String url, final ImageView imageView, int id) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(id)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .error(id)
                .transform(new GlideRoundTransform(context, 2))
                .into(imageView);
    }


    //加载圆形图片
    public static void loadCirclePic(final Activity context, String url, final ImageView imageView, int id) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(id)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

}
