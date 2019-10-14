package springboot2.SpringBoot2.ControllerClasses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springboot2.SpringBoot2.Entity.Instructor;
import springboot2.SpringBoot2.Entity.Lesson;
import springboot2.SpringBoot2.Entity.Student;
import springboot2.SpringBoot2.Entity.Syllabus;
import springboot2.SpringBoot2.Service.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/")
public class ViewController {
    enum days{
        monday(1), tuesday(2), wednesday(3), thursday(4), friday(5);
        private int index;
        private days( int index)
        {
            this.index = index;
        }

    }

    private InstructorService instructorService;
    private LessonService lessonService;
    private StudentService studentService;
    private SyllabusService syllabusService;
    private EmailService emailService = new EmailServiceImpl();
    private int currentId = -1;
    private int currentInsId = -1;
    private int lastSylId = -1;

    @Autowired
    public ViewController( InstructorService instructorService,
                          LessonService lessonService, StudentService studentService, SyllabusService syllabusService)
     {
         this.instructorService = instructorService;
         this.lessonService = lessonService;
         this.studentService = studentService;
         this.syllabusService = syllabusService;
     }

    @GetMapping("/check")
    public String signUp(HttpServletRequest request)
    {
        String email = request.getParameter("email");
        String userName = request.getParameter("name");
        String userLastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String dept = request.getParameter("dept");
        //checking part
        Student dummySt = studentService.findStudentByEmail(email);
        if(dummySt == null)
        {
            dummySt = new Student(userName,userLastName,password,dept,email);
            studentService.save(dummySt);
            //email the user id
            int id = dummySt.getId();
            String text = "you can use following as your id: " + id +"\n Welcome to Student Platform";
            emailService.sendSimpleMail(email,"Provided id", text);
            return "index";
        }
        else {
            //show a message on the page and stay on the page

            return "signUp";
        }
    }
    @GetMapping("/goToInstructorProfile")
    public Object getToInstructorProfile(HttpServletRequest request)
    {
        String name = request.getParameter("insName");
        Instructor instructor = instructorService.findByInsName(name);
        ModelAndView mav = new ModelAndView("reachInstructor");
        mav.addObject("instructor",instructor);
        return mav;

    }
    @GetMapping("/Lessons")
    public Object getLessons()
    {
        List<Lesson> lessonsToShow = new ArrayList<>();
        lessonsToShow = lessonService.getAllLessons();
        ModelAndView model = new ModelAndView("lessons");
        model.addObject("lessonsToShow", lessonsToShow);
        return model;
    }
    //User Authentications
    @GetMapping("/authenticateTheUser" )
    public Object authentication( HttpServletRequest request)
    {
        if(currentId == -1) {
            currentId = parseInt(request.getParameter("userid"));
            String password = request.getParameter("password");
            Student student = new Student();
            try {
                 student = studentService.findStudentById(currentId);
            }
            catch (RuntimeException r)
            {
                currentId = -1;
                return "failureLogin";
              //  return goToFailLogin();
            }
            finally {
                if (student.getPassword().equals(password)) {
                    //redirect to user page with model
                    ModelAndView model = new ModelAndView("profilePage");
                    model.addObject("student", student);
                    //model.addAttribute("student",student.getName());
                    return model;
                } else {
                    //stay on the current page and give an error message
                    System.out.println("case1");
                    return "failureLogin";
                }
            }

            /*   if (student != null) {
                if (student.getPassword().equals(password)) {
                    //redirect to user page with model
                    ModelAndView model = new ModelAndView("profilePage");
                    model.addObject("student", student);
                    //model.addAttribute("student",student.getName());
                    return model;
                } else {
                    //stay on the current page and give an error message
                    System.out.println("case1");
                    return "index";
                }
            } else {
                //stay on the current page and give an error message
                currentId = -1;
                System.out.println("case211111");
                return "index";
            }*/
        }
        else
        {
            Student student = studentService.findStudentById(currentId);
            if (student != null) {
                    //redirect to user page with model
                    ModelAndView model = new ModelAndView("profilePage");
                    model.addObject("student", student);
                    return model;
                }
            else {
                //stay on the current page and give an error message
                currentId = -1;
                return "index";
            }
        }
    }
    @GetMapping("/authenticateTheInstructor")
    public Object authenticateInstructor( HttpServletRequest request)
    {

        if(currentInsId == -1) {
            currentInsId = parseInt(request.getParameter("userid"));
            String password = request.getParameter("password");
            Instructor instructor = new Instructor();
            try {
                instructor = instructorService.findInstructorById(currentInsId);
            }
            catch(RuntimeException r)
            {
                currentInsId = -1;
                return "failureInstructorLogin";
            }
            finally {
                if (instructor != null) {
                    if (instructor.getPassword().equals(password)) {
                        //redirect to user page with model
                        ModelAndView model = new ModelAndView("instructorProfilePage");
                        model.addObject("instructor", instructor);
                        return model;
                    } else {
                        //stay on the current page and give an error message
                        return "failureInstructorLogin";
                    }
                } else {
                    //stay on the current page and give an error message
                    return "failureInstructorLogin";
                }
            }

        }
        else
        {
            Instructor instructor = instructorService.findInstructorById(currentInsId);
            if ( instructor != null) {
                //redirect to user page with model
                ModelAndView model = new ModelAndView("instructorProfilePage");
                model.addObject("instructor", instructor);
                return model;
            }
            else {
                //stay on the current page and give an error message
                currentInsId = -1;
                return "index";
            }
        }

    }
    @GetMapping("/sendMailToIns")
    public Object sendEmailToIns(HttpServletRequest request)
    {
        if(currentId != -1) {
            String from = studentService.findStudentById(currentId).getEmail();
            String to = request.getParameter("instructorMail");
            String subject = "from your profile";
            String index = request.getParameter("emailIndex");
            try {
                emailService.sendSimpleMail(to,subject,index + "this mail originally written from " + from);
           }
            catch (Exception e)
            {
                emailService.sendSimpleMail(from,"The mail cannot be send to user","the mail cannot be send to " + to);
            }

        }
        return authentication(request);
    }

