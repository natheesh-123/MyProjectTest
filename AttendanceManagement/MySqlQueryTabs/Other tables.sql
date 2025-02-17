DESC LeaveTypes;
DESC LeaveRequests;

SELECT * FROM LeaveTypes;
SELECT * FROM AttendanceRequests;
SELECT * FROM LeaveRequests;

INSERT INTO LeaveTypes (id, name, description) VALUES
(1, "SL", "Sick Leave"), (2, "EL", "Emergency Leave");

INSERT INTO AttendanceRequests (user_id, date, status, remarks) VALUES
(100, "2025-01-31", "Present", ""),
(101, "2025-01-31", "Present", ""),
(102, "2025-01-31", "Present", ""),
(103, "2025-01-31", "Present", ""),
(104, "2025-01-31", "Present", ""),
(500, "2025-01-31", "Present", ""),
(501, "2025-01-31", "Present", "");


delete from leaveRequests where user_id = 999;
insert into LeaveRequests (user_id, leave_type_id, date, status, reason) VALUES
(100, 1, "2025-02-1", "pending", "Going to Doctors"),
(101, 1, "2025-02-25", "Accepted", "Going to Home Town"),
(500, 2, "2025-02-20", "Accepted", "Marrage");


insert into LeaveRequestHistory (user_id, leave_type_id, date, status, reason) VALUES
(105, 2, "2025-02-12", "Accepted", "Going to Doctors"),
(501, 1, "2025-02-14", "Accepted", "Not Feeling well");