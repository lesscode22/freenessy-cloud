package cn.starry.freenessy.system;

import cn.starry.freenessy.web.generator.AbstractCodeGenerator;
import cn.starry.freenessy.web.generator.GeneratorConf;

public class LocalCodeGenerator extends AbstractCodeGenerator<GeneratorConf> {

    public static void main(String[] args) {
        GeneratorConf conf = new GeneratorConf()
                .setUrl("*")
                .setUsername("*")
                .setPassword("*")
                .setTableFormat("sys_user");
        LocalCodeGenerator generator = new LocalCodeGenerator();
        generator.run(conf);
    }

    @Override
    public void run(GeneratorConf conf) {
        super.run(conf);
    }
}
