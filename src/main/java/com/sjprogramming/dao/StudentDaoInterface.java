package com.sjprogramming.dao;

import com.sjprogramming.model.Student;

public interface StudentDaoInterface {
    boolean insertStudent(Student s);
    boolean delete(int roll);
    void showAllStudent();
}
