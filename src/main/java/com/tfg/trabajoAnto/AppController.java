package com.tfg.trabajoAnto;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfg.trabajoAnto.modelo.Alumno;
import com.tfg.trabajoAnto.modelo.Aula;
import com.tfg.trabajoAnto.modelo.Complejo;
import com.tfg.trabajoAnto.modelo.Ejercicio;
import com.tfg.trabajoAnto.modelo.ModeloEjercicio;
import com.tfg.trabajoAnto.modelo.Medio;
import com.tfg.trabajoAnto.modelo.ModeloExamen;
import com.tfg.trabajoAnto.modelo.Profesor;
import com.tfg.trabajoAnto.modelo.Simple;
import com.tfg.trabajoAnto.modelo.Usuario;
import com.tfg.trabajoAnto.repositorio.RepoAula;
import com.tfg.trabajoAnto.repositorio.RepoEjercicio;
import com.tfg.trabajoAnto.repositorio.RepoModeloEjercicio;
import com.tfg.trabajoAnto.repositorio.RepoModeloExamen;
import com.tfg.trabajoAnto.servicio.ServicioUsuario;

@Controller
public class AppController {
	
	@Autowired
	public RepoModeloEjercicio repoModeloEjercicio;
	
	@Autowired
	public RepoModeloExamen repoModeloExamen;

	@Autowired
	public RepoEjercicio repoEjercicio;

