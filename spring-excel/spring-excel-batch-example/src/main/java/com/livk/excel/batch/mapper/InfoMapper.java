package com.livk.excel.batch.mapper;

import com.livk.excel.batch.entity.Info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * InfoMapper
 * </p>
 *
 * @author livk
 */
@Mapper
public interface InfoMapper {
    void saveBatch(List<Info> infos);
}
