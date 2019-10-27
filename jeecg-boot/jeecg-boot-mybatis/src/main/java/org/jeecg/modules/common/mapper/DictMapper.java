package org.jeecg.modules.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.common.dto.DictInfo;
import org.jeecg.modules.common.entity.Dict;

import java.util.List;

/**
 * <p>
 * 数据字典表 Mapper 接口
 * </p>
 *
 * @author jeebase
 * @since 2018-10-28
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 查询字典列表
     * @param parentId
     * @return
     */
    List<DictInfo> queryDictTreeProc(Integer parentId);
}
