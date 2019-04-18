
create or replace function requiredJudgeRatesCount ()
returns integer as $requiredCount$
begin
   return 3;
end;
$requiredCount$ language plpgsql;
