SELECT t.*
from (
         SELECT c.*, IFNULL(a.street,'') as 'street', IFNULL(a.strNumber,'')  as 'strNumber', IFNULL(a.postalCode,'')  as 'postalCode'
         FROM citizen c
         left join address a on a.citizen_id=c.id) as t
where t.id like ?
  and t.first_name like ?
  and t.last_name like ?
  and t.gender like ?
  and t.dob like ?
  and t.afm like ?
  and t.street like ?
  and t.strNumber like ?
  and t.postalCode like ?;