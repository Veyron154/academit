SELECT COUNT(*) AS count_of_students FROM student
WHERE form_of_tuition = 'full_time';

SELECT hours_quantity, form_of_examination FROM study_plan
WHERE discipline = 'maths';

SELECT s.surname, a.ag FROM student AS s
INNER JOIN (SELECT student_id, AVG(grade) AS ag FROM progress
GROUP BY student_id
ORDER BY ag DESC
LIMIT 5) AS a
ON s.id = a.student_id;

SELECT s.surname FROM student AS s
INNER JOIN (SELECT student_id, AVG(grade) AS ag FROM progress AS p
INNER JOIN (SELECT id FROM study_plan
WHERE semester = 2) AS q
ON p.discipline_id = q.id
GROUP BY student_id
HAVING ag >= 4.5) AS a
ON s.id = a.student_id;

SELECT s.surname FROM student AS s
INNER JOIN (SELECT student_id, AVG(grade) AS ag FROM progress
GROUP BY student_id) AS a
ON s.id = a.student_id
WHERE a.ag >= (SELECT AVG(ag) FROM (SELECT student_id, AVG(grade) AS ag FROM progress
GROUP BY student_id) as aag);

SELECT sp.discipline FROM study_plan AS sp
INNER JOIN (SELECT discipline_id, AVG(grade) AS ag FROM progress
GROUP BY discipline_id
ORDER BY ag DESC
LIMIT 1) AS a
ON sp.id = a.discipline_id;