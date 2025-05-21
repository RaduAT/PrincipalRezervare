//Clasa pentru control pagini

package org.example.rezervaresali;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.rezervaresali.entities.*;
import org.example.rezervaresali.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
public class views_controller{

    private final StudentService studentService;
    private final ProfesorService profesorService;
    private final AdminService adminService;
    private final SalaService salaService;

    private final RezervareSaliService rezervareSaliService;

    @Autowired
    public views_controller(StudentService studentService, ProfesorService profesorService, AdminService adminService, SalaService salaService, RezervareSaliService rezervareSaliService){
        this.studentService = studentService;
        this.profesorService = profesorService;
        this.adminService = adminService;
        this.salaService = salaService;
        this.rezervareSaliService = rezervareSaliService;

    }


    //Vizualizare Pagini ON/OFF
    @RequestMapping("/")
    public String indexTemporar(){

        int flagupdown = adminService.getAdminFlag();

        if (flagupdown == 1)
            return "index";
        else
            return "indexTemporar";
    }

    @RequestMapping("/Home")
    public String Home(HttpServletRequest request){


        int flagupdown = adminService.getAdminFlag();

        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("LoginCookieStudent")) {
                if (flagupdown == 1)
                    return "homePage";
                else
                    return "indexTemporar";
            }

