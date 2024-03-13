SELECT c.*, IFNULL(a.street,''), IFNULL(a.strNumber,''), IFNULL(a.postalCode,'')
FROM citizen c
left join address a on a.citizen_id=c.id;