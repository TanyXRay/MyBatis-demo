package ru.home.chernyadieva.mybatisdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.home.chernyadieva.mybatisdemo.model.Student;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> findAll();

    Student findStudentFromId(int id);

    void putNewStudent(Student newStudent);

    void updateStudent(Student student);

    void deleteStudentFromId(int id);

    // @Select("select * from student")
    // List<Student> findAll();

    // @Select("select * from student where id=#{id}")
    // Student findStudentFromId(int id);

    // @Insert("insert into student (first_name, last_name, age, university, faculty, time_create)" +
    //         "values(#{firstName},#{lastName},#{age},#{university},#{faculty},#{timeCreate})")
    // void putNewStudent(Student newStudent);

    // @Delete("delete from student where id=#{id}")
    // void deleteStudentFromId(int id);

    // @Update("update student set first_name=#{firstName}, last_name=#{lastName}, age=#{age}, university=#{university}," +
    //         "faculty=#{faculty}, time_create=#{timeCreate} where id=#{id}")
    // void updateStudent(Student convertToStudentModel, int id);
}
