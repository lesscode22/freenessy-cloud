package cn.starry.freenessy.web.generator.mybatisplus;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.starry.freenessy.base.util.DateUtil;
import cn.starry.freenessy.web.generator.CodeGenerator;
import cn.starry.freenessy.web.generator.GeneratorConf;
import cn.starry.freenessy.web.pojo.BaseEntity;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Collections;

public class MybatisPlusGenerator implements CodeGenerator<GeneratorConf> {

    @Override
    public void run(GeneratorConf conf) {

        // 输出目录
        String projectPath = FileUtil.getWebRoot().getAbsolutePath();
        String today = DateUtil.today("ddHHmmss");
        String outDir = projectPath + File.separator + today;

        FastAutoGenerator.create(conf.getUrl(), conf.getUsername(), conf.getPassword())
                .globalConfig(builder -> {
                    builder.author("Auto Generator")        // 设置作者
                            .disableOpenDir()   // 禁止打开输出目录
                            .outputDir(outDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(conf.getDir()) // 设置父包名
                            .entity("entity")
                            .mapper("mapper");
                })
//                .injectionConfig(builder -> {
//                    // 自定义变量
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("customBasePackage", conf.getDir());
//                    builder.customMap(map);
//                })
                .templateConfig(builder ->
                        builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE,
                                        TemplateType.SERVICE_IMPL, TemplateType.XML)
                )
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .formatFileName("%sDO")
                            .enableLombok()
                            .enableChainModel()
                            .addIgnoreColumns("id", "create_time", "update_time", "create_by", "update_by")
                            .enableTableFieldAnnotation()
                            .disableSerialVersionUID()
                            .superClass(BaseEntity.class)
                            .serviceBuilder()
                            .formatServiceFileName("%sService");

                    if (StrUtil.isNotBlank(conf.getTableFormat())) {
                        if (!conf.getTableFormat().startsWith("%") && !conf.getTableFormat().endsWith("%")) {
                            conf.setTableList(Collections.singletonList(conf.getTableFormat()));
                        } else {
                            builder.likeTable(transform(conf.getTableFormat()));
                        }
                    }
                    if (!conf.getTableList().isEmpty()) {
                        builder.addInclude(conf.getTableList()); // 设置需要生成的表名
                    }
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    private LikeTable transform(String value) {
        if (value.startsWith("%") && value.endsWith("%")) {
            return new LikeTable(value);
        } else if (value.startsWith("%")) {
            return new LikeTable(value, SqlLike.LEFT);
        } else {
            return new LikeTable(value, SqlLike.RIGHT);
        }
    }
}
