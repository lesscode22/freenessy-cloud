package cn.starry.freenessy.system.modules.user;

import cn.starry.freenessy.base.resp.Result;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system/user")
public class AppUserController {

    @GetMapping("dataList")
    public Result<List<Map<String, Object>>> dataList() {

        Map<String, Object> map = new HashMap<>();
        map.put("test", "AA");
        return Result.ok(Lists.newArrayList(map));
    }
}
