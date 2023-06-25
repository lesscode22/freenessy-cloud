package cn.starry.freenessy.web.generator;

public interface CodeGenerator<T> {

    void run(T conf);
}
