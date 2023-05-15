package ${customBasePackage}.service;

import ${customBasePackage}.entity.${entity};
import ${customBasePackage}.mapper.${table.mapperName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> {

}