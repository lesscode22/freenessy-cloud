package cn.starry.freenessy.web.generator;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class GeneratorConf {

    private String url;
    private String username;
    private String password;

    // 元数据目录, 默认会取cn.starry.freenessy下的第一个metadata目录
    private String dir;
    // 表名集合
    private List<String> tableList = new ArrayList<>();
    // 表名模糊查询, 用%表示前模糊或者后模糊查询, 例如: data%, %user
    private String tableFormat;
}
