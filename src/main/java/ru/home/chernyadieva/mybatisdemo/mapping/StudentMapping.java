package ru.home.chernyadieva.mybatisdemo.mapping;


import org.apache.ibatis.annotations.Mapper;
import ru.home.chernyadieva.mybatisdemo.model.StudentModel;

import java.util.List;

@Mapper
public interface StudentMapping {

    List<StudentModel> findAll();

    StudentModel findStudentFromId(int id);

    void putNewStudent(StudentModel newStudent);

    void updateStudent(StudentModel studentModel, int id);

    void deleteStudentFromId(int id);

    // @Select("select * from student")
    // List<StudentModel> findAll();

    // @Select("select * from student where id=#{id}")
    // StudentModel findStudentFromId(int id);

    // @Insert("insert into student (first_name, last_name, age, university, faculty, time_create)" +
    //         "values(#{firstName},#{lastName},#{age},#{university},#{faculty},#{timeCreate})")
    // void putNewStudent(StudentModel newStudent);

    // @Delete("delete from student where id=#{id}")
    // void deleteStudentFromId(int id);

    // @Update("update student set first_name=#{firstName}, last_name=#{lastName}, age=#{age}, university=#{university}," +
    //         "faculty=#{faculty}, time_create=#{timeCreate} where id=#{id}")
    // void updateStudent(StudentModel convertToStudentModel, int id);
}
