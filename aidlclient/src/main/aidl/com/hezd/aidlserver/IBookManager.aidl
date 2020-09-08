// IBookManager.aidl
package com.hezd.aidlserver;
import com.hezd.aidlserver.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * 添加一本书
     */
    void addBook(in Book book);

    /**
    * 获取所有书
    */
    List<Book> getBookList();

}
