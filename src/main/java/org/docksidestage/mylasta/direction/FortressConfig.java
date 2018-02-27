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
package org.docksidestage.mylasta.direction;

import org.docksidestage.mylasta.direction.FortressEnv;
import org.lastaflute.core.direction.exception.ConfigPropertyNotFoundException;

/**
 * @author FreeGen
 */
public interface FortressConfig extends FortressEnv, org.docksidestage.mylasta.direction.MyFortressProp {

    /** The key of the configuration. e.g. fortress */
    String DOMAIN_NAME = "domain.name";

    /** The key of the configuration. e.g. Fortress */
    String DOMAIN_TITLE = "domain.title";

    /** The key of the configuration. e.g. yyyy/MM/dd */
    String APP_STANDARD_DATE_PATTERN = "app.standard.date.pattern";

    /** The key of the configuration. e.g. yyyy/MM/dd HH:mm:ss */
    String APP_STANDARD_DATETIME_PATTERN = "app.standard.datetime.pattern";

    /** The key of the configuration. e.g. HH:mm:ss */
    String APP_STANDARD_TIME_PATTERN = "app.standard.time.pattern";

    /** The key of the configuration. e.g. / */
    String COOKIE_DEFAULT_PATH = "cookie.default.path";

    /** The key of the configuration. e.g. 31556926 */
    String COOKIE_DEFAULT_EXPIRE = "cookie.default.expire";

    /** The key of the configuration. e.g. 315360000 */
    String COOKIE_ETERNAL_EXPIRE = "cookie.eternal.expire";

    /** The key of the configuration. e.g. FTR */
    String COOKIE_REMEMBER_ME_FORTRESS_KEY = "cookie.remember.me.fortress.key";

    /** The key of the configuration. e.g. 4 */
    String PAGING_PAGE_SIZE = "paging.page.size";

    /** The key of the configuration. e.g. 3 */
    String PAGING_PAGE_RANGE_SIZE = "paging.page.range.size";

    /** The key of the configuration. e.g. true */
    String PAGING_PAGE_RANGE_FILL_LIMIT = "paging.page.range.fill.limit";

    /** The key of the configuration. e.g. escaped "あいうえお" and plain "あいうえお" */
    String WHITEBOX_CONFIG_JAPANESE_CAOS = "whitebox.config.japanese.caos";

    /**
     * Get the value of property as {@link String}.
     * @param propertyKey The key of the property. (NotNull)
     * @return The value of found property. (NotNull: if not found, exception)
     * @throws ConfigPropertyNotFoundException When the property is not found.
     */
    String get(String propertyKey);

    /**
     * Is the property true?
     * @param propertyKey The key of the property which is boolean type. (NotNull)
     * @return The determination, true or false. (if not found, exception)
     * @throws ConfigPropertyNotFoundException When the property is not found.
     */
    boolean is(String propertyKey);

    /**
     * Get the value for the key 'domain.name'. <br>
     * The value is, e.g. fortress <br>
     * comment: The name of domain (means this application) as identity
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getDomainName();

    /**
     * Get the value for the key 'domain.title'. <br>
     * The value is, e.g. Fortress <br>
     * comment: The title of domain (means this application) for logging
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getDomainTitle();

    /**
     * Get the value for the key 'app.standard.date.pattern'. <br>
     * The value is, e.g. yyyy/MM/dd <br>
     * comment: The pattern of date as application standard used by e.g. Thymeleaf handy.format()
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getAppStandardDatePattern();

    /**
     * Get the value for the key 'app.standard.datetime.pattern'. <br>
     * The value is, e.g. yyyy/MM/dd HH:mm:ss <br>
     * comment: The pattern of date-time as application standard used by e.g. Thymeleaf handy.format()
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getAppStandardDatetimePattern();

    /**
     * Get the value for the key 'app.standard.time.pattern'. <br>
     * The value is, e.g. HH:mm:ss <br>
     * comment: The pattern of time as application standard used by e.g. Thymeleaf handy.format()
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getAppStandardTimePattern();

    /**
     * Get the value for the key 'cookie.default.path'. <br>
     * The value is, e.g. / <br>
     * comment: The default path of cookie (basically '/' if no context path)
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getCookieDefaultPath();

    /**
     * Get the value for the key 'cookie.default.expire'. <br>
     * The value is, e.g. 31556926 <br>
     * comment: The default expire of cookie in seconds e.g. 31556926: one year, 86400: one day
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getCookieDefaultExpire();

    /**
     * Get the value for the key 'cookie.default.expire' as {@link Integer}. <br>
     * The value is, e.g. 31556926 <br>
     * comment: The default expire of cookie in seconds e.g. 31556926: one year, 86400: one day
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     * @throws NumberFormatException When the property is not integer.
     */
    Integer getCookieDefaultExpireAsInteger();

