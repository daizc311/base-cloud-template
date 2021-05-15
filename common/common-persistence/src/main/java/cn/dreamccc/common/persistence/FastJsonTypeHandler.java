package cn.dreamccc.common.persistence;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * <h2>Mybatis的FastJson类型处理器</h2>
 *
 * @author Daizc-kl
 * @date 2020-07-27 17:54:23
 */
@Slf4j
@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class FastJsonTypeHandler extends AbstractJsonTypeHandler<JSONObject> {

    @Override
    protected JSONObject parse(String json) {
        return JSONObject.parseObject(json);
    }

    @Override
    protected String toJson(JSONObject obj) {
        return JSONObject.toJSONString(obj);
    }

}
