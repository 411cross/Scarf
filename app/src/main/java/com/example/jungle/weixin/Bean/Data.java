package com.example.jungle.weixin.Bean;

/**
 * Created by jungle on 2017/11/7.
 */

public class Data {
    public class pageData{
        public int count;
        public int currentPage;
    }
    public pageData pageData;
    public listData[] list;
    public class listData{
        public int id;
    }
}
