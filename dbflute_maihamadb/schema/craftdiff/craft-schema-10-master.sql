
-- =======================================================================================
--                                                                             Master Data
--                                                                             ===========
-- #df:assertEquals(MemberStatus)#
select MEMBER_STATUS_CODE, MEMBER_STATUS_NAME, DISPLAY_ORDER
  from MEMBER_STATUS
 order by MEMBER_STATUS_CODE
;

-- #df:assertTableEquals(TableCls, suffix:_STATUS, except:DESCRIPTION)#
select 'dummy'
;
