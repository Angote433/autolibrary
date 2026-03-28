package com.arnold.autolibrary.services;

import com.arnold.autolibrary.model.Stream;
import com.arnold.autolibrary.model.Student;
import com.arnold.autolibrary.repo.StreamRepo;
import com.arnold.autolibrary.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StreamRepo streamRepo;

    public Student createStudent(Student student){
        //id to be unique amongst students
        if(studentRepo.existsByAdmissionNumber(student.getAdmissionNumber())){
            throw new RuntimeException("Student with adm"+ student.getAdmissionNumber()+
                    "ecists");
        }

        //stream to nbe active /exist
        Stream stream = streamRepo.findById(student.getStream().getStreamID()).
        orElseThrow(()->new RuntimeException("Stream not found"));

        if(!stream.isActive()){
            throw new RuntimeException("Stream not active ");
        }
        student.setActive(true);
        return studentRepo.save(student);
    }

    public List<Student> getByStream(int streamId){
        return studentRepo.findByStreamStreamIdAndIsActive(streamId,true);
    }
    public Student getStudentByAdmission(String admission){
        return studentRepo.findStudentByAdmissionNumber(admission).orElseThrow(
                ()->new RuntimeException("Student not found" + admission)
        );
    }

    public Student deactivateStudent(int studentId){
        Student student = studentRepo.getById(studentId);
        student.setActive(false);
        return studentRepo.save(student);
    }


}
