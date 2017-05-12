package com.tzxt.util;

import com.tzxt.dto.LedgerDataSet;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * 台账数据表 分页信息
 * <p>
 * Created by wangshang on 17/5/13.
 */
@Data
public class LedgerDataPageInfo {

    // 是否有上一页
    private boolean hasPreviousPage;

    // 上一页 的页码
    private int prePage;

    // 是否有下一页
    private boolean hasNextPage;

    // 下一页 的页码
    private int nextPage;

    // 导航页 的页码
    private int[] navigatepageNums;

    // 页的 大小
    private int pageSize;

    // 当前页
    private int pageNum;

    // 当前页 大小
    private int size;

    // 总页数
    private int pages;

    // 导航页 的 页码个数
    private int navigatePages;


    // 记录总数
    private long total;

    // 数据 集
    private List<LedgerDataSet> list;

    public LedgerDataPageInfo(List<LedgerDataSet> list, int pageNum, int pageSize, long total) {
        this.list = list;
        this.size = list.size();
        this.navigatePages = 8;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        setTotal(total);

        //计算导航页
        calcNavigatepageNums();
        //计算前后页，第一页，最后一页
        calcPage();
        //判断页面边界
        judgePageBoudary();
    }

    private void setTotal(long total) {
        this.total = total;
        if (total == -1) {
            pages = 1;
            return;
        }
        if (pageSize > 0) {
            pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        } else {
            pages = 0;
        }
    }

    /**
     * 计算导航页
     */
    private void calcNavigatepageNums() {
        //当总页数小于或等于导航页码数时
        if (pages <= navigatePages) {
            navigatepageNums = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages];
            int startNum = pageNum - navigatePages / 2;
            int endNum = pageNum + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
    }

    private int navigateFirstPage;

    private int navigateLastPage;

    /**
     * 计算前后页，第一页，最后一页
     */
    private void calcPage() {
        if (navigatepageNums != null && navigatepageNums.length > 0) {
            navigateFirstPage = navigatepageNums[0];
            navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
            if (pageNum > 1) {
                prePage = pageNum - 1;
            }
            if (pageNum < pages) {
                nextPage = pageNum + 1;
            }
        }
    }

    private boolean isFirstPage;
    private boolean isLastPage;

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
        hasPreviousPage = pageNum > 1;
        hasNextPage = pageNum < pages;
    }
}
