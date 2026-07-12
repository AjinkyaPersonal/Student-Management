package com.crud.mysql.CrudUsingMysql.service;

import com.crud.mysql.CrudUsingMysql.dto.AddStudentRequestDto;
import com.crud.mysql.CrudUsingMysql.dto.StudentDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface StudentService {

    StudentDto getStudent(Long id);

    List<StudentDto> getAllStudents();

    StudentDto postStudent(AddStudentRequestDto studentRequestDto);

    void deleteStudent(Long id);
    StudentDto updateStudent(Long id, AddStudentRequestDto studentRequestDto);

    StudentDto updatePartialStudent(Long id, Map<String, Objects> studentUpdateRequest);

}
