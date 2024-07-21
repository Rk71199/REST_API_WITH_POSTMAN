package net.jyb.journalApp.controller;

import net.jyb.journalApp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/23")
@RestController
public class JournalEntryController {

    //    @GetMapping("/abc")
//    public String see (){
//        return "Page_Working";
//    }
    private Map<String, JournalEntry> journalEntrires = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntrires.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntrires.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("/id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable String myid) {
        return journalEntrires.get(myid);

    }

    @DeleteMapping("/id/{id}")
    public JournalEntry DeletJournalEntryById(@PathVariable String myid) {
        return journalEntrires.remove(myid);

    }

    @PutMapping("/id/{id}")
    public JournalEntry UpdateJournalEntryById(@PathVariable String id, @RequestBody JournalEntry myEntry) {
        return journalEntrires.put(id, myEntry);

    }
}
