delimiter //

CREATE FUNCTION get_count_students_with_form_of_tuition (form VARCHAR(20)) RETURNS INT
BEGIN
	DECLARE count_of_students INT;
	SELECT COUNT(*) INTO count_of_students FROM student
	WHERE form_of_tuition = form;
	RETURN count_of_students;
END//

CREATE PROCEDURE get_data_about_discipline (IN discipline_name VARCHAR(100))
BEGIN
	SELECT hours_quantity, form_of_examination FROM study_plan
	WHERE discipline = discipline_name;
END//

CREATE PROCEDURE get_top_students (IN count_of_students INT)
BEGIN
	SELECT s.surname, a.ag FROM student AS s
	INNER JOIN (SELECT student_id, AVG(grade) AS ag FROM progress
	GROUP BY student_id
	ORDER BY ag DESC
	LIMIT count_of_students) AS a
	ON s.id = a.student_id;
END//

CREATE PROCEDURE get_students_with_grant (IN number_of_semester INT)
BEGIN
	SELECT s.surname FROM student AS s
	INNER JOIN (SELECT student_id, AVG(grade) AS ag FROM progress AS p
	INNER JOIN (SELECT id FROM study_plan
	WHERE semester = number_of_semester) AS q
	ON p.discipline_id = q.id
	GROUP BY student_id
	HAVING ag >= 4.5) AS a
	ON s.id = a.student_id;
END//

CREATE PROCEDURE get_top_average_students ()
BEGIN
	SELECT s.surname FROM student AS s
	INNER JOIN (SELECT student_id, AVG(grade) AS ag FROM progress
	GROUP BY student_id) AS a
	ON s.id = a.student_id
	WHERE a.ag >= (SELECT AVG(ag) FROM (SELECT student_id, AVG(grade) AS ag FROM progress
	GROUP BY student_id) as aag);
END//

CREATE PROCEDURE get_top_discipline ()
BEGIN
	SELECT sp.discipline FROM study_plan AS sp
	INNER JOIN (SELECT discipline_id, AVG(grade) AS ag FROM progress
	GROUP BY discipline_id
	ORDER BY ag DESC
	LIMIT 1) AS a
	ON sp.id = a.discipline_id;
END//

delimiter ;