package com.crud.mysql.CrudUsingMysql.service.impl;

import com.crud.mysql.CrudUsingMysql.dto.AddStudentRequestDto;
import com.crud.mysql.CrudUsingMysql.dto.StudentDto;
import com.crud.mysql.CrudUsingMysql.entity.Student;
import com.crud.mysql.CrudUsingMysql.repository.StudentRepository;
import com.crud.mysql.CrudUsingMysql.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

//    public StudentServiceImpl(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//    Instead of this use annotation @RequiredArgsConstructor

    @Override
    public StudentDto getStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found exception"));
//        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getEmail());
//        return studentDto;
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

//        List<StudentDto> studentDtoList = students
//                .stream()
//                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
//                .toList();
        List<StudentDto> studentDtoList = students
                .stream()
                .map(student -> modelMapper.map(student,StudentDto.class))
                .toList();
        return studentDtoList;
    }

    @Override
    public StudentDto postStudent(AddStudentRequestDto studentRequestDto) {
        Student newStudent = modelMapper.map(studentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);


    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student doesn't exist with id:"+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto studentRequestDto) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student doesn't exist with id" + id));
        modelMapper.map(studentRequestDto, student);
        studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Objects> studentUpdateRequest) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student doesn't exist with id" + id));

        studentUpdateRequest.forEach((field, value) -> {
            switch (field) {
                case "name":
                    student.setName(value.toString());
                    break;
                case "email":
                    student.setEmail(value.toString());
                    break;
                default: throw new IllegalArgumentException("Entered wrong field");
            }
        });
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }
}