	@Autowired
	public ServicioUsuario usuarios;
	@Autowired
	public RepoAula repoAulas;
	

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request, Authentication au) {

		;
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			model.addAttribute("logged", true);
			model.addAttribute("name", request.getRemoteUser());
			Usuario u = usuarios.getUsuarioPorNombre(au.getName());
			model.addAttribute(u.Rol(), true);
			
		} else {
			model.addAttribute("logged", false);
		}
	}
	
	@RequestMapping("/")
	public String inicio() {
		
		return "index";
	}
	
	@RequestMapping("/contenidos")
	public String contenidos() {
		return "contenidos";
	}
	
	@RequestMapping("/signin")
    public String registrarUsuario(Model m) {
		
    	List<Aula> aulas;
    	Usuario usu = usuarios.getUsuarioPorNombre((String)m.getAttribute("name"));
    	if(usu.Rol()=="PROFESORES") {
    		aulas = usuarios.getAulasByProfesor((Profesor) usu);
    	}else //if(usu.Rol()=="ADMINISTRADOR") 
    	{
    		aulas = usuarios.getAllAulas();
    	}
    	
    	m.addAttribute("lista",aulas);
        return "registro";
    }
	
    @RequestMapping("/registro")
    public String terminarRegistro(Model m,@RequestParam String nombre, @RequestParam String contra, @RequestParam String contra2, @RequestParam String institucion, @RequestParam String rol, @RequestParam String aula) {
    	//CONTRASEÑAS COINCIDEN
    	if(!contra.equals(contra2)) {
    		m.addAttribute("ErrorContra",true);
    		return registrarUsuario(m);
    	}
    	//USUARIO YA EXISTENTE
    	else if(usuarios.getUsuarioPorNombre(nombre)!=null) {
    		m.addAttribute("ErrorNombre",true);
    		return registrarUsuario(m);
    	}
    	System.out.println(nombre);
    	System.out.println(institucion);
    	System.out.println(contra);
    	//REGISTRAR ALUMNO
    	if (rol.equals("ALUMNOS")) {
    		Alumno alumno = new Alumno();
    		alumno.setNombre(nombre);
    		alumno.setInstitucion(institucion);
    		alumno.setContra(contra);
    		if (aula!="") {
				System.out.println("aaa");
    			Aula aul = repoAulas.getAulaByNombre(aula);
        		aul.getAlumnos().add(alumno);
			}else {
				System.out.println("sss");
			}
            usuarios.registrarUsuario(alumno);
		}
    	//REGISTRAR PROFESOR
    	else {
			Profesor profesor = new Profesor();
			profesor.setNombre(nombre);
        	profesor.setInstitucion(institucion);
        	profesor.setContra(contra);
            usuarios.registrarUsuario(profesor);
		}
        return "redirect:/";
    }
    
    @RequestMapping("/login")
    public String iniciarSesion() {
        return "iniciosesion";
    }
    
    @RequestMapping("/logout")
	public String cerrarSesion() {
		return "index";
	}
    
    @RequestMapping("/revision")
   	public String revision(Model m, @RequestParam Integer id, @RequestParam int idAlumno ) {
       	
       	List<Ejercicio> listaEjs = repoEjercicio.getEjerciciosDeAlumnoByExamenId(idAlumno, id, "simple");
       	List<Ejercicio> listaEjsI = repoEjercicio.getEjerciciosDeAlumnoByExamenId(idAlumno, id, "simpleImg");
       	List<Ejercicio> listaEjsTV = repoEjercicio.getEjerciciosDeAlumnoByExamenId(idAlumno, id, "simpleTV");
       	List<Ejercicio> listaEjm = repoEjercicio.getEjerciciosDeAlumnoByExamenId(idAlumno, id, "medio");
       	List<Ejercicio> listaEjmI = repoEjercicio.getEjerciciosDeAlumnoByExamenId(idAlumno, id, "medioImg");
       	List<Ejercicio> listaEjmTV = repoEjercicio.getEjerciciosDeAlumnoByExamenId(idAlumno, id, "medioTV");
       	List<Ejercicio> listaEjc = repoEjercicio.getEjerciciosDeAlumnoByExamenId(idAlumno, id, "complejo");
       	
       	
       	m.addAttribute("listaEjs",listaEjs);
       	m.addAttribute("listaEjm",listaEjm);
       	m.addAttribute("listaEjsTV",listaEjsTV);
       	m.addAttribute("listaEjmTV",listaEjmTV);
       	m.addAttribute("listaEjsI",listaEjsI);
       	m.addAttribute("listaEjmI",listaEjmI);
       	m.addAttribute("listaEjc",listaEjc);
       	
   		return "revision";
   	}
    
    /******************************************************************************************************************************************/
    /*************************************************ADMIN ONLY*******************************************************************************/
    /******************************************************************************************************************************************/
    
  //REDIRIGE A LA PÁGINA DE GESTION DE LOS ADMINISTRADORES
    @RequestMapping("/administradores")
	public String administradores(Model m) {
		return "administradores";
	}
    //MUESTRA UN LISTADO DE USUARIOS
    @RequestMapping("/listaUsuarios")
	public String listaUsuarios(Model m) {
    	List<Profesor> profesores = usuarios.getAllProfesores();
    	m.addAttribute("profesores",profesores);
    	List<Alumno> alumnos = usuarios.getAllAlumnos();
    	m.addAttribute("alumnos",alumnos);
		return "listaUsuarios";
	}
    
    //BANEA O DESBANEA AL USUARIO SELECCIONADO
    @RequestMapping("/banUsuarios")
   	public String banearUsuarios(Model m,@RequestParam String nombre) {
       	usuarios.banUsuarioByNombre(nombre);
       	return "redirect:/";
   	}
    
    //REDIRIGE A UNA PAGINA PARA MODIFICAR LAS AULAS ASIGNADAS AL PROFESOR PASADO POR PARAMETRO
    @RequestMapping("/asignarAulas{nombre}")
	public String asignarAulas(Model m,@PathVariable String nombre) {
    	Profesor profesor = (Profesor) usuarios.getUsuarioPorNombre(nombre);
    	List<Aula> aulas = usuarios.getNuevasAulasProfesorId(profesor);
    	m.addAttribute("profe",profesor);
    	m.addAttribute("lista",aulas);
    	m.addAttribute("misAulas",profesor.getAula());
		return "asignarAulas";
	}
    
    //MODIFICA LAS AULAS MARCADAS Y RETORNA A LA PÁGINA DE INICIO.
    @RequestMapping("/asignarAulasSubmit")
	public String addAula(Model m, @RequestParam String nombre , @RequestParam String[] adquirir, @RequestParam String[] abandonar) {
    	Profesor profesor = (Profesor) usuarios.getUsuarioPorNombre(nombre);

    	for (int i = 0; i < adquirir.length; i++) {
			Aula a = repoAulas.getAulaByNombre(adquirir[i]);
			profesor.getAula().add(a);
		}
    	
    	for (int i = 0; i < abandonar.length; i++) {
			Aula a = repoAulas.getAulaByNombre(abandonar[i]);
			profesor.getAula().remove(a);
		}
    	usuarios.save(profesor);
    	return "redirect:/";
	}
    @RequestMapping("/perfilAlumno{id}")
	public String perfilAlumno(Model m,@PathVariable int id) {
    	Alumno alumno = (Alumno) usuarios.getUsuarioPorId(id);
    	List<Integer> examenes = usuarios.getExamenesByUserId(id);
    	m.addAttribute("examenes",examenes);
    	m.addAttribute("alumno",alumno);
		return "perfilAlumno";
	}
    @RequestMapping("/cambiarContra")
	public String cambiarContra(Model m,@RequestParam String newContra,@RequestParam int id) {
    	Alumno al = (Alumno) usuarios.getUsuarioPorId(id);
    	usuarios.cambiarContra(al,newContra);
    	return "redirect:/";
	}
    
    //REDIRIGE A UNA PÁGINA CON UNA LISTA DE LOS ALUMNOS PARA CREAR UN AULA CON LOS SELECCIONADOS
    @RequestMapping("/crearAula")
	public String crearAula(Model m) {
    	m.addAttribute("lista",usuarios.getAllAlumnos());
    	return "crearAula";
	}
    
    //CREA LA NUEVA AULA Y RETORNA A LA PÁGINA DE INICIO.
    @RequestMapping("/crearAulaSubmit")
	public String crearAulaSubmit(Model m, @RequestParam String[] alumno, @RequestParam String aula, @RequestParam String curso , @RequestParam String asignatura) {
    	
    	if(usuarios.getAulaByNombre(aula)!=null) {
    		m.addAttribute("ErrorNombre",true);
    		return crearAula(m);
    	}
    	usuarios.crearAula(aula,alumno,curso,asignatura);
    	return "redirect:/";
	}
    
    @RequestMapping("/listaAulas")
   	public String listaAulas(Model m) {
       	List<Aula> aulas = usuarios.getAllAulas();
       	m.addAttribute("lista",aulas);
   		return "listaAulas";
   	}
    
    @RequestMapping("/gestionarAula{id}")
   	public String gestionarAula(Model m,@PathVariable int id) {
       	Aula aula = repoAulas.getAulaById(id);
       	List<Alumno> alumnos = usuarios.getAllAlumnos();
       	alumnos.removeAll(aula.getAlumnos());
       	
       	m.addAttribute("alumnosIn",aula.getAlumnos());
       	m.addAttribute("alumnosOut",alumnos);
       	m.addAttribute("aula",aula);
       	return "asignarAlumnos";
   	}
  //MODIFICA LAS AULAS MARCADAS Y RETORNA A LA PÁGINA DE INICIO.
    @RequestMapping("/asignarAlumnosSubmit")
	public String asignarAlumnosSubmit(Model m,@RequestParam int id, @RequestParam String[] adquirir, @RequestParam String[] abandonar) {
    	Aula aula = repoAulas.getAulaById(id);

    	for (int i = 0; i < adquirir.length; i++) {
			Alumno a = (Alumno) usuarios.getUsuarioPorNombre(adquirir[i]);
			aula.getAlumnos().add(a);
		}
    	
    	for (int i = 0; i < abandonar.length; i++) {
			Alumno a = (Alumno) usuarios.getUsuarioPorNombre(abandonar[i]);
			aula.getAlumnos().remove(a);
		}
    	repoAulas.save(aula);
    	return "redirect:/";
	}
    @RequestMapping("/cambiarNombre")
   	public String cambiarNombre(Model m,@RequestParam int id,@RequestParam String newName) {
    	if(usuarios.getAulaByNombre(newName)!=null) {
    		m.addAttribute("ErrorNombre",true);
           	return "redirect:/gestionarAula"+id;
    	}
       	Aula aula = repoAulas.getAulaById(id);
       	aula.setNombre(newName);
       	repoAulas.save(aula);
       	return "redirect:/gestionarAula"+id;
   	}
    @RequestMapping("/cambiarCurso")
   	public String cambiarCurso(Model m,@RequestParam int id,@RequestParam String newCurso) {
       	Aula aula = repoAulas.getAulaById(id);
       	aula.setCursoAcademico(newCurso);
       	repoAulas.save(aula);
       	return "redirect:/gestionarAula"+id;
   	}
    @RequestMapping("/cambiarAsignatura")
   	public String cambiarAsignatura(Model m,@RequestParam int id,@RequestParam String newAsignatura) {
       	Aula aula = repoAulas.getAulaById(id);
       	aula.setAsignatura(newAsignatura);
       	repoAulas.save(aula);
       	return "redirect:/gestionarAula"+id;
   	}
    
    /******************************************************************************************************************************************/
    /************************************************PROFESOR ONLY*****************************************************************************/
    /******************************************************************************************************************************************/
    
    //REDIRIGE A LA PÁGINA DE GESTION DE LOS PROFESORES
    @RequestMapping("/profes")
	public String profes(Model m) {
    	
    	List<Aula> aulas = usuarios.getAulasByProfesor((Profesor) usuarios.getUsuarioPorNombre((String)m.getAttribute("name")));
    	m.addAttribute("lista",aulas);

		return "profes";
	}
    
    @RequestMapping("/crearExamen")
	public String crearExamen(Model m) {
    	
    	List<ModeloEjercicio> ejS = repoModeloEjercicio.getAllSimples();
    	m.addAttribute("listaS",ejS);
    	
    	List<ModeloEjercicio> ejM = repoModeloEjercicio.getAllMedios();
    	m.addAttribute("listaM",ejM);

    	List<ModeloEjercicio> ejC = repoModeloEjercicio.getAllComplejos();
    	m.addAttribute("listaC",ejC);
    	
		return "crearExamen";
	}
    
    @RequestMapping("/crearExamenSubmit")
	public String crearExamenSubmit(Model m, @RequestParam String nombre, @RequestParam int[] noImg, @RequestParam int[] siImg, @RequestParam int[] tablaVerdad) {
    	ModeloExamen modeloExamen = new ModeloExamen();
    	if(repoModeloExamen.getExamenByNombre(nombre)!=null) {
    		m.addAttribute("ErrorNombre",true);
    		return crearExamen(m);
    	}
    	modeloExamen.setNombre(nombre);
    	for (int i : noImg) {
    		modeloExamen.getNoImg().add(repoModeloEjercicio.getEjercicioById(i));
		}
    	for (int i : siImg) {
    		modeloExamen.getSiImg().add(repoModeloEjercicio.getEjercicioById(i));
		}
    	for (int i : tablaVerdad) {
    		modeloExamen.getTablaVerdad().add(repoModeloEjercicio.getEjercicioById(i));
		}
    	repoModeloExamen.save(modeloExamen);
    	return "redirect:/";
	}
    @RequestMapping("/simple")
	public String simple() {
		return "addsimple";
	}
	
	@RequestMapping("/medio")
	public String medio() {
		return "addmedio";
	}
	
	@RequestMapping("/complejo")
	public String complejo() {
		return "addcomplejo";
	}
    @RequestMapping("/addsimple")
    public String addSimple(Model m,@RequestParam String ej, @RequestParam String img) {
    	ModeloEjercicio simp = new Simple();
    	simp.setFuncion_logica("S = "+ej);
    	if(img.equals("")) {
        	simp.setImagen(null);
    	}else {
        	simp.setImagen(img);
    	}
    	repoModeloEjercicio.save(simp);
		return "redirect:/profes";
	}
    
    @RequestMapping("/addmedio")
    public String addMedio(Model m,@RequestParam String ej, @RequestParam String img) {
    	ModeloEjercicio med = new Medio();
    	med.setFuncion_logica("S = "+ej);
    	if(img.equals("")) {
    		med.setImagen(null);
    	}else {
    		med.setImagen(img);
    	}
    	repoModeloEjercicio.save(med);
		return "redirect:/profes";
	}
    
    @RequestMapping("/addcomplejo")
    public String addComplejo(Model m,@RequestParam String ejD,@RequestParam String ejE,@RequestParam String ejF,@RequestParam String ejG,@RequestParam String ejH,@RequestParam String ejS, @RequestParam String img) {
    	ModeloEjercicio com = new Complejo();
    	((Complejo) com).setFunciones_logicas("D = "+ejD, "E = "+ejE, "F = "+ejF, "G = "+ejG, "H = "+ejH, "S = "+ejS);
    	com.setImagen(img);
    	repoModeloEjercicio.save(com);
		return "redirect:/profes";
	}
    
    @RequestMapping("/examen{id}")
	public String mostrarExamenes(Model m, @PathVariable Integer id) {
    	List<Integer> e = usuarios.getExamenesByUserId(id);
		m.addAttribute("lista",e);
		m.addAttribute("idAlumno",id);
		return "examenes";
	}
    
    @RequestMapping("/examenes")
	public String examenes(Model m) {
    	List<ModeloExamen> e = repoModeloExamen.getAllExamen();
		m.addAttribute("examenes",e);
		return "listaExamenes";
	}
    
    @RequestMapping("/borrar")
   	public String borrar(Model m,@RequestParam int[] borrar) {
    	for (int i : borrar) {
    		ModeloExamen modEx = repoModeloExamen.getExamenById(i);
    		modEx.setNoImg(null);
    		modEx.setSiImg(null);
    		modEx.setTablaVerdad(null);
    		repoModeloExamen.delete(modEx);
		}
    	return "redirect:/";
   	}
    
    @RequestMapping("/copiar{id}")
   	public String copiar(Model m,@PathVariable int id) {
    	
    	ModeloExamen x = repoModeloExamen.getExamenById(id);
    	
    	List<ModeloEjercicio> ejS = repoModeloEjercicio.getAllSimples();
    	HashMap<ModeloEjercicio, boolean[]> mapaS = new HashMap<>();
    	for (ModeloEjercicio modeloEjercicio : ejS) {
    		boolean[] bAux = {false,false,false};
			if(x.getNoImg().contains(modeloEjercicio)) {
				bAux[0]=true;
			}
			if(x.getSiImg().contains(modeloEjercicio)) {
				bAux[1]=true;
			}
			if(x.getTablaVerdad().contains(modeloEjercicio)) {
				bAux[2]=true;
			}
			mapaS.put(modeloEjercicio, bAux);
		}
    	m.addAttribute("listaS",mapaS.entrySet());
    	
    	List<ModeloEjercicio> ejM = repoModeloEjercicio.getAllMedios();
    	HashMap<ModeloEjercicio, boolean[]> mapaM = new HashMap<>();
    	for (ModeloEjercicio modeloEjercicio : ejM) {
    		boolean[] bAux = {false,false,false};
			if(x.getNoImg().contains(modeloEjercicio)) {
				bAux[0]=true;
			}
			if(x.getSiImg().contains(modeloEjercicio)) {
				bAux[1]=true;
			}
			if(x.getTablaVerdad().contains(modeloEjercicio)) {
				bAux[2]=true;
			}
			mapaM.put(modeloEjercicio, bAux);
		}
    	m.addAttribute("listaM",mapaM.entrySet());
    	
    	List<ModeloEjercicio> ejC = repoModeloEjercicio.getAllComplejos();
    	HashMap<ModeloEjercicio, Boolean> mapaC = new HashMap<>();
    	for (ModeloEjercicio modeloEjercicio : ejC) {
    		boolean bAux = false;
			if(x.getSiImg().contains(modeloEjercicio)) {
				bAux=true;
			}
			mapaC.put(modeloEjercicio, bAux);
		}
    	m.addAttribute("listaC",mapaC.entrySet());
    	
    	return "clonarExamen";
   	}
    
    @RequestMapping("/estadisticasAlumno")
	public String estadisticas(Model m, int id) {
    	int[] i = {id};
    	HashMap<Simple, String> simples = usuarios.getSimpleById(i);
    	HashMap<Medio, String> medios = usuarios.getMedioById(i);
    	HashMap<Complejo, String> complejos = usuarios.getComplejoById(i);
    	
    	m.addAttribute("simples",simples.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	m.addAttribute("medios",medios.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	m.addAttribute("complejos",complejos.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	
    	
    	return "estadisticas";
	}
    
    @RequestMapping("/estadisticas")
	public String estadisticas(Model m) {
    	HashMap<Simple, String> simples = usuarios.getAllSimple();
    	HashMap<Medio, String> medios = usuarios.getAllMedio();
    	HashMap<Complejo, String> complejos = usuarios.getAllComplejo();
    	
    	m.addAttribute("simples",simples.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	m.addAttribute("medios",medios.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	m.addAttribute("complejos",complejos.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	
    	
    	return "estadisticas";
	}
    
    @RequestMapping("/estadisticasGrupales{id}")
	public String estadisticasGrupales(Model m, @PathVariable int id) {
    	Aula aula = repoAulas.getAulaById(id);
    	int[] ids = new int[aula.getAlumnos().size()];
    	int i=0;
    	for (Alumno al : aula.getAlumnos()) {
			ids[i++]=al.getId();
		}
    	HashMap<Simple, String> simples = usuarios.getSimpleById(ids);
    	HashMap<Medio, String> medios = usuarios.getMedioById(ids);
    	HashMap<Complejo, String> complejos = usuarios.getComplejoById(ids);
    	
    	m.addAttribute("simples",simples.entrySet()); 
    	m.addAttribute("medios",medios.entrySet()); 
    	m.addAttribute("complejos",complejos.entrySet()); 
    	
    	
    	return "estadisticas";
	}
    
    @RequestMapping("/rankearAlumnos{id}")
	public String rankearAlumnos(Model m, @PathVariable int id) {
    	Aula aula = repoAulas.getAulaById(id);
    	Alumno[] alumnos = new Alumno[aula.getAlumnos().size()];
    	int i=0;
    	for (Alumno al : aula.getAlumnos()) {
    		alumnos[i++]=al;
		}
    	
    	HashMap<Alumno, Float> rank = usuarios.rankear(alumnos);
    	
    	m.addAttribute("rank",rank.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	m.addAttribute("aula",aula);
    	
    	return "ranking";
	}
    
    @RequestMapping("/borrarEjercicios")
	public String borrarEjercicios(Model m) {
    	List<ModeloEjercicio> lista =  repoModeloEjercicio.getNewEjercicios();
    	
    	m.addAttribute("lista",lista);
    	return "borrarEjercicios";
	}
    
    @RequestMapping("/borrarEjerciciosSubmit")
	public String borrarEjerciciosSubmit(Model m, @RequestParam int[] borrar) {
    	for (int i : borrar) {
    		ModeloEjercicio modEj = repoModeloEjercicio.getEjercicioById(i);
    		repoModeloEjercicio.delete(modEj);
		}
    	return "redirect:/";
	}
    /******************************************************************************************************************************************/
    /*************************************************ALUMNO ONLY******************************************************************************/
    /******************************************************************************************************************************************/
    
    @RequestMapping("/modoEvaluacion")
	public String modoEvaluacion(Model m) {
		return "modoEvaluacion";
	}
    
    @RequestMapping("/autoevaluacionCasual")
	public String autoevaluacionCasual(Model m) {
		return "autoevaluacionCasual";
	}
    
    @RequestMapping("/autoevaluacionFormal")
	public String autoevaluacionFormal(Model m) {
		return "autoevaluacionFormal";
	}
    
    @RequestMapping("/autoevaluacionFormalSubmit")
	public String autoevaluacionFormalSubmit(Model m, @RequestParam String[] demo) {
    	Alumno al = (Alumno) usuarios.getUsuarioPorNombre((String)m.getAttribute("name"));
    	int[] aciertos = al.getAciertos();
    	int[] intentos = al.getIntentos();
    	
    	for (int i = 0; i < demo.length; i++) {
			if(demo[i].equals("Correcto")) {
				intentos[i]++;
				aciertos[i]++;
			}else if(demo[i].equals("Incorrecto")) {
				intentos[i]++;
			}
		}
    	al.setAciertos(aciertos);
    	al.setIntentos(intentos);
    	usuarios.save(al);
    	return "redirect:/";
	}
    
    @RequestMapping("/examinarse")
	public String examinarse(Model m,@RequestParam String nombre) {
    	ModeloExamen modeloExamen = repoModeloExamen.getExamenByNombre(nombre);
    	Alumno al = (Alumno) usuarios.getUsuarioPorNombre((String)m.getAttribute("name"));
    	if(modeloExamen == null) {
        	m.addAttribute("Error","El nombre que has escrito no se corresponde con el de ningun examen de la base de datos.");
        	return "modoEvaluacion";
    	}else if(usuarios.checkUserExam(al.getId(), modeloExamen.getId())!=null) {
        	m.addAttribute("Error","Ya te has evaluado de este examen.");
        	return "modoEvaluacion";
    	}
    	m.addAttribute("modelo",nombre);
    	
    	List<ModeloEjercicio> listaEjs = new ArrayList<>();
    	List<ModeloEjercicio> listaEjm = new ArrayList<>();
    	List<ModeloEjercicio> listaEjmTV = new ArrayList<>();
    	List<ModeloEjercicio> listaEjsI = new ArrayList<>();
    	List<ModeloEjercicio> listaEjmI = new ArrayList<>();
    	List<ModeloEjercicio> listaEjc = new ArrayList<>();
    	List<ModeloEjercicio> listaEjsTV = new ArrayList<>();
    	
    	boolean sim = false;
    	boolean med = false;
    	boolean com = false;

		int id;
    	for (ModeloEjercicio ej : modeloExamen.getNoImg()) {
    		id = ej.getId();
			if(repoModeloEjercicio.isSimpleById(id)==1) {
				Simple mAux = new Simple(ej);
				mAux.setA((int) (Math.random()*2));
				mAux.setB((int) (Math.random()*2));
				listaEjs.add(mAux);
				sim=true;
			}
			else if(repoModeloEjercicio.isMedioById(id)==1) {
				Medio mAux = new Medio(ej);
				mAux.setA((int) (Math.random()*2));
				mAux.setB((int) (Math.random()*2));
				mAux.setC((int) (Math.random()*2));
				listaEjm.add(mAux);
				med=true;
			}
			else if(repoModeloEjercicio.isComplejoById(id)==1) { //No deberían existir ejercicios complejos sin imagen
				//lista0.add(ej);
			}
		}
    	m.addAttribute("listaEjs",listaEjs);
    	m.addAttribute("listaEjm",listaEjm);
    	
    	for (ModeloEjercicio ej : modeloExamen.getTablaVerdad()) {
    		id = ej.getId();
			if(repoModeloEjercicio.isSimpleById(id)==1) {
				listaEjsTV.add(ej);
			}
			else if(repoModeloEjercicio.isMedioById(id)==1) {
				listaEjmTV.add(ej);
			}
		}
    	m.addAttribute("listaEjsTV",listaEjsTV);
    	m.addAttribute("listaEjmTV",listaEjmTV);
    	
    	for (ModeloEjercicio ej : modeloExamen.getSiImg()) {
    		id = ej.getId();
			if(repoModeloEjercicio.isSimpleById(id)==1) {
				Simple mAux = new Simple(ej);
				mAux.setA((int) (Math.random()*2));
				mAux.setB((int) (Math.random()*2));
				listaEjsI.add(mAux);
				sim=true;
			}
			else if(repoModeloEjercicio.isMedioById(id)==1) {
				Medio mAux = new Medio(ej);
				mAux.setA((int) (Math.random()*2));
				mAux.setB((int) (Math.random()*2));
				mAux.setC((int) (Math.random()*2));
				listaEjmI.add(mAux);
				med=true;
			}
			else if(repoModeloEjercicio.isComplejoById(id)==1) {
				ej.setA((int) (Math.random()*2));
				ej.setB((int) (Math.random()*2));
				ej.setC((int) (Math.random()*2));
				listaEjc.add(ej);
				com=true;
			}
		}
    	m.addAttribute("listaEjsI",listaEjsI);
    	m.addAttribute("listaEjmI",listaEjmI);
    	m.addAttribute("listaEjc",listaEjc);
    	
    	m.addAttribute("isSim", sim);
    	m.addAttribute("isMed", med);
    	m.addAttribute("isCom", com);
		return "autoevaluacionEspecifica";
	}

    @RequestMapping("/examinarseSubmit")
	public String examinarseSubmit(Model m, 
			@RequestParam String modelo,
			@RequestParam int[] ejs, @RequestParam int[] ejsI, @RequestParam int[] ejsTV,
			@RequestParam int[] ejm, @RequestParam int[] ejmI, @RequestParam int[] ejmTV,
			@RequestParam int[] ejc, 
			@RequestParam char[] A, @RequestParam char[] B, @RequestParam char[] C, @RequestParam char[] S) {
    	
    	ModeloExamen ex = repoModeloExamen.getExamenByNombre(modelo);
    	Alumno al = (Alumno) usuarios.getUsuarioPorNombre((String)m.getAttribute("name"));
    	
    	al.getExamenes().add(ex);
    	
    	ModeloEjercicio ejercicio;
    	Ejercicio ej;
    	int i, offsetS=0, offsetAB=0, offsetC=0;
    	boolean p;
    	//SIMPLES
    	for (i = 0; i < ejs.length; i++) {
    		ej = new Ejercicio("simple");
    		ej.setA(A[i]);
    		ej.setB(B[i]);
    		ej.setS(S[i]);
    		System.out.println(ejs[i]);
    		ejercicio = repoModeloEjercicio.getEjercicioById(ejs[i]);
    		ej.setModeloEjercicio(ejercicio);
    		ej.setAlumno(al);
    		ej.setAuxiliar(ejercicio.getFuncion_logica());
			p = Transformaciones.executeFunction(ejercicio.getFuncion_logica().substring(4),A[i],B[i],'2');
			if((p && S[i]=='1')||(!p && S[i]=='0')) {
				System.out.println("ej"+ejs[i]+" correcto.");
				ej.correcto();
			}else {
				System.out.println("ej"+ejs[i]+" incorrecto.");
				ej.incorrecto();
			}
			ej.setModeloExamen(ex);
			repoEjercicio.save(ej);
		}
    	offsetAB += i;
    	offsetS += i;
    	
    	//SIMPLES CON IMAGEN
    	for (i = 0; i < ejsI.length; i++) {
    		ej = new Ejercicio("simpleImg");
    		ej.setA(A[i+offsetAB]);
    		ej.setB(B[i+offsetAB]);
    		ej.setS(S[i+offsetS]);
    		
    		ejercicio = repoModeloEjercicio.findById(ejsI[i]).get();
    		ej.setModeloEjercicio(ejercicio);
    		ej.setAlumno(al);
    		ej.setAuxiliar(ejercicio.getImagen());
			p = Transformaciones.executeFunction(ejercicio.getFuncion_logica().substring(4),A[i+offsetAB],B[i+offsetAB],'2');
			if((p && S[i+offsetS]=='1')||(!p && S[i+offsetS]=='0')) {
				ej.correcto();
				System.out.println("ej"+ejsI[i]+" correcto.");
			}else {
				ej.incorrecto();
				System.out.println("ej"+ejsI[i]+" incorrecto.");
			}
			ej.setModeloExamen(ex);
			repoEjercicio.save(ej);
		}
    	offsetAB += i;
    	offsetS += i;
    	
    	//TABLA DE VERDAD SIMPLES
    	for (i = 0; i < ejsTV.length; i++) {
    		ej = new Ejercicio("simpleTV");
    		ejercicio = repoModeloEjercicio.findById(ejsTV[i]).get();
    		ej.setModeloEjercicio(ejercicio);
    		ej.setAlumno(al);
    		ej.setAuxiliar(ejercicio.getFuncion_logica());
    		System.out.println("Tabla de verdad del ej"+ejsTV[(i)]+": ");
    		System.out.println("  a\tb");
    		for(int x=0;x<4;x++) {
        		ej.setS(S[(4*i+x)+offsetS],x);
    			int a = x/2;
    			int b = x%2;
    			p = Transformaciones.executeFunction(ejercicio.getFuncion_logica().substring(4),Character.forDigit(a, 10),Character.forDigit(b, 10),'2');
    			System.out.print("  "+a+"\t"+b);
    			if((p && S[(4*i+x)+offsetS]=='1')||(!p && S[(4*i+x)+offsetS]=='0')) {
					System.out.println(": correcto.");
					ej.correcto(x);
				}else {
					System.out.println(": incorrecto.");
					ej.incorrecto(x);
				}
    		}
    		ej.setModeloExamen(ex);
			repoEjercicio.save(ej);
		}
    	offsetS += (i*4);

    	//MEDIOS
    	for (i = 0; i < ejm.length; i++) {
    		ej = new Ejercicio("medio");
    		ej.setA(A[i+offsetAB]);
    		ej.setB(B[i+offsetAB]);
    		ej.setC(C[i]);
    		ej.setS(S[i+offsetS]);
    		
    		ejercicio = repoModeloEjercicio.findById(ejm[i]).get();
    		ej.setModeloEjercicio(ejercicio);
    		ej.setAlumno(al);
    		ej.setAuxiliar(ejercicio.getFuncion_logica());
			p = Transformaciones.executeFunction(ejercicio.getFuncion_logica().substring(4),A[i+offsetAB],B[i+offsetAB],C[i]);
			if((p && S[i+offsetS]=='1')||(!p && S[i+offsetS]=='0')) {
				System.out.println("ej"+ejm[i]+" correcto.");
				ej.correcto();
			}else {
				System.out.println("ej"+ejm[i]+" incorrecto.");
				ej.incorrecto();
			}
			ej.setModeloExamen(ex);
			repoEjercicio.save(ej);
		}
    	offsetAB += i;
    	offsetC += i;
    	offsetS += i;
    	
    	//MEDIOS CON IMAGEN
    	for (i = 0; i < ejmI.length; i++) {
    		ej = new Ejercicio("medioImg");
    		ej.setA(A[i+offsetAB]);
    		ej.setB(B[i+offsetAB]);
    		ej.setC(C[i+offsetC]);
    		ej.setS(S[i+offsetS]);
    		
    		ejercicio = repoModeloEjercicio.findById(ejmI[i]).get();
    		ej.setModeloEjercicio(ejercicio);
    		ej.setAlumno(al);
    		ej.setAuxiliar(ejercicio.getImagen());
			p = Transformaciones.executeFunction(ejercicio.getFuncion_logica().substring(4),A[i+offsetAB],B[i+offsetAB],C[i+offsetC]);
			if((p && S[i+offsetS]=='1')||(!p && S[i+offsetS]=='0')) {
				System.out.println("ej"+ejmI[i]+" correcto.");
				ej.correcto();
			}else {
				System.out.println("ej"+ejmI[i]+" incorrecto.");
				ej.incorrecto();
			}
			ej.setModeloExamen(ex);
			repoEjercicio.save(ej);
		}
    	offsetAB += i;
    	offsetC += i;
    	offsetS += i;
    	
    	//TABLA DE VERDAD MEDIOS
    	for (i = 0; i < ejmTV.length; i++) {
    		ej = new Ejercicio("medioTV");
    		ejercicio = repoModeloEjercicio.findById(ejmTV[i]).get();
    		ej.setModeloEjercicio(ejercicio);
    		ej.setAlumno(al);
    		ej.setAuxiliar(ejercicio.getFuncion_logica());
    		System.out.println("Tabla de verdad del ej"+ejmTV[(i)]+": ");
    		System.out.println("  a\tb\tc");
    		for(int x=0;x<8;x++) {
    			ej.setS(S[(8*i+x)+offsetS],x);
    			int a = x/4;
    			int b = (x/2)%2;
    			int c = x%2;
    			p = Transformaciones.executeFunction(ejercicio.getFuncion_logica().substring(4),Character.forDigit(a, 10),Character.forDigit(b, 10),Character.forDigit(c, 10));
    			System.out.print("  "+a+"\t"+b+"\t"+c);
    			if((p && S[(8*i+x)+offsetS]=='1')||(!p && S[(8*i+x)+offsetS]=='0')) {
					System.out.println(": correcto.");
					ej.correcto(x);
				}else {
					System.out.println(": incorrecto.");
					ej.incorrecto(x);
				}
    		}
    		ej.setModeloExamen(ex);
			repoEjercicio.save(ej);
		}
    	offsetS += (i*8);
    	
    	//COMPLEJOS
    	for (i = 0; i < ejc.length; i++) {
    		ej = new Ejercicio("complejo");
    		ej.setA(A[i+offsetAB]);
    		ej.setB(B[i+offsetAB]);
    		ej.setC(C[i+offsetC]);
    		ej.setS(S[i+offsetS]);
    		
    		ejercicio = repoModeloEjercicio.findById(ejc[i]).get();
    		ej.setModeloEjercicio(ejercicio);
    		ej.setAlumno(al);
    		ej.setAuxiliar(ejercicio.getImagen());
    		System.out.println("Ejercicio"+ejc[i]+":");
    		char letra = 'D';
    		for(int x=0;x<6;x++) {
    			ej.setS(S[((6*i)+x)+offsetS],x);
    			
    			p = Transformaciones.executeFunction(((Complejo) ejercicio).getFuncion_logica(x).substring(4), A[i+offsetAB], B[i+offsetAB], C[i+offsetC]);
    			System.out.print(letra++);
    			if((p && S[((6*i)+x)+offsetS]=='1')||(!p && S[((6*i)+x)+offsetS]=='0')) {
					System.out.println(": correcto.");
					ej.correcto(x);
				}else {
					System.out.println(": incorrecto.");
					ej.incorrecto(x);
				}
    			if(x==4) {
    				letra = 'S';
    			}
    		}
    		ej.setModeloExamen(ex);
			repoEjercicio.save(ej);
		}
    	offsetAB += i;
    	offsetC += i;
    	offsetS += i;
    	
    	
		return "redirect:/";
    }
    
    @RequestMapping("/miPerfil")
	public String miPerfil(Model m) {
    	Alumno alumno = (Alumno) usuarios.getUsuarioPorNombre((String)m.getAttribute("name"));
    	List<Integer> examenes = usuarios.getExamenesByUserId(alumno.getId());
    	m.addAttribute("examenes",examenes);
    	m.addAttribute("alumno",alumno);
		return "perfilAlumno";
	}
    
    @RequestMapping("/cambiarMiContra")
	public String cambiarMiContra(Model m,@RequestParam String newContra, @RequestParam String contra) {
    	int id = usuarios.getUsuarioPorNombre((String)m.getAttribute("name")).getId();
    	Alumno al = (Alumno) usuarios.getUsuarioPorId(id);
    	if(usuarios.cambiarMiContra(al,newContra, contra)) {
        	return "redirect:/";
    	}
    	miPerfil(m);
    	m.addAttribute("Error","la contraseña actual no coincide");
    	return "perfilAlumno";
	}
    
    @RequestMapping("/misEstadisticas")
	public String misEstadisticas(Model m) {
    	int[] id = {usuarios.getUsuarioPorNombre((String)m.getAttribute("name")).getId()};
    	HashMap<Simple, String> simples = usuarios.getSimpleById(id);
    	HashMap<Medio, String> medios = usuarios.getMedioById(id);
    	HashMap<Complejo, String> complejos = usuarios.getComplejoById(id);
    	
    	m.addAttribute("simples",simples.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	m.addAttribute("medios",medios.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	m.addAttribute("complejos",complejos.entrySet()); //NO ESTOY SEGURO DE HASTA QUE PUNTO ESTO ES UNA BUENA IDEA
    	
    	
    	return "estadisticas";
	}
    @RequestMapping("/miAutoevaluacion")
	public String miAutoevaluacion(Model m) {
    	Alumno al = (Alumno) usuarios.getUsuarioPorNombre((String)m.getAttribute("name"));
    	int[] lista = new int[86];
    	int[] aciertos = al.getAciertos();
    	int[] intentos = al.getIntentos();
    	
    	for (int i = 0; i < 43; i++) {
			
			lista[i*2]=aciertos[i];
			lista[i*2+1]=intentos[i];
		}
    	
    	m.addAttribute("lista", lista);
    	return "revisionFormal";
	}
}