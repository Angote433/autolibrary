package com.arnold.autolibrary.services;

import com.arnold.autolibrary.model.Stream;
import com.arnold.autolibrary.model.UserDetails;
import com.arnold.autolibrary.repo.SchoolClassRepository;
import com.arnold.autolibrary.repo.StreamRepo;
import com.arnold.autolibrary.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamService {
    @Autowired
    private StreamRepo streamRepo;
    @Autowired
    private SchoolClassRepository schoolClassRepository;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    //in a class
    public Stream createStream(Stream stream){
        //teacher manages only one stream
        //check if assigned another stream
        if(stream.getTeacher() != null){


        }
        return streamRepo.save(stream);




    }

    //reassingning / assigning a classteacher
    public Stream assignTeacher(int streamId,int userId){
        Stream stream = streamRepo.getById(streamId);
        UserDetails teacher = userDetailsRepo.findById(userId).orElseThrow(()->
                new RuntimeException("Teacher not found with id "+ userId));

        //teacher running another stream?
        streamRepo.findByTeacher(teacher).ifPresent(existingStream ->
        {if(existingStream.getStreamID() != streamId){
        throw new RuntimeException("Teacher manages another stream "+ existingStream.getStreamName());}
        });

        stream.setTeacher(teacher);
        return streamRepo.save(stream);

    }

    public List<Stream> getAllStreams(){
        return streamRepo.findAll();
    }

    public Stream deactivateStream(int streamId){
        Stream stream = streamRepo.getStreamByStreamId(streamId);
        stream.setActive(false);

        return streamRepo.save(stream);

    }




}
