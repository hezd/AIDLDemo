package com.hezd.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.hezd.aidlserver.Book;
import com.hezd.aidlserver.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by hezd on 20-8-25 下午4:45
 */
public class BookManagerService extends Service {
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public void addBook(Book book) {
            mBookList.add(book);
        }

        @Override
        public List<Book> getBookList() {
            return mBookList;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book("Android", 100));
        mBookList.add(new Book("IOS", 50));
    }
}
