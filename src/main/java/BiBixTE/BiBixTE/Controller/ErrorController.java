package BiBixTE.BiBixTE.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ErrorController {
    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) throws Exception {
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        model.addAttribute("exception", e);
        model.addAttribute("url", req.getRequestURL());
        return "clienteGiaEssiste";
    }
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public HashMap<String, String> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
//        HashMap<String, String> response = new HashMap<>();
//        response.put("status", "fail");
//        response.put("message", e.getLocalizedMessage());
//        return response;
//    }
}