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

SELECT s.surname FROM (progress AS p
INNER JOIN study_plan AS sp
ON p.discipline_id = sp.id
INNER JOIN student AS s
ON s.id = p.student_id)
WHERE sp.semester = ((2004 - s.entrance_year) * 2) + 2
GROUP BY s.id
HAVING AVG(grade) >= 4.5;

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