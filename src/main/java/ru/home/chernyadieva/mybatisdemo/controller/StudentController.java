package ru.home.chernyadieva.mybatisdemo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.home.chernyadieva.mybatisdemo.dto.StudentDTO;
import ru.home.chernyadieva.mybatisdemo.mapping.StudentMapping;
import ru.home.chernyadieva.mybatisdemo.model.StudentModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentMapping studentMapping;
    private ModelMapper modelMapper;

    public StudentController(StudentMapping studentMapping, ModelMapper modelMapper) {
        this.studentMapping = studentMapping;
        this.modelMapper = modelMapper;
    }

    //GET
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudent() {
        List<StudentDTO> studentDTOList = studentMapping.findAll().stream()
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
            StudentDTO studentDTO = convertToStudentDTO(studentMapping.findStudentFromId(id));
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

        StudentModel studentModel = convertToStudentModel(newStudent);
        studentModel.setTimeCreate(LocalDateTime.now());
        studentMapping.putNewStudent(studentModel);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //PATCH
    @PatchMapping("/add/{id}")
    public ResponseEntity<HttpStatus> updateStudent(@RequestBody StudentDTO studentDTO,
                                                    @PathVariable("id") int id) {
        StudentModel studentModel = convertToStudentModel(studentDTO);

        if (studentModel.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentMapping.updateStudent(studentModel, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id) {
        StudentModel studentModel = studentMapping.findStudentFromId(id);

        if (Objects.isNull(studentModel)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentMapping.deleteStudentFromId(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * DAO конвертируем в сущность
     *
     * @param studentDTO
     * @return
     */
    private StudentModel convertToStudentModel(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, StudentModel.class);
    }

    /**
     * Сущность конвертируем в DTO
     *
     * @param studentModel
     * @return
     */
    private StudentDTO convertToStudentDTO(StudentModel studentModel) {
        return modelMapper.map(studentModel, StudentDTO.class);
    }
}
