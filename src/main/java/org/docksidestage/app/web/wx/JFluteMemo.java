/*
 * Copyright 2015-2018 the original author or authors.
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
package org.docksidestage.app.web.wx;

/**
 * @author jflute
 */
public class JFluteMemo {

    // TODO jflute lastaflute: [A] fitting: {}/@word/{} with OptionalThing parameter
    // TODO jflute lastaflute: [A] fitting: ScalarResponse instead of JsonResponse<String>
    // TODO jflute lastaflute: [A] fitting: StreamResponse can be treated as ApiResponse option?
    // TODO jflute lastaflute: [A] fitting: ValidateTypeFailure for JSON
    // TODO jflute lastaflute: [A] fitting: ValidateTypeFailure for List element
    // TODO jflute lastaflute: [A] fitting: FormMappingOption provides date pattern
    // TODO jflute lastaflute: [A] fitting: TypeLocalDate can specify date pattern
    // TODO jflute lastaflute: [B] fitting: @Execute(camelToUrlElement=true)
    // TODO jflute lastaflute: [B] fitting: Gson, Enum failure, null to exception
    // TODO jflute lastaflute: [B] fitting: type-safe messages property
    // TODO jflute lastaflute: [B] fitting: Form, Result, Optional property (related to Gson, Validator)
    // TODO jflute lastaflute: [B] fitting: SlaveDB follow
    // TODO jflute lastaflute: [B] fitting: throw 404 when No routing action by option?
    // TODO jflute lastaflute: [B] fitting: Thymeleaf: render parts manager for Ajax
    // TODO jflute lastaflute: [B] example: example of REST orleans
    // TODO jflute lastaflute: [B] fitting: MailFlute, parameter property comment
    // TODO jflute lastaflute: [B] fitting: Lasta Di, Di xml's expression new injection
    // TODO jflute lastaflute: [B] fitting: Lasta DI, support lazy component
    // TODO jflute lastaflute: [B] fitting: Lasta Job, Job Timeout
    // TODO jflute lastaflute: [B] fitting: Lasta Job, Job Order
    // TODO jflute lastaflute: [B] fitting: Lasta Job, Initial Boot
    // TODO jflute lastaflute: [B] fitting: Lasta Job, Boot from Previous Boot
    // TODO jflute lastaflute: [B] fitting: SSL: https://gist.github.com/taknb2nch/fdf048908ad98e6a8829ce9670e0bd0c
    // TODO jflute lastaflute: [B] function: UTFlute stop directly entity for nested property
    // TODO jflute lastaflute: [B] function: SessionSharedStorage, cookie example
    // TODO jflute lastaflute: [B] fitting: JettyBoot SSL: https://gist.github.com/taknb2nch/fdf048908ad98e6a8829ce9670e0bd0c
    // TODO jflute lastaflute: [B] document: LastaDoc, design and action driven
    // TODO jflute lastaflute: [B] fitting: ??? LastaFilter: passing option
    // TODO jflute lastaflute: [B] example: ??? use IndependentProcessor instead of Runnable
    // TODO jflute lastaflute: [B] function: Form/Body/Bean property as OptionalThing
    // TODO jflute lastaflute: [B] thinking: Form constructor mapping
    // TODO jflute lastaflute: [B] fitting: improve message, TypeLocalDate for LocalDate (and javadoc)
    // TODO jflute lastaflute: [B] fitting: TypeLocalDate's message {propertyType} cannot be resolved

    // TODO jflute lastaflute: [C] function: Gson, indefined property check
    // TODO jflute lastaflute: [C] document: DI list, ContainerManagementServlet
    // TODO jflute lastaflute: [C] fitting: Heroku, JDBC URL
    // TODO jflute lastaflute: [C] fitting: LoginCredential, hibernate validator?
    // TODO jflute lastaflute: [C] fitting: UTFlute, lonely new404()
    // TODO jflute lastaflute: [C] fitting: ??? multipart, get submit error message
    // TODO jflute lastaflute: [C] function: ??? original type mapping interface
    // TODO jflute lastaflute: [C] fitting: ??? Form, Bean public field @Resource
    // TODO jflute lastaflute: [C] function: ??? thymeleaf, optionMap?

    // TODO jflute lastaflute: [D] fitting: Mayaa: viewPrefix
    // TODO jflute lastaflute: [D] fitting: LastaMixer2
    // TODO jflute lastaflute: [D] function: getter way, validation error order
    // TODO jflute lastaflute: [D] fitting: Taglib: errors escape
    // TODO jflute lastaflute: [D] fitting: multipart, submit button name handling
    // TODO jflute lastaflute: [D] fitting: action, form package OUT
    // TODO jflute lastaflute: [D] fitting: improve AsyncManager
    // TODO jflute lastaflute: [D] fitting: maven plugin for executable war
    // TODO jflute lastaflute: [D] fitting: FreeGen maven execute performance tuning
    // TODO jflute lastaflute: [D] function: HtmlFailureHook, StreamFailureHook
    // TODO jflute lastaflute: [D] fitting: Lasta Di :: private AOP or exception
    // TODO jflute lastaflute: [D] fitting: Hotdeploy, Action's inner class throws class cast exception in session
    // TODO jflute lastaflute: [D] fitting: LastaTaglib :: la:number, la:date
    // TODO jflute lastaflute: [D] fitting: Lasta Di :: throw exception if +component.xml's component not found

    // TODO jflute lastaflute: [E] fitting: ActionAdjustment, ActionType URL filter for numeric URL
    // TODO jflute lastaflute: [E] function: FreeGen for execute method and form property definition
    // TODO jflute lastaflute: [E] fitting: nest JSON debug challenge
    // TODO jflute lastaflute: [E] function: asRedirect(ProductListAciton.class).index(1);
    // TODO jflute lastaflute: [E] fitting: want to judge validation target nested bean but no valid annotation
    // TODO jflute lastaflute: [E] function: AsyncResponse for ElasticSearch? (future)
    // TODO jflute lastaflute: [E] thinking: upload Servlet MultipartConfig
    // TODO jflute lastaflute: [E] function: JSON bean item exitence check
    // TODO jflute lastaflute: [E] function: x meta(form).memberName
    // TODO jflute lastaflute: [E] fitting: jetty + tag files + hotdeploy problem, URLClassLoader, TagFileProcessor
    // TODO jflute lastaflute: [E] fitting: Form,Body property OptionalThing? *thinking: Gson, HibernateValidator
    // TODO jflute lastaflute: [E] improvement: Lasta DI :: redefiner, ++.xml logging path adjustment
    // TODO jflute lastaflute: [E] improvement: Lasta DI :: cooldeploy-autoregister twice new? when cool, by +dicon?

    // TODO jflute lastaflute: [F] function: TimeManager typical business date
    // TODO jflute lastaflute: [F] function: immutable Form, Body *thinking
    // TODO jflute lastaflute: [F] function: post/get limitation, verifyHttpMethod()? annotation?
    // TODO jflute lastaflute: [F] function: restful, for promotion

    // TODO jflute lastaflute: [G] fitting: before checking index size, not to be out of memory *thinking
    // TODO jflute lastaflute: [G] function: urlPattern="{}/{$class}/{$method}" *thinking
    // TODO jflute lastaflute: [G] test: unit test for MDCListener, ActionFormMapperTest
}
