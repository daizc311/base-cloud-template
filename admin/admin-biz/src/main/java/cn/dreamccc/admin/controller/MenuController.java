package cn.dreamccc.admin.controller;

import cn.dreamccc.admin.mapper.VueRouterMapper;
import cn.dreamccc.admin.model.VueRouter;
import cn.dreamccc.common.model.DResp;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/menu")
@Api(value = "菜单", tags = "菜单")
public class MenuController {

    @Autowired
    VueRouterMapper vueRouterMapper;

    @RequestMapping("/listAllRouter")
    public DResp<Object> listAllRouter() {

        Map<String, VueRouter> routerMap = vueRouterMapper.selectList(Wrappers.emptyWrapper()).stream().collect(Collectors.toMap(VueRouter::getName, v -> v));
//        return DResp.data(vueRouterMapper.selectList(Wrappers.emptyWrapper()));
        return DResp.data(routerMap);
    }

    @RequestMapping("/listMenu")
    public DResp<?> listMenu(HttpServletRequest request) {


        final var data = "{" +
                "    router: 'root'," +
                "    children: [" +
                "      {" +
                "        router: 'dashboard'," +
                "        children: ['workplace', 'analysis']," +
                "      }," +
                "      {" +
                "        router: 'form'," +
                "        children: ['basicForm', 'stepForm', 'advanceForm']" +
                "      }," +
                "      {" +
                "        router: 'basicForm'," +
                "        name: '验权表单'," +
                "        icon: 'file-excel'," +
                "        authority: 'queryForm'" +
                "      }," +
                "      {" +
                "        router: 'antdv'," +
                "        path: 'antdv'," +
                "        name: 'Ant Design Vue'," +
                "        icon: 'ant-design'," +
                "        link: 'https://www.antdv.com/docs/vue/introduce-cn/'" +
                "      }," +
                "      {" +
                "        router: 'document'," +
                "        path: 'document'," +
                "        name: '使用文档'," +
                "        icon: 'file-word'," +
                "        link: 'https://iczer.gitee.io/vue-antd-admin-docs/'" +
                "      }" +
                "    ]" +
                "  }";

        return DResp.data(JSON.parse(data));
    }

}
