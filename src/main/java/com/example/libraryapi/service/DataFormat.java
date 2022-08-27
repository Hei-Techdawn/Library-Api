package com.example.libraryapi.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFormat<T> {
    private int lastPage;
    private int currentPage;
    private List<T> data;
}
