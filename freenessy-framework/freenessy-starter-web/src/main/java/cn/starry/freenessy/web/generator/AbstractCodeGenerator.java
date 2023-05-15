package cn.starry.freenessy.web.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;
import cn.hutool.core.util.StrUtil;
import cn.starry.freenessy.base.exception.Asserts;
import cn.starry.freenessy.web.generator.mybatisplus.MybatisPlusGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public abstract class AbstractCodeGenerator<T extends GeneratorConf> implements CodeGenerator<GeneratorConf> {

    private static final String META_DIR_NAME = "metadata";

    CodeGenerator<GeneratorConf> generator = new MybatisPlusGenerator();

    public void setGenerator(CodeGenerator<GeneratorConf> generator) {
        this.generator = generator;
    }

    public void run(T conf) {
        conf.setDir(getDir());
        generator.run(conf);
    }

    protected String getDir() {
        String basePackage = "cn.starry.freenessy";
        String baseDirPath = basePackage.replace(".", File.separator);
        String projectPath = FileUtil.getWebRoot().getAbsolutePath();
        String basePath = "/src/main/java/" + baseDirPath;
        File root = new File(projectPath + basePath);
        Asserts.notNull(root, rootDirNotExist);

        final StringBuilder target = new StringBuilder();
        PathUtil.walkFiles(root.toPath(), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (dir.getFileName().toString().equals(META_DIR_NAME)) {
                    target.append(dir.toAbsolutePath());
                    return FileVisitResult.TERMINATE;
                }
                return super.postVisitDirectory(dir, exc);
            }
        });

        if (StrUtil.isEmpty(target)) {
            return basePackage + "." + META_DIR_NAME;
        }
        String replace = StrUtil.subAfter(target.toString(), baseDirPath, true).replace(File.separator, ".");
        return basePackage + replace;
    }

}
