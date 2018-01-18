package org.baoshengVillage.mvp.presenter.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.soundcloud.android.crop.Crop;

import org.baoshengVillage.R;
import org.baoshengVillage.application.UserManager;
import org.baoshengVillage.mvp.model.PublicModel;
import org.baoshengVillage.mvp.model.bean.BaseEntity;
import org.baoshengVillage.mvp.model.bean.UpdateIconBean;
import org.baoshengVillage.mvp.model.bean.UserBean;
import org.baoshengVillage.mvp.view.UserInfoDelegate;
import org.baoshengVillage.utils.DialogUtil;
import org.baoshengVillage.utils.LoadImgUtil;
import org.baoshengVillage.utils.LogUtil;
import org.baoshengVillage.utils.SharedPreferencesUtil;
import org.baoshengVillage.utils.ToastUtil;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Map;

import rx.Subscriber;

import static org.baoshengVillage.constants.Constants.CHOOSE_PHOTO_FROM_GALLERY_CODE;
import static org.baoshengVillage.constants.Constants.TAKE_PHOTO_REQUEST_CODE;
import static org.baoshengVillage.constants.Constants.USER_PHONE;
import static org.baoshengVillage.constants.Constants.USER_PWD;

/**
 * Created by www on 2018/1/8.
 * 个人中心页面
 */

public class UserInfoActivity extends ActivityPresenter<UserInfoDelegate> {
    private EditText name_et;
    private RadioButton radioMale, radioFemale;
    private TextView birth_tv;
    private File imgFile, lastFile;
    private StringBuffer stringBuffer;
    private AlertDialog dialog;
    private ImageView user_info_icon;
    private Uri imageUri;

    @Override
    public Class<UserInfoDelegate> getDelegateClass() {
        return UserInfoDelegate.class;
    }

