package com.crud.mysql.CrudUsingMysql.controller;

import com.crud.mysql.CrudUsingMysql.dto.AddStudentRequestDto;
import com.crud.mysql.CrudUsingMysql.dto.StudentDto;
import com.crud.mysql.CrudUsingMysql.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity<List<StudentDto>> getStudentsTest(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("studentId") Long id){
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDto> postStudent(@RequestBody @Valid AddStudentRequestDto studentRequestDto){
        return new ResponseEntity<>(studentService.postStudent(studentRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequestDto studentRequestDto){
        return new ResponseEntity<StudentDto>(studentService.updateStudent(id, studentRequestDto), HttpStatus.OK);
    }

    @PatchMapping("/id")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody Map<String, Objects> studentUpdateRequest){
        return new ResponseEntity<StudentDto>(studentService.updatePartialStudent(id, studentUpdateRequest), HttpStatus.OK);
    }

}
