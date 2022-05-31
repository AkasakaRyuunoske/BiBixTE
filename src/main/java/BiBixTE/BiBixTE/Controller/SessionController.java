package BiBixTE.BiBixTE.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Controller
public class SessionController {

    private static final String MY_SESSION_NOTES_CONSTANT = "MY_SESSION_NOTES";
    private static final Logger log = LoggerFactory.getLogger(SessionController.class);

    @GetMapping(value = "/index")
    public String home(final Model model, final HttpSession session) {
        final List<String> notes = (List<String>) session.getAttribute(MY_SESSION_NOTES_CONSTANT);
        model.addAttribute("sessionNotes", !CollectionUtils.isEmpty(notes) ? notes : new ArrayList<>());
        return "home";
    }

    @PostMapping(value = "/save/note")
    public String saveNote(@RequestParam("note") final String note, final HttpServletRequest request) {
        // Get the notes from request session.
        List<String> notes = (List<String>) request.getSession().getAttribute(MY_SESSION_NOTES_CONSTANT);

        // Check if notes is present in session or not.
        if (CollectionUtils.isEmpty(notes)) {
            log.info("No notes are fetch from the session object. Setting the value in the session object.");
            notes = new ArrayList<>();
        }

        notes.add(note);
        request.getSession().setAttribute(MY_SESSION_NOTES_CONSTANT, notes);
        return "redirect:/index";
    }

    @PostMapping(value = "/destroy/session")
    public String destroySession(final HttpServletRequest request) {
        log.info("Invaliding the session and removing the data.");
        // Invalidate the session and this will clear the data from the configured database.
        request.getSession().invalidate();
        return "redirect:/index";
    }
}