    /**
     * Get the value for the key 'cookie.eternal.expire'. <br>
     * The value is, e.g. 315360000 <br>
     * comment: The eternal expire of cookie in seconds e.g. 315360000: ten year, 86400: one day
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getCookieEternalExpire();

    /**
     * Get the value for the key 'cookie.eternal.expire' as {@link Integer}. <br>
     * The value is, e.g. 315360000 <br>
     * comment: The eternal expire of cookie in seconds e.g. 315360000: ten year, 86400: one day
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     * @throws NumberFormatException When the property is not integer.
     */
    Integer getCookieEternalExpireAsInteger();

    /**
     * Get the value for the key 'cookie.remember.me.fortress.key'. <br>
     * The value is, e.g. FTR <br>
     * comment: The cookie key of auto-login for Fortress
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getCookieRememberMeFortressKey();

    /**
     * Get the value for the key 'paging.page.size'. <br>
     * The value is, e.g. 4 <br>
     * comment: The size of one page for paging
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getPagingPageSize();

    /**
     * Get the value for the key 'paging.page.size' as {@link Integer}. <br>
     * The value is, e.g. 4 <br>
     * comment: The size of one page for paging
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     * @throws NumberFormatException When the property is not integer.
     */
    Integer getPagingPageSizeAsInteger();

    /**
     * Get the value for the key 'paging.page.range.size'. <br>
     * The value is, e.g. 3 <br>
     * comment: The size of page range for paging
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getPagingPageRangeSize();

    /**
     * Get the value for the key 'paging.page.range.size' as {@link Integer}. <br>
     * The value is, e.g. 3 <br>
     * comment: The size of page range for paging
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     * @throws NumberFormatException When the property is not integer.
     */
    Integer getPagingPageRangeSizeAsInteger();

    /**
     * Get the value for the key 'paging.page.range.fill.limit'. <br>
     * The value is, e.g. true <br>
     * comment: The option 'fillLimit' of page range for paging
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getPagingPageRangeFillLimit();

    /**
     * Is the property for the key 'paging.page.range.fill.limit' true? <br>
     * The value is, e.g. true <br>
     * comment: The option 'fillLimit' of page range for paging
     * @return The determination, true or false. (if not found, exception but basically no way)
     */
    boolean isPagingPageRangeFillLimit();

    /**
     * Get the value for the key 'whitebox.config.japanese.caos'. <br>
     * The value is, e.g. escaped "あいうえお" and plain "あいうえお" <br>
     * @return The value of found property. (NotNull: if not found, exception but basically no way)
     */
    String getWhiteboxConfigJapaneseCaos();

    /**
     * The simple implementation for configuration.
     * @author FreeGen
     */
    public static class SimpleImpl extends FortressEnv.SimpleImpl implements FortressConfig {

        /** The serial version UID for object serialization. (Default) */
        private static final long serialVersionUID = 1L;

        public String getDomainName() {
            return get(FortressConfig.DOMAIN_NAME);
        }

        public String getDomainTitle() {
            return get(FortressConfig.DOMAIN_TITLE);
        }

        public String getAppStandardDatePattern() {
            return get(FortressConfig.APP_STANDARD_DATE_PATTERN);
        }

        public String getAppStandardDatetimePattern() {
            return get(FortressConfig.APP_STANDARD_DATETIME_PATTERN);
        }

        public String getAppStandardTimePattern() {
            return get(FortressConfig.APP_STANDARD_TIME_PATTERN);
        }

        public String getCookieDefaultPath() {
            return get(FortressConfig.COOKIE_DEFAULT_PATH);
        }

        public String getCookieDefaultExpire() {
            return get(FortressConfig.COOKIE_DEFAULT_EXPIRE);
        }

        public Integer getCookieDefaultExpireAsInteger() {
            return getAsInteger(FortressConfig.COOKIE_DEFAULT_EXPIRE);
        }

        public String getCookieEternalExpire() {
            return get(FortressConfig.COOKIE_ETERNAL_EXPIRE);
        }

        public Integer getCookieEternalExpireAsInteger() {
            return getAsInteger(FortressConfig.COOKIE_ETERNAL_EXPIRE);
        }

        public String getCookieRememberMeFortressKey() {
            return get(FortressConfig.COOKIE_REMEMBER_ME_FORTRESS_KEY);
        }

        public String getPagingPageSize() {
            return get(FortressConfig.PAGING_PAGE_SIZE);
        }

        public Integer getPagingPageSizeAsInteger() {
            return getAsInteger(FortressConfig.PAGING_PAGE_SIZE);
        }

        public String getPagingPageRangeSize() {
            return get(FortressConfig.PAGING_PAGE_RANGE_SIZE);
        }

        public Integer getPagingPageRangeSizeAsInteger() {
            return getAsInteger(FortressConfig.PAGING_PAGE_RANGE_SIZE);
        }

        public String getPagingPageRangeFillLimit() {
            return get(FortressConfig.PAGING_PAGE_RANGE_FILL_LIMIT);
        }

        public boolean isPagingPageRangeFillLimit() {
            return is(FortressConfig.PAGING_PAGE_RANGE_FILL_LIMIT);
        }

        public String getWhiteboxConfigJapaneseCaos() {
            return get(FortressConfig.WHITEBOX_CONFIG_JAPANESE_CAOS);
        }
    }
}
