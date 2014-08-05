package com.dadaban.repository.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jrose on 7/29/14.
 */
public class Page<T> implements Serializable {

    /**
     * 默认的序列化版本 id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * 分页查询开始记录位置.
     */
    private int begin;
    /**
     * 分页查看下结束位置.
     */
    private int end;
    /**
     * 每页显示记录数.
     */
    private int length = 10;
    /**
     * 查询结果总记录数.
     */
    private int totalRecords;
    /**
     * 当前页码.
     */
    private int pageNo;
    /**
     * 总共页数.
     */
    private int pageCount;

    private List<T> content;

    /**
     * 分页显示的页数2.3,4,5,6
     */
    private int paginationSize = 5;

    public Page() {
    }


    /**
     * 构造函数.
     *
     * @param begin
     * @param length
     */
    public Page(int begin, int length) {
        this.begin = begin;
        this.length = length;
        this.end = this.begin + this.length;
        this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
    }

    /**
     * @param begin
     * @param length
     */
    public Page(int begin, int length, int totalRecords) {
        this(begin, length);
        this.totalRecords = totalRecords;
    }

    /**
     * 设置页数，自动计算数据范围.
     *
     * @param pageNo
     */
    public Page(int pageNo) {
        this.pageNo = pageNo;
        pageNo = pageNo > 0 ? pageNo : 1;
        this.begin = this.length * (pageNo - 1);
        this.end = this.length * pageNo;
    }

    /**
     * @return the begin
     */
    public int getBegin() {
        return begin;
    }

    /**
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(int begin) {
        this.begin = begin;
        if (this.length != 0) {
            this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
        }
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
        if (this.begin != 0) {
            this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
        }
    }

    /**
     * @return the totalRecords
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * @param totalRecords the totalRecords to set
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        this.pageCount = (int) Math.floor((this.totalRecords * 1.0d) / this.length);
        if (this.totalRecords % this.length != 0) {
            this.pageCount++;
        }
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        pageNo = pageNo > 0 ? pageNo : 1;
        this.begin = this.length * (pageNo - 1);
        this.end = this.length * pageNo;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        if (pageCount == 0) {
            return 1;
        }
        return pageCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPaginationSize() {
        return paginationSize;
    }

    public void setPaginationSize(int paginationSize) {
        this.paginationSize = paginationSize;
    }

    public int getShowStart() {
        int current = pageNo - 1;
        int showStart = Math.max(1, current - paginationSize / 2);
        return showStart;
    }

    public int getShowEnd() {
        int current = pageNo - 1;
        int showStart = Math.max(1, current - paginationSize / 2);
        int showEnd = Math.min(showStart + (paginationSize - 1), pageCount);
        return showEnd;
    }

    public boolean hasPreviousPage() {
        return getPageNo() > 1;
    }

    public boolean hasNextPage() {
        return getPageNo() < this.pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("begin=").append(begin).append(", end=")
                .append(end).append(", length=").append(length).append(", totalRecords=").append(
                        totalRecords).append(", pageNo=").append(pageNo).append(", pageCount=")
                .append(pageCount);
        return builder.toString();


    }
}