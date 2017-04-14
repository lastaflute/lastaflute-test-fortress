
-- #df:changeUser(system)#
-- #df:checkUser(mainSchema)#
create database /*$dfprop.mainFortress*/;

-- #df:reviveUser()#
-- #df:checkUser(mainUser, grant)#
grant all privileges on /*$dfprop.mainFortress*/.*
  to /*$dfprop.mainUser*/@localhost identified by '/*$dfprop.mainPassword*/';

flush privileges;