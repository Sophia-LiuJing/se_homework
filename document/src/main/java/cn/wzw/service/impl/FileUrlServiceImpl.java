package cn.wzw.service.impl;

import cn.wzw.domain.FileUrl;
import cn.wzw.service.FileUrlService;
import cn.wzw.service.mapper.FileUrlMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUrlServiceImpl implements FileUrlService {


    @Autowired
    private FileUrlMapper mapper;

    @Override
    public List<FileUrl> list() {
        return mapper.selectList(Wrappers.<FileUrl>lambdaQuery()
                .select(FileUrl::getUrl)
                .orderByDesc(FileUrl::getId)
        );
    }

    @Override
    public void add(String suffix) {
        FileUrl fileUrl = new FileUrl();
        fileUrl.setUrl(suffix);
        mapper.insert(fileUrl);
    }

    @Override
    public void delete(String path) {
        mapper.delete(Wrappers.<FileUrl>lambdaQuery()
            .eq(FileUrl::getUrl,path)
        );
    }
}
