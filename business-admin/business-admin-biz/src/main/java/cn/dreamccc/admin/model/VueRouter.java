package cn.dreamccc.admin.model;

import cn.dreamccc.common.persistence.FastJsonTypeHandler;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 对Vue-Router的封装
 *
 * <a>https://router.vuejs.org/zh/guide/</a>
 *
 * @author DreamSpace
 */
@Data
@TableName(value = "sys_menu")
public class VueRouter {

    @TableField("router_name")
    private String name;

    @TableField("path")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;

    /**
     * like this:
     * <meta>@/pages/dashboard/analysis</meta>
     */
    @TableField("router_component")
    private String component;

    /**
     * like this:
     * <meta>@/pages/dashboard/analysis</meta>
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(value = "router_component",typeHandler = FastJsonTypeHandler.class)
    private JSONObject authority;

    /**
     * like this:
     * <meta>/login</meta>
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField("router_redirect")
    private String redirect;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField("router_alias")
    private String alias;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(value = "meta",typeHandler = FastJsonTypeHandler.class)
    private JSONObject meta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Collection<VueRouter> children;

    public VueRouter() {
        children = new ArrayList<>();
    }
}
