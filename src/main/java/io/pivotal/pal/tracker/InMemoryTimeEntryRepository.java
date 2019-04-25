package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long,TimeEntry> repo = new HashMap<>();
    private long index = 1L;

    public TimeEntry create(TimeEntry timeEntry ){
        Long id = index++;
        TimeEntry entry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        repo.put(id,entry);
        return entry;
    }
    public TimeEntry find(long id){
       return repo.get(id);
    }
    public TimeEntry update(long id, TimeEntry timeEntry ){
        if(find(id) == null)
            return null;
        TimeEntry entry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        repo.replace(id,entry);
        return entry;
    }
    public TimeEntry delete(long id){
        TimeEntry entry = repo.get(id);
        repo.remove(id);
        return entry;
    }
    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(repo.values());
    }
}
