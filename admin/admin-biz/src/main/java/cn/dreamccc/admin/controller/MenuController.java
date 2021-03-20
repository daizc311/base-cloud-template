package cn.dreamccc.admin.controller;

import cn.dreamccc.common.model.DResp;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/menu")
@Api(value = "菜单", tags = "菜单")
public class MenuController {


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
