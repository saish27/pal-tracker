package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        return new ResponseEntity<> (timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry entry = timeEntryRepository.find(id);
        if (entry == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<> (entry, HttpStatus.OK);
        }

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<> (timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id,@RequestBody TimeEntry timeEntry){
        TimeEntry entry = timeEntryRepository.update(id,timeEntry) ;
        if (entry == null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<> (entry, HttpStatus.OK);
        }

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
