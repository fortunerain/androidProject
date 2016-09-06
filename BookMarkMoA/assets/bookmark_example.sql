SELECT _id, url, title, visits, favicon, bookmark 
FROM (SELECT touch_icon, NULL AS user_entered, 
CASE WHEN bookmarks.title IS NOT NULL THEN bookmarks.title ELSE history.title 
END AS title, thumbnail, 
CASE WHEN bookmarks._id IS NOT NULL THEN 1 ELSE 0 
END AS bookmark, 
CASE WHEN bookmarks._id IS NOT NULL THEN bookmarks._id ELSE history._id 
END AS _id, 
favicon, history.created AS created, date, visits, history.url AS url 
FROM history 
LEFT OUTER JOIN 
(SELECT * FROM bookmarks WHERE (deleted = 0)) bookmarks 
ON history.url = bookmarks.url 
LEFT OUTER JOIN images 
ON history.url = images.url_key 
UNION ALL 
SELECT touch_icon, NULL AS user_entered, title, thumbnail, 1 AS bookmark, 
_id, favicon, created, NULL AS date, 0 AS visits, url 
FROM bookmarks 
LEFT OUTER JOIN images 
ON bookmarks.url = images.url_key 
WHERE (deleted = 0 AND url NOT IN (SELECT url FROM history))) 
WHERE (title = naver) ORDER BY visits desc