package cn.wzw.service;

import cn.wzw.domain.Ztb;

import java.util.List;

public interface ZtbService {
    void add(String content);

    List<Ztb> list();
}
