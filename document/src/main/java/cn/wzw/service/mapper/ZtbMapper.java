package cn.wzw.service.mapper;

import cn.wzw.domain.Ztb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZtbMapper extends BaseMapper<Ztb> {

    @Select("select body from ztb order by create_time desc limit 0,1")
    List<Ztb> select();
}
