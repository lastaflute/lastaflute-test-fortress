/*
 * Copyright 2015-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.masterslave.resortlinedb;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
// [tips] this annotation is for slave-basis annotation style
// so delete this class if you use slave-basis on-demand style
// _/_/_/_/_/_/_/_/_/_/
/**
 * The annotation for access to master explicitly.
 * <pre>
 * &#064;[Schema]MasterDB
 * pulic class SeaAction extends ... {
 * }
 * 
 *  or
 * 
 * pulic class SeaAction extends ... {
 * 
 *     public JsonResponse... index(...) { // select only
 *     } 
 * 
 *     &#064;[Schema]MasterDB
 *     public void update(...) {
 *     } 
 * }
 * </pre>
 * @author jflute
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RUNTIME)
@Documented
public @interface ResortlineMasterDB {
}