            if(cookie.getName().equals("LoginCookieProfesor")){
                if (flagupdown == 1)
                    return "homePage";
                else
                    return "indexTemporar";
            }
        }

        return "redirect:/";

    }


    @RequestMapping("/Login")
    public String login(HttpServletRequest request){

        int flagupdown = adminService.getAdminFlag();

        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies){
            if(cookie.getName().equals("LoginCookieStudent"))
                return "redirect:/Home";
            if(cookie.getName().equals("LoginCookieProfesor"))
                return "redirect:/Home";
        }

        if (flagupdown == 1)
            return "login";
        else
            return "indexTemporar";

    }

    @RequestMapping("/Register")
    public String register(){

        int flagupdown = adminService.getAdminFlag();
        if (flagupdown == 1)
            return "register";
        else
            return "indexTemporar";

    }

    @RequestMapping("/RegisterProfesor")
    public String registerProfesor(){

        int flagupdown = adminService.getAdminFlag();
        if (flagupdown == 1)
            return "registerProfesor";
        else
            return "indexTemporar";

    }

    @RequestMapping("/Contact")
    public String contact(HttpServletRequest request){

        int flagupdown = adminService.getAdminFlag();

        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("LoginCookieStudent")) {
                if (flagupdown == 1)
                    return "contact";
                else
                    return "indexTemporar";
            }

            if(cookie.getName().equals("LoginCookieProfesor")){
                if (flagupdown == 1)
                    return "contact";
                else
                    return "indexTemporar";
            }
        }

        return "redirect:/";

    }

    @RequestMapping("/Admin")
    public String admin(Model model){

        int flagupdown = adminService.getAdminFlag();

        model.addAttribute("flagupdown", flagupdown);

        return "admin";


    }


    @RequestMapping("/Test")
    public String test(Model model){

        List<Sala> sali = salaService.findAllSaliService();
        model.addAttribute("sali", sali);

        List<RezervareSali> rezervari = rezervareSaliService.findAllRezervariSaliService();
        model.addAttribute("rezervari", rezervari);

        return "test";

    }


    //Test Rezervari
    @PostMapping("/makeRezervation")
    public String rezervareValidation(HttpServletRequest request, @RequestParam long Sala, @RequestParam String StartingHour, @RequestParam String EndingHour){

        Cookie[] cookies = request.getCookies();
        long index = -1;
        int flag = 0;

        for(Cookie cookie:cookies){
            if(cookie.getName().equals("Index")){
                index = Long.parseLong(cookie.getValue());
            }
            if(cookie.getName().equals("LoginCookieStudent")){
               flag = 1;
            }
            if(cookie.getName().equals("LoginCookieProfesor")){
                flag = 2;
            }
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            Date date1 = sdf.parse(StartingHour);
            Date date2 = sdf.parse(EndingHour);

            if(flag == 1){
                rezervareSaliService.addRezervareSali((int)Sala, (int)index, 1, date1, date2);
            }

            if(flag == 2){
                rezervareSaliService.addRezervareSali((int)Sala, 1, (int)index, date1, date2);

            }

        } catch (ParseException e) {
            System.out.println("Error parsing the date");
            e.printStackTrace();
        }


        return "redirect:/Test";

    }


    //Functii

    //Creare Cont Nou
    @PostMapping("/registerValidation")

    public String registerValidation(@RequestParam String Nume, @RequestParam String Prenume, @RequestParam String Serie, @RequestParam String Mail, @RequestParam String Telefon, @RequestParam String Parola){

        System.out.println(Mail);
        studentService.addStudentService(Nume, Prenume, Serie, Mail, Telefon, Parola);

        return "redirect:/";

    }

    //Creare Cont Nou Profesori
    @PostMapping("/registerValidationProfesor")

    public String registerValidationProfesor(@RequestParam String Nume, @RequestParam String Prenume, @RequestParam String Departament, @RequestParam String Mail, @RequestParam String Telefon, @RequestParam String Parola){

        System.out.println(Mail.substring(Mail.length()-"@upb.ro".length()-1));
        if(!Mail.substring(Mail.length()-"@upb.ro".length()).equals("@upb.ro")){
         return "redirect:/RegisterProfesor";
        }

        System.out.println(Mail);
        profesorService.addProfesorService(Nume, Prenume, Departament, Mail, Telefon, Parola);

        return "redirect:/";

    }




    //Cookie de Login
    private static Cookie getCookie(byte[] hashBytes, String cookieName) {
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        Cookie loginCookie = new Cookie(cookieName,hexString.toString());
        loginCookie.setMaxAge(60 * 60);

        return loginCookie;
    }

    //Verificare Login
    @PostMapping("/loginValidation")
    public String loginValidation(@RequestParam String Mail, @RequestParam String Parola, HttpServletResponse response){

        List<Student> studenti = studentService.getStudentByMail(Mail);
        List<Profesor> profesori = profesorService.getProfesorByMail(Mail);

        if(studenti.isEmpty()) {
            if(profesori.isEmpty())
                return "redirect:/Login";

            else{
                //Verificam primul si teoretic singurul profesor gasit
                Profesor profesor = profesori.get(0);

                if(Parola.equals(profesor.getParola())){

                    try{

                        //Criptam datele
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");

                        byte[] hashBytes = digest.digest(Mail.getBytes(StandardCharsets.UTF_8));

                        Cookie loginCookie = getCookie(hashBytes, "LoginCookieProfesor");
                        Cookie indexloginCookie = new Cookie("Index", Long.toString(profesor.getIdprofesor()));
                        loginCookie.setMaxAge(60 * 60);
                        indexloginCookie.setMaxAge(60*60);
                        response.addCookie(loginCookie);
                        response.addCookie(indexloginCookie);
                        return "redirect:/Home";


                    }
                    catch (Exception e){
                        return "redirect:/Login";
                    }
                }


            }

        }
        else{

            //Verificam primul si teoretic singurul student gasit
            Student student = studenti.get(0);

            if(Parola.equals(student.getParola())){

                try{

                    //Criptam datele
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");

                    byte[] hashBytes = digest.digest(Mail.getBytes(StandardCharsets.UTF_8));

                    Cookie loginCookie = getCookie(hashBytes, "LoginCookieStudent");
                    Cookie indexloginCookie = new Cookie("Index", Long.toString(student.getIdstudent()));
                    loginCookie.setMaxAge(60 * 60);
                    indexloginCookie.setMaxAge(60*60);
                    response.addCookie(loginCookie);
                    response.addCookie(indexloginCookie);
                    return "redirect:/Home";


                }
                catch (Exception e){
                    return "redirect:/Login";
                }
            }
        }

        return "redirect:/Login";

    }

    //Functie Logout
    @GetMapping("/Logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies){
            if(cookie.getName().equals("LoginCookieStudent")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if(cookie.getName().equals("LoginCookieProfesor")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if(cookie.getName().equals("Index")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

        }

        return "redirect:/";

    }


    //Vizualizare Sali/Studenti/Profesori/Rezervari in Buton Modal
    @GetMapping("/Admin")
    public String viewModal(Model model){

        List<Sala> sali = salaService.findAllSaliService();
        model.addAttribute("sali", sali);

        List<Student> studenti = studentService.findAllStudentiService();
        model.addAttribute("studenti", studenti);

        List<Profesor> profesori = profesorService.findAllProfesoriService();
        model.addAttribute("profesori", profesori);

        List<RezervareSali> rezervari = rezervareSaliService.findAllRezervariSaliService();
        model.addAttribute("rezervari", rezervari);

        //returneaza HTML
        return "admin";

    }

    //Vizualizare Rezervari in Pagina Gardian Modal
    @GetMapping("/Gardian")
    public String viewRezervariGardian(Model model){

        List<RezervareSali> rezervari = rezervareSaliService.findAllRezervariSaliService();
        model.addAttribute("rezervari", rezervari);

        //returneaza HTML
        return "gardian";

    }


    //Adaugare Sala Noua in Buton Modal
    @PostMapping("/addSalaModal")
    public String addSalaModal(@RequestParam String NumeSala, @RequestParam String EtajSala, @RequestParam Integer LocuriSala){

        salaService.addSalaService(NumeSala, EtajSala, LocuriSala);

        return "redirect:/Admin";

    }

    //Stergere Sala Existenta in Buton Modal
    @PostMapping("/deleteSalaModal")
    public String deleteSalaModal(@RequestParam int[] checkDeleteSali){
        salaService.deleteSalaByIdService(checkDeleteSali);
        return "redirect:/Admin";
    }


    //Adaugare Student Nou in Buton Modal
    @PostMapping("/addStudentModal")
    public String addStudentModal(@RequestParam String NumeStudent, @RequestParam String PrenumeStudent, @RequestParam String Serie, @RequestParam String Mail, @RequestParam String Telefon, @RequestParam String Parola){

        studentService.addStudentService(NumeStudent, PrenumeStudent, Serie, Mail, Telefon, Parola);

        return "redirect:/Admin";

    }

    //Stergere Student Existent in Buton Modal
    @PostMapping("/deleteStudentModal")
    public String deleteStudentModal(@RequestParam int[] checkDeleteStudenti){
        studentService.deleteStudentByIdService(checkDeleteStudenti);
        return "redirect:/Admin";
    }

    //Adaugare Profesor Nou in Buton Modal
    @PostMapping("/addProfesorModal")
    public String addProfesorModal(@RequestParam String NumeProfesor, @RequestParam String PrenumeProfesor, @RequestParam String DepartamentProfesor, @RequestParam String MailProfesor, @RequestParam String TelefonProfesor, @RequestParam String ParolaProfesor){

        profesorService.addProfesorService(NumeProfesor, PrenumeProfesor, DepartamentProfesor, MailProfesor, TelefonProfesor, ParolaProfesor);

        return "redirect:/Admin";

    }

    //Stergere Profesor Existent in Buton Modal
    @PostMapping("/deleteProfesorModal")
    public String deleteProfesorModal(@RequestParam int[] checkDeleteProfesori){
        profesorService.deleteProfesorByIdService(checkDeleteProfesori);
        return "redirect:/Admin";
    }

    //Stergere Rezervare Existent in Buton Modal
    @PostMapping("/deleteRezervareModal")
    public String deleteRezervareModal(@RequestParam int[] checkDeleteRezervari){
        rezervareSaliService.deleteRezervareByIdService(checkDeleteRezervari);
        return "redirect:/Admin";
    }

    //Verificare Suprasciere Calendar
    @PostMapping("/checkDates")
    @ResponseBody
    public String checkDates(@RequestParam String starthour , @RequestParam String endhour, @RequestParam int idsala){


        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date1 = sf.parse(starthour);
            Date date2 = sf.parse(endhour);



            List<RezervareSali> rezervari = rezervareSaliService.findRezervareBySali(idsala);

            int flag = 0;

            for(RezervareSali rezervare:rezervari){
                if((date1.after(rezervare.getStarthour()) || date1.equals(rezervare.getStarthour())) && (date1.before(rezervare.getEndhour()) || date1.equals(rezervare.getEndhour()))){

                    flag = 1;
                    break;
                }

                if((date2.after(rezervare.getStarthour()) || date2.equals(rezervare.getStarthour())) && (date2.before(rezervare.getEndhour()) || date2.equals(rezervare.getEndhour()))){

                    flag = 1;
                    break;
                }
                if(date1.before(rezervare.getStarthour()) && date2.after(rezervare.getEndhour()))
                {
                    flag = 1;
                    break;
                }
            }

            if(flag == 0)
                return "No";
            else
                return "Yes";

        } catch (Exception e) {
            System.out.println("Error parsing the date");
            e.printStackTrace();
        }

        return "Yes";

    }


    //Controller Pagina UP-DOWN
    @PostMapping("/checkMentenanta")
    @ResponseBody
    public String checkMentenanta(){

        int admin = adminService.getAdminFlag();

        System.out.println(admin);

        if(admin == 1)
            adminService.setAdminFlag1();
        else
            adminService.setAdminFlag0();

        return "Status Changed";

    }




}
