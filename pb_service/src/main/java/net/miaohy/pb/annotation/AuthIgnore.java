package net.miaohy.pb.annotation;

import java.lang.annotation.*;


/**
 * 效验忽略
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
