package cn.wzw.service.impl;

import cn.wzw.domain.Ztb;
import cn.wzw.service.ZtbService;
import cn.wzw.service.mapper.ZtbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZtbServiceImpl implements ZtbService {
    @Autowired
    private ZtbMapper ztbMapper;

    @Override
    public void add(String content) {
        Ztb ztb = new Ztb();
        ztb.setBody(content);
        ztbMapper.insert(ztb);
    }

    @Override
    public List<Ztb> list() {
        return ztbMapper.select();
    }
}
