package cn.starry.freenessy.web.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@ComponentScan(value = {"cn.starry.freenessy.web"})
@MapperScan({"cn.starry.freenessy.*.metadata.mapper", "cn.starry.freenessy.*.modules.*.mapper"})
@JsonComponent
@AutoConfigureBefore(ValidationAutoConfiguration.class) // 在自动配置之前加载 Validator 快速失败的bean, 否则无效
public class CommonWebConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilder() {
        return builder -> {
            builder.failOnUnknownProperties(false)  // JSON串含有未知字段时, 反序列化依旧可以成功
                    .failOnEmptyBeans(false)        // 如果一个类没有public的方法或属性时序列化为空JSON串
            ;
            // 避免精度溢出
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            // Date类型格式
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            builder.dateFormat(df);
            // LocalDate, LocalDateTime 格式
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        };
    }

    /**
     * 参数校验快速失败
     */
    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure().failFast(true)
                .buildValidatorFactory().getValidator();
    }
}
