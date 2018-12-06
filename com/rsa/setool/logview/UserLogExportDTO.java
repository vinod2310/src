package com.rsa.setool.logview;

import java.io.Serializable;
import java.util.List;

public class UserLogExportDTO implements Serializable {
    private static final long serialVersionUID = 8111440613331162759L;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int currentPage;
    private List<UserLogExportEntryDTO> userEventLogExportEntries;

    public UserLogExportDTO() {
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public String toString() {
        return "UserLogExportDTO{totalPages=" + this.totalPages + ", totalElements=" + this.totalElements +
                ", pageSize=" + this.pageSize + ", elements=" + this.userEventLogExportEntries + '}';
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<UserLogExportEntryDTO> getUserEventLogExportEntries() {
        return userEventLogExportEntries;
    }

    public void setUserEventLogExportEntries(List<UserLogExportEntryDTO> userEventLogExportEntries) {
        this.userEventLogExportEntries = userEventLogExportEntries;
    }
}