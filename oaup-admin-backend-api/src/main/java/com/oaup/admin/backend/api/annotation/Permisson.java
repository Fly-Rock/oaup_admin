package com.oaup.admin.backend.api.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permisson {

    String main();
    String sub();

}
