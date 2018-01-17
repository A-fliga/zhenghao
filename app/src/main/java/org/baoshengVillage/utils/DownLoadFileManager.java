package org.baoshengVillage.utils;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;


import org.baoshengVillage.constants.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by www on 2017/11/27.
 * 单任务队列下载，尽量保证在前面要显示的界面优先下载好
 */

public class DownLoadFileManager {
    private Boolean stopDownLoad = false;
    private long stopId = 0;
    private String downloadDir; // 文件保存路径
    private static DownLoadFileManager instance; // 单例

    // 单线程任务队列
    private static Executor executor;
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "DownLoadFileManager #" + mCount.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(256);
    private static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);


    private DownLoadFileManager() {
        // 初始化下载路径
//        downloadDir = Constants.DOWNLOAD_DIR + "TvDownload";
        executor = new SerialExecutor();
    }

    public void stopDownLoad(Boolean stop) {
        stopDownLoad = stop;
    }

    public void setStopId(long id) {
        this.stopId = id;
    }


    /**
     * 顺序执行下载任务
     */
    private static class SerialExecutor implements Executor {
        private ArrayDeque<Runnable> mTasks = new ArrayDeque<>();
        Runnable mActive;

        public synchronized void execute(@NonNull final Runnable r) {
            mTasks.offer(new Runnable() {
                public void run() {
                    try {
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (mActive == null) {
                scheduleNext();
            }
        }

        protected synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                THREAD_POOL_EXECUTOR.execute(mActive);
            }
        }

    }

    /**
     * 获取单例对象
     *
     * @return
     */
    public static DownLoadFileManager getInstance() {
        if (instance == null) {
            instance = new DownLoadFileManager();
        }
        return instance;
    }


    //全部删掉之前下载的文件
    public void deleteFilesByDirectory(String path) {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

}
