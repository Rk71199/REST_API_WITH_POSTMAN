package net.jyb.journalApp.controller;

import net.jyb.journalApp.Entity.JournalEntry;
import net.jyb.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired(required = true)
    private JournalEntryService journalEntryService;



    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("/id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myid) {
        return journalEntryService.FindById(myid).orElse(null);
    }

    @DeleteMapping("/id/{myid}")
    public boolean JournalEntrydeletById(@PathVariable ObjectId myid) {
       journalEntryService.DeletById(myid);
       return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry UpdateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
      JournalEntry old=journalEntryService.FindById(id).orElse(null);
        if(old !=null){
            old.setContent(newEntry.getContent()!=null && newEntry.equals("")? newEntry.getContent() : old.getContent());
            old.setTitle(newEntry.getTitle()!= null && newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
        }
      journalEntryService.saveEntry(old);
        return old;
    }

}