    @GetMapping("/instructorLessons")
    public Object instructorLessons()
    {
        List<Lesson> lessonsToShow = new ArrayList<>();
        lessonsToShow = lessonService.getAllLessons();
        ModelAndView model = new ModelAndView("lessonsOfInstructors");
        model.addObject("lessonsToShow", lessonsToShow);
        return model;
    }

    @GetMapping("/addSyllabus")
    public String saveSyllabus(HttpServletRequest request)
    {

        return "/saveLesson";
    }

    @GetMapping("/saveLesson")
    public String saveLesson(HttpServletRequest request)
    {
        String description = request.getParameter("addSyll");
        Syllabus syllabus = new Syllabus();
        syllabus.setSyllabusDetail(description);
        syllabusService.save(syllabus);
        lastSylId = syllabus.getId();

        String day = request.getParameter("day");
        String lessonName = request.getParameter("lessonsName");
        double lessonsCredit = parseDouble((request.getParameter("lessonsCredit")));
        String lessonStart = request.getParameter("startTime");
        String lessonEnd = request.getParameter("endingTime");
                //currentId
        lessonStart = day +" " + lessonStart + "-";
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonName);
        lesson.setEndingTime(lessonEnd);
        lesson.setLessonGpa(lessonsCredit);
        lesson.setStartingTime(lessonStart);
        lesson.setSyllabus(syllabus);

        Instructor ins = instructorService.findInstructorById(currentInsId);

        lesson.setInstructor(ins);
        lessonService.save(lesson);
        return  "lessonsOfInstructors";
    }

    @GetMapping("/takeLesson")
    public Object takeLesson( HttpServletRequest request)
    {
        String lessonName = request.getParameter("lessonName");
        Lesson lesson = lessonService.findLessonByName(lessonName);


        Student thisStudent = studentService.findStudentById(currentId);

        //check duplicates

        try {

            lesson.addStudent(thisStudent);
            if(thisStudent.getLessons().contains(lesson))
                throw new Exception("Lesson is already taken");
            //else if()
            else {
                lessonService.save(lesson);
            }
        }
        catch(Exception exception)
        {
            return "errorLessons";
        }
        return this.getLessons();
    }
    @GetMapping("/goToSyllabus")
    public Object goToSyllabus(HttpServletRequest request)
    {
        String name = request.getParameter("lessonName");
        Lesson lesson = lessonService.findLessonByName(name);
        ModelAndView obj = new ModelAndView("syllabus");
        obj.addObject("lesson",lesson);
        Syllabus syllabus = lesson.getSyllabus();
        return obj;
    }
    //Redirection methods
    @GetMapping("/")
    public String getIndex()
    {
        return "index";
    }

    @GetMapping("/goToAboutUs")
    public String redirectAboutUs()
    {
        return "aboutUs";
    }
    @GetMapping("/goToInstructorLoginPage")
    public String goToInstructorLogin()
    {
        return "instructorLoginPage";
    }
    @GetMapping("/goToSignUpPage")
    public String redirectSignUp()
    {
        return "signUp";
    }

    @GetMapping("/gpaCalc")
    public String preCalculate()
    {
        return  "gpaCalculator";
    }
    @GetMapping("/goLoginFailure")
    public String goToFailLogin()
    {
        System.out.println("111");
        return "failureLogin";
    }
}
