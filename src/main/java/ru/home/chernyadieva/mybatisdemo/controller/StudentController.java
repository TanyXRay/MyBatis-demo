package ru.home.chernyadieva.mybatisdemo.controller;

import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.home.chernyadieva.mybatisdemo.dto.StudentDTO;
import ru.home.chernyadieva.mybatisdemo.mapper.StudentMapper;
import ru.home.chernyadieva.mybatisdemo.model.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentMapper studentMapper;
    private ModelMapper modelMapper;

    public StudentController(StudentMapper studentMapper, ModelMapper modelMapper) {
        this.studentMapper = studentMapper;
        this.modelMapper = modelMapper;
    }

    //GET
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudent() {
        List<StudentDTO> studentDTOList = studentMapper.findAll().stream()
                .map(this::convertToStudentDTO)
                .toList();

        if (studentDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentDTOList, HttpStatus.OK);
    }

    //GET (id)
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentFromId(@PathVariable(value = "id") int id) {
        try {
            StudentDTO studentDTO = convertToStudentDTO(studentMapper.findStudentFromId(id));
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException("This object student with this id not found in DB!");
        }
    }

    //PUT
    @PutMapping("/add")
    public ResponseEntity<HttpStatus> addStudent(@RequestBody StudentDTO newStudent,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        Student student = convertToStudentModel(newStudent);
        student.setTimeCreate(LocalDateTime.now());
        studentMapper.putNewStudent(student);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //PATCH
    @PatchMapping("/patch/{id}")
    public ResponseEntity<HttpStatus> updateStudent(@RequestBody StudentDTO studentDTO,
                                                    @PathVariable @Param("id") int id) {

        Student studentFromDB = studentMapper.findStudentFromId(id);
        Student updateStudent = convertToStudentModel(studentDTO);

        if (Objects.isNull(studentFromDB)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updateStudent.setTimeCreate(LocalDateTime.now());
        updateStudent.setId(id);
        studentMapper.updateStudent(updateStudent);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id) {
        Student student = studentMapper.findStudentFromId(id);

        if (Objects.isNull(student)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentMapper.deleteStudentFromId(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * DAO конвертируем в сущность
     *
     * @param studentDTO
     * @return
     */
    private Student convertToStudentModel(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    /**
     * Сущность конвертируем в DTO
     *
     * @param student
     * @return
     */
    private StudentDTO convertToStudentDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
}
