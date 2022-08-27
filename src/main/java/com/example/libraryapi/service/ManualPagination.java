package com.example.libraryapi.service;

import java.util.List;

public class ManualPagination {
    public static <T> List<T> getPage(List<T> list, int page) {
        int lastPage = (list.size() / 10);
        if (page < lastPage) {
            return list.subList(10 * page, 10 * page + 10);
        }
        return list.subList(0, 10);
    }
}
