package cn.starry.freenessy.web.generator;

public interface CodeGenerator<T> extends GeneratorException {

    void run(T conf);
}