    @Override
    public boolean isSetDisplayHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initClickEvent();
    }

    private void initView() {
        name_et = get(R.id.user_name_et);
        radioMale = get(R.id.radioMale);
        radioFemale = get(R.id.radioFemale);
        birth_tv = get(R.id.user_info_birth_tv);
        user_info_icon = get(R.id.user_info_icon);
    }

    private void initClickEvent() {
        viewDelegate.setOnClickListener(onClickListener, R.id.toolbar_right_rl, R.id.user_info_birth_tv,
                R.id.user_info_icon, R.id.user_icon_tv);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.toolbar_right_rl:
                    if (lastFile == null) {
                        toSaveInfo("");
                    } else {
                        //更新头像
                        updateIcon(lastFile);
                    }
                    break;
                case R.id.user_info_birth_tv:
                    //点击"日期"按钮布局 设置日期
                    setDate();
                    break;
                case R.id.user_info_icon:
                case R.id.user_icon_tv:
                    dialog = DialogUtil.createAlertDialog(UserInfoActivity.this, showDialogAndItemClickListener());
                    dialog.show();
                    break;
            }
        }
    };


    @NonNull
    private View showDialogAndItemClickListener() {
        View view = DialogUtil.getDialogView(UserInfoActivity.this);
        CheckedTextView camera = (CheckedTextView) view.findViewById(R.id.fromCamera);
        CheckedTextView photo = (CheckedTextView) view.findViewById(R.id.fromPhoto);
        CheckedTextView cancel = (CheckedTextView) view.findViewById(R.id.cancel);
        camera.setOnClickListener(dialogOnClickListener);
        photo.setOnClickListener(dialogOnClickListener);
        cancel.setOnClickListener(dialogOnClickListener);
        return view;
    }

    private View.OnClickListener dialogOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fromCamera:
                    LogUtil.i("调用相机");
                    if (cameraIsCanUse())
                        startCamera();
                    else {
                        ToastUtil.l("请打开手机的相机权限");
                    }
                    break;
                case R.id.fromPhoto:
                    LogUtil.i("调用相册");
                    chooseFromGallery();
                    break;
                case R.id.cancel:
                    LogUtil.i("调用取消");
                    dialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 返回true 表示可以使用  返回false表示不可以使用
     */
    public boolean cameraIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }


    /**
     * 从相册选择图片
     * WRITE_EXTERNAL_STORAGE0-
     */
    private void chooseFromGallery() {
        //构建一个内容选择的Intent
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //设置选择类型为图片类型
        //打开图片选择
        startActivityForResult(intent, CHOOSE_PHOTO_FROM_GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO_REQUEST_CODE:
                try {
                    Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    if (bm != null) {
                        //开始裁剪
                        toCrop(imageUri);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case CHOOSE_PHOTO_FROM_GALLERY_CODE:
                if (data != null) {
                    //用户从图库选择图片后会返回所选图片的Uri
                    //开始裁剪
                    toCrop(data.getData());
                }
                break;
            case Crop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    lastFile = imgFile;
                    LoadImgUtil.loadCirclePic(this, imgFile.getAbsolutePath(), user_info_icon, R.mipmap.normal_head);
                }
                dialog.dismiss();
            default:
                break;
        }

    }

    private void toCrop(Uri uri) {
        //新建文件夹用于存放裁剪后的图片
        File tmpDir = new File(getExternalCacheDir() + File.separator + "baoshengcun");
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        //新建文件存储裁剪后的图片
        imgFile = new File(tmpDir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg");
        Crop.of(uri, Uri.fromFile(imgFile)).withMaxSize(300, 300).asSquare().start(UserInfoActivity.this);
    }


    /**
     * 启动相机
     */
    public void startCamera() {
        File file = new File(getExternalCacheDir() + File.separator + "baoshengcun");
        if (!file.exists()) {
            file.mkdir();
        }
        /**
         * 这里将时间作为不同照片的名称
         */
        File output = new File(file, System.currentTimeMillis() + ".jpg");
        /**
         * 隐式打开拍照的Activity，并且传入TAKE_PHOTO_REQUEST_CODE常量作为拍照结束后回调的标志
         * 将文件转化为uri
         */
        imageUri = Uri.fromFile(output);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
    }


    private void updateIcon(File file) {
        PublicModel.getInstance().updateIcon(new Subscriber<UpdateIconBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(UpdateIconBean iconBeanBase) {
                if (iconBeanBase.getCode() != 0) {
                    ToastUtil.s(iconBeanBase.getMsg());
                } else {
                    toSaveInfo(iconBeanBase.getResult().get(0).getUrl());
                }
            }
        }, file);
    }

    private void toSaveInfo(String icon) {
        if (name_et.getText().toString().replaceAll(" ", "").isEmpty()) {
            ToastUtil.s("昵称不能为空");
        } else {
            UserBean userBean = UserManager.getInstance().getUserBean();
            if (userBean != null) {
                userBean.setNames(name_et.getText().toString().replaceAll(" ", ""));
                if (!icon.isEmpty()) {
                    userBean.setHeadUrl(icon);
                } else userBean.setHeadUrl(null);
                if (radioMale.isChecked()) {
                    userBean.setGender(1);
                }
                if (radioFemale.isChecked()) {
                    userBean.setGender(2);
                }
                if (stringBuffer != null && !stringBuffer.toString().isEmpty()) {
                    birth_tv.setText(stringBuffer);
                    userBean.setBirthTime(stringBuffer.toString());
                } else {
                    userBean.setBirthTime(null);
                }
                saveInfo(userBean);
            }
        }
    }

    private void saveInfo(UserBean userBean) {
        PublicModel.getInstance().saveUserInfo(new Subscriber<BaseEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.s("未知错误，请联系管理员");
            }

            @Override
            public void onNext(BaseEntity baseEntity) {
                if (baseEntity.getCode() != 0) {
                    ToastUtil.s(baseEntity.getMsg());
                } else {
                    ToastUtil.s("修改成功");
                    toAutoLogin();
                }
            }
        }, userBean);
    }

    private void toAutoLogin() {
        Map<String, String> map = SharedPreferencesUtil.getPhonePwd();
        if (map != null && map.containsKey(USER_PHONE)) {
            PublicModel.getInstance().autoLogin(map.get(USER_PHONE), map.get(USER_PWD), new PublicModel.ReLoginListener() {
                @Override
                public void success(BaseEntity<UserBean> userBean) {
                    UserManager.getInstance().setUserBean(userBean.getResult());
                    EventBus.getDefault().post(true);
                    finish();
                }

                @Override
                public void error() {
                    ToastUtil.l("未知错误");
                }

                @Override
                public void serverException(BaseEntity<UserBean> userBean) {
                    ToastUtil.s(userBean.getMsg());
                }
            });
        }
    }


    public void setDate() {
        final Calendar calendar = Calendar.getInstance();
        //通过自定义控件AlertDialog实现
        AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);
        View view = getLayoutInflater().inflate(R.layout.data_dialog, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        //设置日期简略显示 否则详细显示 包括:星期\周
        datePicker.setCalendarViewShown(false);
        //初始化当前日期
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);
        //设置date布局
        builder.setView(view);
        builder.setTitle("设置日期信息");
        builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //日期格式
                stringBuffer = new StringBuffer();
                stringBuffer.append(String.format("%d-%02d-%02d",
                        datePicker.getYear(),
                        datePicker.getMonth() + 1,
                        datePicker.getDayOfMonth()));
                TextView tv = get(R.id.user_info_birth_tv);
                tv.setText(stringBuffer);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

}
