package cn.wzw.service;


import cn.wzw.domain.FileUrl;

import java.util.List;

public interface FileUrlService {

    List<FileUrl> list();

    void add(String suffix);

    void delete(String path);
}
